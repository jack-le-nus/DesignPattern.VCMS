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

import sg.edu.nus.iss.vmcs.store.Store;
/**
 * This control object monitors data entered into a StoreViewer, when the Controller uses
 * the MachinerySimulatorPanel&#46; When data is entered, it initiates the process to store the data.
 *
 * @version 3.0 5/07/2003
 * @author Olivo Miotto, Pang Ping Li
 */
import sg.edu.nus.iss.vmcs.store.StoreController;
import sg.edu.nus.iss.vmcs.system.UpdateQuantityCommand;
import sg.edu.nus.iss.vmcs.system.VMCSPanel;

public class StoreViewerListener implements ActionListener {
	private int type;
	private int item;
	private StoreController storeCtrl;
	private MachineryController mCtrl;
	
	

	/**
	 * This constructor creates an instance of StoreViewerListener object.
	 * @param type the type of store.
	 * @param item the store item.
	 * @param sctrl the StoreController.
	 */
	public StoreViewerListener(int type, int item, MachineryController mctrl) {
		this.type = type;
		this.item = item;
		storeCtrl = mctrl.getMainController().getStoreController();
		this.mCtrl = mctrl;
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
		storeCtrl.changeStoreQty(type, item, qty);
		UpdateQuantityCommand command;
		
		if(type == Store.CASH)
		{
		command = new UpdateQuantityCommand(mCtrl.getMainController().getMaintenanceController().getMpanel().getCoinDisplay());
		}
		else
		{
		command = new UpdateQuantityCommand(mCtrl.getMainController().getMaintenanceController().getMpanel().getDrinksDisplay());
		}
		command.execute();
	}
}//End of class StoreViewerListener