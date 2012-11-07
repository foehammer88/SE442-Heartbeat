import java.rmi.Remote;
import java.rmi.RemoteException;

public interface NurseAlarmReceiver extends Remote {
	
	public void alarmRaised(AlarmPair alarmObject) throws RemoteException;
}
