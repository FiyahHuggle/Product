package GUILayer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTable;

import CtrLayer.*;
import ModelLayer.Customer;
import ModelLayer.Employee;
import ModelLayer.OrderLine;
import ModelLayer.Person;
import ModelLayer.Product;
import ModelLayer.Sale;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.Console;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.ScrollPaneConstants;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
/**
 * The Employee GUI Class. Responsible for managing the employees. Class CreateEmployeeGUI and EditEmployee when interacting with Employees.
 *
 * @author Steven Teglman
 * @version 20.01.2019
 */
public class EmployeeGUI {
	private PersonCtr pCtr =new PersonCtr();
	//public static JFrame frame;
	private static JTable table_1;
	private JTextArea textArea;
	private JTextArea textArea_1;
	public static TableModel tm;
	private JTextArea textArea_2;
	ProductCtr prodCtr = new ProductCtr();
	SaleCtr sCtr = new SaleCtr();
	private DecimalFormat df2 = new DecimalFormat(".##");

	private static JFrame frmSalesystememployeeManagement;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeGUI window = new EmployeeGUI();
					window.frmSalesystememployeeManagement.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EmployeeGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSalesystememployeeManagement = new JFrame();
		frmSalesystememployeeManagement.setResizable(false);
		frmSalesystememployeeManagement.setTitle("SaleSystem/Employee Management");
		frmSalesystememployeeManagement.getContentPane().setBackground(Color.WHITE);
		frmSalesystememployeeManagement.setBackground(Color.WHITE);
		frmSalesystememployeeManagement.setBounds(100, 100, 600, 450);
		frmSalesystememployeeManagement.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSalesystememployeeManagement.getContentPane().setLayout(null);
		
		JLabel lblEmployeeNo = new JLabel("New label");
		lblEmployeeNo.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblEmployeeNo.setBounds(10, 11, 179, 14);
		lblEmployeeNo.setText("Emp ID: " + MainMenuGUI.empID);
		frmSalesystememployeeManagement.getContentPane().add(lblEmployeeNo);
		
