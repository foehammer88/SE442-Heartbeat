import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import java.awt.GridLayout;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javax.swing.border.LineBorder;


public class NurseView extends MonitorView{

	private JFrame frmNurseMonitor;
	private JFrame frmPatientTrend;
	private PatientTrend winPatientTrend;

	JTextPane textPaneBP;
	JTextPane textPaneHR;
	JTextPane textPaneTemp;
	JTextPane textPaneRR;
	
	JPanel panel_AlarmCode;
	
	JRadioButton radioBP;
	JRadioButton radioHR;
	JRadioButton radioTemp;
	JRadioButton radioRR;
	
	private NurseStation nurseStation;
	private NurseView nurseView;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		try {
            // Set System L&F
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} 
		catch (UnsupportedLookAndFeelException e) {
			// handle exception
		}
		catch (ClassNotFoundException e) {
			// handle exception
		}
		catch (InstantiationException e) {
			// handle exception
		}
		catch (IllegalAccessException e) {
			// handle exception
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NurseSideCommunicator nurseComm = new NurseSideCommunicator();
					NurseView window = new NurseView();
					NurseStation ns = new NurseStation(nurseComm, window);
					window.startNurseView(ns);
					window.frmNurseMonitor.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NurseView() {
		nurseView = this;
	}

	public NurseView(NurseStation ns) {
		nurseView = this;
		nurseStation = ns;
		initialize();
	}
	
	public void startNurseView(NurseStation ns){
		nurseStation = ns;
		initialize();
	}
	
	public void showWindow(){
		frmNurseMonitor.setVisible(true);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNurseMonitor = new JFrame();
		frmNurseMonitor.setTitle("Nurse Monitor\r\n");
		frmNurseMonitor.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\AnDev\\git\\SE442-Heartbeat\\Heartbeat\\heartbeat.jpg"));	
		frmNurseMonitor.setBounds(100, 100, 450, 300);
		frmNurseMonitor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmNurseMonitor.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNewPatient = new JMenuItem("New Patient");
		mntmNewPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewPatientDialog newPatient = new NewPatientDialog(nurseView);
				newPatient.setVisible(true);
			}
		});
		mnFile.add(mntmNewPatient);
		
		JMenuItem mntmOpenPatient = new JMenuItem("Open Patient");
		mnFile.add(mntmOpenPatient);
		
		JMenuItem mntmClose = new JMenuItem("Close");
		mnFile.add(mntmClose);
		
		JSeparator separator = new JSeparator();
		mnFile.add(separator);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mnFile.add(mntmSave);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenuItem mntmCopy = new JMenuItem("Copy");
		mnEdit.add(mntmCopy);
		
		JMenuItem mntmPaste = new JMenuItem("Paste");
		mnEdit.add(mntmPaste);
		
		JMenu mnTools = new JMenu("Tools");
		menuBar.add(mnTools);
		
		JMenuItem mntmPatientType = new JMenuItem("Patient Type");
		mnTools.add(mntmPatientType);
		
		JMenuItem mntmDeletePatient = new JMenuItem("Delete Patient");
		mnTools.add(mntmDeletePatient);
		
		JSeparator separator_2 = new JSeparator();
		mnTools.add(separator_2);
		
		JMenuItem mntmDisplayHistory = new JMenuItem("Display History");
		mntmDisplayHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Clicked");
				createTrendWindow();
			}
		});
		mnTools.add(mntmDisplayHistory);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmNurseMonitor.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("New tab", null, panel, null);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JTextPane txtpnPatientName = new JTextPane();
		txtpnPatientName.setEditable(false);
		txtpnPatientName.setText("Patient Name");
		txtpnPatientName.setBorder(BorderFactory.createLineBorder(Color.black));
		panel_1.add(txtpnPatientName);
		
		JTextPane txtpnPatientId = new JTextPane();
		txtpnPatientId.setEditable(false);
		txtpnPatientId.setText("Patient ID");
		txtpnPatientId.setBorder(BorderFactory.createLineBorder(Color.black));
		panel_1.add(txtpnPatientId);
		
		JTextPane txtpnAdmitDate = new JTextPane();
		txtpnAdmitDate.setEditable(false);
		txtpnAdmitDate.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtpnAdmitDate.setText("Admit Date:");
		panel_1.add(txtpnAdmitDate);
		
		JTextPane textPane_4 = new JTextPane();
		textPane_4.setEditable(false);
		textPane_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		textPane_4.setText("11/30/2012");
		panel_1.add(textPane_4);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new GridLayout(0, 3, 0, 0));
		
		JTextPane txtpnNibp = new JTextPane();
		txtpnNibp.setEditable(false);
		txtpnNibp.setText("NIBP:");
		panel_2.add(txtpnNibp);
		
		textPaneBP = new JTextPane();
		textPaneBP.setEditable(false);
		textPaneBP.setText("120/80");
		panel_2.add(textPaneBP);
		
		radioBP = new JRadioButton("");
		radioBP.setEnabled(false);
		radioBP.setSelected(false);
		panel_2.add(radioBP);
		
		JTextPane txtpnPulse = new JTextPane();
		txtpnPulse.setEditable(false);
		txtpnPulse.setText("Pulse:");
		panel_2.add(txtpnPulse);
		
		textPaneHR = new JTextPane();
		textPaneHR.setEditable(false);
		textPaneHR.setText("60");
		panel_2.add(textPaneHR);
		
		radioHR = new JRadioButton("");
		radioHR.setEnabled(false);
		radioHR.setSelected(false);
		panel_2.add(radioHR);
		
		JTextPane txtpnTemp = new JTextPane();
		txtpnTemp.setEditable(false);
		txtpnTemp.setText("Temp:");
		panel_2.add(txtpnTemp);
		
		textPaneTemp = new JTextPane();
		textPaneTemp.setEditable(false);
		textPaneTemp.setText("98.6");
		panel_2.add(textPaneTemp);
		
		radioTemp = new JRadioButton("");
		radioTemp.setEnabled(false);
		radioTemp.setSelected(false);
		panel_2.add(radioTemp);
		
		JTextPane txtpnRespiratoryRate = new JTextPane();
		txtpnRespiratoryRate.setEditable(false);
		txtpnRespiratoryRate.setText("Respiratory rate:");
		panel_2.add(txtpnRespiratoryRate);
		
		textPaneHR = new JTextPane();
		textPaneHR.setEditable(false);
		textPaneHR.setText("30");
		panel_2.add(textPaneHR);
		
		radioRR = new JRadioButton("");
		radioRR.setEnabled(false);
		radioRR.setSelected(false);
		panel_2.add(radioRR);
		
		JTextPane txtpnAlarm = new JTextPane();
		txtpnAlarm.setEditable(false);
		txtpnAlarm.setText("Alarm:");
		panel_2.add(txtpnAlarm);
		
		panel_AlarmCode = new JPanel();
		panel_AlarmCode.setBackground(Color.GREEN);
		panel_2.add(panel_AlarmCode);
		
		JButton btnResetAlarm = new JButton("Reset Alarm");
		panel_2.add(btnResetAlarm);
	}

	public void createTrendWindow(){
		System.out.println("Creating Trend Window");
		winPatientTrend = new PatientTrend(this);
	}
	
	public void addPatient(String name, String type){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date today = Calendar.getInstance().getTime();
		String reportDate = df.format(today);
		
		Random rand = new Random();
		Integer idInt = rand.nextInt(9999) + 1;
		System.out.println(name + ", " + type  + ", " + idInt.toString() +", " + reportDate);
		nurseStation.admitPatient(idInt.toString(), name, type);
		
	}
	
	public void updateVitals(String bp, String hr, String temp, String rr){
		textPaneBP.setText(bp);
		textPaneHR.setText(hr);
		textPaneTemp.setText(temp);
		textPaneRR.setText(rr);
	}
	
	public void setAlarm(String patientName, String vital, String degree){
		if (degree.equals("Red")){
			System.out.println("Got red");
			panel_AlarmCode.setBackground(Color.RED);
		} else if(degree.equals("Yellow")){
			panel_AlarmCode.setBackground(Color.YELLOW);
		}
		panel_AlarmCode.revalidate();
		if (vital.equals("BP")){
			radioBP.setSelected(true);
		} else if (vital.equals("HR")){
			radioHR.setSelected(true);
		} else if (vital.equals("Temp")){
			radioTemp.setSelected(true);
		} else if (vital.equals("RR")){
			radioRR.setSelected(true);
		}
	}

	public void getPatientData(String patientName, PatientTrend patientTrend) {
		// TODO Auto-generated method stub
		nurseStation.getPatientTrendData(patientName, patientTrend);
	}
	
}
