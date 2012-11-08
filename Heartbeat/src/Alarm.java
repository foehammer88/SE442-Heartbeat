import java.util.ArrayList;
import java.rmi.registry.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * @author Patrick
 *
 */
public class Alarm {
    private static final String ns_handle = "Nurse-Alarm-Receiver";
    private static Registry registry;
    private static ArrayList<AlarmPair> buffer = new ArrayList<AlarmPair>();
    private static boolean nurseAlarmBufferFull = false; 
   
    /**
     * Initializes this class' reference to the RMI Registry
     * @param host The host name of the machine the registry is located on
     * @param port The port number to connect through (-1 if default is acceptable)
     * @throws RemoteException 
     */
    public static void initializeRegistry(String host, int port) throws RemoteException {
        if(registry == null) {
            if(port < 0 && host.equals("")) {
                registry = LocateRegistry.getRegistry();
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
        	System.out.println("Sending alarm");
        	if (nurseAlarmBufferFull) { 
        		System.out.println("Nurse Alarm Buffer is full, sending alarm to local buffer...");
        		bufferAlarm(color, patientID);
        	} else { 
        		 NurseRMI ns = (NurseRMI) registry.lookup(ns_handle);
                 AlarmPair p = new AlarmPair(patientID, color);
                 ns.alarmRaised(p);
        	}
        }catch(RemoteException e) {
            e.printStackTrace();
        }
        
        return true;
    }
    
    public static void bufferAlarm(String color, String patientID) throws RemoteException {
        AlarmPair p = new AlarmPair(patientID, color);
        buffer.add(p);
    }
    
    public static boolean sendBufferedAlarm() throws NotBoundException {
        AlarmPair p = buffer.remove(0);
        return tripAlarm(p.getAlarmType(), p.getId());
    }
}