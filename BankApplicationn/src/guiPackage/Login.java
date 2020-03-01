package guiPackage;
import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import classPackage.BaseConnection;
import classPackage.UserDataCheck;

import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.Color;
import java.sql.*;
import javax.swing.JPasswordField;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField LoginBox;
	private JTextField PasswordBox;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	

	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 250));
		contentPane.setForeground(new Color(0, 100, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 76, 107, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Bank Application");
		lblNewLabel_1.setForeground(new Color(0, 128, 0));
		lblNewLabel_1.setBackground(new Color(0, 128, 0));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 29));
		lblNewLabel_1.setBounds(20, 11, 390, 43);
		contentPane.add(lblNewLabel_1);
		
		LoginBox = new JTextField();
		LoginBox.setBounds(20, 92, 390, 36);
		contentPane.add(LoginBox);
		LoginBox.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Password:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(10, 139, 91, 14);
		contentPane.add(lblNewLabel_2);
		
		PasswordBox = new JTextField();
		PasswordBox.setBounds(20, 166, 390, 38);
		contentPane.add(PasswordBox);
		
		JButton loginbutton = new JButton("Login");
		loginbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				if(UserDataCheck.CheckUserLoginData(LoginBox.getText(),PasswordBox.getText())) {
					JOptionPane.showMessageDialog(null,"Welcome in our system! ");
					System.out.println("login i haslo -correct");
					dispose();
					Menu men=new Menu(LoginBox.getText());
					men.setVisible(true);
				}
			}catch(Exception exc) {
				System.out.println(exc.getMessage());
			}
		}});
		loginbutton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		loginbutton.setBounds(40, 215, 146, 23);
		contentPane.add(loginbutton);
		
		JButton registrationbutton = new JButton("Create Account");
		registrationbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			Registration reg=new Registration();
			reg.setVisible(true);
			
			}
		});
		registrationbutton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		registrationbutton.setBounds(246, 217, 146, 23);
		contentPane.add(registrationbutton);
		

	}
}
