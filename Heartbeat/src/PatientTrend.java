import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.BorderLayout;
import javax.swing.JList;
import java.awt.Dimension;
import javax.swing.JTextPane;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;


public class PatientTrend {

	private JFrame frmPatientTrend;

	private JList patientList;
    private DefaultListModel listModel;
    
    private ArrayList<Integer> dataBP;
    private ArrayList<Integer> dataHR;
    private ArrayList<Integer> dataTemp;
    private ArrayList<Integer> dataRR;
    
    GraphPanel gPanelBP;
    GraphPanel gPanelHR;
    GraphPanel gPanelTemp;
    GraphPanel gPanelRR;
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PatientTrend window = new PatientTrend();
					window.frmPatientTrend.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PatientTrend() {
		listModel = new DefaultListModel();
        listModel.addElement("John Doe");
        listModel.addElement("John Smith");
        listModel.addElement("Patrick Ganson");
        
        EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmPatientTrend.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		initialize();
	}
	
	/**
	 * Create the application.
	 */
	public PatientTrend(ArrayList<Integer> dBP, ArrayList<Integer> dHR, ArrayList<Integer> dTemp,
			ArrayList<Integer> dRR, DefaultListModel patientList) {
		listModel = patientList;
        
        dataBP = dBP;
        dataHR = dHR;
        dataTemp = dTemp;
        dataRR = dRR;
        
        EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmPatientTrend.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPatientTrend = new JFrame();
		frmPatientTrend.setTitle("Patient Trend");
		frmPatientTrend.setBounds(300, 150, 1000, 750);
		frmPatientTrend.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmPatientTrend.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNewPatient = new JMenuItem("New Patient");
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
		mnTools.add(mntmDisplayHistory);
		
		JPanel panel = new JPanel();
		frmPatientTrend.getContentPane().add(panel, BorderLayout.WEST);
		
		
		patientList = new JList(listModel);
		patientList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		patientList.setSelectedIndex(0);
		//patientList.addListSelectionListener(this);
		patientList.setVisibleRowCount(5);
		JScrollPane listScrollPane = new JScrollPane(patientList);
		panel.add(listScrollPane);
		
		JLabel lblPatients = new JLabel("Patients");
		listScrollPane.setColumnHeaderView(lblPatients);
		
		JPanel panel_1 = new JPanel();
		frmPatientTrend.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(2, 2, 0, 0));
		
		ArrayList<Integer> dataBP = new ArrayList<Integer>();
		Random rand = new Random();
		for(int i = 0; i < 100; i++){
			dataBP.add(rand.nextInt(200 - 60 + 1) + 60);
		}
		gPanelBP = new GraphPanel(dataBP, "BP");
		gPanelBP.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		gPanelBP.setBackground(Color.WHITE);
		JLabel lblSystolic = new JLabel("Systolic Blood Pressure");
		lblSystolic.setForeground(Color.WHITE);
		gPanelBP.add(lblSystolic);
		panel_1.add(gPanelBP);
		
		ArrayList<Integer> dataHR = new ArrayList<Integer>();
		for(int i = 0; i < 10; i++){
			dataHR.add(rand.nextInt(200 - 0 + 1) + 0);
		}
		gPanelHR = new GraphPanel(dataHR, "HR");
		gPanelHR.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		JLabel lblHR = new JLabel("Heart Rate");
		lblHR.setForeground(Color.WHITE);
		gPanelHR.add(lblHR);
		panel_1.add(gPanelHR);
		
		ArrayList<Integer> dataTemp = new ArrayList<Integer>();
		for(int i = 0; i < 10; i++){
			dataTemp.add(rand.nextInt(120 - 50 + 1) + 50);
		}
		gPanelTemp = new GraphPanel(dataTemp, "Temp");
		gPanelTemp.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		JLabel lblTemp = new JLabel("Temperature");
		lblTemp.setForeground(Color.WHITE);
		gPanelTemp.add(lblTemp);
		panel_1.add(gPanelTemp);
		
		ArrayList<Integer> dataRR = new ArrayList<Integer>();
		for(int i = 0; i < 10; i++){
			dataRR.add(rand.nextInt(150 - 0 + 1) + 0);
		}
		gPanelRR = new GraphPanel(dataRR, "RR");
		gPanelRR.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		JLabel lblRR = new JLabel("Respiratory Rate");
		lblRR.setForeground(Color.WHITE);
		gPanelRR.add(lblRR);
		panel_1.add(gPanelRR);
	}
	
	public void drawGraphs(ArrayList<Integer> dBP, ArrayList<Integer> dHR, ArrayList<Integer> dTemp,
			ArrayList<Integer> dRR){
		dataBP = dBP;
	    dataHR = dHR;
	    dataTemp = dTemp;
	    dataRR = dRR;
	    
	}
}
