package sg.edu.nus.iss.vmcs.machinery;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;

import sg.edu.nus.iss.vmcs.store.StoreController;
import sg.edu.nus.iss.vmcs.store.StoreItem;
import sg.edu.nus.iss.vmcs.system.Command;
import sg.edu.nus.iss.vmcs.system.Dispatcher;
import sg.edu.nus.iss.vmcs.system.UpdateStoreItemCommand;
import sg.edu.nus.iss.vmcs.util.LabelledDisplay;

public class CashStoreViewer extends StoreViewer {
	
	private LabelledDisplay viewItems[];
	private StoreController storeCtrl;
	private Dispatcher dispatcher;
	
	public static final String TITLE = "Quantity of Coins Available";
	
	public CashStoreViewer(int item, MachineryController mctrl)
	{

		super(mctrl.getMainController().getCashStoreController());
		storeCtrl = mctrl.getMainController().getCashStoreController();
		dispatcher = mctrl.getMainController().getDispatcher();
		
		String title = null;
	
	    title = CASH_VIEW_TITLE;
			
		Panel pl = new Panel(new FlowLayout(FlowLayout.LEFT));
		pl.add(new Label(title));

		int sSize = storeCtrl.getStoreSize();
		viewItems = new LabelledDisplay[sSize];

		StoreItem[] storeItem = storeCtrl.getStoreItems();
		this.setLayout(new GridLayout(0, 1));
		this.add(pl);

		for (int i = 0; i < sSize; i++) {
			String name = storeItem[i].getContent().getName();
			viewItems[i] = new LabelledDisplay(name,
						LabelledDisplay.DEFAULT,
						LabelledDisplay.GRID);
			viewItems[i].addListener(
                        new StoreViewerListener(i, mctrl.getMainController().getCashStoreController()));
			this.add(viewItems[i]);
		
		}
		
		storeCtrl.configureCommands(sSize, dispatcher);
	}
	
	
	
	

	
	public void update () {
		StoreItem[] storeItem = storeCtrl.getStoreItems();
		for (int i = 0; i < storeItem.length; i++) {
			int val = storeItem[i].getQuantity();
			String sval = String.valueOf(val);
			viewItems[i].setValue(sval);			
		}
	}

}
