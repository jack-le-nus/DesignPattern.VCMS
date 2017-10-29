package sg.edu.nus.iss.vmcs.store;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.iss.vmcs.system.CashPropertyLoader;
import sg.edu.nus.iss.vmcs.system.DrinkPropertyLoader;
import sg.edu.nus.iss.vmcs.system.Environment;
import sg.edu.nus.iss.vmcs.system.MainController;

public class StoreControllerTest extends TestCase{
	private String propertyFilename=System.getProperty("propertyFilename");
	
	@Before
	public void setup() throws Exception{
		
	}

	@After
	public void tearDown() throws Exception{
	}
	
	@Test
	public void testStoreControllerConstructor() throws Exception{
		Environment.initialize(propertyFilename);
		CashPropertyLoader cashLoader =
			new CashPropertyLoader(Environment.getCashPropFile());
		DrinkPropertyLoader drinksLoader =
			new DrinkPropertyLoader(Environment.getDrinkPropFile());
		cashLoader.initialize();
		drinksLoader.initialize();
		//Act
		//StoreController storeController=new StoreController(cashLoader, drinksLoader);
		CashStoreController cashStoreController = new CashStoreController(cashLoader, new CashStore());
		DrinksStoreController drinksStoreController = new DrinksStoreController(drinksLoader, new DrinksStore());
		cashStoreController.initialize();
		drinksStoreController.initialize();
		
		//Assert
		assertNotNull(cashStoreController);
		assertNotNull(drinksStoreController);
		
		assertNotNull(cashStoreController.getStore());
		assertNotNull(drinksStoreController.getStore());
	}
	
	@Test
	public void testInitialize() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		StoreController storeController=mainCtrl.getStoreController();
		CashStoreController cashStoreController = mainCtrl.getCashStoreController();
		DrinksStoreController drinksStoreController = mainCtrl.getDrinksStoreController();
		
		//Act initialize
		storeController.initialize();		
		CashStore cashStore=(CashStore)storeController.getStore();
		DrinksStore drinksStore=(DrinksStore)storeController.getStore();
		
