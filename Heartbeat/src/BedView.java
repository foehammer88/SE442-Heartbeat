import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import javax.swing.JSplitPane;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import java.awt.Toolkit;


public class BedView {

	private JFrame frmBedsideMonitor;

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
					BedView window = new BedView();
					window.frmBedsideMonitor.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BedView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBedsideMonitor = new JFrame();
		frmBedsideMonitor.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\AnDev\\git\\SE442-Heartbeat\\Heartbeat\\heartbeat.jpg"));
		frmBedsideMonitor.setTitle("Bedside Monitor\r\n");
		frmBedsideMonitor.setBounds(100, 100, 450, 300);
		frmBedsideMonitor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBedsideMonitor.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		frmBedsideMonitor.getContentPane().add(splitPane, BorderLayout.NORTH);
		
		JTextPane txtpnPatientName = new JTextPane();
		txtpnPatientName.setText("Patient Name");
		splitPane.setLeftComponent(txtpnPatientName);
		
		JTextPane txtpnPatientId = new JTextPane();
		txtpnPatientId.setText("Patient ID");
		splitPane.setRightComponent(txtpnPatientId);
		
		JPanel panel = new JPanel();
		frmBedsideMonitor.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new MigLayout("", "[432px,grow]", "[14.00px][grow][grow][29.00px,grow][68px,grow]"));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, "cell 0 0,grow");
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane_1 = new JSplitPane();
		panel_1.add(splitPane_1);
		
		JTextPane txtpnNibp = new JTextPane();
		txtpnNibp.setText("NIBP:");
		splitPane_1.setLeftComponent(txtpnNibp);
		
		JTextPane textPane = new JTextPane();
		textPane.setText("120/80");
		splitPane_1.setRightComponent(textPane);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, "cell 0 1,grow");
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane_2 = new JSplitPane();
		panel_2.add(splitPane_2);
		
		JTextPane txtpnPulse = new JTextPane();
		txtpnPulse.setText("Pulse:");
		splitPane_2.setLeftComponent(txtpnPulse);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setText("60");
		splitPane_2.setRightComponent(textPane_1);
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3, "cell 0 2,grow");
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane_3 = new JSplitPane();
		panel_3.add(splitPane_3);
		
		JTextPane txtpnTemp = new JTextPane();
		txtpnTemp.setText("Temp:");
		splitPane_3.setLeftComponent(txtpnTemp);
		
		JTextPane textPane_2 = new JTextPane();
		textPane_2.setText("98.6");
		splitPane_3.setRightComponent(textPane_2);
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4, "cell 0 3,grow");
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane_4 = new JSplitPane();
		panel_4.add(splitPane_4);
		
		JTextPane txtpnAlarm = new JTextPane();
		txtpnAlarm.setText("Alarm:");
		splitPane_4.setLeftComponent(txtpnAlarm);
		
		JPanel panel_AlarmCode = new JPanel();
		panel_AlarmCode.setBackground(Color.GREEN);
		splitPane_4.setRightComponent(panel_AlarmCode);
		
		JPanel panel_5 = new JPanel();
		panel.add(panel_5, "cell 0 4,grow");
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JButton btnResetAlarm = new JButton("Reset Alarm");
		panel_5.add(btnResetAlarm, BorderLayout.CENTER);
		
		JMenuBar menuBar = new JMenuBar();
		frmBedsideMonitor.setJMenuBar(menuBar);
		
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
	}

}