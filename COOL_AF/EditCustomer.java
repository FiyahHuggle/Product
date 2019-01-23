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
import ModelLayer.Customer;

import javax.swing.SwingConstants;
import java.awt.Toolkit;

/**
 * The Edit Customer class. Takes the selected customer from CustomerGUI, enters their information, and then allows the user to alter it.
 *
 * @author Steven Teglman
 * @version 20.01.2019
 */
public class EditCustomer extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JDialog dialog;
	private JTextField textFieldName;
	private JTextField textFieldPhone;
	private JTextField textFieldPostal;
	private static String phone;
	private static Customer c;


	/**
	 * Launch the application.
	 */
	public static void main(String p) {
		try {
			PersonCtr pCtr = new PersonCtr();
			phone = p;
			c=pCtr.findCustomer(phone);
			EditCustomer dialog = new EditCustomer();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setModalityType(DEFAULT_MODALITY_TYPE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EditCustomer() {
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
			JLabel lblCreateNewCustomer = new JLabel("Edit " + pCtr.findCustomer(phone).getName()+"'s Information");
			lblCreateNewCustomer.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblCreateNewCustomer.setBounds(10, 11, 164, 14);
			contentPanel.add(lblCreateNewCustomer);
		}
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 52, 123, 14);
		contentPanel.add(lblName);
		
		textFieldName = new JTextField();
		textFieldName.setText(c.getName());//TODO 
		textFieldName.setBackground(Color.LIGHT_GRAY);
		textFieldName.setBounds(143, 50, 187, 20);
		contentPanel.add(textFieldName);
		textFieldName.setColumns(10);
		
		textFieldPhone = new JTextField();
		textFieldPhone.setText(c.getPhone());
		textFieldPhone.setBackground(Color.LIGHT_GRAY);
		textFieldPhone.setColumns(10);
		textFieldPhone.setBounds(143, 81, 187, 20);
		contentPanel.add(textFieldPhone);
		
		JLabel lblPostalCode = new JLabel("Phone Number");
		lblPostalCode.setBounds(10, 80, 123, 14);
		contentPanel.add(lblPostalCode);
		
		textFieldPostal = new JTextField();
		textFieldPostal.setText(c.getPostalCode());
		textFieldPostal.setBackground(Color.LIGHT_GRAY);
		textFieldPostal.setColumns(10);
		textFieldPostal.setBounds(143, 112, 187, 20);
		contentPanel.add(textFieldPostal);
		
		JLabel lblPhoneNumber = new JLabel("Postal Code");
		lblPhoneNumber.setBounds(10, 111, 123, 14);
		contentPanel.add(lblPhoneNumber);
		
		JLabel lblWarningEmpy = new JLabel("*Please fill all fields");
		lblWarningEmpy.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblWarningEmpy.setForeground(Color.RED);
		lblWarningEmpy.setBounds(143, 143, 164, 14);
		lblWarningEmpy.setVisible(false);
		contentPanel.add(lblWarningEmpy);
		
		JLabel lblWarningPhone = new JLabel("<html>"+"*Phone number already in system"+"</html>");
		lblWarningPhone.setForeground(Color.RED);
		lblWarningPhone.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblWarningPhone.setVerticalAlignment(SwingConstants.TOP);
		lblWarningPhone.setBounds(340, 61, 70, 71);
		lblWarningPhone.setVisible(false);
		contentPanel.add(lblWarningPhone);
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
						lblWarningPhone.setVisible(false);
						if(textFieldName.getText().equals("")||textFieldPhone.getText().equals("")||textFieldPostal.getText().equals("")) {
							lblWarningEmpy.setVisible(true);
							return;
						}
						if(!textFieldPhone.getText().equals(phone)) {
							System.out.println("Phone: " +phone);
							System.out.println("TextFieldPhone: " +textFieldPhone.getText());
							
							if(pCtr.findCustomer(textFieldPhone.getText()) != null) {
								lblWarningPhone.setVisible(true);
								return;
							}
							
						}
						else {
							pCtr.updateNameCustomer(phone, textFieldName.getText());
							pCtr.updatePhoneCustomer(phone, textFieldPhone.getText());
							phone = textFieldPhone.getText();
							pCtr.updatePostalCodeCustomer(phone, textFieldPostal.getText());
							c = pCtr.findCustomer(phone);
							JOptionPane confirm = new JOptionPane();
							JOptionPane.showMessageDialog(confirm, c.getName() + " Has been edited!", "Customer Edited", JOptionPane.INFORMATION_MESSAGE);
							CustomerGUI.updateTable();
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
