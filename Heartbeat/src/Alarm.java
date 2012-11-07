import java.util.ArrayList;
import java.nio.channels.AlreadyBoundException;
import java.rmi.registry.*;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author Patrick
 *
 */
public class Alarm {
    private static final String ns_handle = "Nurse-Alarm-Receiver";
    private static Registry registry;
    private static ArrayList<AlarmPair> buffer = new ArrayList<AlarmPair>();
    /**
     * Initializes this class' reference to the RMI Registry
     * @param host The host name of the machine the registry is located on
     * @param port The port number to connect through (-1 if default is acceptable)
     * @throws RemoteException 
     */
    public static void initializeRegistry(String host, int port) throws RemoteException {
        if(registry == null) {
            if(port < 0) {
                registry = LocateRegistry.getRegistry(host);
            }else {
                registry = LocateRegistry.getRegistry(host, port);
            }
        }
    }
    
    /**
     * Sends an alarm signal to the nurse's station
     * @param color Indicates the severity of the alarm
     * @param patientID the ID of the patient the alarm is for
     * @return true if the alarm makes it across the network, false otherwise
     * @throws NotBoundException 
     */
    public static boolean tripAlarm(String color, String patientID) throws NotBoundException {
        try {
            NurseAlarmReceiver ns = (NurseAlarmReceiver) registry.lookup(ns_handle);
            AlarmPair p = new AlarmPair(patientID, color);
            ns.alarmRaised(p);
        }catch(RemoteException e) {
            return false;
        }catch(AlreadyBoundException e) {
            return false;
        }
        
        return true;
    }
    
    public static void bufferAlarm(String color, String patientID) {
        AlarmPair p = new AlarmPair(patientID, color);
        buffer.add(p);
    }
    
    public static boolean sendBufferedAlarm() throws NotBoundException {
        AlarmPair p = buffer.remove(0);
        return tripAlarm(p.getAlarmType(), p.getId());
    }
}