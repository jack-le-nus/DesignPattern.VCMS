package sg.edu.nus.iss.vmcs.system;

import sg.edu.nus.iss.vmcs.store.StoreController;
import sg.edu.nus.iss.vmcs.store.StoreItem;

public class UpdateStoreItemCommand implements Command{
	
	StoreItem item;
	
	StoreController storecontroller;
	
	
	public UpdateStoreItemCommand(StoreController storecontroller)
	{
		this.storecontroller = storecontroller;
	}

	/*public void execute(Object object) {
		// TODO Auto-generated method stub
		
		if(object[0] instanceof Integer)
		{
			item.setQuantity((Integer)object);
			System.out.println("command executedinside");
		}
		System.out.println("command executed");
	}*/

	@Override
	public void execute(Object... objects) {
		// TODO Auto-generated method stub
		
		if(objects[0] instanceof Integer)
		{
			StoreItem item = storecontroller.getStoreItem((Integer)objects[0]);
			item.setQuantity((Integer)objects[1]);
		}
		
	}

}
