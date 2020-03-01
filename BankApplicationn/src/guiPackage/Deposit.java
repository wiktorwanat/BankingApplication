 package guiPackage;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import classPackage.AccountBalanceOperation;
import classPackage.BaseConnection;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Deposit extends JFrame {

	private JPanel contentPane;
	private JTextField deposit_box;
	private JTextField amount_box;

	
	public Deposit(String Login) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 254);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		deposit_box = new JTextField();
		deposit_box.setFont(new Font("Tahoma", Font.PLAIN, 28));
		deposit_box.setText("Deposit into your account");
		deposit_box.setHorizontalAlignment(SwingConstants.CENTER);
		deposit_box.setEditable(false);
		deposit_box.setBounds(10, 11, 414, 38);
		contentPane.add(deposit_box);
		deposit_box.setColumns(10);
		
		amount_box = new JTextField();
		amount_box.setBounds(50, 99, 334, 42);
		contentPane.add(amount_box);
		amount_box.setColumns(10);
		
		JButton Depositclass_deposit_button = new JButton("Deposit!");
		Depositclass_deposit_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				AccountBalanceOperation.change_account_balance(Login,Double.parseDouble(amount_box.getText()),"+");
				}
			catch(NumberFormatException nfe) {
				System.out.println(nfe.getMessage());
				JOptionPane.showMessageDialog(null, "Amount need to be integer or floating point!");
			}
			}
		});
		Depositclass_deposit_button.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Depositclass_deposit_button.setBounds(147, 152, 126, 23);
		contentPane.add(Depositclass_deposit_button);
		
		JLabel amount_of_deposit_label = new JLabel("Amount of deposit:");
		amount_of_deposit_label.setHorizontalAlignment(SwingConstants.CENTER);
		amount_of_deposit_label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		amount_of_deposit_label.setBounds(20, 65, 159, 23);
		contentPane.add(amount_of_deposit_label);
		
		JButton backbutton = new JButton("Back");
		backbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Menu men=new Menu(Login);
				men.setVisible(true);
				
			}
		});
		backbutton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		backbutton.setBounds(169, 181, 89, 23);
		contentPane.add(backbutton);
		
	}
}
