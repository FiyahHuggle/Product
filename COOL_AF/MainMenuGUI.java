package GUILayer;

import CtrLayer.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JList;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JComboBox;
import java.awt.BorderLayout;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.SystemColor;

/**
 * Main Menu GUI
 *
 * @author Pien van den Abeele
 * @version 20.01.2019
 */
public class MainMenuGUI {
	private SaleGUI saleGUI = new SaleGUI();
	private ProductGUI productGUI = new ProductGUI();
	private static PersonCtr perCtr = new PersonCtr();
	private static ProductCtr proCtr = new ProductCtr();
	private static JFrame MainMenu;
	public static int empID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenuGUI window = new MainMenuGUI();
					window.MainMenu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the application.
	 */
	public MainMenuGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		MainMenu = new JFrame();
		MainMenu.getContentPane().setBackground(Color.WHITE);
		MainMenu.setResizable(false);
		MainMenu.setTitle("SaleSystem/MainMenu");
		MainMenu.setBounds(100, 100, 600, 450);
		MainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainMenu.getContentPane().setLayout(null);
		
		String empIDtoString = Integer.toString(empID);
		JLabel lblNewLabel = new JLabel(empIDtoString);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel.setBounds(94, 14, 122, 16);
		MainMenu.getContentPane().add(lblNewLabel);
		
		JButton btnLogOut = new JButton("Log out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				WelcomeGUI.main(null);
				MainMenu.dispose();
			}
		});
		btnLogOut.setBounds(495, 387, 89, 23);
		MainMenu.getContentPane().add(btnLogOut);
		
		JButton btnStartSale = new JButton("Start Sale");
		btnStartSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StartSaleGUI.main(null);
				MainMenu.setVisible(false);
			}
		});
		btnStartSale.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnStartSale.setBounds(10, 115, 239, 92);
		MainMenu.getContentPane().add(btnStartSale);
		
		JButton btnNewButton = new JButton("Customer Management");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerGUI.main(null);
				MainMenu.setVisible(false);
			}
		});
		btnNewButton.setBounds(259, 241, 155, 47);
		MainMenu.getContentPane().add(btnNewButton);
		
		JButton btnProductManagement = new JButton("Product Management");
		btnProductManagement.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnProductManagement.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ProductGUI.main(null);
					MainMenu.setVisible(false);
			}			
		});
		btnProductManagement.setBounds(424, 241, 155, 47);
		MainMenu.getContentPane().add(btnProductManagement);
		
		JButton btnEmployeeManagement = new JButton("Employee Management");
		btnEmployeeManagement.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnEmployeeManagement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeeGUI.main(null);
			}
		});
		btnEmployeeManagement.setBounds(259, 299, 155, 47);
		MainMenu.getContentPane().add(btnEmployeeManagement);
		
		JButton btnSaleManagement = new JButton("Sale Management");
		btnSaleManagement.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnSaleManagement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaleGUI.main(null);
				MainMenu.setVisible(false);
				
			}
		});
		btnSaleManagement.setBounds(424, 299, 155, 47);
		MainMenu.getContentPane().add(btnSaleManagement);
		
		JLabel lblNewLabel_1 = new JLabel("Vestbjerg Byggecenter");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 27));
		lblNewLabel_1.setBounds(259, 127, 325, 52);
		MainMenu.getContentPane().add(lblNewLabel_1);
		
		JButton btnHelp = new JButton("About ");
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AboutGUI.main(null);
			}
		});
		
		btnHelp.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnHelp.setBounds(495, 9, 89, 23);
		MainMenu.getContentPane().add(btnHelp);
		
		JLabel lblEmployeeName = new JLabel("Employee name:");
		lblEmployeeName.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblEmployeeName.setBounds(10, 41, 122, 17);
		MainMenu.getContentPane().add(lblEmployeeName);
		
		JLabel lblEmployeeId = new JLabel("Employee ID:");
		lblEmployeeId.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblEmployeeId.setBounds(10, 14, 122, 17);
		MainMenu.getContentPane().add(lblEmployeeId);
		
		String empName = perCtr.findEmployee(empID).getName();
		JLabel label = new JLabel(empName);
		label.setFont(new Font("Tahoma", Font.BOLD, 10));
		label.setBounds(95, 41, 229, 17);
		MainMenu.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("");
		Image icon1 = new ImageIcon(this.getClass().getResource("/No.png")).getImage();
		label_1.setIcon(new ImageIcon(icon1));
		label_1.setBounds(20, 218, 229, 192);
		MainMenu.getContentPane().add(label_1);
	}

	public static void visibilityTrue() {
		MainMenu.setVisible(true);
	}
	
	public static int getEmpID() {
		return empID;
	}

	public static void setEmpID(int i) {
		empID = i;
		
	}
}
