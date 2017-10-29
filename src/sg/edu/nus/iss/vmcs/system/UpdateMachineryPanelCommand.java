package sg.edu.nus.iss.vmcs.system;

import java.awt.Color;

import sg.edu.nus.iss.vmcs.maintenance.ButtonItem;
import sg.edu.nus.iss.vmcs.util.VMCSException;

public class UpdateMachineryPanelCommand implements Command {
	
	ButtonItem item;

	public UpdateMachineryPanelCommand(ButtonItem item)
	{
		this.item = item;
	}
	

	@Override
	public void execute(Object quantity) {
		try
		{	
		 if(quantity instanceof Integer)
		 {
			 item.setValue((Integer)quantity);
			 item.tempBackground(Color.green);
			 Thread.sleep(500);
			 item.tempBackground(Color.white);
		 }
		}
		catch(Exception e)
		{
			System.out.println("Not able to update Quantity");
		}
	
	}

	

}
