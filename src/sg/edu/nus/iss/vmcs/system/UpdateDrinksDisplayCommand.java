package sg.edu.nus.iss.vmcs.system;

import java.awt.Color;

import sg.edu.nus.iss.vmcs.maintenance.ButtonItem;
import sg.edu.nus.iss.vmcs.maintenance.ButtonItemDisplay;
import sg.edu.nus.iss.vmcs.maintenance.MaintenanceController;

public class UpdateDrinksDisplayCommand implements Command{
	
	MaintenanceController maintainanceController;
	
	
	public UpdateDrinksDisplayCommand(MaintenanceController maintainanceController)
	{
		this.maintainanceController = maintainanceController;
	}

	@Override
	public void execute(Object... objects) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				try
				{	
				 if(objects[0] instanceof Integer)
				 {
					 maintainanceController.displayDrinks((Integer)objects[0]);
					 ButtonItemDisplay displayItem = maintainanceController.getMpanel().getDrinksDisplay().getBi();
					 ButtonItem item = displayItem.getButtonItem((Integer)objects[0]);
					 item.tempBackground(Color.green);
					 Thread.sleep(500);
					 item.tempBackground(Color.white);
				 }
				}
				catch(Exception e)
				{
					e.printStackTrace();
					System.out.println("Not able to update Quantity-ButtonItem");
				}
	}

}
