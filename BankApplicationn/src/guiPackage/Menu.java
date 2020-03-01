package guiPackage;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import classPackage.BaseConnection;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.awt.Color;
import java.awt.SystemColor;

public class Menu extends JFrame {

	private JPanel contentPane;
	private JTextField account_balance_box;
	private JTextField account_number_box;
	private JTextField ID_box;
	private JTextField welcome_label;


	public void setUserData(String Login) {
		try {
		Connection connect=BaseConnection.getConnection();
		String str="select AccountNumber,AccountBalance,UserID,FirstName from account.users where Login like '"+Login+"';";
		PreparedStatement statement=connect.prepareStatement(str);
		ResultSet rs=statement.executeQuery();
		if(rs.next()) {
			account_number_box.setText(Integer.toString((rs.getInt(1))));
			account_balance_box.setText(Double.toString(rs.getDouble(2)));
			ID_box.setText(Integer.toString(rs.getInt(3)));
			System.out.println(rs.getString(4));
			String str2="Welcome in our bank application "+rs.getString(4)+"!";
			welcome_label.setText(str2);
		}
		
		
		
		
	}catch(SQLException exception) {
		
		System.out.println(exception.getMessage());
		
	}
	} 
	public Menu(String Login) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 426, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel accountbalance_label = new JLabel("Your Account Balance");
		accountbalance_label.setForeground(new Color(0, 0, 0));
		accountbalance_label.setHorizontalAlignment(SwingConstants.CENTER);
		accountbalance_label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		accountbalance_label.setBounds(117, 84, 177, 14);
		contentPane.add(accountbalance_label);
		
		account_balance_box = new JTextField();
		account_balance_box.setEditable(false);
		account_balance_box.setBounds(58, 104, 300, 23);
		contentPane.add(account_balance_box);
		account_balance_box.setColumns(10);
		
		account_number_box = new JTextField();
		account_number_box.setEditable(false);
		account_number_box.setColumns(10);
		account_number_box.setBounds(58, 158, 300, 23);
		contentPane.add(account_number_box);
		
		JLabel AccountNumber_label = new JLabel("Your Account Number");
		AccountNumber_label.setHorizontalAlignment(SwingConstants.CENTER);
		AccountNumber_label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		AccountNumber_label.setBounds(117, 138, 177, 14);
		contentPane.add(AccountNumber_label);
		
		JLabel ID_label = new JLabel("Your user ID");
		ID_label.setHorizontalAlignment(SwingConstants.CENTER);
		ID_label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		ID_label.setBounds(117, 192, 177, 14);
		contentPane.add(ID_label);
		
		ID_box = new JTextField();
		ID_box.setEditable(false);
		ID_box.setColumns(10);
		ID_box.setBounds(117, 215, 177, 23);
		contentPane.add(ID_box);
		
		welcome_label = new JTextField();
		welcome_label.setForeground(new Color(0, 128, 0));
		welcome_label.setBackground(SystemColor.control);
		welcome_label.setHorizontalAlignment(SwingConstants.CENTER);
		welcome_label.setEditable(false);
		welcome_label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		welcome_label.setColumns(10);
		welcome_label.setBounds(10, 26, 390, 47);
		contentPane.add(welcome_label);
		
		setUserData(Login);
		
		JButton transferbutton = new JButton("Transfer");
		transferbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Transfer trf=new Transfer(Login);
				trf.setVisible(true);
			}
		});
		transferbutton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		transferbutton.setBounds(10, 271, 192, 23);
		contentPane.add(transferbutton);
		

		
		JButton accountsettingsbutton = new JButton("Account Settings");
		accountsettingsbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Settings sett=new Settings(Login);
				sett.setVisible(true);
			}
		});
		accountsettingsbutton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		accountsettingsbutton.setBounds(212, 271, 188, 23);
		contentPane.add(accountsettingsbutton);
		
		JButton logoutbutton = new JButton("Logout");
		logoutbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				System.out.println("you're logout");
				Login log=new Login();
				log.setVisible(true);
				
			}
		});
		logoutbutton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		logoutbutton.setBounds(212, 308, 188, 23);
		contentPane.add(logoutbutton);
		
		JButton deposit_button = new JButton("Deposit");
		deposit_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Deposit dep=new Deposit(Login);
				dep.setVisible(true);
			}
		});
		deposit_button.setFont(new Font("Tahoma", Font.PLAIN, 17));
		deposit_button.setBounds(10, 308, 192, 23);
		contentPane.add(deposit_button);
		
		
	}
}
