package sg.edu.nus.iss.vmcs.store;

public class DrinktStoreManager implements StoreManager {

	
	DrinksStore drinkStore;
	PropertyLoader drinksLoader;
	
	public DrinktStoreManager(DrinksStore drinkStore,PropertyLoader drinksLoader)
	{
		this.drinkStore = drinkStore;
		this.drinksLoader = drinksLoader;
	}
	@Override
	public void initializeStore() {
		// get the drink file from the environment property file;
				int numOfItems = drinksLoader.getNumOfItems();
				drinkStore.setStoreSize(numOfItems);

				for (int i = 0; i < numOfItems; i++) {
		            DrinksStoreItem item = (DrinksStoreItem) drinksLoader.getItem(i);
					StoreObject brand = item.getContent();
					StoreObject existingBrand = drinkStore.findObject(brand.getName());
					if (existingBrand != null) {
					    item.setContent(existingBrand);
					}
					drinkStore.addItem(i, item);
				}
		
	}

}
