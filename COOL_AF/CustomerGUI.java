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
 * The Customer GUI Class. Responsible for managing the customers. Class CreateCustomerGUI and EditCustomer when interacting with Customers.
 *
 * @author Steven Teglman
 * @version 20.01.2019
 */
public class CustomerGUI {

	private PersonCtr pCtr =new PersonCtr();
	public static JFrame frmSalesystemcustomerManagement;
	private static JTable table_1;
	private JTextArea textArea;
	private JTextArea textArea_1;
	public static TableModel tm;
	private JTextArea textArea_2;
	ProductCtr prodCtr = new ProductCtr();
	SaleCtr sCtr = new SaleCtr();
	private DecimalFormat df2 = new DecimalFormat(".##");

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerGUI window = new CustomerGUI();
					window.frmSalesystemcustomerManagement.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CustomerGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//TODO Make a dynamic name and ID of the employee that is logged in.
		//TODO Update the Title
		frmSalesystemcustomerManagement = new JFrame();
		frmSalesystemcustomerManagement.setResizable(false);
		frmSalesystemcustomerManagement.setTitle("SaleSystem/Customer Management");
		frmSalesystemcustomerManagement.getContentPane().setBackground(Color.WHITE);
		frmSalesystemcustomerManagement.setBackground(Color.WHITE);
		frmSalesystemcustomerManagement.setBounds(100, 100, 600, 450);
		frmSalesystemcustomerManagement.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSalesystemcustomerManagement.getContentPane().setLayout(null);
		
		JLabel lblEmployeeNo = new JLabel("New label");
		lblEmployeeNo.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblEmployeeNo.setBounds(10, 11, 159, 14);
		lblEmployeeNo.setText("Employee ID: " + MainMenuGUI.empID);
		frmSalesystemcustomerManagement.getContentPane().add(lblEmployeeNo);
		
