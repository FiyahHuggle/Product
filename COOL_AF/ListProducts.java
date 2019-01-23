package GUILayer;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import CtrLayer.ProductCtr;
import ModelLayer.Product;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JTree;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Iterator;
import java.awt.Window.Type;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JRadioButton;

public class ListProducts extends JFrame {
	
	private JPanel contentPane;
	private static ProductCtr proCtr = new ProductCtr();
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListProducts frame = new ListProducts();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ListProducts() {

		setType(Type.POPUP);
		setTitle("List of products");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnGoBack = new JButton("Go back");
		btnGoBack.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				ProductGUI.main(null);
			}
		});
		
		btnGoBack.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnGoBack.setBounds(370, 340, 200, 50);
		contentPane.add(btnGoBack);
		
		JLabel lblNewLabel = new JLabel("Product list");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(12, 13, 158, 21);
		contentPane.add(lblNewLabel);
		
		table = new JTable();
		table.setBackground(Color.LIGHT_GRAY);
		table.setBounds(45, 49, 491, 255);
		contentPane.add(table);}
		
		private JTable popTable5() {
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
		DefaultTableModel tm = new DefaultTableModel(data, columnNames);
		
		return new JTable(tm) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
	}
}