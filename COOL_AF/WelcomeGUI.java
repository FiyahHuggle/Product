package GUILayer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;


import javax.swing.JTextField;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import java.awt.Color;

import CtrLayer.*;
import java.awt.Toolkit;
import javax.swing.JToolBar;

public class WelcomeGUI {

	private static PersonCtr perCtr = new PersonCtr();
	private static ProductCtr proCtr = new ProductCtr();
	private static SaleCtr saleCtr = new SaleCtr();
	private JFrame frmSalesystemlogIn;
	private JTextField textField;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WelcomeGUI window = new WelcomeGUI();
					window.frmSalesystemlogIn.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WelcomeGUI() {
		perCtr.createEmployee("Steven Jack Teglman", "9000");
		perCtr.createCustomer("John Smith", "9001", "12345678");
		perCtr.createCustomer("TestCust2", "9002", "87654321");
		perCtr.createCustomer("TestCust3", "9003", "13579246");
		perCtr.createEmployee("Dora Grgurev", "9200");
		perCtr.createEmployee("Pien van den Abeele", "9220");
		perCtr.createEmployee("Jens Henrik Vollesen", "9222");
		proCtr.createProduct("Blue Door", 95, 12.99);
		proCtr.createProduct("Metal Table", 25, 3.99);
		proCtr.createProduct("Hammer", 45, 95.99);
		saleCtr.createSale(0, "12345678");
		saleCtr.createSale(1, "87654321");
		saleCtr.createSale(2, "13579246");
		saleCtr.createSale(3, "12345678");
		saleCtr.addOrderLine(0, 2, 2);
		saleCtr.addOrderLine(2, 1, 2);
		saleCtr.addOrderLine(3, 0, 2);
		saleCtr.addOrderLine(2, 2, 2);
		saleCtr.addOrderLine(0, 1, 3);
		saleCtr.addOrderLine(1, 0, 4);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSalesystemlogIn = new JFrame();
		frmSalesystemlogIn.getContentPane().setBackground(Color.WHITE);
		frmSalesystemlogIn.setTitle("SaleSystem/Log In");
		frmSalesystemlogIn.setResizable(false);
		frmSalesystemlogIn.setBounds(100, 100, 450, 300);
		frmSalesystemlogIn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSalesystemlogIn.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 414, 239);
		frmSalesystemlogIn.getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblWelcome = new JLabel("Welcome!");
		lblWelcome.setForeground(Color.BLACK);
		lblWelcome.setBackground(Color.WHITE);
		lblWelcome.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblWelcome, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		JLabel lblEmployeeId = new JLabel("Employee ID");
		lblEmployeeId.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEmployeeId.setBounds(90, 37, 157, 14);
		panel_1.add(lblEmployeeId);
		
		JLabel noEmp = new JLabel("Employee not found");
		noEmp.setForeground(Color.RED);
		noEmp.setBounds(257, 62, 147, 20);
		panel_1.add(noEmp);
		noEmp.setVisible(false);
		
		JLabel label = new JLabel("Invalid Entry");
		label.setForeground(Color.RED);
		label.setBounds(257, 62, 147, 20);
		panel_1.add(label);
		label.setVisible(false);
		
		textField = new JTextField();
		textField.setBounds(90, 62, 157, 20);
		panel_1.add(textField);
		textField.setColumns(10);
		Action logIn = new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				label.setVisible(false);
				noEmp.setVisible(false);
				int i = 1000000;
				String t = textField.getText();
				try {
					i = Integer.parseInt(t);
				}
				
				catch (NumberFormatException e){
					label.setVisible(true);
					return;
				}
				
				if (t.equals("")) {
					label.setVisible(true);
				}
				
				else if (perCtr.findEmployee(i) == null) {
					noEmp.setVisible(true);
				}
				
				else {
					
					int answer = JOptionPane.showConfirmDialog(frmSalesystemlogIn, "Employee name: " +perCtr.getEmployeeName(i)+"\nEmployee ID:        "+i+"\nIs this you?", "Is this you?", JOptionPane.YES_NO_OPTION);
					
					if (answer == JOptionPane.YES_OPTION) {
						MainMenuGUI.main(null);
						MainMenuGUI.setEmpID(i);
						frmSalesystemlogIn.dispose();
					}				
				}
			}
		};
		textField.addActionListener(logIn);
		
		JButton btnNewButton = new JButton("Log In");
		btnNewButton.setBounds(90, 93, 139, 23);
		panel_1.add(btnNewButton);
		btnNewButton.addActionListener(logIn);
		
		JButton btnCreateNewEmployee = new JButton("Create New Employee");
		btnCreateNewEmployee.setBounds(10, 185, 169, 23);
		panel_1.add(btnCreateNewEmployee);
		Action nEmp = new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CreateEmployeeGUI.main(null);
				
			}
		};
		btnCreateNewEmployee.addActionListener(nEmp);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.setBounds(315, 185, 89, 23);
		panel_1.add(btnQuit);
		
		JLabel lblEstbjergByggecenterAs_1 = new JLabel("Vestbjerg Byggecenter A/S");
		lblEstbjergByggecenterAs_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEstbjergByggecenterAs_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstbjergByggecenterAs_1.setBounds(102, 0, 222, 23);
		panel_1.add(lblEstbjergByggecenterAs_1);
		Action quit = new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frmSalesystemlogIn.dispose();
				
			}
		};
		btnQuit.addActionListener(quit);
		
		
	}
}