		JLabel lblEmployeeName = new JLabel("New label");
		lblEmployeeName.setVerticalAlignment(SwingConstants.TOP);
		lblEmployeeName.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblEmployeeName.setBounds(10, 33, 159, 42);
		lblEmployeeName.setText("<html>Employee Name: <br/>" + pCtr.getEmployeeName(MainMenuGUI.empID)+"</html>");
		frmSalesystemcustomerManagement.getContentPane().add(lblEmployeeName);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 281, 564, 119);
		frmSalesystemcustomerManagement.getContentPane().add(scrollPane);
		
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
		
		JButton btnCreateNewCustomer = new JButton("Create New Customer");
		btnCreateNewCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CreateCustomerGUI.main(null);
				table_1.getSelectionModel().clearSelection();
				updateTextAreaData();
				updateTable();
			}
		});
		btnCreateNewCustomer.setBounds(10, 86, 159, 23);
		frmSalesystemcustomerManagement.getContentPane().add(btnCreateNewCustomer);
		
		JButton btnDeleteCustomer = new JButton("Delete Customer");
		btnDeleteCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					pCtr = new PersonCtr();
					Customer c = pCtr.findCustomer(table_1.getModel().getValueAt(table_1.getSelectedRow(), 1).toString());
					int n = JOptionPane.showConfirmDialog(frmSalesystemcustomerManagement, "Are you sure you want to delete " + c.getName() + " from the database?", "Confirmation", JOptionPane.YES_NO_OPTION);
					table_1.clearSelection();
					
					if (n==0) {
						pCtr.removeCustomer(c.getPhone());
					}
					else {
						return;
					}
					updateTable();
					updateTextAreaData();
								
				
				//TODO Delete Selected Customer
			}
				catch (Exception e) {
					return;
				}
			}
		});
		btnDeleteCustomer.setBounds(10, 120, 159, 23);
		frmSalesystemcustomerManagement.getContentPane().add(btnDeleteCustomer);
		
		JButton btnEditCustomer = new JButton("Edit Customer");
		btnEditCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
				EditCustomer.main(table_1.getModel().getValueAt(table_1.getSelectedRow(), 1).toString());
				table_1.clearSelection();
				//TODO Edit selected customer
				}
				catch (Exception e) {
					return;
				}
				}
		});
		btnEditCustomer.setBounds(10, 154, 159, 23);
		frmSalesystemcustomerManagement.getContentPane().add(btnEditCustomer);
		
		JButton btnReturnToMenu = new JButton("Return to Main Menu");
		btnReturnToMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainMenuGUI.visibilityTrue();
				frmSalesystemcustomerManagement.dispose();
			}
		});
		btnReturnToMenu.setBounds(10, 247, 159, 23);
		frmSalesystemcustomerManagement.getContentPane().add(btnReturnToMenu);
		
		
		textArea_1 = new JTextArea();
		textArea_1.setBackground(Color.LIGHT_GRAY);
		textArea_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textArea_1.setBounds(199, 33, 375, 64);
		frmSalesystemcustomerManagement.getContentPane().add(textArea_1);
		textArea_1.setEditable(false);
		textArea_1.setText(
				"Name:           " + 
				"\nPhone Number:  "+ 
				"\nPostal Code:   "+ 
				"\nCustomer ID #: ");
		
		JLabel lblCustomerSales = new JLabel("Customer Sales");
		lblCustomerSales.setBounds(199, 108, 255, 14);
		frmSalesystemcustomerManagement.getContentPane().add(lblCustomerSales);
		
		JLabel lblCustomerInfo = new JLabel("Customer Info");
		lblCustomerInfo.setBounds(199, 11, 86, 14);
		frmSalesystemcustomerManagement.getContentPane().add(lblCustomerInfo);
		
		textArea_2 = new JTextArea();
		textArea_2.setText(" ");
		textArea_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textArea_2.setEditable(false);
		textArea_2.setBackground(Color.LIGHT_GRAY);
		textArea_2.setBounds(322, 135, 375, 137);
		frmSalesystemcustomerManagement.getContentPane().add(textArea_2);
		
		JScrollPane scrollPane_1 = new JScrollPane(textArea_2);
		scrollPane_1.setBounds(199, 133, 375, 137);
		frmSalesystemcustomerManagement.getContentPane().add(scrollPane_1);
		
		
		}
	
	private static JTable popTable() {
		PersonCtr p = new PersonCtr();
		String[] columnNames = {"Name", "Phone Number", "Postal Code", "Customer ID #"};
		HashSet<Customer> customers = p.getCustomers();
		Iterator<Customer> it = customers.iterator();
		int i =0;
		Object[][] data = new Object[customers.size()][4];
		while (it.hasNext()) {
			Customer c = it.next();
			data[i][0] = c.getName();
			data[i][1] = c.getPhone();
			data[i][2] = c.getPostalCode();
			data[i][3] = c.getCustomerID();
			i++;
		}
		System.out.println(p.getCustomers().size());
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
			
			
			String phone = table_1.getModel().getValueAt(table_1.getSelectedRow(), 1).toString();
			Customer c = pc.findCustomer(phone);
			String data = 
					
					"Name:\t\t"+ c.getName() + 
					"\nPhone Number:\t" + c.getPhone() + 
					"\nPostal Code:\t\t" + c.getPostalCode()+ 
					"\nCustomer ID #:\t" + c.getCustomerID() ;
					textArea_1.setText(data);
					} 
		catch (Exception e) {
			textArea_1.setText(
					"Name:           " + 
					"\nPhone Number:  "+ 
					"\nPostal Code:   "+ 
					"\nCustomer ID #: ");
		}
		try {
			double gTotal = 0;
			String phone = table_1.getModel().getValueAt(table_1.getSelectedRow(), 1).toString();
			Iterator<Sale> it = sCtr.findCustomerSales(phone).iterator();
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
			textArea_2.append("\n Total Customer Purchases: $"+ df2.format(gTotal));
		}
		catch (Exception e) {
			textArea_2.setText("");
		}
		
	}
	
	public static void updateTable() {
		PersonCtr p = new PersonCtr();
		String[] columnNames = {"Name", "Phone Number", "Postal Code", "Customer ID #"};
		HashSet<Customer> customers = p.getCustomers();
		Iterator<Customer> it = customers.iterator();
		int i =0;
		Object[][] data = new Object[customers.size()][4];
		while (it.hasNext()) {
			Customer c = it.next();
			data[i][0] = c.getName();
			data[i][1] = c.getPhone();
			data[i][2] = c.getPostalCode();
			data[i][3] = c.getCustomerID();
			i++;
		}
		System.out.println(p.getCustomers().size());
		tm = new DefaultTableModel(data, columnNames);
		table_1.setModel(tm);
		
	}
}

