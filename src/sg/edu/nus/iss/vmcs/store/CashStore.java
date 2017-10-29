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
 * This object represents the store of cash in the vending machine.
 * 
 * @see CashStoreItem
 * @see Coin
 * @see DrinksBrand
 * @see DrinksStore
 * @see DrinksStoreItem
 * @see Store
 * @see StoreController
 * @see StoreItem
 * @see StoreObject
 * 
 * @version 3.0 5/07/2003
 * @author Olivo Miotto, Pang Ping Li
 */
public class CashStore implements Store {
	/**This is the constant for coin invalid weight.*/
	public final static int INVALID_COIN_WEIGHT = 9999;
	
	
	/**This attribute hold the size of the store*/
	protected int size;
    /**This attribute hold the items of the store*/
	protected StoreItem items[];

	/**
	 * This constructor creates an instance of the CashStore object.
	 */
	public CashStore() {
	}
	
	/**
	 * This constructor creates an instance of the CashStore object.
	 */
	public CashStore(int itemn) {
		size = itemn;
		items = new StoreItem[size];
	}
	
	/**
	 * This method find and returns the index of the coin in the CashStore of the given Coin&#46;
	 * @param c the Coin of interest&#46;
	 * @return the index of the given Coin&#46; Return -1 if unknown Coin is detected.
	 */
	public int findCashStoreIndex (Coin c) {
		int size = getStoreSize();
		for (int i = 0; i < size; i++) {
			StoreItem item = (CashStoreItem) getStoreItem(i);
			Coin current = (Coin) item.getContent();
			if (current.getWeight() == c.getWeight())
				return i;
		}
		return -1;
	}

	/**
	 * This method determine whether the given weight of the {@link Coin} is valid.
	 * @param weight the weight of the Coin to be tested.
	 * @return TRUE if the weight is valid, otherwise, return FALSE.
	 */
	public boolean isValidWeight(double weight){
		int size = getStoreSize();
		for (int i = 0; i < size; i++) {
			StoreItem item = (CashStoreItem) getStoreItem(i);
			Coin current = (Coin) item.getContent();
			if (current.getWeight() == weight)
				return true;
		}
		return false;
	}
	
	/**
	 * This method will locate a {@link Coin} denomination held, with the input data 
	 * (coin weight)&#46; If found, it returns an existence identifier (reference)&#46;
	 * Otherwise, it informs the requestor that the coin is invalid.
	 * @param weight the weight of the coin to be found.
	 * @return Coin the coin which has the input weight.
	 */
	public Coin findCoin(double weight){
		int size = getStoreSize();
		for (int i = 0; i < size; i++) {
			StoreItem item = (CashStoreItem) getStoreItem(i);
			Coin current = (Coin) item.getContent();
			if (current.getWeight() == weight)
				return current;
		}
		return null;
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
	public void initialize(PropertyLoader cashLoader) {
		// get the cash file from the environment property file;
		int numOfItems = cashLoader.getNumOfItems();
		this.setStoreSize(numOfItems);

		for (int i = 0; i < numOfItems; i++) {
		    CashStoreItem item = (CashStoreItem) cashLoader.getItem(i);
		    this.addItem(i, item);
		}
		
	}

	@Override
	public void saveProperties(PropertyLoader cashLoader) throws IOException {
		int size = this.getStoreSize();
		cashLoader.setNumOfItems(size);
		for (int i = 0; i < size; i++) {
			cashLoader.setItem(i, this.getStoreItem(i));
		}
		cashLoader.saveProperty();
		
	}
	
	
	

}//End of class CashStore