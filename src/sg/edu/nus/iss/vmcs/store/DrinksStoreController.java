package sg.edu.nus.iss.vmcs.store;

public class DrinksStoreController extends StoreController {
	
	DrinksStore drinkStore;
	PropertyLoader drinksLoader;
	
	public DrinksStoreController(PropertyLoader drinksLoader , DrinksStore drinkStore)
	{
		super(drinksLoader,drinkStore);
		this.drinksLoader = drinksLoader;
		this.drinkStore = drinkStore;
	}
	
	
	/**
	 * This method instructs the {@link DrinksStore} to dispense one drink, and then updates the 
	 * {@link sg.edu.nus.iss.vmcs.machinery.MachinerySimulatorPanel}&#46; It returns TRUE or FALSE to indicate whether dispensing
	 * was successful&#46;
	 * @param idx the index of the drinks to be dispensed&#46;
	 */
	public void dispenseDrink(int idx)  {
		DrinksStoreItem item;
		item = (DrinksStoreItem) drinkStore.getStoreItem(idx);
		item.decrement();
	}
	
	/**
	 * This method will sets the price for the {@link StoreItem} with the given index and price.
	 * @param idx the index of the StoreItem.
	 * @param pr the price of the StoreItem.
	 */
	public void setPrice(int idx, int pr)  {
		StoreItem item;

		item = (DrinksStoreItem) drinkStore.getStoreItem(idx);
		DrinksBrand bd;

		bd = (DrinksBrand) item.getContent();

		bd.setPrice(pr);
	}
	

}