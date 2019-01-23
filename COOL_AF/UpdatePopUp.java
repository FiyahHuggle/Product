package GUILayer;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class UpdatePopUp extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdatePopUp frame = new UpdatePopUp();
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
	public UpdatePopUp() {
		setTitle("Update a product");
		setType(Type.POPUP);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 450);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("The product was successfully updated. ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(30, 25, 290, 40);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewProductInfo = new JLabel("New product info: ");
		lblNewProductInfo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewProductInfo.setBounds(30, 66, 193, 25);
		contentPane.add(lblNewProductInfo);
		
		JLabel lblName = new JLabel("Name: ");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblName.setBounds(30, 110, 65, 30);
		contentPane.add(lblName);
		
		JLabel lblStock = new JLabel("Stock: ");
		lblStock.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblStock.setBounds(30, 170, 65, 30);
		contentPane.add(lblStock);
		
		JLabel lblPrice = new JLabel("Price: ");
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPrice.setBounds(30, 230, 65, 30);
		contentPane.add(lblPrice);
		
		JLabel lblId = new JLabel("ID: ");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblId.setBounds(30, 290, 65, 30);
		contentPane.add(lblId);
		
		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				ProductGUI.main(null);
			}
		});
		btnOK.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnOK.setBounds(100, 340, 150, 50);
		contentPane.add(btnOK);
	}
}