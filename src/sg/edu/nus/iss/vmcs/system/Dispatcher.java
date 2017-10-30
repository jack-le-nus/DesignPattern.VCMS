package sg.edu.nus.iss.vmcs.system;

import java.util.HashMap;

public class Dispatcher {
	
	private HashMap<String, Command> commandObjects = new HashMap<String, Command>();
	
	
	public void addCommand(String commandString, Command command)
	{
		commandObjects.put(commandString, command);
	}
	
	public void dispatchCommand(String commandString, Object... object)
	{
		System.out.println(commandString);
		commandObjects.get(commandString).execute(object);
	}


}
