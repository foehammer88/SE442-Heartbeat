import java.rmi.Remote;
import java.rmi.RemoteException;

public interface NurseRMI extends Remote {
	
	public void alarmRaised(AlarmPair alarmObject) throws RemoteException;
	
	public void receivePatientVitals(String bp, String hr, String temp, String rr) throws RemoteException;
}
