package sg.edu.nus.iss.vmcs.store;

public class CashStoreManager implements StoreManager {

	CashStore cashStore;
	PropertyLoader cashLoader;
	
	public CashStoreManager(CashStore cashStore,PropertyLoader cashLoader)
	{
		this.cashStore = cashStore;
		this.cashLoader = cashLoader;
	}
	
	
	@Override
	public void initializeStore() {
		
		// get the cash file from the environment property file;
				int numOfItems = cashLoader.getNumOfItems();
				cashStore.setStoreSize(numOfItems);

				for (int i = 0; i < numOfItems; i++) {
				    CashStoreItem item = (CashStoreItem) cashLoader.getItem(i);
				    cashStore.addItem(i, item);
				}
	}

}
