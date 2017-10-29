/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */
package sg.edu.nus.iss.vmcs.store;

import java.io.IOException;

import sg.edu.nus.iss.vmcs.system.Dispatcher;

/**
 * This control object manages changes in CashStore attributes and 
 * the DrinksStore attributes.
 *
 * @see CashStore
 * @see CashStoreItem
 * @see Coin
 * @see DrinksBrand
 * @see DrinksStore
 * @see DrinksStoreItem
 * @see Store
 * @see StoreItem
 * @see StoreObject
 * 
 * @version 3.0 5/07/2003
 * @author Olivo Miotto, Pang Ping Li
 */
public class StoreController {
	
	private Store store;
	private PropertyLoader propertyLoader;
	
	private Dispatcher dispatcher;
	
	
	/*private CashStore cStore;
	private DrinksStore dStore;

	private PropertyLoader cashLoader;
	private PropertyLoader drinksLoader;
*/
	
	
	
	/**
	 * This constructor creates an instance of StoreController object.
	 * @param loads the data from the loader
	 */
	public StoreController(PropertyLoader propertyLoader,Store store) {
		this.propertyLoader = propertyLoader;
		this.store = store;
	}


	/**
	 * This method instantiate the {@link CashStore}, {@link DrinksStore} and initialize it.
	 * @throws IOException if fail to initialize stores; reading properties.
	 */
	public void initialize() throws IOException {
		store.initialize(propertyLoader);
	}

	/**
	 * This method return the total size of the {@link Store} of the given type of {@link Store}.
	 * @param type the type of the Store (either CASH or DRINK).
	 * @return the size of the store of the given type of Store.
	 */
	public int getStoreSize() {
			return store.getStoreSize();
	}

	/**
	 * This method returns an array of {@link StoreItem} of the given type of {@link Store}.
	 * @param type the type of Store.
	 * @return an array of StoreItem.
	 */
	public StoreItem[] getStoreItems() {
		return store.getItems();
	}

	/**
	 * This method will either:
	 * <br>
	 * - instruct the {@link CashStore} to update the quantity of a {@link Coin} denomination to new
	 * value supplied and update the total cash held in the {@link CashStore}; or
	 * <br>
	 * - instruct the {@link DrinksStore} to update the drinks stock for a {@link DrinksBrand} required
	 * to a new value supplied.
	 * @param type the type of Store.
	 * @param idx the index of the StoreItem.
	 * @param qty the quantity of the StoreItem.
	 */
	public void changeStoreQty(int idx, int qty) {
			System.out.println("StoreController.changeStoreQty: type:"+ " qty:"+ qty);
			store.setQuantity(idx, qty);
	}

	/**
	 * This method returns the {@link StoreItem} with the given {@link Store} type and index of {@link StoreItem}.
	 * @param type the type of Store.
	 * @param idx the index of the StoreItem.
	 * @return the StoreItem.
	 */
	public StoreItem getStoreItem(int idx) {
		return store.getStoreItem(idx);
	}

	


	/**
	 * This method will close down the store management function of the vending machine.
	 * This involves saving the attributes of the stores to the property file.
	 * @throws IOException if fail to save cash properties and drinks properties.
	 */
	public void closeDown() throws IOException {
		// save back cash property;
		store.saveProperties(propertyLoader);
	}
	
	
	/**
	 * This method returns a {@link Store} of a specified type (i&#46;e&#46; Cash or Drink)&#46;
	 * @param type the type of Store&#46;
	 * @return the Store of the specified type&#46;
	 */
	public Store getStore() {
		return store;
	}


	public Dispatcher getDispatcher() {
		return dispatcher;
	}


	public void setDispatcher(Dispatcher dispatcher) {
		this.dispatcher = dispatcher;
	}
	
	

}//End of class StoreController