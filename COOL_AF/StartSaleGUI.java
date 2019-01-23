package GUILayer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JTextField;

import CtrLayer.*;
import ModelLayer.Sale;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

/**
 * Start Sale GUI
 *
 * @author Pien van den Abeele
 * @version 20.01.2019
 */
public class StartSaleGUI {

	private JFrame frame;
	private JTextField textField;
	private static PersonCtr perCtr = new PersonCtr();
	private static SaleCtr saleCtr = new SaleCtr();
	private String phone;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel lblCustomerDoesNot;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartSaleGUI window = new StartSaleGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StartSaleGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setLayout(null);
		label_3 = new JLabel(MainMenuGUI.empID + "");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 10));
		label_3.setBounds(112, 13, 122, 17);
		frame.getContentPane().add(label_3);
		
		textField = new JTextField();
		textField.setBounds(125, 107, 165, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblPleaseEnterCustomer = new JLabel("Please Enter Customer Phone Number");
		lblPleaseEnterCustomer.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPleaseEnterCustomer.setBounds(101, 76, 250, 20);
		frame.getContentPane().add(lblPleaseEnterCustomer);
		
		label = new JLabel("Employee name:");
		label.setFont(new Font("Tahoma", Font.BOLD, 10));
		label.setBounds(10, 33, 122, 17);
		frame.getContentPane().add(label);
		
		label_1 = new JLabel("Employee ID:");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		label_1.setBounds(10, 13, 122, 17);
		frame.getContentPane().add(label_1);
		
		String empName = perCtr.findEmployee(MainMenuGUI.getEmpID()).getName();
		label_2 = new JLabel(empName);
		label_2.setFont(new Font("Tahoma", Font.BOLD, 10));
		label_2.setBounds(10, 48, 204, 17);
		frame.getContentPane().add(label_2);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (perCtr.findCustomer(textField.getText()) == null) {
					lblCustomerDoesNot.setVisible(true);
					return;
				}
				else {
				Sale s = saleCtr.createSale(MainMenuGUI.getEmpID(), phone);
				SaleGUI.main(null);
				JOptionPane confirm = new JOptionPane();
				JOptionPane.showMessageDialog(confirm, "Sale was Created. Sale ID: " + s.getSaleID(), "Sale Created", JOptionPane.INFORMATION_MESSAGE);
				String id = Integer.toString(s.getSaleID());
				System.out.print(id);
				SaleGUI.setTextField(id);
				SaleGUI.clickButton();
				frame.dispose();
				}
			}
		});
		btnConfirm.setBounds(163, 138, 89, 23);
		frame.getContentPane().add(btnConfirm);
		
		JButton btnBackToMain = new JButton("Back to Main Menu");
		btnBackToMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainMenuGUI.visibilityTrue();
				frame.dispose();
			}
		});
		btnBackToMain.setBounds(268, 10, 156, 23);
		frame.getContentPane().add(btnBackToMain);
		
		JButton btnCreateNewCustomer = new JButton("Create new Customer");
		btnCreateNewCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateCustomerGUI.main(null);
				try {
					phone = CreateCustomerGUI.getTextFieldPhone().getText();
					textField.setText(phone);
				}
				catch (Exception e1) {
					return;
				}
			}
		});
		btnCreateNewCustomer.setBounds(212, 227, 212, 23);
		frame.getContentPane().add(btnCreateNewCustomer);
		
		lblCustomerDoesNot = new JLabel("Customer does not Exist");
		lblCustomerDoesNot.setVisible(false);
		lblCustomerDoesNot.setForeground(Color.RED);
		lblCustomerDoesNot.setBounds(300, 110, 124, 14);
		frame.getContentPane().add(lblCustomerDoesNot);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
