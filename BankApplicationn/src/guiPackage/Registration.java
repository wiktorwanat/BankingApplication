package guiPackage;
import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.Random;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import classPackage.AccountNumberGenerator;
import classPackage.BaseConnection;
import classPackage.Mail;
import classPackage.UserDataCheck;

public class Registration extends JFrame {

	private JPanel contentPane;
	private JTextField user;
	private JTextField password;
	private JTextField firstname;
	private JTextField surname;
	private JTextField email;





	
	public Registration() {
		
		String accountnumber=AccountNumberGenerator.setAccountNumber();
		int AccountNumber=Integer.parseInt(accountnumber);
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 593);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Registration");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setForeground(new Color(0, 128, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 29));
		lblNewLabel.setBounds(10, 11, 414, 38);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(20, 60, 105, 21);
		contentPane.add(lblNewLabel_1);
		
		user = new JTextField("username");
		user.setText("");
		user.setBounds(52, 88, 331, 26);
		contentPane.add(user);
		user.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Password:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(20, 125, 105, 14);
		contentPane.add(lblNewLabel_2);
		
		password = new JTextField("password");
		password.setText("");
		password.setBounds(52, 150, 331, 26);
		contentPane.add(password);
		password.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("First Name:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(20, 187, 105, 14);
		contentPane.add(lblNewLabel_3);
		
		firstname = new JTextField("firstname");
		firstname.setText("");
		firstname.setBounds(52, 213, 331, 26);
		contentPane.add(firstname);
		firstname.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Surname:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(20, 250, 105, 14);
		contentPane.add(lblNewLabel_4);
		
		surname = new JTextField("surname");
		surname.setText("");
		surname.setBounds(52, 270, 331, 26);
		contentPane.add(surname);
		surname.setColumns(10);
		
		
		JLabel lblNewLabel_5 = new JLabel("Address Email:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_5.setBounds(20, 307, 128, 14);
		contentPane.add(lblNewLabel_5);
		
		email = new JTextField("addressemail");
		email.setText("");
		email.setBounds(52, 330, 331, 26);
		contentPane.add(email);
		email.setColumns(10);
		
		JCheckBox firstcheckbutton = new JCheckBox("I allow to processed my data");
		firstcheckbutton.setHorizontalAlignment(SwingConstants.CENTER);
		firstcheckbutton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		firstcheckbutton.setBounds(52, 378, 292, 23);
		contentPane.add(firstcheckbutton);
		
		JCheckBox secondcheckbutton = new JCheckBox("I agreed with the Terms of Use");
		secondcheckbutton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		secondcheckbutton.setHorizontalAlignment(SwingConstants.CENTER);
		secondcheckbutton.setBounds(51, 417, 293, 23);
		contentPane.add(secondcheckbutton);
		
		JButton createbutton = new JButton("Create an account!");
		createbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(UserDataCheck.checkUserName(user.getText())) {
						
						if(UserDataCheck.EmailValidation(email.getText())) {
							
							if( firstcheckbutton.isSelected() && secondcheckbutton.isSelected()) {
	
									Connection conn=BaseConnection.getConnection();
									PreparedStatement PS=conn.prepareStatement("insert into users(Login,Password,AccountNumber,AccountBalance,FirstName,LastName,AddressEmail) values(?,?,?,?,?,?,?);");
									PS.setString(1,user.getText());
									PS.setString(2,password.getText());
									PS.setInt(3,AccountNumber);
									PS.setDouble(4, 0.0);
									PS.setString(5,firstname.getText());
									PS.setString(6,surname.getText());
									PS.setString(7,email.getText());
									
									
									int x=PS.executeUpdate();//return executed uptades
									if(x>0) {
										JOptionPane.showMessageDialog(null,"Account create saccessfully");
										Mail.SendEmail(email.getText(), firstname.getText());
										dispose();
										Login log=new Login();
										log.setVisible(true);
									}
									else {
										JOptionPane.showMessageDialog(null,"Account failed to create saccessfully");
										
									}
							}
							else {
								System.out.println("you need to agree with terms to create an account");
							}
						}else {
							System.out.println("your email is incorrect");
						}
					}else {
						System.out.println("your user is already in use");
					}
				}catch(Exception exc) {
					System.out.println(exc);
				}
			}
		});
		createbutton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		createbutton.setBounds(96, 478, 224, 23);
		contentPane.add(createbutton);
		JButton backbutton = new JButton("Back");
		backbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			Login log=new Login();
			log.setVisible(true);
			
			}
		});
		backbutton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		backbutton.setBounds(164, 512, 89, 23);
		contentPane.add(backbutton);
	}

}
