package sg.edu.nus.iss.vmcs.store;

public class CashStoreController extends StoreController {
	
	
	CashStore cashStore;
	PropertyLoader cashLoader;
	
	public CashStoreController(PropertyLoader cashLoader , CashStore cashStore)
	{
		super(cashLoader,cashStore);
		this.cashLoader = cashLoader;
		this.cashStore = cashStore;
	}
	
	
	public int getTotalCash(){
		int i;
		int size;

		size = this.getStoreSize();
		CashStoreItem item;
		int qty;
		int val;
		int tc = 0;
		Coin c;

		for (i = 0; i < size; i++) {
			item = (CashStoreItem) cashStore.getStoreItem(i);
			qty = item.getQuantity();
			c = (Coin) item.getContent();
			val = c.getValue();
			tc = tc + qty * val;
		}
		return tc;
	}
	
	
	/**
	 * This method will instruct the {@link CashStore} to store the {@link Coin} sent as input, and then
	 * update the display on the {@link sg.edu.nus.iss.vmcs.machinery.MachinerySimulatorPanel}.
	 * @return the number of cash transfered.
	 */
	public int transferAll()  {
		int i;
		int cc = 0; // coin quauntity;
		int size = this.getStoreSize();

		CashStoreItem item;
		for (i = 0; i < size; i++) {
			item = (CashStoreItem) cashStore.getStoreItem(i);
			cc = cc + item.getQuantity();
			item.setQuantity(0);
		}

		return cc;
	}
	
	
	/**
	 * This method instructs the {@link CashStore} to issue a number of {@link Coin} of a specific
	 * denomination, and then updates the {@link sg.edu.nus.iss.vmcs.machinery.MachinerySimulatorPanel}&#46; It return TRUE
	 * or FALSE to indicate whether the change issue was successful&#46;
	 * @param idx the index of the Coin&#46;
	 * @param numOfCoins the number of Coin to deduct&#46; 
	 */
	public void giveChange(int idx, int numOfCoins)  {
		CashStoreItem item;
		item = (CashStoreItem) cashStore.getStoreItem(idx);
		for (int i = 0; i < numOfCoins; i++)
			item.decrement();
	}
	
	
	/**
	 * This method will instruct the {@link CashStore} to store the {@link Coin} sent as input, and
	 * update the display on the Machinery Simulator Panel.
	 * @param c the Coin to be stored.
	 */
	public void storeCoin(Coin c) {
		int idx = cashStore.findCashStoreIndex(c);
		CashStoreItem item;
		item = (CashStoreItem) this.getStoreItem(idx);
		item.increment();
	}
	
	

}
