import java.rmi.RemoteException;
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
	private NurseView nurseView; 
	
	/**
	 * Nurse Station Constructor.
	 */
	public NurseStation(NurseSideCommunicator communicator, NurseView nurseView) { 
		try  { 
			this.communicator = communicator;
			this.nurseView = nurseView;
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
	
	public void updateVitals(String bp, String hr, String temp, String rr) { 
		
		nurseView.updateVitals(bp, hr, temp, rr);
	}
	
	/**
	 * Method to admit patient to a bedside. 
	 * @param patientID - id of patient. 
	 */
	public void admitPatient(String patientID, String patientName, String patientType) { 
		
		String admitDate = getCurrentTimeDate();
		
		try { 
			String message = communicator.registerPatientToBedSide(patientID, patientName, admitDate, patientType);
			System.out.println("Message from bed side: " + message);
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
	 * @param patientTrend 
	 */
	public void getPatientTrendData(String patientID, PatientTrend patientTrend) { 
		
		communicator.getPatientTrendData(patientID);
	}
	
	/**
	 * Method which handle the acknowledgement of alarms from the nurse 
	 */
	public void acknowledgeAlaram() { 
		
		try {
			communicator.acknowledgeAlarm();
		} catch (RemoteException e) {
			System.out.println("Warning! Exception found trying to send acknowledge alarm.");
			e.printStackTrace();
		}
	}
	
	/**
	 * Method in which alarm raised will be shown to the nurse user interface.
	 * @param alarmObject
	 */
	public void alarmRaised(String patientName, String vital, String degree) { 
		
		nurseView.setAlarm(patientName, vital, degree);
	}
	
	/**
	 * Main Method for Nurse System 
	 * @param args
	 * @throws RemoteException 
	 */
	public static void main(String[] args) { 
		
		try { 
			NurseSideCommunicator nurseComm = new NurseSideCommunicator();
			NurseView nurseUI = new NurseView();
			NurseStation nurseStation = new NurseStation(nurseComm, nurseUI); 
			nurseUI.startNurseView(nurseStation);
			NurseRMIImpl nurseAlarmReceiver = new NurseRMIImpl(nurseStation, nurseComm); 
			
			//Start Nurse Side Alarm RMI Service
			nurseAlarmReceiver.bindToRegistry();
			
			//Show Nurse UI 
			nurseUI.showWindow();
			
		} catch (Exception excep) { 
			System.out.println("Warning! Found an Exception in starting Nurse System");
			excep.printStackTrace();
		}
			
	}
}