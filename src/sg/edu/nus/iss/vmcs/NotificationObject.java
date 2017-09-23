package sg.edu.nus.iss.vmcs;

public class NotificationObject {
	private Object obj;
	private NotificationType type;
	
	public void setType(NotificationType type) {
		this.type = type;
	}
	
	public NotificationType getType() {
		return this.type;
	}
	
	public void setObject(Object obj) {
		this.obj = obj;
	}
	public Object getObject() {
		return this.obj;
	}
	
	public enum NotificationType {
		RefreshSimulatorControlPanel,
		IsCustomerPanelOpened,
		IsDoorClosed,
		AuthencationMaintainer,
		TransferAll
	}
}
