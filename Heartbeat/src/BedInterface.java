import java.rmi.RemoteException;

/**
 * 
 */

/**
 * @author ZachH
 *
 */
public class BedInterface{
	
	private Patient patient; 
	private BedView bedView; 
	private BedSideCommunicator bedSideComm; 
	
	/**
	 * Constructor
	 */
	public BedInterface(BedView bedView) { 
		this.bedView = bedView; 
	}
	
	public void updateVitals(String bp, String hr, String temp, String rr) { 
		
		//Update Vitals for bed view 
		bedView.updateVitals(bp, hr, temp, rr);
		
		//Send Vitals to Nurse side via bedside communciator 
		bedSideComm.sendPatientVitalSigns(bp, hr, temp, rr);
	}
	
	/**
	 * Method to admit and register patient to bed side. 
	 * @param patientName
	 * @param patientID
	 * @param admitDate
	 */
	public void registerPatient(String patientName, String patientType, String patientID, String admitDate) { 
		
		//Create a new patient
		patient = new Patient(patientName, patientID, admitDate, "", patientType, 1000);
		patient.setController(this);
		System.out.println("Patient Registerd is: " + patient.getPatientName());
	
		//Generate Patient Vitals 
		System.out.println("Generating Automatic Patient Vital Signs");
		patient.startPatientVitalSigns();
	}
	
	/**
	 * Method to discharge patient from bed side. 
	 * @param patientID
	 * @param dischargeDate
	 */
	public void dischargePatient(String patientID, String dischargeDate) { 
		
		//Destroy patient.
		patient = null; 
		
		//But write discharge date to log file. 
		
	}
	
	/**
	 * Method to get patient trend data. 
	 */
	public void getPatientTrendDate() { 
		
		patient.getPatientTrendData();
	}
	
	/**
	 * Method which simply acknowledges an alarm. 
	 */
	public void alarmAcknowledged() { 
		
		patient.acknowledgeAlarm("yes");
	}
	
	public void setBedSideComm(BedSideCommunicator bedSideComm) { 
		this.bedSideComm = bedSideComm;
	}
	
	/**
	 * Main Method for the bed side system 
	 * @throws RemoteException 
	 */
	public static void main(String[] args) { 
		
		BedSideRMIImpl bedRMI;
		BedSideCommunicator bedSideComm = null;
		try { 
			bedSideComm= new BedSideCommunicator();
		} catch (Exception excep) { 
			System.out.println("Error trying to establish communication with nurse");
			excep.printStackTrace();
		}
		BedView bedView = new BedView(); 
		BedInterface bedInterface = new BedInterface(bedView);
		bedInterface.setBedSideComm(bedSideComm);
		bedView.startBedView(bedInterface);
		Alarm alarm = new Alarm();
		
		boolean rmiStarted = false ; 
		
		//Initialize variables and start RMI service for Bed Side.
		try { 
			bedRMI = new BedSideRMIImpl(bedInterface, alarm); 
			
			//Start bed side rmi 
			System.out.println("Starting Bed Side RMI Service.");
			bedRMI.startBedSideRMI();
			rmiStarted = true; 
			
			//Intialize alarm registry 
			alarm.initializeRegistry("", -1);
			
		} catch (Exception excep) { 
			System.out.println("Warning! Exception Found! Could not start RMI service on Bed Side");
			excep.printStackTrace();
			rmiStarted = false; 
		}
		
		//Show Bed View only if RMI interface started 
		if (rmiStarted) {
			System.out.println("Loading Bed Side View....");
			bedView.showWindow();
		}
	}
}
