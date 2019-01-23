package GUILayer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.Action;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import CtrLayer.PersonCtr;
import CtrLayer.ProductCtr;
import ModelLayer.Product;

import java.awt.SystemColor;
import java.awt.Window;

import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.Toolkit;

public class ProductGUI {
	
	private JFrame Product;
	private static JFrame CreateProduct;
	private static JFrame ListProducts;
	private static JPanel ConfirmProductCreation;
	//private static JPanel RemoveProduct;
	
	private static PersonCtr perCtr = new PersonCtr();
	private static ProductCtr proCtr = new ProductCtr();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductGUI window = new ProductGUI();
					window.Product.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ProductGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Product = new JFrame();
		Product.setBackground(SystemColor.info);
		Product.getContentPane().setBackground(Color.WHITE);
		Product.setTitle("Product Management");
		Product.setBounds(100, 100, 600, 450);
		Product.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Product.getContentPane().setLayout(null);
		
		JButton btnReturnToMain = new JButton("Return to Main Menu");
		btnReturnToMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenuGUI.visibilityTrue();
			}
		});
		btnReturnToMain.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnReturnToMain.setBounds(380, 330, 180, 50);
		Product.getContentPane().add(btnReturnToMain);
		
		JButton btnRemoveAProduct = new JButton("Remove a product");
		btnRemoveAProduct.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnRemoveAProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 RemoveProduct.main(null);
				 Product.dispose();				
			}
		});	
		
		btnRemoveAProduct.setBounds(50, 330, 170, 50);
		Product.getContentPane().add(btnRemoveAProduct);
				
		JButton btnUpdateAProduct = new JButton("Update a product");
		btnUpdateAProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UpdateProduct.main(null);
				Product.dispose();
				//Product.dispose();
				//UpdateProduct up = new UpdateProduct();
				//up.setVisible(true);
			}
		});				
		btnUpdateAProduct.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnUpdateAProduct.setBounds(50, 233, 170, 50);
		Product.getContentPane().add(btnUpdateAProduct);
		
		
		JButton btnListProducts = new JButton("List products");
		btnListProducts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Product.dispose();
				ListProducts lp = new ListProducts();
				lp.setVisible(true);
			}			
		});		
		btnListProducts.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnListProducts.setBounds(380, 233, 170, 50);
		Product.getContentPane().add(btnListProducts);
		
		
		JButton btnCreateAProduct = new JButton("Create a product");
		btnCreateAProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Product.dispose();
				CreateProduct cp = new CreateProduct();
				cp.setVisible(true);
			}
		});
		btnCreateAProduct.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnCreateAProduct.setBounds(200, 100, 200, 80);
		Product.getContentPane().add(btnCreateAProduct);
		
		JLabel lblNewLabel = new JLabel("Welcome to the product management menu !");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		lblNewLabel.setBounds(100, 23, 400, 50);
		Product.getContentPane().add(lblNewLabel);
	}	
}