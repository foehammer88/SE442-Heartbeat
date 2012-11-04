import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BedSideRMI extends Remote{

	public void getPatientTrendInfo(String patientID) throws RemoteException; 
	
	public void registerPatientToBedSide(String patiendName, String patientID, String nurseRegistryID, String admitDate) throws RemoteException; 

	public void dischargePatientFromBedSide(String patientID, String dischargeDate) throws RemoteException; 
}
