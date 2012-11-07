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
public class NurseRMIImpl extends UnicastRemoteObject implements NurseRMI{
	
	private String rmiRegistryName = "Nurse-Alarm-Receiver";
	private ArrayList<AlarmPair> localAlarmBuffer; 
	private boolean bufferFull; 
	private NurseSideCommunicator communicator; 
	private NurseStation nurseStation; 
	
	/**
	 * Constructor
	 * @throws RemoteException
	 */
	public NurseRMIImpl(NurseStation nurseStation, NurseSideCommunicator communicator) throws RemoteException { 
		
		localAlarmBuffer = new ArrayList<AlarmPair>();
		this.nurseStation = nurseStation; 
		this.communicator = communicator; 
	}
	
	private boolean checkIfAlarmBufferFull() { 
		
		if (localAlarmBuffer.size() == 25) { 
			return true; 
		} else { 
			return false; 
		}
	}
	
	private void notifyNurseStationAlarmInBuffer(ArrayList<AlarmPair> alarmQueue) { 
		
		AlarmPair localAlarmObject = alarmQueue.get(0);
		String alarmDegree = localAlarmObject.getAlarmType();
		String patientID = localAlarmObject.getId();
		nurseStation.alarmRaised(alarmDegree);
	}
	
	public void alarmRaised(AlarmPair alarmObject) throws RemoteException{ 
		
		if (checkIfAlarmBufferFull()) { 
			notifyBedSideOfAlarmBuffer(true);
		} else { 
		//Add to alarm buffer 
		localAlarmBuffer.add(alarmObject);
		}
		notifyNurseStationAlarmInBuffer(localAlarmBuffer); 
	}
	
	/**
	 * Method to notify of Alarm Buffer
	 */
	public void notifyBedSideOfAlarmBuffer(boolean status) { 
		
		communicator.sendNurseSideBufferInfo(status);
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
