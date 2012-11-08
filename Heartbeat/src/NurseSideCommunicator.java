import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * 
 * @author ShunMok
 *
 */
public class NurseSideCommunicator extends UnicastRemoteObject{

	private String nurseRmiRegistryName = "Nurse-Alarm-Receiver";
	private String bedSideRMI = "Bed-Side-RMI";
	private BedSideRMI bedSide;
	
	public NurseSideCommunicator() throws RemoteException {
		super();
	}
	
	/**
	 * Method to connect to a bed side 
	 * @throws RemoteException
	 * @throws NotBoundException 
	 */
	private void connectoBedSide() throws RemoteException, NotBoundException { 
		
		System.out.println("Locating Bed Side...");
		Registry rmiRegistry = LocateRegistry.getRegistry(); 
		bedSide = (BedSideRMI)rmiRegistry.lookup(bedSideRMI);
	}
	
	public void acknowledgeAlarm() throws RemoteException { 
		
		System.out.println("Sending acknowledge alarm signal to bed side");
		bedSide.alarmAcknowledged();
	}
	
	public String registerPatientToBedSide(String patientID, String patientName, String admitDate, String patientType) { 
		
		String message = "";
		
		try { 
			
			connectoBedSide();
			System.out.println("Registering Patient to BedSide");
			message = bedSide.registerPatientToBedSide(patientName, patientID, nurseRmiRegistryName, admitDate, patientType);
			return message;
		} catch (Exception excep) { 
			System.out.println("Warning! Found an exception when trying to register patient to bedside!");
			excep.printStackTrace();
		}
		return message;
		
	}
	
	public void dischargePatient(String patientID, String dischargeDate) { 
		try { 
			
			bedSide.dischargePatientFromBedSide(patientID, dischargeDate);
		} catch (Exception excep) { 
			System.out.println("Warning! Found an exception trying to discharge patient.");
			excep.printStackTrace();
		}
	}
	
	public void getPatientTrendData(String patientID) { 
		
		try { 
			connectoBedSide();
			bedSide.getPatientTrendInfo();
			
		} catch(Exception excep) { 
			System.out.println("Warning! Found an exception trying to fetch patient trend data"); 
			excep.printStackTrace();
		}
	}
	
	public void sendNurseSideBufferInfo(boolean status) { 
		
		try { 
			connectoBedSide();
			bedSide.alarmQueueStatus(status);
		} catch (Exception excep) { 
			System.out.println("Warning! Found an exception trying to sent nurse side alarm buffer status");
			excep.printStackTrace();
		}
	}
}
