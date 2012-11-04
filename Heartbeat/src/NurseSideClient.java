import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class NurseSideClient extends UnicastRemoteObject{

	private String nurseRmiRegistryName = "Nurse-Alarm-Receiver";
	private String bedSideRMI = "Bed-Side-RMI";
	private BedSideRMI bedSide;
	
	public NurseSideClient() throws RemoteException {
		super();
	}
	
	/**
	 * Method to connect to a bed side 
	 * @throws RemoteException
	 * @throws NotBoundException 
	 */
	private void connectoBedSide() throws RemoteException, NotBoundException { 
		
		System.out.println("Locating Bed Side...");
		Registry rmiRegistry = LocateRegistry.getRegistry(); 
		bedSide = (BedSideRMI)rmiRegistry.lookup(bedSideRMI);
	}
	
	public void registerPatientToBedSide(String patientID) { 
		
		try { 
			
			connectoBedSide();
			bedSide.registerPatientToBedSide(patientID, nurseRmiRegistryName);
		} catch (Exception excep) { 
			System.out.println("Warning! Found an exception when trying to register patient to bedside!");
			excep.printStackTrace();
		}
		
	}
	
	public void getPatientTrendData(String patientID) { 
		
		try { 
			
			bedSide.getPatientTrendInfo(patientID);
			
		} catch(Exception excep) { 
			System.out.println("Warning! Found an exception trying to fetch patient trend data"); 
			excep.printStackTrace();
		}
	}
	
}
