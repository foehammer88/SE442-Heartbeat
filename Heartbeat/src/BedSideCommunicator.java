import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class BedSideCommunicator extends UnicastRemoteObject{

	
	private String ns_handle = "Nurse-Alarm-Receiver";
    private Registry registry;
    private NurseRMI nsRMI;
    
	/**
	 * Constructor 
	 * @throws RemoteException
	 */
	public BedSideCommunicator() throws RemoteException {
		super();
		
	}
	
	public void connectToNurseSideRMI(String host, int port) throws RemoteException { 
		if(registry == null) {
            if(port < 0) {
                registry = LocateRegistry.getRegistry(host);
            }else {
                registry = LocateRegistry.getRegistry(host, port);
            }
        }
	}
	
	public void sendPatientVitalSigns(){ 
		
		try { 
			connectToNurseSideRMI("localhost", -1);
			nsRMI = (NurseRMI) registry.lookup(ns_handle);
			
		} catch(Exception excep) { 
			System.out.println("Warning! Found an Exception!");
			excep.printStackTrace();
		}
	}

}
