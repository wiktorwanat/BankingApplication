package guiPackage;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import classPackage.AccountBalanceOperation;
import classPackage.BaseConnection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Transfer extends JFrame {

	private JPanel contentPane;
	private JTextField firstname_box;
	private JTextField accountnumber_box;
	private JTextField surname_box;
	private JTextField amount_box;


	public static boolean TransferMoney(String From,String send_to_FirstName,String send_to_Surname,String send_to_AccountNumber,Double amount) {
		boolean transfer=false;
		try {
		Connection connect=BaseConnection.getConnection();
		String str="select AccountBalance from account.users where Login like \""+From+"\";";
		PreparedStatement statement=connect.prepareStatement(str);
		ResultSet result=statement.executeQuery();
		if(result.next()) {
			if(result.getDouble(1)>=amount) {
				AccountBalanceOperation.change_account_balance(From, amount, "-");
				String str3="update account.users set AccountBalance=AccountBalance+"+amount+" where FirstName like \""+send_to_FirstName+"\"and  LastName like \""+send_to_Surname+"\"and AccountNumber like \""+send_to_AccountNumber+"\";";
				statement=connect.prepareStatement(str3);
				statement.executeUpdate();
				transfer=true;
				System.out.println("transfer done!");
			}
			else {
				System.out.println("You don't have enough money to make that transfer!");
			}
		}
		}catch(SQLException sqlexc) {
			System.out.println(sqlexc.getMessage());
		}
		return transfer;
	}
	
	public Transfer(String Login) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 471);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel transfer_table = new JLabel("Transfer");
		transfer_table.setHorizontalAlignment(SwingConstants.CENTER);
		transfer_table.setForeground(new Color(0, 128, 0));
		transfer_table.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 29));
		transfer_table.setBackground(Color.WHITE);
		transfer_table.setBounds(10, 11, 414, 38);
		contentPane.add(transfer_table);
		
		JLabel FirstName_label = new JLabel("Firstname:");
		FirstName_label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		FirstName_label.setBounds(10, 79, 95, 14);
		contentPane.add(FirstName_label);
		
		firstname_box = new JTextField();
		firstname_box.setBounds(51, 104, 334, 20);
		contentPane.add(firstname_box);
		firstname_box.setColumns(10);
		
		JLabel Surname_label = new JLabel("Surname:");
		Surname_label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Surname_label.setBounds(10, 142, 95, 14);
		contentPane.add(Surname_label);
		
		JLabel Accountnumber_label = new JLabel("Account Number");
		Accountnumber_label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Accountnumber_label.setBounds(10, 205, 136, 14);
		contentPane.add(Accountnumber_label);
		
		accountnumber_box = new JTextField();
		accountnumber_box.setColumns(10);
		accountnumber_box.setBounds(51, 230, 334, 20);
		contentPane.add(accountnumber_box);
		
		surname_box = new JTextField();
		surname_box.setColumns(10);
		surname_box.setBounds(51, 174, 334, 20);
		contentPane.add(surname_box);
		
		JLabel amount_label = new JLabel("Amount:");
		amount_label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		amount_label.setBounds(10, 265, 136, 14);
		contentPane.add(amount_label);
		
		amount_box = new JTextField();
		amount_box.setColumns(10);
		amount_box.setBounds(51, 290, 334, 20);
		contentPane.add(amount_box);
		
		JButton sendmoney_button = new JButton("Send Money!");
		sendmoney_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				if(TransferMoney(Login,firstname_box.getText(),surname_box.getText(),accountnumber_box.getText(),Double.parseDouble(amount_box.getText()))) {
					JOptionPane.showMessageDialog(null,"Money send successfully");
					dispose();
					Menu men=new Menu(Login);
					men.setVisible(true);
					
				}else {
					JOptionPane.showMessageDialog(null,"Check the recipient's data,or your account balance-maybe you don't have enough money! ");
				}
			}catch(NumberFormatException nfe) {
				System.out.println(nfe.getMessage());
				JOptionPane.showMessageDialog(null, "Amount need to be integer or floating point!");
				
			}
		}});
		sendmoney_button.setFont(new Font("Tahoma", Font.PLAIN, 18));
		sendmoney_button.setBounds(128, 338, 150, 23);
		contentPane.add(sendmoney_button);
		
		JButton backbutton = new JButton("Back");
		backbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Menu mn=new Menu(Login);
				mn.setVisible(true);
			}
		});
		backbutton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		backbutton.setBounds(161, 382, 89, 23);
		contentPane.add(backbutton);
		
	}
}
