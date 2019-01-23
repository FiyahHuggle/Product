package GUILayer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import CtrLayer.*;
import ModelLayer.*;

import javax.swing.JTable;
import javax.swing.JTextArea;

import java.awt.event.ActionListener;
import java.util.*;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import java.awt.Font;
import javax.swing.JSpinner;
import javax.swing.JScrollPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import java.awt.Toolkit;

/**
 * Sale GUI
 *
 * @author Pien van den Abeele
 * @version 20.01.2019
 */
public class SaleGUI {

	private static JFrame SaleMenu;
	private static JTextField textField;
	private static PersonCtr perCtr = new PersonCtr();
	private static ProductCtr proCtr = new ProductCtr();
	private static SaleCtr saleCtr = new SaleCtr();
	private static JTable table;
	private static JTabbedPane tabbedPane;
	private static JTable table_2;
	private static int saleID;
	private static int saleID1;
	private static JTable table_1;
	public static TableModel tm;
	public static TableModel tm1;
	public static TableModel tm2;
	public static TableModel tm3;
	private static JTable table_3;
	private JTextArea txtInfo;
	private static JButton btnEnter;
	private JButton btnSave;
	private static JLabel lblTotalPrice;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SaleGUI window = new SaleGUI();
					window.SaleMenu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SaleGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		SaleMenu = new JFrame();
		SaleMenu.setResizable(false);
		SaleMenu.getContentPane().setBackground(Color.WHITE);
		SaleMenu.setTitle("SaleSystem/Sale");
		SaleMenu.setBounds(100, 100, 600, 450);
		SaleMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SaleMenu.getContentPane().setLayout(null);
		JLabel label_3 = new JLabel(MainMenuGUI.empID + "");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 10));
		label_3.setBounds(103, 14, 122, 16);
		SaleMenu.getContentPane().add(label_3);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 59, 574, 351);
		SaleMenu.getContentPane().add(tabbedPane);
		
		JPanel sale = new JPanel();
		sale.setBackground(Color.WHITE);
		tabbedPane.addTab("Sales", null, sale, null);
		sale.setLayout(null);
		
		JLabel lblEnterSaleID = new JLabel("Enter Sale ID:");
		lblEnterSaleID.setBounds(10, 11, 77, 14);
		sale.add(lblEnterSaleID);
		
		textField = new JTextField();
		textField.setBounds(84, 8, 86, 20);
		sale.add(textField);
		textField.setColumns(10);
		
		JLabel lblSaleId = new JLabel("Sale ID: ");
		lblSaleId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSaleId.setBounds(202, 133, 77, 23);
		sale.add(lblSaleId);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(424, 35, 46, 14);
		sale.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(294, 92, 265, 220);
		sale.add(scrollPane);
		
		table = popTable();
	    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		table.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(table);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 160, 276, 120);
		sale.add(scrollPane_1);
		
		JLabel invalid = new JLabel("Invalid Entry");
		invalid.setForeground(Color.RED);
		invalid.setVisible(false);
		invalid.setBounds(262, 11, 122, 14);
		sale.add(invalid);
		
		JLabel noSale = new JLabel("Sale doesn't exist");
		noSale.setForeground(Color.RED);
		noSale.setVisible(false);
		noSale.setBounds(262, 11, 135, 14);
		sale.add(noSale);
		
		JLabel lblInvalidAmount = new JLabel("Invalid Amount");
		lblInvalidAmount.setHorizontalAlignment(SwingConstants.RIGHT);
		lblInvalidAmount.setForeground(Color.RED);
		lblInvalidAmount.setVisible(false);
		lblInvalidAmount.setBounds(-15, 75, 95, 14);
		sale.add(lblInvalidAmount);
		
		JButton btnRemoveProduct = new JButton("Remove Product From Sale");
		btnRemoveProduct.setEnabled(false);
		btnRemoveProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Object p = table_1.getValueAt(table_1.getSelectedRow(), 0);
					int p1 = (int) p;
					Product p2 = proCtr.findProductByID(p1);
					int n = JOptionPane.showConfirmDialog(SaleMenu, "Are you sure you want to delete " + p2.getProductName() + " from the sale?", "Confirmation", JOptionPane.YES_NO_OPTION);
					table_1.clearSelection();
					if (n==0) {
					saleCtr.removeOrderLine(saleID, p1);
					System.out.println("boobies");
					updateTable();
					updateTable1();
					}
					else {
						return;
					}
					lblInvalidAmount.setVisible(false);
					
				}
				catch (Exception e1) {
					return;
				}
				
			}
		});
		btnRemoveProduct.setBounds(84, 105, 200, 24);
		sale.add(btnRemoveProduct);
		
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		JFormattedTextField tf = ((JSpinner.DefaultEditor) spinner.getEditor()).getTextField();
	    tf.setEditable(false);
	    tf.setBackground(Color.white);
		spinner.setBounds(249, 71, 35, 23);
		sale.add(spinner);
		
		JButton btnAddProduct = new JButton("Add Product");
		btnAddProduct.setEnabled(false);
		btnAddProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
				Object p = table.getValueAt(table.getSelectedRow(), 0);
				Object q = spinner.getValue();
				int p1 = (int) p;
				int q1 = (int) q;
				if (q1 <= proCtr.findProductByID(p1).getStock() && q1 > 0) {
					saleCtr.addOrderLine(saleID, p1, q1);
					spinner.setValue(0);
					lblInvalidAmount.setVisible(false);
				}
				else {
					lblInvalidAmount.setVisible(true);
					spinner.setValue(0);
					return;
				}
				}
				catch (Exception e) {
					return;
				}
				updateTable();
				updateTable1();
				updateTable2();
			}
		});
		btnAddProduct.setBounds(84, 70, 155, 24);
		sale.add(btnAddProduct);
		
		JButton btnNewButton_1 = new JButton("Finalize");
		btnNewButton_1.setEnabled(false);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				saleCtr.finalizeSale(saleID);
				btnRemoveProduct.setEnabled(false);
				btnAddProduct.setEnabled(false);
				btnSave.setEnabled(false);
				btnNewButton_1.setEnabled(false);
				textField.setText("");
				table_1 = popTable1();
				table_1.setModel(tm1);
			}
		});
		btnNewButton_1.setBounds(10, 291, 77, 23);
		sale.add(btnNewButton_1);
		
		btnSave = new JButton("Save");
		btnSave.setEnabled(false);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnRemoveProduct.setEnabled(false);
				btnAddProduct.setEnabled(false);
				btnNewButton_1.setEnabled(false);
				btnSave.setEnabled(false);
				textField.setText("");
				table_1 = popTable1();
				table_1.setModel(tm1);
			}
		});
		btnSave.setBounds(97, 291, 72, 23);
		sale.add(btnSave);
		
		table_1 = popTable1();
		table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_1.getColumnModel().getColumn(0).setResizable(false);
		table_1.getColumnModel().getColumn(2).setResizable(false);
		table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_1.getTableHeader().setReorderingAllowed(false);
		table_1.setAutoCreateRowSorter(true);
		scrollPane_1.setViewportView(table_1);
		
		btnEnter = new JButton("Enter");
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				invalid.setVisible(false);
				noSale.setVisible(false);
				int i = 1000000;
				String t = textField.getText();
				try {
					i = Integer.parseInt(t);
					saleID = i;
					lblSaleId.setText("Sale ID: " + saleID);
					updateTable1();
					lblInvalidAmount.setVisible(false);
					table_2.getSelectionModel().clearSelection();
					if (saleCtr.findSale(saleID).isPaid() == true) {
						btnAddProduct.setEnabled(false);
						btnRemoveProduct.setEnabled(false);
						btnNewButton_1.setEnabled(false);
						btnSave.setEnabled(false);
					}
					else if (saleCtr.findSale(saleID).isPaid() == false) {
						btnAddProduct.setEnabled(true);
						btnRemoveProduct.setEnabled(true);
						btnNewButton_1.setEnabled(true);
						btnSave.setEnabled(true);
					}
				}
				
				catch (NumberFormatException e){
					invalid.setVisible(true);
					return;
				}
				
				if (t.equals("")) {
					invalid.setVisible(true);
				}
				
				else if (saleCtr.findSale(i) == null) {
					noSale.setVisible(true);					
				}
				updateTable();
				updateTable1();
				updateTable2();
			}
		});
		
		btnEnter.setBounds(180, 7, 72, 23);
		sale.add(btnEnter);
		
		JLabel lblProducts = new JLabel("Products:");
		lblProducts.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblProducts.setBounds(294, 70, 77, 23);
		sale.add(lblProducts);
		
		JButton btnStartSale = new JButton("Start New Sale");
		btnStartSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StartSaleGUI.main(null);
				SaleMenu.dispose();
			}
		});
		btnStartSale.setBounds(362, 7, 197, 58);
		sale.add(btnStartSale);
		
		lblTotalPrice = new JLabel("Total Price: $");
		lblTotalPrice.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTotalPrice.setBounds(172, 291, 112, 21);
		sale.add(lblTotalPrice);
		
		JLabel lblNewLabel_1 = new JLabel(perCtr.findCustomer(saleCtr.findSale(saleID).getCustomerPhone()).getName());
		lblNewLabel_1.setBounds(10, 133, 182, 19);
		sale.add(lblNewLabel_1);
		
		JPanel saleLists = new JPanel();
		saleLists.setBackground(Color.WHITE);
		tabbedPane.addTab("Sale Lists", null, saleLists, null);
		saleLists.setLayout(null);
		 
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(280, 34, 279, 248);
		saleLists.add(scrollPane_2);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 193, 260, 119);
		saleLists.add(scrollPane_3);
		
		JLabel lblSaleIsAlready = new JLabel("Therefore can't be removed");
		lblSaleIsAlready.setBounds(276, 304, 158, 19);
		saleLists.add(lblSaleIsAlready);
		lblSaleIsAlready.setForeground(Color.RED);
		
		JLabel lblSaleCantBe = new JLabel("Sale is already paid");
		lblSaleCantBe.setBounds(276, 289, 158, 17);
		saleLists.add(lblSaleCantBe);
		lblSaleCantBe.setForeground(Color.RED);
		lblSaleCantBe.setVisible(false);
		lblSaleIsAlready.setVisible(false);
		
		table_2 = popTable2();
	    table_2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_2.getColumnModel().getColumn(0).setResizable(false);
		table_2.getColumnModel().getColumn(2).setResizable(false);
		table_2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_2.getTableHeader().setReorderingAllowed(false);
		table_2.setAutoCreateRowSorter(true);
		scrollPane_2.setViewportView(table_2);
		table_2.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				updateTextAreaData();
				updateTable3();
			}
		});
		
		table_3 = popTable3();
		table_3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_3.getColumnModel().getColumn(0).setResizable(false);
		table_3.getColumnModel().getColumn(2).setResizable(false);
		table_3.getTableHeader().setReorderingAllowed(false);
		table_3.setAutoCreateRowSorter(true);
		scrollPane_3.setViewportView(table_3);
		
		JButton btnRemoveSale = new JButton("Remove Sale");
		btnRemoveSale.setBounds(444, 289, 115, 23);
		btnRemoveSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Object o = table_2.getModel().getValueAt(table_2.getSelectedRow(), 0);
					int saleID1 = (int) o;
					Sale s = saleCtr.findSale(saleID1);
					if (s.isPaid() == false) {
						int n = JOptionPane.showConfirmDialog(SaleMenu, "Are you sure you want to delete sale" + s.getSaleID() + " from the database?", "Confirmation", JOptionPane.YES_NO_OPTION);
						table_3 = popTable3();
						table_3.setModel(tm3);
						table_2.clearSelection();
						if (n == 0) {
							saleCtr.removeSale(saleID1);
						}
						else {
							return;
						}
					}
					else if (s.isPaid() == true) {
						lblSaleCantBe.setEnabled(true);
						lblSaleIsAlready.setEnabled(true);
					}
					

				}
				catch (Exception e1) {
					
				}
				updateTable2();
			}
		});
		saleLists.add(btnRemoveSale);
		
		txtInfo = new JTextArea();
		txtInfo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtInfo.setEditable(false);
		txtInfo.setText("Employee Info\r\nName:\r\nID:\r\n\r\nCustomer Info\r\nName:\r\nPhone number:\r\n\r\nTotal Price: \r\nPaid: " );
		txtInfo.setBounds(10, 11, 260, 154);
		saleLists.add(txtInfo);
		
		JLabel lblSales = new JLabel("Sales:");
		lblSales.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSales.setBounds(280, 9, 154, 14);
		saleLists.add(lblSales);
		
		JLabel lblProductsInSale = new JLabel("Products in Sale:");
		lblProductsInSale.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblProductsInSale.setBounds(10, 168, 253, 14);
		saleLists.add(lblProductsInSale);
		
		JButton btnMainMenu = new JButton("Back to Main Menu");
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainMenuGUI.visibilityTrue();
				SaleMenu.dispose();
			}
			
		});
		btnMainMenu.setBounds(427, 11, 147, 23);
		SaleMenu.getContentPane().add(btnMainMenu);
		
		JLabel label = new JLabel("Employee name:");
		label.setFont(new Font("Tahoma", Font.BOLD, 10));
		label.setBounds(10, 31, 106, 17);
		SaleMenu.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Employee ID:");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		label_1.setBounds(10, 14, 122, 17);
		SaleMenu.getContentPane().add(label_1);
		
		String empName = perCtr.findEmployee(MainMenuGUI.getEmpID()).getName();
		JLabel label_2 = new JLabel(empName);
		label_2.setFont(new Font("Tahoma", Font.BOLD, 10));
		label_2.setBounds(103, 31, 227, 17);
		SaleMenu.getContentPane().add(label_2);
	}
	
	private JTable popTable() {
		String[] columnNames = {"Product ID", "Product", "Stock", "Price"};
		HashSet<Product> products = proCtr.getAllProducts();
		Iterator<Product> it = products.iterator();
		int i = 0;
		Object[][] data = new Object[products.size()][4];
		while (it.hasNext()) {
			Product p = it.next();
			data[i][0] = p.getProductID();
			data[i][1] = p.getProductName();
			data[i][2] = p.getStock();
			data[i][3] = p.getPrice();
			i++;
		}
		tm = new DefaultTableModel(data, columnNames);
		
		return new JTable(tm) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
	}
	
	private static void updateTable() {
		try {
		String[] columnNames = {"Product ID", "Product", "Stock", "Price"};
		HashSet<Product> products = proCtr.getAllProducts();
		Iterator<Product> it = products.iterator();
		int i = 0;
		Object[][] data = new Object[products.size()][4];
		while (it.hasNext()) {
			Product p = it.next();
			data[i][0] = p.getProductID();
			data[i][1] = p.getProductName();
			data[i][2] = p.getStock();
			data[i][3] = p.getPrice();
			i++;
		}
		tm = new DefaultTableModel(data, columnNames);
		table.setModel(tm);
	}
	catch (Exception e) {
		return;
	}
	}
	
	private JTable popTable1() {
		String[] columnNames = {"Product ID", "Product", "quantity", "Price"};
		int i = 0;
		Object[][] data = new Object[1][4];
			data[i][0] = ("");
			data[i][1] = ("");
			data[i][2] = ("");
			data[i][3] = ("");
			i++;
		tm1 = new DefaultTableModel(data, columnNames);
		return new JTable(tm1) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
	}
	
	
	
	private static void updateTable1() {
		try {
			String[] columnNames = {"Product ID", "Product", "quantity", "Price"};
			HashSet<OrderLine> basket = saleCtr.getSaleInfo(saleID);
			Iterator<OrderLine> oit = basket.iterator();
			int i = 0;
			Object[][] data = new Object[basket.size()][4];
			while (oit.hasNext()) {
				OrderLine o = oit.next();
				Product p = proCtr.findProductByID(o.getProductID());
				data[i][0] = o.getProductID();
				data[i][1] = p.getProductName();
				data[i][2] = o.getQuantity();
				data[i][3] = p.getPrice();
				i++;
			}
		tm1 = new DefaultTableModel(data, columnNames);
		table_1.setModel(tm1);
		totalPrice(saleID);
		}
		catch (Exception e) {
			return;
		}
	}
	
	private JTable popTable3() {
		String[] columnNames = {"Product ID", "Product", "quantity", "Price"};
		int i = 0;
		Object[][] data = new Object[1][4];
			data[i][0] = ("");
			data[i][1] = ("");
			data[i][2] = ("");
			data[i][3] = ("");
			i++;
		tm3 = new DefaultTableModel(data, columnNames);

		return new JTable(tm3) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
	}
	
	private static void updateTable3() {
		
		try {
		Object oid = table_2.getModel().getValueAt(table_2.getSelectedRow(), 0);
		saleID1 = (int) oid;		
		String[] columnNames = {"Product ID", "Product", "quantity", "Price"};
		HashSet<OrderLine> basket = saleCtr.getSaleInfo(saleID1);
		Iterator<OrderLine> oit = basket.iterator();
		int i = 0;
		Object[][] data = new Object[basket.size()][4];
		while (oit.hasNext()) {
			OrderLine o = oit.next();
			Product p = proCtr.findProductByID(o.getProductID());
			data[i][0] = o.getProductID();
			data[i][1] = p.getProductName();
			data[i][2] = o.getQuantity();
			data[i][3] = p.getPrice();
			i++;
		}
		tm3 = new DefaultTableModel(data, columnNames);
		table_3.setModel(tm3);
		}
		catch (Exception e) {
			return;
		}
	}
	
	private JTable popTable2() {
		String[] columnNames = {"Sale ID", "Employee ID", "Customer Phone number"};
		HashSet<Sale> sales = saleCtr.getAllSales();
		Iterator<Sale> sit = sales.iterator();
		int i = 0;
		Object[][] data = new Object[sales.size()][3];
		while (sit.hasNext()) {
			Sale s = sit.next();
			data[i][0] = s.getSaleID();
			data[i][1] = s.getEmployeeID();
			data[i][2] = s.getCustomerPhone();
			i++;
		}
		tm2 = new DefaultTableModel(data, columnNames);
		
		return new JTable(tm2) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
	}
	
	private static void updateTable2() {
		String[] columnNames = {"Sale ID", "Employee ID", "Customer Phone number"};
		HashSet<Sale> sales = saleCtr.getAllSales();
		Iterator<Sale> sit = sales.iterator();
		int i = 0;
		Object[][] data = new Object[sales.size()][3];
		while (sit.hasNext()) {
			Sale s = sit.next();
			data[i][0] = s.getSaleID();
			data[i][1] = s.getEmployeeID();
			data[i][2] = s.getCustomerPhone();
			i++;
		}
		tm2 = new DefaultTableModel(data, columnNames);
		table_2.setModel(tm2);
	}
	
	private void updateTextAreaData() {
		try {
			Object o = table_2.getModel().getValueAt(table_2.getSelectedRow(), 0);
			int id = (int) o;
			String infoY = ("Employee Info"
				+ "\r\nName:\t\t" + perCtr.getEmployeeName(saleCtr.findSale(id).getEmployeeID())
				+ "\r\nID:\t\t" + saleCtr.findSale(id).getEmployeeID()
				+ "\r\n\r\n"
				+ "Customer Info"
				+ "\r\nName:\t\t" + perCtr.findCustomer(saleCtr.findSale(id).getCustomerPhone()).name
				+ "\r\nPhone Number:\t" + saleCtr.findSale(id).getCustomerPhone()
				+ "\r\n\r\n "
				+ "Total Price:\t\t $" + saleCtr.findSale(id).totalPrice()
				+ "\r\nPaid:\t\t Yes.");
			
			String infoN = ("Employee Info"
					+ "\r\nName:\t\t" + perCtr.getEmployeeName(saleCtr.findSale(id).getEmployeeID())
					+ "\r\nID:\t\t" + saleCtr.findSale(id).getEmployeeID()
					+ "\r\n\r\n"
					+ "Customer Info"
					+ "\r\nName:\t\t" + perCtr.findCustomer(saleCtr.findSale(id).getCustomerPhone()).name
					+ "\r\nPhone Number:\t" + saleCtr.findSale(id).getCustomerPhone()
					+ "\r\n\r\n"
					+ "Total Price:\t\t $" + saleCtr.findSale(id).totalPrice()
					+ "\r\nPaid:\t\t No.");
			if (saleCtr.findSale(id).isPaid() == true) {
				txtInfo.setText(infoY);
			}
			else {
				txtInfo.setText(infoN);
			}
		} catch (Exception e) {
			//System.out.println("Exception found"+"\nRow :"+table_2.getSelectedRow()+"\nValue: "+ table_2.getModel().getValueAt(table_2.getSelectedRow(), 1).toString());
			return;
		}
		
	}
	
	public static void setTextField(String newTextField) {
		textField.setText(newTextField);
	}
	
	public static void clickButton() {
		btnEnter.doClick();
	}
	
	public static void totalPrice(int saleID) {
		lblTotalPrice.setText("Total Price: $" + saleCtr.findSale(saleID).totalPrice());
	}
}
