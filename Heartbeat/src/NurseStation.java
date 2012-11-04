import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 
 */

/**
 * @author Patrick
 *
 */
public class NurseStation {

	private NurseSideCommunicator communicator; 
	private String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * Nurse Station Constructor.
	 */
	public NurseStation() { 
		try  { 
			communicator = new NurseSideCommunicator(); 
		} catch(Exception excep) { 
			System.out.println("Warning! Found an exception"); 
			excep.printStackTrace();
		}
	}
	
	private String getCurrentTimeDate() { 
		
		String dischargeDate;
		
		Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
		dischargeDate = sdf.format(cal.getTime()).toString();
		
		return dischargeDate; 
	}
	
	/**
	 * Method to admit patient to a bedside. 
	 * @param patientID - id of patient. 
	 */
	public void admitPatient(String patientID, String patientName) { 
		
		String admitDate = getCurrentTimeDate();
		
		try { 
			communicator.registerPatientToBedSide(patientID, patientName, admitDate);
		} catch (Exception excep) { 
			System.out.println("Warning! Found an exception");
			excep.printStackTrace();
		}
		
	}
	
	/**
	 * Method to get discharge patient from the bedside. 
	 * @param patientID - id of patient. 
	 */
	public void dischargePatient(String patientID) { 

		String dischargeDate = getCurrentTimeDate();
	    
		try { 
			communicator.dischargePatient(patientID, dischargeDate);
		} catch (Exception excep) { 
			System.out.println("Warning! Found an exception");
			excep.printStackTrace();
		}
	}
	
	/**
	 * Method to get patient trend data 
	 * @param patientID - the id of patient 
	 */
	public void getPatientTrendData(String patientID) { 
		
		communicator.getPatientTrendData(patientID);
	}
	
	/**
	 * Method which handle the acknowledgement of alarms from the nurse 
	 */
	public void acknowledgeAlaram() { 
		
	}
}