		JLabel lblEmployeeName = new JLabel("New label");
		lblEmployeeName.setVerticalAlignment(SwingConstants.TOP);
		lblEmployeeName.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblEmployeeName.setBounds(10, 33, 179, 42);
		lblEmployeeName.setText("<html>Employee Name: <br/>" + pCtr.getEmployeeName(MainMenuGUI.empID)+"<html/>");
		frmSalesystememployeeManagement.getContentPane().add(lblEmployeeName);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 281, 564, 119);
		frmSalesystememployeeManagement.getContentPane().add(scrollPane);
		
		table_1 = popTable();
		table_1.getColumnModel().getColumn(0).setResizable(false);
		table_1.getColumnModel().getColumn(2).setResizable(false);
		table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_1.getTableHeader().setReorderingAllowed(false);
		table_1.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(table_1);
		table_1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				updateTextAreaData();
			}
		});
		
		JButton btnCreateNewCustomer = new JButton("Create New Employee");
		btnCreateNewCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CreateEmployeeGUI.main(null);
				table_1.getSelectionModel().clearSelection();
				updateTextAreaData();
				updateTable();
			}
		});
		btnCreateNewCustomer.setBounds(10, 86, 159, 23);
		frmSalesystememployeeManagement.getContentPane().add(btnCreateNewCustomer);
		
		JButton btnDeleteCustomer = new JButton("Delete Employee");
		btnDeleteCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					pCtr = new PersonCtr();
					Employee e = pCtr.findEmployee((int)table_1.getModel().getValueAt(table_1.getSelectedRow(), 2));
					int n = JOptionPane.showConfirmDialog(frmSalesystememployeeManagement, "Are you sure you want to delete " + e.getName() + " from the database?", "Confirmation", JOptionPane.YES_NO_OPTION);
					table_1.clearSelection();
					
					if (n==0) {
						pCtr.removeEmployee(e.getEmployeeID());
					}
					else {
						return;
					}
					updateTable();
					updateTextAreaData();
								
				
			}
				catch (Exception ee) {
					return;
				}
			}
		});
		btnDeleteCustomer.setBounds(10, 120, 159, 23);
		frmSalesystememployeeManagement.getContentPane().add(btnDeleteCustomer);
		
		JButton btnEditCustomer = new JButton("Edit Employee");
		btnEditCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
				EditEmployee.main((int)table_1.getModel().getValueAt(table_1.getSelectedRow(), 2));
				table_1.clearSelection();
				//TODO Edit selected Employee
				}
				catch (Exception e) {
					return;
				}
				}
		});
		btnEditCustomer.setBounds(10, 154, 159, 23);
		frmSalesystememployeeManagement.getContentPane().add(btnEditCustomer);
		
		JButton btnReturnToMenu = new JButton("Return to Main Menu");
		btnReturnToMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainMenuGUI.visibilityTrue();
				frmSalesystememployeeManagement.dispose();
			}
		});
		btnReturnToMenu.setBounds(10, 247, 159, 23);
		frmSalesystememployeeManagement.getContentPane().add(btnReturnToMenu);
		
		
		textArea_1 = new JTextArea();
		textArea_1.setBackground(Color.LIGHT_GRAY);
		textArea_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textArea_1.setBounds(199, 33, 375, 49);
		frmSalesystememployeeManagement.getContentPane().add(textArea_1);
		textArea_1.setEditable(false);
		textArea_1.setText(
				"Name:           " + 
				"\nPostal Code:   "+ 
				"\nEmployee ID #: ");
		
		JLabel lblCustomerSales = new JLabel("Employee Sales");
		lblCustomerSales.setBounds(199, 90, 255, 14);
		frmSalesystememployeeManagement.getContentPane().add(lblCustomerSales);
		
		JLabel lblCustomerInfo = new JLabel("Employee Info");
		lblCustomerInfo.setBounds(199, 11, 86, 14);
		frmSalesystememployeeManagement.getContentPane().add(lblCustomerInfo);
		
		textArea_2 = new JTextArea();
		textArea_2.setText(" ");
		textArea_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textArea_2.setEditable(false);
		textArea_2.setBackground(Color.LIGHT_GRAY);
		textArea_2.setBounds(322, 135, 375, 137);
		frmSalesystememployeeManagement.getContentPane().add(textArea_2);
		
		JScrollPane scrollPane_1 = new JScrollPane(textArea_2);
		scrollPane_1.setBounds(199, 115, 375, 155);
		frmSalesystememployeeManagement.getContentPane().add(scrollPane_1);
		
		
		}
	
	private static JTable popTable() {
		PersonCtr p = new PersonCtr();
		String[] columnNames = {"Name", "Postal Code", "Employee ID #"};
		HashSet<Employee> employees = p.getEmployees();
		Iterator<Employee> it = employees.iterator();
		int i =0;
		Object[][] data = new Object[employees.size()][3];
		while (it.hasNext()) {
			Employee e = it.next();
			data[i][0] = e.getName();
			data[i][1] = e.getPostalCode();
			data[i][2] = e.getEmployeeID();
			i++;
		}
		System.out.println(p.getEmployees().size());
		tm = new DefaultTableModel(data, columnNames);
		
		return new JTable(tm){
			   public boolean isCellEditable(int row, int column){
			        return false;
			   }
			};
	}
	private void updateTextAreaData() {
		PersonCtr pc = new PersonCtr();
		try {
			
			
			int employeeID = (int) table_1.getModel().getValueAt(table_1.getSelectedRow(), 2);
			Employee e = pc.findEmployee(employeeID);
			String data = 
					
					"Name:\t\t"+ e.getName() + 
					"\nPostal Code:\t\t" + e.getPostalCode()+ 
					"\nEmployee ID #:\t" + e.getEmployeeID() ;
					textArea_1.setText(data);
					} 
		catch (Exception e) {
			textArea_1.setText(
					"Name:           " + 
					"\nPostal Code:   "+ 
					"\nEmployee ID #: ");
		}
		try {
			double gTotal = 0;
			int employeeID = (int) table_1.getModel().getValueAt(table_1.getSelectedRow(), 2);
			Iterator<Sale> it = sCtr.findEmployeeSales(employeeID).iterator();
			Sale sale = null;
			textArea_2.setText(" ");
			while (it.hasNext()) {
				sale = it.next();
				textArea_2.append("\n Sale ID: " + sale.getSaleID());
				Iterator<OrderLine> oit = sale.getBasket().iterator();
				OrderLine orderLine = null;
				while (oit.hasNext()) {
					orderLine = oit.next();
					int prodID = orderLine.getProductID();
					Product prod = prodCtr.getPC().findProductByID(prodID);
					textArea_2.append("\n Product: " + prod.getProductName()+" @ $"+prod.getPrice());
					textArea_2.append("\n     x "+orderLine.getQuantity()+" = " + orderLine.calculateSubTotal());
				}
				gTotal += sale.totalPrice();
				textArea_2.append("\n Total: $" + sale.totalPrice());
				textArea_2.append("\n----------------------------------------------------------------");
			}
			textArea_2.append("\n Total Employee Sales: $"+ df2.format(gTotal));
		}
		catch (Exception e) {
			textArea_2.setText("");
		}
		
	}
	
	public static void updateTable() {
		PersonCtr p = new PersonCtr();
		String[] columnNames = {"Name", "Postal Code", "Employee ID #"};
		HashSet<Employee> employees = p.getEmployees();
		Iterator<Employee> it = employees.iterator();
		int i =0;
		Object[][] data = new Object[employees.size()][3];
		while (it.hasNext()) {
			Employee e = it.next();
			data[i][0] = e.getName();
			data[i][1] = e.getPostalCode();
			data[i][2] = e.getEmployeeID();
			i++;
		}
		System.out.println(p.getEmployees().size());
		tm = new DefaultTableModel(data, columnNames);
		table_1.setModel(tm);
	}
}