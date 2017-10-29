/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */
package sg.edu.nus.iss.vmcs.store;

import java.io.IOException;

/**
 * This object is storage, in the vending machine's memory, for the total number of
 * cans of each DrinksBrand held by the vending machine&#46;
 * 
 * @see CashStore
 * @see CashStoreItem
 * @see Coin
 * @see DrinksBrand
 * @see DrinksStoreItem
 * @see Store
 * @see StoreController
 * @see StoreItem
 * @see StoreObject
 * 
 * @version 3.0 5/07/2003
 * @author Olivo Miotto, Pang Ping Li
 */
public class DrinksStore implements Store {
	
	protected int size;
    /**This attribute hold the items of the store*/
	protected StoreItem items[];


	/**
	 * This constructor creates an instance of DrinksStore object.
	 */
	public DrinksStore() {
	}
	
	
	/**
	 * This constructor creates an instance of the CashStore object.
	 */
	public DrinksStore(int itemn) {
		size = itemn;
		items = new StoreItem[size];
	}
	
	
	/**
	 * This method sets the size of the items array in the Store.
	 * @param sz the store size.
	 */
	public void setStoreSize(int sz) {
		size = sz;
		items = new StoreItem[size];
	}

	/**
	 * This method returns the {@link StoreItem} corresponding to the index entered.
	 * @return the array of {@link StoreItem}.
	 */
	public StoreItem[] getItems() {
		return items;
	}

	/**
	 * This method adds {@link StoreItem} into the store.
	 * @param idx the index of the item.
	 * @param object the store item to be added.
	 */
	public void addItem(int idx, StoreItem object) {
		if ((idx >= size) || (idx < 0))
			return;
		items[idx] = object;
	}

	/**
	 * This method returns the {@link StoreItem} with the given index.
	 * @return the StoreItem.
	 */
	public StoreItem getStoreItem(int idx) {
		if ((idx >= size) || (idx < 0))
            return null;
		return items[idx];
	}

	/**
	 * This method finds a {@link StoreObject} in the store with a specified name&#46;
	 * @param name the name of the Store Object&#46;
	 * @return the Store Object&#46; Return null if no matching Store Object found&#46;
	 */
	public StoreObject findObject(String name) {
		String en;
		StoreObject so;
		int i;

		for (i = 0; i < size; i++) {
			if (items[i] == null)
				return null;
			so = items[i].getContent();
			if (so == null)
				return null;
			en = so.getName();
			if (en != null) {
				if (en.compareTo(name) == 0)
					return so;
			}
		}
		return null;
	}

	/**
	 * This method sets the total number of a store item held.
	 * @param idx the index of the store item.
	 * @param qty the quantity of the store item.
	 */
	public void setQuantity(int idx, int qty) {
		System.out.println("Store: setQauntity - qty=" + qty);
		if ((idx >= size) || (idx < 0))
			return;
		items[idx].setQuantity(qty);
	}

	/**
	 * This method return the store size; the total number of store item held.
	 * @return the store size.
	 */
	public int getStoreSize() {
		return size;
	}


	@Override
	public void initialize(PropertyLoader drinksLoader) {
		// get the drink file from the environment property file;
		int numOfItems = drinksLoader.getNumOfItems();
		this.setStoreSize(numOfItems);

		for (int i = 0; i < numOfItems; i++) {
            DrinksStoreItem item = (DrinksStoreItem) drinksLoader.getItem(i);
			StoreObject brand = item.getContent();
			StoreObject existingBrand = this.findObject(brand.getName());
			if (existingBrand != null) {
			    item.setContent(existingBrand);
			}
			this.addItem(i, item);
		}
		
	}


	@Override
	public void saveProperties(PropertyLoader drinksLoader) throws IOException {
		
		int size = this.getStoreSize();
		drinksLoader.setNumOfItems(size);
		for (int i = 0; i < size; i++) {
			drinksLoader.setItem(i, this.getStoreItem(i));
		}
		drinksLoader.saveProperty();
		
	}
	
	
	
}//End of class DrinksStore
