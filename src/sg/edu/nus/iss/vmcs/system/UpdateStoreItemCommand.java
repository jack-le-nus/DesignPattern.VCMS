package sg.edu.nus.iss.vmcs.system;

import sg.edu.nus.iss.vmcs.store.StoreItem;

public class UpdateStoreItemCommand implements Command{
	
	StoreItem item;
	
	
	public UpdateStoreItemCommand(StoreItem item)
	{
		this.item = item;
	}

	@Override
	public void execute(Object object) {
		// TODO Auto-generated method stub
		
		if(object instanceof Integer)
		{
			item.setQuantity((Integer)object);
			System.out.println("command executedinside");
		}
		System.out.println("command executed");
	}

}
