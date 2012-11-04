import java.rmi.Remote;
import java.rmi.RemoteException;

public interface NurseAlarmReceiver extends Remote {
	
	public void alarmRaised(String alarm, String patientID) throws RemoteException;
}
