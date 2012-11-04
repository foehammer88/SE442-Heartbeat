import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;


public class Patient implements Runnable {
	
	private String patientName; 
	private String patientID; 
	private String patientAdmitDate; 
	private String patientDischargeDate;
	private String alarmType;
	private String alarmAcknowledged;
	private Integer patientBloodPressure;
	private Integer patientBPLow;
	private Integer patientBPHigh;
	private Integer patientTemp;
	private Integer patientTempHigh;
	private Integer patientTempLow;
	private Integer patientPulse;
	private Integer patientPulseHigh;
	private Integer patientPulseLow;
	private Integer patientRespRate;
	private Integer patientRespRateHigh;
	private Integer patientRespRateLow;
	private Date timeStamp;
	private Integer interval;
	private String[] patientHistory = new String[6];
	private Random rand = new Random();
	
	/**
	 * Patient Object Constructor
	 * @param patientName - Name of Patient
	 * @param patientId - ID of Patient
	 * @param patientAdmitDate - Admit date of patient 
	 * @param patientDischargeDate - Discharge date of patient 
	 * @param interval - Designated interval for generating vital signs
	 * @param patientBPHigh - High number for blood pressure range
	 * @param patientBPLow - Low number for blood pressure range
	 * @param patientTempHigh - High number for temperature range
	 * @param patientTempLow - Low number for temperature range
	 * @param patientPulseHigh - High number for pulse range
	 * @param patientPulseLow - Low number for pulse range
	 * @param patientRespRateHigh - High number for respiratory rate range
	 * @param patientRespRateLow - Low number for respiratory rate range
	 */
	public Patient(String patientName, String patientId, String patientAdmitDate, String patientDischargeDate
			, Integer interval, Integer patientBPHigh, Integer patientBPLow, Integer patientTempHigh, Integer patientTempLow, 
			Integer patientPulseHigh, Integer patientPulseLow, Integer patientRespRateHigh, Integer patientRespRateLow){ 
		
		this.patientName = patientName; 
		this.patientID = patientId; 
		this.patientAdmitDate = patientAdmitDate; 
		this.patientDischargeDate = patientDischargeDate;
		this.patientBPHigh = patientBPHigh;
		this.patientBPLow = patientBPLow;
		this.patientTempHigh = patientTempHigh;
		this.patientTempLow = patientTempLow;
		this.patientPulseHigh = patientPulseHigh;
		this.patientPulseLow = patientPulseLow;
		this.patientRespRateHigh = patientRespRateHigh;
		this.patientRespRateLow = patientRespRateLow;
		this.interval = interval;
		
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
	
	//Returns patientTrendData
	public String getPatientTrendData() {
		return "blah";
	}
	
	//Generates all patients vital signs
	//Time Stamp, Blood Pressure, Pulse, Temperature, Respiratory Rate, Alarm type, Alarm Acknowledged
	private void generateVitalSigns(){
	
		//Time stamp
		
		patientHistory[0] = "2001";
				//new Timestamp(timeStamp.getTime()).toString();
		
		//Blood Pressure
		int randomNum = rand.nextInt(patientBPHigh - patientBPLow + 1) + patientBPLow;
		patientBloodPressure = randomNum;
		patientHistory[1] = patientBloodPressure.toString();
		
		//Pulse
		randomNum = rand.nextInt(patientPulseHigh - patientPulseLow + 1) + patientPulseLow;
		patientPulse = randomNum;
		patientHistory[2] = patientPulse.toString();
		
		//Temperature
		randomNum = rand.nextInt(patientTempHigh - patientTempLow + 1) + patientTempLow;
		patientTemp = randomNum;
		patientHistory[3] = patientTemp.toString();
		
		//Respiratory Rate
		randomNum = rand.nextInt(patientRespRateHigh - patientRespRateLow + 1) + patientRespRateLow;
		patientRespRate = randomNum;
		patientHistory[4] = patientRespRate.toString();
		
		writeHistory();
	}
	
	private void writeHistory(){
		for(int x = 0; x <= 5; x++){
		System.out.println(patientHistory[x]);
		}
		System.out.println("--------------------------------------------");
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
		try {
				generateVitalSigns();
				Thread.sleep(interval);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	
	public static void main(String args[]) {
		(new Thread(new Patient("Zach Huenink", "001", "5/17/1991", "7/17/1991", 10000, 150, 100, 98, 60,
				170, 90, 10, 0))).start();
	}
}
