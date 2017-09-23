package sg.edu.nus.iss.vmcs;

import java.util.Observable;
import java.util.Observer;

public interface ChangeManager {

	public abstract void register(Observable subject, Observer observer);

	public abstract void unregister(Observable subject, Observer observer);

	public abstract void notifyObservers(Observable subject);

}
