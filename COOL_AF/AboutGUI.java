package GUILayer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextPane;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Main Menu GUI
 *
 * @author Pien van den Abeele
 * @version 20.01.2019
 */
public class AboutGUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AboutGUI window = new AboutGUI();
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
	public AboutGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setLayout(null);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnOk.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnOk.setBounds(165, 203, 90, 33);
		frame.getContentPane().add(btnOk);
		
		JTextPane txtpnAuthers = new JTextPane();
		txtpnAuthers.setEditable(false);
		txtpnAuthers.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtpnAuthers.setText("Vestbjerg Byggecenter A/S\r\nSale System\r\nVersion: 1.02.2019\r\n\r\nAuthors:\r\nSteven Jack Teglman\r\nDora Grgurev\r\nPien van den Abeele\r\n");
		txtpnAuthers.setBounds(165, 11, 259, 168);
		frame.getContentPane().add(txtpnAuthers);
		
		JLabel label = new JLabel("");
		Image icon1 = new ImageIcon(this.getClass().getResource("/NoSmall1.png")).getImage();
		label.setIcon(new ImageIcon(icon1));
		label.setBounds(24, 11, 120, 135);
		frame.getContentPane().add(label);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
