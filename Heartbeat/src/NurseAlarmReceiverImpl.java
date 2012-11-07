import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 
 * @author ShunMok
 *
 */
public class NurseAlarmReceiverImpl extends UnicastRemoteObject implements NurseAlarmReceiver{
	
	private String rmiRegistryName = "Nurse-Alarm-Receiver";
	private ArrayList<AlarmPair> localAlarmBuffer; 
	private boolean bufferFull; 
	
	/**
	 * Constructor
	 * @throws RemoteException
	 */
	public NurseAlarmReceiverImpl(NurseStation nurseStation) throws RemoteException { 
		
		localAlarmBuffer = new ArrayList<AlarmPair>();
	}
	
	private boolean checkIfAlarmBufferFull() { 
		
		if (localAlarmBuffer.size() == 25) { 
			return true; 
		} else { 
			return false; 
		}
	}
	
	public void alarmRaised(AlarmPair alarmObject) throws RemoteException{ 
		
		if (checkIfAlarmBufferFull()) { 
			notifyBedSideOfAlarmBuffer(true);
		} else { 
		//Add to alarm buffer 
		localAlarmBuffer.add(alarmObject);
		}
		
	}
	
	/**
	 * Method to notify of Alarm Buffer
	 */
	public void notifyBedSideOfAlarmBuffer(boolean status) { 
		
	}
	
	public void bindToRegistry() throws RemoteException, MalformedURLException { 
		System.out.println("Binding nurse alarm to RMI registry"); 
		Naming.rebind(rmiRegistryName, this);
	}
	
	public void unBindToRegistry() throws RemoteException, MalformedURLException, NotBoundException { 
		System.out.println("Unbinding nurse alarm server from RMI registry");
		Naming.unbind(rmiRegistryName);
	}
}
