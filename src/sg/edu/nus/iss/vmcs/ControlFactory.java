package sg.edu.nus.iss.vmcs;

import java.io.IOException;

import sg.edu.nus.iss.vmcs.customer.DispenseController;
import sg.edu.nus.iss.vmcs.customer.TransactionController;
import sg.edu.nus.iss.vmcs.machinery.MachineryController;
import sg.edu.nus.iss.vmcs.maintenance.MaintenanceController;
import sg.edu.nus.iss.vmcs.store.StoreController;
import sg.edu.nus.iss.vmcs.system.CashPropertyLoader;
import sg.edu.nus.iss.vmcs.system.DrinkPropertyLoader;
import sg.edu.nus.iss.vmcs.system.Environment;
import sg.edu.nus.iss.vmcs.system.MainController;
import sg.edu.nus.iss.vmcs.system.SimulationController;
import sg.edu.nus.iss.vmcs.util.VMCSException;

public class ControlFactory {
    private static MainController mainController = null;
    private static DispenseController dispenseController = null;
    private static TransactionController transactionController = null;
    private static MachineryController machineryController = null;
    private static MaintenanceController maintenanceController = null;
    private static SimulationController  simulatorController = null;
    private static StoreController storeController = null;
    
    private static String propertyFile = null;
    private static CashPropertyLoader cashLoader = null;
    private static DrinkPropertyLoader drinksLoader = null;
    
    public static void initialize(String propertyFile) throws VMCSException {
		try {
			Environment.initialize(propertyFile);
			cashLoader =
				new CashPropertyLoader(Environment.getCashPropFile());
			drinksLoader =
				new DrinkPropertyLoader(Environment.getDrinkPropFile());
			cashLoader.initialize();
			drinksLoader.initialize();
			
			
			getStoreController().initialize();
			getMachineryController().initialize();
			
		} catch (IOException e) {
			throw new VMCSException(
				"MainController.initialize",
				e.getMessage());
		}
	}
    
    public static MainController getMainController() {
        if (mainController == null) mainController = new MainController(propertyFile);
        return mainController;
    }
    
    public static SimulationController getSimulationController() {
        if (simulatorController == null) simulatorController = new SimulationController();
        return simulatorController;
    }

    public static DispenseController getDispenseController() {
        if (dispenseController == null) dispenseController = new DispenseController(getTransactionController());
        return dispenseController;
    }

    public static TransactionController getTransactionController() {
        if (transactionController == null) transactionController = new TransactionController(getMainController());
        return transactionController;
    }

    public static MachineryController getMachineryController(){
        if (machineryController == null) machineryController = new MachineryController();
        return machineryController;
    }

    public static MaintenanceController getMaintenanceController() {
        if (maintenanceController == null) maintenanceController = new MaintenanceController();
        return maintenanceController;
    }
    
    public static StoreController getStoreController() {
        if (storeController == null) storeController = new StoreController(cashLoader, drinksLoader);
        return storeController;
    }
}
