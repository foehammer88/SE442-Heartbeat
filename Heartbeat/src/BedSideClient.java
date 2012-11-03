/**
 * 
 */

import java.rmi.Remote; 
import java.rmi.RemoteException;

public interface BedSideClient {
	
	public void addBedSideListener() throws RemoteException;
		
	public void removeBedSideListener() throws RemoteException;
	
	public void alarmAcknowledged() throws RemoteException;
	
	public void getPatientHistoryInfo() throws RemoteException; 
	
	public void getPatientTrendInfo() throws RemoteException; 
}
