import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;

/**
 * 
 * @author ShunMok
 *
 */
public class NurseAlarmReceiverImpl extends UnicastRemoteObject implements NurseAlarmReceiver{
	
	private String rmiRegistryName = "Nurse-Alarm-Receiver";
	private LinkedList alarmBuffer = new LinkedList();  
	private boolean bufferFull; 
	
	/**
	 * Constructor
	 * @throws RemoteException
	 */
	public NurseAlarmReceiverImpl() throws RemoteException { 
		
	}
	
	public void alarmRaised(String alarm, String patientID) throws RemoteException{ 
		
		//Add to alarm buffer 
		
		//If alarm buffer full then notify bed side 
		
		
	}
	
	public void notifyBedSide() { 
		
	}
	
	public void bindToRegistry() throws RemoteException, MalformedURLException { 
		System.out.println("Binding nurse alarm to RMI registry");
		NurseAlarmReceiverImpl nurseAlarm = new NurseAlarmReceiverImpl(); 
		Naming.rebind(rmiRegistryName, nurseAlarm);
	}
	
	public void unBindToRegistry() throws RemoteException, MalformedURLException, NotBoundException { 
		System.out.println("Unbinding nurse alarm server from RMI registry");
		Naming.unbind(rmiRegistryName);
	}
}
