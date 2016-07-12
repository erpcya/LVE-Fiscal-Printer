/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * Copyright (C) 2003-2016 E.R.P. Consultores y Asociados.                    *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpcya.com                                 *
 *****************************************************************************/
package org.spin.util;

import org.spin.model.MADDeviceConfig;

/**
 * @author <UserName>, <MailName>@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [  ] 
 *		@see 
 */
public class FiscalPrinterBixolon extends DeviceTypeManagement {

	/**
	 * *** Constructor ***
	 * @author <UserName>, <MailName>@erpcya.com, ERPCyA http://www.erpcya.com
	 *		<li> FR [  ] 
	 *		@see 
	 */
	public FiscalPrinterBixolon() {
	}

	/* (non-Javadoc)
	 * @see org.spin.util.DeviceTypeManagement#connect(org.spin.model.MADDeviceConfig)
	 */
	@Override
	public void connect(MADDeviceConfig config) {
	}

	/* (non-Javadoc)
	 * @see org.spin.util.DeviceTypeManagement#close()
	 */
	@Override
	public void close() {
	}

	/* (non-Javadoc)
	 * @see org.spin.util.DeviceTypeManagement#read(org.spin.model.MADDeviceConfig)
	 */
	@Override
	public Object read(MADDeviceConfig config) {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.spin.util.DeviceTypeManagement#write(org.spin.model.MADDeviceConfig)
	 */
	@Override
	public Object write(MADDeviceConfig config) {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.spin.util.DeviceTypeManagement#isCheckOk()
	 */
	@Override
	public boolean isCheckOk() {
		return false;
	}

}
