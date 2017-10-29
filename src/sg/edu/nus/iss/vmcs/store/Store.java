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
 * This entity object implements a generic Store&#46; It has methods to load (add) {@link StoreItem}
 * into the Store and release {@link StoreItem} from the Store.
 * 
 * @see CashStore
 * @see CashStoreItem
 * @see Coin
 * @see DrinksBrand
 * @see DrinksStore
 * @see DrinksStoreItem
 * @see StoreController
 * @see StoreItem
 * @see StoreObject
 * 
 * @version 3.0 5/07/2003
 * @author Olivo Miotto, Pang Ping Li
 */
public interface Store {
	/**This constant attribute represent Cash*/
	public final static int CASH  = 1;
	/**This constant attribute represnet Drink*/
	public final static int DRINK = 2;
	/**This attribute hold the size of the store*/

	

	/**
	 * This method sets the size of the items array in the Store.
	 * @param sz the store size.
	 */
	public abstract void setStoreSize(int sz);

	/**
	 * This method returns the {@link StoreItem} corresponding to the index entered.
	 * @return the array of {@link StoreItem}.
	 */
	public abstract StoreItem[] getItems();

	/**
	 * This method adds {@link StoreItem} into the store.
	 * @param idx the index of the item.
	 * @param object the store item to be added.
	 */
	public abstract void addItem(int idx, StoreItem object);

	/**
	 * This method returns the {@link StoreItem} with the given index.
	 * @return the StoreItem.
	 */
	public abstract StoreItem getStoreItem(int idx);

	/**
	 * This method finds a {@link StoreObject} in the store with a specified name&#46;
	 * @param name the name of the Store Object&#46;
	 * @return the Store Object&#46; Return null if no matching Store Object found&#46;
	 */
	public abstract StoreObject findObject(String name);

	/**
	 * This method sets the total number of a store item held.
	 * @param idx the index of the store item.
	 * @param qty the quantity of the store item.
	 */
	public abstract void setQuantity(int idx, int qty);

	/**
	 * This method return the store size; the total number of store item held.
	 * @return the store size.
	 */
	public abstract int getStoreSize();
	
	
	public abstract void initialize(PropertyLoader propertyLoader);
	
	public abstract void saveProperties(PropertyLoader propertyLoader) throws IOException;
}//End of class Store