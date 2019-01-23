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
 * The Create Employee GUI. A pop-up dialog for creating one employee
 *
 * @author Steven Teglman
 * @version 20.01.2019
 */
public class CreateCustomerGUI extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JDialog dialog;
	private JTextField textFieldName;
	private static JTextField textFieldPhone;
	private JTextField textFieldPostal;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CreateCustomerGUI dialog = new CreateCustomerGUI();
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
	public CreateCustomerGUI() {
		//TODO Create title for the frame
		setModalityType(ModalityType.APPLICATION_MODAL);
		setBackground(Color.WHITE);
		setBounds(100, 100, 426, 240);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblCreateNewCustomer = new JLabel("Create New Customer");
			lblCreateNewCustomer.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblCreateNewCustomer.setBounds(10, 11, 164, 14);
			contentPanel.add(lblCreateNewCustomer);
		}
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 52, 123, 14);
		contentPanel.add(lblName);
		
		textFieldName = new JTextField();
		textFieldName.setBackground(Color.LIGHT_GRAY);
		textFieldName.setBounds(143, 50, 187, 20);
		contentPanel.add(textFieldName);
		textFieldName.setColumns(10);
		
		setTextFieldPhone(new JTextField());
		getTextFieldPhone().setBackground(Color.LIGHT_GRAY);
		getTextFieldPhone().setColumns(10);
		getTextFieldPhone().setBounds(143, 81, 187, 20);
		contentPanel.add(getTextFieldPhone());
		
		JLabel lblPostalCode = new JLabel("Phone Number");
		lblPostalCode.setBounds(10, 80, 123, 14);
		contentPanel.add(lblPostalCode);
		
		textFieldPostal = new JTextField();
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
						if(textFieldName.getText().equals("")||getTextFieldPhone().getText().equals("")||textFieldPostal.getText().equals("")) {
							lblWarningEmpy.setVisible(true);
							return;
						}
						if(pCtr.findCustomer(getTextFieldPhone().getText()) != null) {
							lblWarningPhone.setVisible(true);
							return;
						}
						else {
							Customer c = pCtr.createCustomer(textFieldName.getText(), textFieldPostal.getText(), getTextFieldPhone().getText());
							JOptionPane confirm = new JOptionPane();
							JOptionPane.showMessageDialog(confirm, c.getName() + " was Created", "Customer Created", JOptionPane.INFORMATION_MESSAGE);
							dispose();
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

	public static JTextField getTextFieldPhone() {
		return textFieldPhone;
	}

	public void setTextFieldPhone(JTextField textFieldPhone) {
		this.textFieldPhone = textFieldPhone;
	}
}
