package sg.edu.nus.iss.vmcs.system;

import java.awt.Color;

import sg.edu.nus.iss.vmcs.maintenance.ButtonItem;
import sg.edu.nus.iss.vmcs.maintenance.ButtonItemDisplay;
import sg.edu.nus.iss.vmcs.maintenance.MaintenanceController;
import sg.edu.nus.iss.vmcs.util.VMCSException;

public class UpdateCoinDisplayCommand implements Command {
	
	MaintenanceController maintainanceController;

	public UpdateCoinDisplayCommand(MaintenanceController maintainanceController)
	{
		this.maintainanceController = maintainanceController;
	}



	@Override
	public void execute(Object... objects) {
		// TODO Auto-generated method stub
		try
		{	
		 if(objects[0] instanceof Integer)
		 {
			 maintainanceController.displayCoin((Integer)objects[0]);
			 ButtonItemDisplay displayItem = maintainanceController.getMpanel().getCoinDisplay().getBi();
			 ButtonItem item = displayItem.getButtonItem((Integer)objects[0]);
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
