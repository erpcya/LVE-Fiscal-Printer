/**
 * 
 */
package test;

import org.adempiere.exceptions.AdempiereException;

import tfhka.PrinterException;

/**
 * @author erpcya_tt
 *
 */
public class FiscalPrinterBixolon extends FiscalPrinterAbstract2 {

	/**
	 * 
	 */
	public FiscalPrinterBixolon() {
		super();
	}
	
	protected boolean isConnected() {
		if(tf != null
				&& tf.IndPuerto)
			return true;
		else
			return false;
	}

	public void openPort() {
		if(isConnected()) {
			tf.CloseFpctrl();
		} else {
			msg = "Fiscal printer not conected";
		}
		if(portName != null
				&& portName.length() > 0) {
			tf.OpenFpctrl(portName);
			if(isConnected()) {
				msg = "Port Initialized";
			} else {
				msg = "Port Not Initialized";
			}
		} else {
			msg = "Select port";
		}
	}
	
	public void closePort() {
		if(isConnected()) {
			tf.CloseFpctrl();
			if(tf.IndPuerto) {
				msg = "Port not closed";
			} else {
				msg = "Port closed";
			}
		} else {
			msg = "Fiscal printer not conected";
		}
	}
	
	public void checkFiscalPrinter () {
		if(isConnected()) {
			if(tf.CheckFprinter())	{
				msg = "Fiscal printer ready to print";
			} else {
				msg ="Fiscal printer not ready to print";
			}
		} else {
			msg = "Fiscal printer not conected";
		}
	}
	
	public void checkDrawer () {
		if(isConnected()) {
			if(tf.CheckDrawer())	{
				msg = "Connected drawer";
			} else {
				msg = "Not connected drawer";
			}
		} else {
			msg = "Fiscal printer not conected";
		}
	}
	
	public void readFPStatus () {
		if(isConnected()) {
			tf.ReadFpStatus();
			m_PrinterStatus = tf.getPrinterStatus();
			msg = m_PrinterStatus.getPrinterStatusCode() + " : " + m_PrinterStatus.getPrinterStatusDescription() + " - " + m_PrinterStatus.getPrinterErrorCode() + " : " + m_PrinterStatus.getPrinterErrorDescription();
		} else {
			msg = "Fiscal printer not conected";
		}
	}
	
	public void sendCmd (String cmd) throws AdempiereException, PrinterException {
		if(isConnected()) {
			tf.SendCmd(cmd);
		} else {
			msg = "Fiscal printer not conected";
		}
	}
	
	public int sendFileCmd (String route) {
		if(isConnected()){
			return tf.SendFileCmd(route);
		} else {
			msg = "Fiscal printer not conected";
			return 0;
		} 
	}

}