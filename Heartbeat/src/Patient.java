
public class Patient {
	
	private String patientName; 
	private String patientID; 
	private String patientAdmitDate; 
	private String patientDischargeDate; 
	
	/**
	 * Patient Object Constructor
	 * @param patientName - Name of Patient
	 * @param patientId - ID of Patient
	 * @param patientAdmitDate - Admit date of patient 
	 * @param patientDischargeDate - Discharge date of patient 
	 */
	public Patient(String patientName, String patientId, String patientAdmitDate, String patientDischargeDate){ 
		
		this.patientName = patientName; 
		this.patientID = patientId; 
		this.patientAdmitDate = patientAdmitDate; 
		this.patientDischargeDate = patientDischargeDate; 
	}
	
	public String getPatientName() { 
		return patientName; 
	}
	
	public String getPatientId() { 
		return patientID; 
	}
	
	public String getPatientAdmitDate() { 
		return patientAdmitDate;
	}
	
	public String getPatientDischargeDate() { 
		return patientDischargeDate;
	}
}
