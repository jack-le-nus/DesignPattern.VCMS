package sg.edu.nus.iss.vmcs;

import java.awt.event.ActionListener;

import sg.edu.nus.iss.vmcs.store.StoreItem;
import sg.edu.nus.iss.vmcs.util.VMCSException;

public interface ItemDisplayElement {

	void addListener(ActionListener l);

	void setActionCommand(String valueOf);

	void setActive(boolean st);

	void clear();

	void setValue(String value);
	
	void update(int idx, int qty) throws VMCSException;
	
	void update();
	
	void setItems(ItemDisplayElement[] items);
	
	void setStoreItems(StoreItem[] items);
}
