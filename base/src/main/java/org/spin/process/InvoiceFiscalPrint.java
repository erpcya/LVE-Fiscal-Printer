/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 or later of the                                  *
 * GNU General Public License as published                                    *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * Copyright (C) 2003-2016 E.R.P. Consultores y Asociados, C.A                *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpya.com                                  *
 *****************************************************************************/
package org.spin.process;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MDocType;
import org.compiere.model.MInvoice;
import org.compiere.model.X_C_DocType;
import org.spin.model.I_AD_FP_Document;
import org.spin.model.MADDevice;
import org.spin.util.FiscalDocumentHandler;
import org.spin.util.FiscalPrinterHandler;

/** Generated Process for (Print Invoices to Fiscal Printer)
 *  @author ADempiere (generated) 
 *  @version Release 3.8.0
 */
public class InvoiceFiscalPrint extends InvoiceFiscalPrintAbstract {
	@Override
	protected void prepare() {
		super.prepare();
	}

	@Override
	protected String doIt() throws Exception {
		if(getRecord_ID() == 0)
			throw new AdempiereException("@C_Invoice_ID@ @NotFound@");
		//	
		MInvoice invoice = new MInvoice(getCtx(), getRecord_ID(), get_TrxName());
		//	Validate Printing
		if(invoice.get_ValueAsInt("AD_Device_ID") != 0
				&& invoice.get_ValueAsString("FiscalDocumentNo") != null) {
			return "@C_Invoice_ID@ " + invoice.getDocumentNo() + " @Printed@";
		}
		//	Validate onlyu completed
		if(invoice.getDocStatus().equals(MInvoice.STATUS_Reversed)
				|| invoice.getDocStatus().equals(MInvoice.STATUS_Voided)) {
			return "@C_Invoice_ID@ " + invoice.getDocumentNo() + " @Voided@";
		}
		//	Validate is Paid
//		if(!invoice.isPaid())
//			throw new AdempiereException("@C_Invoice_ID@ @No@ @IsPaid@");
		//	Get Device
		if(getFiscalPrinterId() == 0)
			throw new AdempiereException("@AD_Device_ID@ @NotFound@");
		//	
		MADDevice device = new MADDevice(getCtx(), getFiscalPrinterId(), get_TrxName());
		FiscalDocumentHandler documentHandler = new FiscalDocumentHandler(device);
		//	Get Document Type
		MDocType docType = MDocType.get(getCtx(), invoice.getC_DocType_ID());
		//	Set Transaction Name
		documentHandler.set_TrxName(get_TrxName());
		//	Establish connection
		documentHandler.connectPrinter();
		try {
			//	Print
			documentHandler.printDocument(getRecord_ID(), docType.get_ValueAsInt(I_AD_FP_Document.COLUMNNAME_AD_FP_DocumentType_ID), getProcessInfo());
			//	Set Document Values
			String fiscalDocumentNo = null;
			if(docType.getDocBaseType().equals(X_C_DocType.DOCBASETYPE_ARInvoice)) {
				if(invoice.get_ValueAsInt("InvoiceToAllocate_ID") != 0) {
					fiscalDocumentNo = documentHandler.getLastDocumentNo(FiscalPrinterHandler.DOCUMENT_TYPE_DEBIT_MEMO);
				} else {
					fiscalDocumentNo = documentHandler.getLastDocumentNo(FiscalPrinterHandler.DOCUMENT_TYPE_INVOICE);
				}
			} else if(docType.getDocBaseType().equals(X_C_DocType.DOCBASETYPE_ARCreditMemo)) {
				fiscalDocumentNo = documentHandler.getLastDocumentNo(FiscalPrinterHandler.DOCUMENT_TYPE_CREDIT_MEMO);
			}
			//	Set Device
			invoice.set_ValueOfColumn("AD_Device_ID", getFiscalPrinterId());
			//	Set
			if(fiscalDocumentNo != null
					&& fiscalDocumentNo.length() > 0) {
				invoice.set_ValueOfColumn("FiscalDocumentNo", fiscalDocumentNo);
//				invoice.setDocumentNo(fiscalDocumentNo);
			}
			//	Save
			invoice.saveEx();
		} catch(Exception e) {
			throw e;
		} finally {
			//	Close Connection
			documentHandler.closePrinter();
		}
		//	Ok
		return "Ok";
	}
}