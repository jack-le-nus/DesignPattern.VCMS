package sg.edu.nus.iss.vmcs.system;

import sg.edu.nus.iss.vmcs.util.VMCSException;

public class UpdateQuantityCommand implements Command {
	
	VMCSPanel panel;
	
	
	
	
	
	public UpdateQuantityCommand(VMCSPanel panel)
	{
		this.panel = panel;
	}
	
	

	@Override
	public void execute() {
		try
		{	
		panel.update();	
		}
		catch(Exception e)
		{
			System.out.println("Not able to update Quantity");
		}
	
	}

	

}
