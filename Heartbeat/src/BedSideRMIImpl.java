import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;


public class BedSideRMIImpl implements BedSideRMI{

	private String patientID; 
	private String nurseRegistryID;
	private String rmiRegistryName = "Bed-Side-RMI";
	
	public void getPatientTrendInfo(String patientID) throws RemoteException { 
		
	}
	
	public void registerPatientToBedSide(String patientID, String nurseRegistryID) throws RemoteException { 
		
		this.patientID = patientID; 
		this.nurseRegistryID = nurseRegistryID; 
	}
	
	private void startBedSideRMI() throws RemoteException, MalformedURLException { 
		
		System.out.println("Binding bed side to RMI registry...");
		BedSideRMIImpl bedSideRMI = new BedSideRMIImpl(); 
		Naming.rebind(rmiRegistryName, bedSideRMI);
	}
	
	private void stopBedSideRMI() throws RemoteException, MalformedURLException, NotBoundException { 
		
		System.out.println("Unbinding bed side from RMI registry...");
		Naming.unbind(rmiRegistryName);
	}
}
