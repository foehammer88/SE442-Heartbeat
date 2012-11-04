import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import net.miginfocom.swing.MigLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class NewPatientDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;

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
		try {
			NewPatientDialog dialog = new NewPatientDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public NewPatientDialog() {
		final NewPatientDialog dialog = this;
		setSize(new Dimension(400, 50));
		setTitle("Add New Patient");
		setBounds(500, 300, 450, 200);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JLabel lblEnterInInformation = new JLabel("Enter in information on a newly admitted patient.");
			contentPanel.add(lblEnterInInformation, BorderLayout.NORTH);
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new MigLayout("", "[422px]", "[32px][32px]"));
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1, "cell 0 0,growx,aligny top");
				panel_1.setLayout(new FlowLayout(FlowLayout.CENTER));
				{
					JLabel label = new JLabel("Patient Name:");
					panel_1.add(label);
				}
				{
					textField = new JTextField();
					textField.setText("");
					textField.setColumns(15);
					panel_1.add(textField);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1, "cell 0 1,growx,aligny top");
				{
					JLabel label = new JLabel("Patient Type:");
					panel_1.add(label);
				}
				{
					JComboBox comboBox = new JComboBox();
					comboBox.addItem("Adult");
					comboBox.addItem("Adolescent");
					comboBox.addItem("Child");
					comboBox.addItem("Infant");
					panel_1.add(comboBox);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dialog.setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
