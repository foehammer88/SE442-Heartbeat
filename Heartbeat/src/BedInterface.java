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
	
	/**
	 * Constructor
	 */
	public BedInterface() { 
		
	}
	
	/**
	 * Method to admit and register patient to bed side. 
	 * @param patientName
	 * @param patientID
	 * @param admitDate
	 */
	public void registerPatient(String patientName, String patientType, String patientID, String admitDate) { 
		
		//Create a new patient
		patient = new Patient(patientName, patientID, admitDate, "", patientType, null);
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
		
	}
	
	/**
	 * Main Method for the bed side system 
	 */
	public static void main(String[] args) { 
		
		BedSideRMIImpl bedRMI;
		BedInterface bedInterface = new BedInterface();
		BedView bedView = new BedView(bedInterface); ;
		Alarm alarm = new Alarm();
		
		boolean rmiStarted = false ; 
		
		//Initialize variables and start RMI service for Bed Side.
		try { 
			bedRMI = new BedSideRMIImpl(bedInterface, alarm); 
			
			System.out.println("Starting Bed Side RMI Service.");
			bedRMI.startBedSideRMI();
			rmiStarted = true; 
		} catch (Exception excep) { 
			System.out.println("Warning! Exception Found! Could not start RMI service on Bed Side");
			excep.printStackTrace();
			rmiStarted = false; 
		}
		
		//Show Bed View only if RMI interface started 
		if (rmiStarted) {
			System.out.println("Loading Bed Side View....");
			bedView.main(null);
		}
	}
}
