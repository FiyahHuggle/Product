package GUILayer;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dialog.ModalityType;
import CtrLayer.PersonCtr;
import ModelLayer.Employee;

import javax.swing.SwingConstants;
import java.awt.Toolkit;

/**
 * The Edit Employee class. Takes the selected employee from EmployeeGUI, enters their information, and then allows the user to alter it.
 *
 * @author Steven Teglman
 * @version 20.01.2019
 */
public class EditEmployee extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JDialog dialog;
	private JTextField textFieldName;
	private JTextField textFieldPostal;
	private static int employeeID;
	private static Employee e;
	/**
	 * Launch the application.
	 */
	public static void main(int ID) {
		try {
			PersonCtr pCtr = new PersonCtr();
			employeeID = ID;
			e=pCtr.findEmployee(employeeID);
			EditEmployee dialog = new EditEmployee();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EditEmployee() {
		setModalityType(ModalityType.APPLICATION_MODAL);
		setBackground(Color.WHITE);
		setBounds(100, 100, 426, 240);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			PersonCtr pCtr = new PersonCtr();
			JLabel lblCreateNewCustomer = new JLabel("Edit " + pCtr.findEmployee(employeeID).getName()+"'s Information");
			lblCreateNewCustomer.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblCreateNewCustomer.setBounds(10, 11, 297, 14);
			contentPanel.add(lblCreateNewCustomer);
		}
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 52, 123, 14);
		contentPanel.add(lblName);
		
		textFieldName = new JTextField();
		textFieldName.setText(e.getName());//TODO 
		textFieldName.setBackground(Color.LIGHT_GRAY);
		textFieldName.setBounds(143, 50, 187, 20);
		contentPanel.add(textFieldName);
		textFieldName.setColumns(10);
		
		textFieldPostal = new JTextField();
		textFieldPostal.setText(e.getPostalCode());
		textFieldPostal.setBackground(Color.LIGHT_GRAY);
		textFieldPostal.setColumns(10);
		textFieldPostal.setBounds(143, 84, 187, 20);
		contentPanel.add(textFieldPostal);
		
		JLabel lblPhoneNumber = new JLabel("Postal Code");
		lblPhoneNumber.setBounds(10, 87, 123, 14);
		contentPanel.add(lblPhoneNumber);
		
		JLabel lblWarningEmpy = new JLabel("*Please fill all fields");
		lblWarningEmpy.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblWarningEmpy.setForeground(Color.RED);
		lblWarningEmpy.setBounds(143, 143, 164, 14);
		lblWarningEmpy.setVisible(false);
		contentPanel.add(lblWarningEmpy);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						PersonCtr pCtr = new PersonCtr();
						lblWarningEmpy.setVisible(false);
						if(textFieldName.getText().equals("")||textFieldPostal.getText().equals("")) {
							lblWarningEmpy.setVisible(true);
							return;
						}	
						else {
							pCtr.updateNameEmployee(employeeID, textFieldName.getText());
							pCtr.updatePostalCodeEmployee(employeeID, textFieldPostal.getText());
							JOptionPane confirm = new JOptionPane();
							JOptionPane.showMessageDialog(confirm, e.getName() + " Has been edited!", "Employee Edited", JOptionPane.INFORMATION_MESSAGE);
							EmployeeGUI.updateTable();
							dispose();
							//TODO Create a confirmation message
							//TODO refresh the table in CustomerGUI
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