		//Assert
		assertNotNull(cashStore);
		assertNotNull(drinksStore);
	}

	@Test
	public void testInitializeStores() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		StoreController storeController=mainCtrl.getCashStoreController();
		
		//Act initialize indirect Act initializeStores
		storeController.initialize();
		
		//Get Cash Store and test its elements
		CashStore cashStore=(CashStore)storeController.getStore();
		int storeSize=cashStore.getStoreSize();
		for(int i=0;i<storeSize;i++){
			CashStoreItem cashStoreItem=(CashStoreItem)cashStore.getStoreItem(i);
			Coin coin=(Coin)cashStoreItem.getContent();
			//Assert
			assertNotNull(coin);
		}
		
		storeController=mainCtrl.getDrinksStoreController();
		
		//Get Drinks Store and test its elements
		DrinksStore drinksStore=(DrinksStore)storeController.getStore();
		storeSize=drinksStore.getStoreSize();
		for(int i=0;i<storeSize;i++){
			DrinksStoreItem drinksStoreItem=(DrinksStoreItem)drinksStore.getStoreItem(i);
			DrinksBrand drinksBrand=(DrinksBrand)drinksStoreItem.getContent();
			//Assert
			assertNotNull(drinksBrand);
		}
	}

	@Test
	public void testInitializeDrinkStore() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		StoreController storeController=mainCtrl.getStoreController();
		
		//Act initialize indirect Act initializeDrinkStore
		storeController.initialize();
		
		//Get Drinks Store and test its elements
		DrinksStore drinksStore=(DrinksStore)storeController.getStore();
		int storeSize=drinksStore.getStoreSize();
		for(int i=0;i<storeSize;i++){
			DrinksStoreItem drinksStoreItem=(DrinksStoreItem)drinksStore.getStoreItem(i);
			DrinksBrand drinksBrand=(DrinksBrand)drinksStoreItem.getContent();
			//Assert
			assertNotNull(drinksBrand);
		}
	}

	@Test
	public void testInitializeCashStore() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		StoreController storeController=mainCtrl.getStoreController();
		
		//Act initialize indirect Act initializeCashStore
		storeController.initialize();
		
		//Get Cash Store and test its elements
		CashStore cashStore=(CashStore)storeController.getStore();
		int storeSize=cashStore.getStoreSize();
		for(int i=0;i<storeSize;i++){
			CashStoreItem cashStoreItem=(CashStoreItem)cashStore.getStoreItem(i);
			Coin coin=(Coin)cashStoreItem.getContent();
			assertNotNull(coin);
		}
	}

	@Test
	public void testStoreCoin() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		CashStoreController storeController=mainCtrl.getCashStoreController();
		
		storeController.initialize();
		
		CashStore cashStore=(CashStore)storeController.getStore();
		int storeSize=cashStore.getStoreSize();
		for(int i=0;i<storeSize;i++){
			CashStoreItem cashStoreItem=(CashStoreItem)cashStore.getStoreItem(i);
			Coin coin1=(Coin)cashStoreItem.getContent();
			//Act storeCoin
			storeController.storeCoin(coin1);
			Coin coin2=cashStore.findCoin(coin1.getWeight());
			//Assert
			assertNotNull(coin2);
			assertSame(coin1,coin2);
		}
	}

	@Test
	public void testGetStoreSize() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		StoreController storeController=mainCtrl.getCashStoreController();
		
		//Initializing the Store
		storeController.initialize();
		
		//Get Cash Store and test its elements
		CashStore cashStore=(CashStore)storeController.getStore();
		//Act getStoreSize and test looping the store with the store size
		int storeSize=storeController.getStoreSize();
		for(int i=0;i<storeSize;i++){
			CashStoreItem cashStoreItem=(CashStoreItem)cashStore.getStoreItem(i);
			Coin coin=(Coin)cashStoreItem.getContent();
			//Assert
			assertNotNull(coin);
		}
		storeController=mainCtrl.getDrinksStoreController();
		//Get Drinks Store and test its elements
		DrinksStore drinksStore=(DrinksStore)storeController.getStore();
		//Act getStoreSize and test looping the store with the store size
		storeSize=storeController.getStoreSize();
		for(int i=0;i<storeSize;i++){
			DrinksStoreItem drinksStoreItem=(DrinksStoreItem)drinksStore.getStoreItem(i);
			DrinksBrand drinksBrand=(DrinksBrand)drinksStoreItem.getContent();
			//Assert
			assertNotNull(drinksBrand);
		}
	}

	@Test
	public void testGetStoreItems() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		StoreController storeController=mainCtrl.getCashStoreController();
		
		storeController.initialize();
		
		//Act getStoreItems
		StoreItem[] cashStoreItems=storeController.getStoreItems();
		for(int i=0;i<cashStoreItems.length;i++){
			CashStoreItem cashStoreItem=(CashStoreItem)cashStoreItems[i];
			//Assert
			assertNotNull(cashStoreItem);
		}
		storeController=mainCtrl.getDrinksStoreController();
		//Act getStoreItems
		StoreItem[] drinksStoreItems=storeController.getStoreItems();
		for(int i=0;i<drinksStoreItems.length;i++){
			DrinksStoreItem drinksStoreItem=(DrinksStoreItem)drinksStoreItems[i];
			//Assert
			assertNotNull(drinksStoreItem);
		}
	}

	@Test
	public void testChangeStoreQty() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		StoreController storeController=mainCtrl.getCashStoreController();
		storeController.initialize();
		CashStore cashStore=(CashStore)storeController.getStore();
		int storeSize=cashStore.getStoreSize();
		for(int i=0;i<storeSize;i++){
			int qty1=12+i;
			//Act changeStoreQty
			storeController.changeStoreQty(i, qty1);
			CashStoreItem cashStoreItem=(CashStoreItem)cashStore.getStoreItem(i);
			int qty2=cashStoreItem.getQuantity();
			//Assert
			assertEquals(qty1,qty2);
		}
		DrinksStore drinksStore=(DrinksStore)storeController.getStore();
		storeSize=drinksStore.getStoreSize();
		for(int i=0;i<storeSize;i++){
			int qty1=14+i;
			//Act changeStoreQty
			storeController.changeStoreQty(i, qty1);
			DrinksStoreItem drinksStoreItem=(DrinksStoreItem)drinksStore.getStoreItem(i);
			int qty2=drinksStoreItem.getQuantity();
			//Assert
			assertEquals(qty1,qty2);
		}
	}

	@Test
	public void testGetStoreItem() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		StoreController storeController=mainCtrl.getCashStoreController();
		storeController.initialize();
		CashStore cashStore=(CashStore)storeController.getStore();
		int storeSize=cashStore.getStoreSize();
		for(int i=0;i<storeSize;i++){
			//Act getStoreItem
			CashStoreItem cashStoreItem=(CashStoreItem)storeController.getStoreItem( i);
			//Assert
			assertNotNull(cashStoreItem);
		}
		
		storeController=mainCtrl.getDrinksStoreController();
		DrinksStore drinksStore=(DrinksStore)storeController.getStore();
		storeSize=drinksStore.getStoreSize();
		for(int i=0;i<storeSize;i++){
			//Act getStoreItem
			DrinksStoreItem drinksStoreItem=(DrinksStoreItem)storeController.getStoreItem(i);
			//Assert
			assertNotNull(drinksStoreItem);
		}
	}

	@Test
	public void testSetPrice() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		DrinksStoreController storeController=mainCtrl.getDrinksStoreController();
		storeController.initialize();
		
	    DrinksStore drinksStore=(DrinksStore)storeController.getStore();
		int storeSize=drinksStore.getStoreSize();
		for(int i=0;i<storeSize;i++){
			int price1=60+i;
			//Act setPrice
			storeController.setPrice(i, price1);
			DrinksStoreItem drinksStoreItem=((DrinksStoreItem)storeController.getStoreItem(i));
			DrinksBrand drinksBrand=(DrinksBrand)drinksStoreItem.getContent();
			int price2=drinksBrand.getPrice();
			//Assert
			assertEquals(price1,price2);
		}
	}

	@Test
	public void testGetTotalCash() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		CashStoreController storeController=mainCtrl.getCashStoreController();
		storeController.initialize();
		CashStore cashStore=(CashStore)storeController.getStore();
		//Act getTotalCash
		int totalCash=storeController.getTotalCash();
		int countTotalCash=0;
		int storeSize=cashStore.getStoreSize();
		for(int i=0;i<storeSize;i++){
			CashStoreItem cashStoreItem=(CashStoreItem)cashStore.getStoreItem(i);
			Coin coin=(Coin)cashStoreItem.getContent();
			countTotalCash+=(coin.getValue()*cashStoreItem.getQuantity());
		}
		//Assert
		assertEquals(totalCash,countTotalCash);
	}

	@Test
	public void testTransferAll() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		CashStoreController storeController=mainCtrl.getCashStoreController();
		storeController.initialize();
		CashStore cashStore=(CashStore)storeController.getStore();
		//Act transferAll
		int numOfCashTransfered=storeController.transferAll();
		//Assert
		if(numOfCashTransfered<0)
			fail();
		
		int totalCash=storeController.getTotalCash();
		assertEquals(totalCash,0);
		
		int countTotalCash=0;
		int storeSize=cashStore.getStoreSize();
		for(int i=0;i<storeSize;i++){
			CashStoreItem cashStoreItem=(CashStoreItem)cashStore.getStoreItem(i);
			Coin coin=(Coin)cashStoreItem.getContent();
			countTotalCash+=(coin.getValue()*cashStoreItem.getQuantity());
		}
		//Assert
		assertEquals(totalCash,countTotalCash);
	}

	@Test
	public void testCloseDown() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		CashStoreController cashStoreController = mainCtrl.getCashStoreController();
		DrinksStoreController drinksStoreController  = mainCtrl.getDrinksStoreController();
		cashStoreController.initialize();
		drinksStoreController.initialize();
		int cashStoreSize1=cashStoreController.getStoreSize();
		int drinksStoreSize1=drinksStoreController.getStoreSize();
		//Act closeDown
		cashStoreController.closeDown();
		drinksStoreController.closeDown();
		cashStoreController.initialize();
		drinksStoreController.initialize();
		int cashStoreSize2=cashStoreController.getStoreSize();
		int drinksStoreSize2=drinksStoreController.getStoreSize();
		//Assert
		assertEquals(cashStoreSize1,cashStoreSize2);
		assertEquals(drinksStoreSize1,drinksStoreSize2);
	}

	@Test
	public void testDispenseDrink(int idx) throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		DrinksStoreController storeController=mainCtrl.getDrinksStoreController();
		storeController.initialize();
		DrinksStore drinksStore=(DrinksStore)storeController.getStore();
		int storeSize=drinksStore.getStoreSize();
		for(int i=0;i<storeSize;i++){
			DrinksStoreItem drinksStoreItem=(DrinksStoreItem)drinksStore.getStoreItem(i);
			int qty1=drinksStoreItem.getQuantity();
			if(qty1==0)
				continue;
			//Act dispenseDrink
			storeController.dispenseDrink(i);
			int qty2=drinksStoreItem.getQuantity();
			//Assert
			assertEquals(qty1,qty2+1);
		}
	}

	@Test
	public void testGetStore() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		CashStoreController cashStoreController = mainCtrl.getCashStoreController();
		DrinksStoreController drinksStoreController  = mainCtrl.getDrinksStoreController();
		cashStoreController.initialize();
		drinksStoreController.initialize();
		//Act getStore
		CashStore cashStore=(CashStore)cashStoreController.getStore();
		DrinksStore drinksStore=(DrinksStore)drinksStoreController.getStore();
		//Assert
		assertNotNull(cashStore);
		assertNotNull(drinksStore);
	}

	@Test
	public void testGiveChange() throws Exception{
		MainController mainCtrl=new MainController(propertyFilename);
		mainCtrl.initialize();
		CashStoreController cashStoreController=mainCtrl.getCashStoreController();
		cashStoreController.initialize();
		CashStore cashStore=(CashStore)cashStoreController.getStore();
		int storeSize=cashStore.getStoreSize();
		for(int i=0;i<storeSize;i++){
			CashStoreItem cashStoreItem=(CashStoreItem)cashStore.getStoreItem(i);
			int qty1=cashStoreItem.getQuantity();
			if(qty1==0)
				continue;
			//Act give change
			cashStoreController.giveChange(i,1);
			cashStoreItem=(CashStoreItem)cashStore.getStoreItem(i);
			int qty2=cashStoreItem.getQuantity();
			//Assert
			assertEquals(qty1,qty2+1);
		}
	}
}//End of class StoreControllerTest