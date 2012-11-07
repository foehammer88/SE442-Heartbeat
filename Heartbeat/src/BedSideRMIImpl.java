import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * 
 * @author ShunMok
 *
 */

public class BedSideRMIImpl extends UnicastRemoteObject implements BedSideRMI{

	private String nurseRegistryID;
	private String rmiRegistryName = "Bed-Side-RMI";
	private BedInterface bedInterface;
	private Alarm alarm; 
	
	public BedSideRMIImpl(BedInterface bedInterface, Alarm alarm) throws RemoteException{ 
		
		this.bedInterface = bedInterface;
		this.alarm = alarm;
	}
	
	public synchronized void getPatientTrendInfo() throws RemoteException { 
		
		bedInterface.getPatientTrendDate();
	}
	
	public synchronized String registerPatientToBedSide(String patientName, String patientID, String nurseRegistryID, String admitDate, String patientType) throws RemoteException { 
		
		this.nurseRegistryID = nurseRegistryID;
		bedInterface.registerPatient(patientName,patientType, patientID, admitDate);
		return "Patient has been registered to BedSide";
	}
	
	public synchronized void dischargePatientFromBedSide(String patientId, String dischargeDate) throws RemoteException { 
		
		bedInterface.dischargePatient(patientId, dischargeDate);
	}
	
	public synchronized void alarmAcknowledged() throws RemoteException { 
		
		//write to log 
	}
	
	public synchronized void alarmQueueStatus(boolean queueFull) throws RemoteException { 
			
		//set boolean in alarm true
		
	}
	
	public synchronized void startBedSideRMI() throws RemoteException, MalformedURLException { 
		
		System.out.println("Binding bed side to RMI registry...");
		Naming.rebind("Bed-Side-RMI", this);
	}
	
	public void stopBedSideRMI() throws RemoteException, MalformedURLException, NotBoundException { 
		
		System.out.println("Unbinding bed side from RMI registry...");
		Naming.unbind(rmiRegistryName);
	}
}
