package GUILayer;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JToggleButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;
import javax.swing.JScrollBar;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Toolkit;

public class UpdateProduct extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateProduct frame = new UpdateProduct();
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
	public UpdateProduct() {
		setTitle("Update a product");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUpdateAProduct = new JLabel("Which product do you wish to update? ");
		lblUpdateAProduct.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblUpdateAProduct.setBounds(25, 20, 300, 50);
		contentPane.add(lblUpdateAProduct);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(350, 17, 200, 50);
		contentPane.add(comboBox);
		
		JLabel label = new JLabel("Product name: ");
		label.setFont(new Font("Tahoma", Font.ITALIC, 20));
		label.setBounds(25, 130, 155, 50);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("Product stock: ");
		label_1.setFont(new Font("Tahoma", Font.ITALIC, 20));
		label_1.setBounds(25, 200, 155, 50);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("Product price: ");
		label_2.setFont(new Font("Tahoma", Font.ITALIC, 20));
		label_2.setBounds(25, 270, 155, 50);
		contentPane.add(label_2);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(225, 130, 280, 50);
		contentPane.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(225, 200, 280, 50);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(225, 270, 280, 50);
		contentPane.add(textField_2);
		
		JButton btnNewButton = new JButton("Update");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				UpdateProductPopUp upu = new UpdateProductPopUp();
				upu.setVisible(true);				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setBounds(124, 340, 140, 50);
		contentPane.add(btnNewButton);
		
		JButton btnGoBack = new JButton("Go back");
		btnGoBack.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				UpdateProductPopUp.main(null);
				contentPane.setVisible(false);
				ProductGUI.main(null);
			}
		});
		
		btnGoBack.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnGoBack.setBounds(370, 340, 200, 50);
		contentPane.add(btnGoBack);
	}
}
