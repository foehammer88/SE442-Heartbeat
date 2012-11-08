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
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class PatientTrend {

	private NurseView nurseView;
	private PatientTrend patientTrend;
	private JFrame frmPatientTrend;

	private JList patientList;
    private DefaultListModel listModel;
    
    private ArrayList<Integer> dataBP;
    private ArrayList<Integer> dataHR;
    private ArrayList<Integer> dataTemp;
    private ArrayList<Integer> dataRR;
    
    private JPanel panelGraphs;
    
    private JLabel lblBP;
    private JLabel lblHR;
    private JLabel lblTemp;
    private JLabel lblRR;
    
    private GraphPanel gPanelBP;
    private GraphPanel gPanelHR;
    private GraphPanel gPanelTemp;
    private GraphPanel gPanelRR;
    
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
					//PatientTrend window = new PatientTrend();
					//window.frmPatientTrend.setVisible(true);
					//window.simulateGraphs();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PatientTrend(NurseView nv) {
		patientTrend = this;
		nurseView = nv;
		listModel = new DefaultListModel();
        listModel.addElement("New Patient");
        
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
	
	public PatientTrend(NurseView nv, DefaultListModel patientList) {
		patientTrend = this;
		listModel = patientList;
        nurseView = nv;
        
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
		patientTrend = this;
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
		patientList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println(patientList.getSelectedValue());
				nurseView.getPatientData(patientList.getSelectedValue().toString(), patientTrend);
				//simulateGraphs();
			}
		});
		patientList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//patientList.setSelectedIndex(0);
		//patientList.addListSelectionListener(this);
		patientList.setVisibleRowCount(5);
		JScrollPane listScrollPane = new JScrollPane(patientList);
		panel.add(listScrollPane);
		
		JLabel lblPatients = new JLabel("Patients");
		listScrollPane.setColumnHeaderView(lblPatients);
		
		panelGraphs = new JPanel();
		frmPatientTrend.getContentPane().add(panelGraphs, BorderLayout.CENTER);
		panelGraphs.setLayout(new GridLayout(2, 2, 0, 0));
		
		
		gPanelBP = new GraphPanel();
		gPanelBP.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		gPanelBP.setBackground(Color.WHITE);
		lblBP = new JLabel("Systolic Blood Pressure");
		lblBP.setForeground(Color.WHITE);
		gPanelBP.add(lblBP);
		panelGraphs.add(gPanelBP);
		
		
		gPanelHR = new GraphPanel();
		gPanelHR.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lblHR = new JLabel("Heart Rate");
		lblHR.setForeground(Color.WHITE);
		gPanelHR.add(lblHR);
		panelGraphs.add(gPanelHR);
		
		
		gPanelTemp = new GraphPanel();
		gPanelTemp.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lblTemp = new JLabel("Temperature");
		lblTemp.setForeground(Color.WHITE);
		gPanelTemp.add(lblTemp);
		panelGraphs.add(gPanelTemp);
		
		
		gPanelRR = new GraphPanel();
		gPanelRR.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lblRR = new JLabel("Respiratory Rate");
		lblRR.setForeground(Color.WHITE);
		gPanelRR.add(lblRR);
		panelGraphs.add(gPanelRR);
	}
	
	public void drawGraphs(ArrayList<Integer> dBP, ArrayList<Integer> dHR, ArrayList<Integer> dTemp,
			ArrayList<Integer> dRR){
		dataBP = dBP;
	    dataHR = dHR;
	    dataTemp = dTemp;
	    dataRR = dRR;

	    gPanelBP.setGraph(dataBP, "BP");
	    gPanelBP.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    gPanelHR.setGraph(dataHR, "HR");
	    gPanelHR.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    gPanelTemp.setGraph(dataTemp, "Temp");
	    gPanelTemp.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    gPanelRR.setGraph(dataRR, "RR");
	    gPanelRR.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	}
	
	public void simulateGraphs(){
		ArrayList<Integer> dataBP = new ArrayList<Integer>();
		Random rand = new Random();
		for(int i = 0; i < 100; i++){
			dataBP.add(rand.nextInt(200 - 60 + 1) + 60);
		}
		ArrayList<Integer> dataHR = new ArrayList<Integer>();
		for(int i = 0; i < 10; i++){
			dataHR.add(rand.nextInt(200 - 0 + 1) + 0);
		}
		ArrayList<Integer> dataTemp = new ArrayList<Integer>();
		for(int i = 0; i < 10; i++){
			dataTemp.add(rand.nextInt(120 - 50 + 1) + 50);
		}
		ArrayList<Integer> dataRR = new ArrayList<Integer>();
		for(int i = 0; i < 10; i++){
			dataRR.add(rand.nextInt(150 - 0 + 1) + 0);
		}
		drawGraphs(dataBP, dataHR, dataTemp, dataRR);
	}
}
