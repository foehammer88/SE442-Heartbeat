import java.rmi.Remote;
import java.rmi.RemoteException;

/** 
 * 
 * @author ShunMok
 *
 */
public interface BedSideRMI extends Remote{

	public void getPatientTrendInfo() throws RemoteException; 
	
	public String registerPatientToBedSide(String patiendName, String patientID, String nurseRegistryID, String admitDate, String patientType) throws RemoteException; 

	public void dischargePatientFromBedSide(String patientID, String dischargeDate) throws RemoteException; 
	
	public void alarmAcknowledged() throws RemoteException;  
	
	public void alarmQueueStatus(boolean queueFull) throws RemoteException;
}
