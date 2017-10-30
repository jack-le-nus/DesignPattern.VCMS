/*
 * Copyright 2003 ISS.
 * The contents contained in this document may not be reproduced in any
 * form or by any means, without the written permission of ISS, other
 * than for the purpose for which it has been supplied.
 *
 */
package sg.edu.nus.iss.vmcs.machinery;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sg.edu.nus.iss.vmcs.maintenance.ButtonItemDisplay;
/**
 * This control object monitors data entered into a StoreViewer, when the Controller uses
 * the MachinerySimulatorPanel&#46; When data is entered, it initiates the process to store the data.
 *
 * @version 3.0 5/07/2003
 * @author Olivo Miotto, Pang Ping Li
 */
import sg.edu.nus.iss.vmcs.store.StoreController;
import sg.edu.nus.iss.vmcs.store.StoreItem;
import sg.edu.nus.iss.vmcs.system.Dispatcher;

public class StoreViewerListener implements ActionListener {
	private int type;
	private int item;
	private StoreController storeCtrl;
	private MachineryController mCtrl;
	
	Dispatcher dispatcher;
	

	
	

	/**
	 * This constructor creates an instance of StoreViewerListener object.
	 * @param type the type of store.
	 * @param item the store item.
	 * @param sctrl the StoreController.
	 */
	public StoreViewerListener(int item, StoreController storeCtrl) {
		//this.type = type;
		this.item = item;
		this.storeCtrl = storeCtrl;
	
	}
	
	
	
	
	/**
	 * This method performs actions in response to the data being entered to the store viewer.
	 */
	public void actionPerformed(ActionEvent e) {
		TextField vf;
		int qty;
		String sqty;

		vf = (TextField) e.getSource();
		sqty = vf.getText();
		qty = Integer.parseInt(sqty);
		//storeCtrl.changeStoreQty(item, qty);
		//UpdateQuantityCommand command;
		StoreItem storeItem = storeCtrl.getStoreItem(item);
		
		dispatcher = storeCtrl.getDispatcher();
		dispatcher.dispatchCommand(storeItem.getContent().getName(),item, qty);
		storeCtrl.getDispatcher().dispatchCommand(ButtonItemDisplay.class.getName()+storeItem.getContent().getName(), item);
	
		
		/*if(storeCtrl instanceof CashStoreController)
		{
		command = new UpdateQuantityCommand(mCtrl.getMainController().getMaintenanceController().getMpanel().getCoinDisplay());
		}
		else
		{
		command = new UpdateQuantityCommand(mCtrl.getMainController().getMaintenanceController().getMpanel().getDrinksDisplay());
		}
		command.execute();*/
	}
}//End of class StoreViewerListener