package sg.edu.nus.iss.vmcs.system;

import java.awt.Panel;

import sg.edu.nus.iss.vmcs.util.VMCSException;

public abstract class VMCSPanel extends Panel {
	
	public abstract void update() throws VMCSException;

}
