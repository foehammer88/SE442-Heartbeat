import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.NotBoundException;
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
	private String patientType;
	private Date timeStamp = new Date();
	private Integer interval;
	private Integer[] patientHistory = new Integer[7];
	private Random rand = new Random();
	
	/**
	 * Patient Object Constructor
	 * @param patientName - Name of Patient
	 * @param patientId - ID of Patient
	 * @param patientAdmitDate - Admit date of patient 
	 * @param patientDischargeDate - Discharge date of patient 
	 * @param patientType - Determines ranges used and the type of patient
	 * @param interval - Designated interval for generating vital signs
	 */
	public Patient(String patientName, String patientId, String patientAdmitDate, String patientDischargeDate
			, String patientType, Integer interval){ 
		
		this.patientName = patientName; 
		this.patientID = patientId; 
		this.patientAdmitDate = patientAdmitDate; 
		this.patientDischargeDate = patientDischargeDate;
		this.patientType = patientType;
		this.interval = interval;
		patientHistory[5] = 0;
		patientHistory[6] = 0;
		
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
		
		if(patientType == "Adult"){
			
			patientBPHigh = 120;
			patientBPLow = 110;
			patientPulseHigh = 105;
			patientPulseLow = 55;
			patientTempHigh = 100;
			patientTempLow = 97;
			patientRespRateHigh = 20;
			patientRespRateLow = 10;
			
		}else if(patientType == "Child"){
			
			patientBPHigh = 120;
			patientBPLow = 80;
			patientPulseHigh = 110;
			patientPulseLow = 70;
			patientTempHigh = 100;
			patientTempLow = 97;
			patientRespRateHigh = 30;
			patientRespRateLow = 20;
			
		}else if(patientType == "Infant"){
			
			patientBPHigh = 100;
			patientBPLow = 70;
			patientPulseHigh = 140;
			patientPulseLow = 80;
			patientTempHigh = 100;
			patientTempLow = 97;
			patientRespRateHigh = 30;
			patientRespRateLow = 20;
			
		}else if(patientType == "Adolescent"){
			
			patientBPHigh = 120;
			patientBPLow = 110;
			patientPulseHigh = 105;
			patientPulseLow = 55;
			patientTempHigh = 100;
			patientTempLow = 97;
			patientRespRateHigh = 20;
			patientRespRateLow = 10;
		}
	
		//Time stamp
		patientHistory[0] = (int) timeStamp.getTime();
		
		int randomNum = 0; 
		if(patientHistory[5] == 1){
			
			patientBPHigh = 130;
			patientBPLow = 121;
			randomNum = rand.nextInt(patientBPHigh - patientBPLow + 1) + patientBPLow;
			patientBloodPressure = randomNum;
			patientHistory[1] = patientBloodPressure;
			
		}else if(patientHistory[5] == 2){
			patientBPHigh = 140;
			patientBPLow = 131;
			randomNum = rand.nextInt(patientBPHigh - patientBPLow + 1) + patientBPLow;
			patientBloodPressure = randomNum;
			patientHistory[1] = patientBloodPressure;
		}else{
			randomNum = rand.nextInt(patientBPHigh - patientBPLow + 1) + patientBPLow;
			patientBloodPressure = randomNum;
			patientHistory[1] = patientBloodPressure;
			
		}
		
		//Pulse
		randomNum = rand.nextInt(patientPulseHigh - patientPulseLow + 1) + patientPulseLow;
		patientPulse = randomNum;
		patientHistory[2] = patientPulse;
		
		
		//Temperature
		randomNum = rand.nextInt(patientTempHigh - patientTempLow + 1) + patientTempLow;
		patientTemp = randomNum;
		patientHistory[3] = patientTemp;
		
		
		//Respiratory Rate
		randomNum = rand.nextInt(patientRespRateHigh - patientRespRateLow + 1) + patientRespRateLow;
		patientRespRate = randomNum;
		patientHistory[4] = patientRespRate;
		
		
	}
	
	public void generateAlarm(String alarmType){
		if(alarmType == "Yellow"){
			patientHistory[5] = 1;
			try {
				Alarm.tripAlarm("Yellow", alarmType);
			} catch (NotBoundException e) {
				System.out.println("Warning! Found an exception trying to generate alarm.");
				e.printStackTrace();
			}
		}else if(alarmType == "Red"){
			patientHistory[5] = 2;
			try {
				Alarm.tripAlarm("Red", alarmType);
			} catch (NotBoundException e) {
				System.out.println("Warning! Found an exception trying to generate alarm.");
				e.printStackTrace();
			}
		}else if(alarmType == "None"){
			patientHistory[5] = 0;
		}
	}
	
	public void acknowledgeAlarm(String alarm){
		if(alarm == "Yes"){
			patientHistory[6] = 1;
		}else if(alarm == "No"){
			patientHistory[6] = 0;
		}
	}
	
	public Integer getCurrentStats(Integer stat){
		return patientHistory[stat];
	}
	
	private void writeHistory(){
		try {
			FileWriter fWriter = new FileWriter(patientID + ".txt", true);
			BufferedWriter out = new BufferedWriter(fWriter);
			
			for(int x = 0; x < patientHistory.length; x++){
					out.write(patientHistory[x] + " ");
			}
			out.newLine();
			out.close();
			fWriter.close();
			System.out.println("tick");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
		try {
				generateVitalSigns();
				writeHistory();
				Thread.sleep(interval);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	
	public static void main(String args[]) {
		(new Thread(new Patient("Zach Huenink", "001", "5/17/1991", "7/17/1991", "Adult", 10000))).start();
	}
}
