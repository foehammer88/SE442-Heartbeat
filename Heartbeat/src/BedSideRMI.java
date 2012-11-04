import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BedSideRMI extends Remote{

	public void getPatientTrendInfo(String patientID) throws RemoteException; 
	
	public void registerPatientToBedSide(String patientID, String nurseRegistryID) throws RemoteException; 
}
