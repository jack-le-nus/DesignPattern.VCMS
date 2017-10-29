package sg.edu.nus.iss.vmcs.system;

import java.awt.Panel;

import sg.edu.nus.iss.vmcs.maintenance.ButtonItemDisplay;
import sg.edu.nus.iss.vmcs.util.VMCSException;

public abstract class DisplayPanel extends Panel {
	
	public abstract void update() throws VMCSException;
	public abstract void displayQty(int idx, int qty) throws VMCSException;
}
