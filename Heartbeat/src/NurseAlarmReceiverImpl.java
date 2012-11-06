import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class NurseAlarmReceiverImpl extends UnicastRemoteObject implements NurseAlarmReceiver{
	
	private String rmiRegistryName = "Nurse-Alarm-Receiver";
	
	/**
	 * Constructor
	 * @throws RemoteException
	 */
	public NurseAlarmReceiverImpl() throws RemoteException { 
		
	}
	
	public void alarmRaised(String alarm, String patientID) throws RemoteException{ 
		
		System.out.println("Alarm has been raised: " + alarm);
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
