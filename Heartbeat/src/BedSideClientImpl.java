import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class BedSideClientImpl extends UnicastRemoteObject implements BedSideClient  {
	
	public BedSideClientImpl() throws RemoteException { 
		
		super(); 
	}

	public synchronized void addBedSideListener() { 
		
	}
	
	public synchronized void removeBedSideListener() { 
		
	}
	
	public synchronized void alarmAcknowledged() {
		
	}
	
	public synchronized void getPatientHistoryInfo() { 
		
	}
	
	public synchronized void getPatientTrendInfo() { 
		
	}
	
	public void startBedSideServer() {
		
	}
	
	public void stopBedSideServer() { 
		
	}
}
