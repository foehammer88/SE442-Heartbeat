import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/** 
 * 
 * @author ShunMok
 *
 */
public interface BedSideRMI extends Remote{

	public ArrayList<ArrayList> getPatientTrendInfo() throws RemoteException; 
	
	public void registerPatientToBedSide(String patiendName, String patientID, String nurseRegistryID, String admitDate, String patientType) throws RemoteException; 

	public void dischargePatientFromBedSide(String patientID, String dischargeDate) throws RemoteException; 
	
	public void alarmAcknowledged() throws RemoteException;  
	
	public void alarmQueueStatus(boolean queueFull) throws RemoteException;
}
