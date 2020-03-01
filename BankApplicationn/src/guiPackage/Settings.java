package guiPackage;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import classPackage.BaseConnection;
import classPackage.UserDataCheck;
import classPackage.AccountNumberGenerator;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Settings extends JFrame {

	private JPanel contentPane;
	private JTextField setting_username_box;
	private JTextField settings_password_box;
	private JTextField settings_firstname_box;
	private JTextField settings_surname_box;
	private JTextField settings_email_box;

	
	public void ShowAccountData(String Login) {
		try {
			Connection connect=BaseConnection.getConnection();
			String str="select FirstName,LastName,Password,AddressEmail from account.users where Login like '"+Login+"'";
			PreparedStatement statement=connect.prepareStatement(str);
			ResultSet result=statement.executeQuery();
			if(result.next()) {
				setting_username_box.setText(Login);
				settings_password_box.setText(result.getString(3));
				settings_firstname_box.setText(result.getString(1));
				settings_surname_box.setText(result.getString(2));
				settings_email_box.setText(result.getString(4));
				
			}
		}catch(SQLException exc) {
			System.out.println(exc.getMessage());
		}
	}
	
	
	public void setchanges(String Login) {
		try {
		Connection connect=BaseConnection.getConnection();
		String basestatement="update account.users set Login=\""+setting_username_box.getText()+"\",Password=\""+settings_password_box.getText()+"\",FirstName=\""+settings_firstname_box.getText()+"\",LastName=\""+settings_surname_box.getText()+"\",AddressEmail=\""+settings_email_box.getText()+"\" where Login=\""+Login+"\";";
		PreparedStatement PS=connect.prepareStatement(basestatement);
		
		PS.executeUpdate();
		System.out.println("changes set");
		}catch(SQLException exc) {
			System.out.println(exc.getMessage());
	}}
	
	
	public boolean ChangeAccountData(String Login) {
		boolean bool=false;

			if(UserDataCheck.EmailValidation(settings_email_box.getText())) {
				if(!setting_username_box.getText().equals(Login)) {

					if(UserDataCheck.checkUserName(setting_username_box.getText())) {
						setchanges(Login);
						bool=true;
					}
					else {bool=false;}
				}else {
					setchanges(Login);
					bool=true;
				}
			}
		return bool;
	}
	public void DeleteAccount(String Login) {
		try {
			Connection baseconnect=BaseConnection.getConnection();
			String basequery="delete from account.users where Login=\""+Login+"\";";
			PreparedStatement pst=baseconnect.prepareStatement(basequery);
			pst.executeUpdate();
			JOptionPane.showMessageDialog(null,"Account deleted");
			dispose();
			Login log=new Login();
			log.setVisible(true);
			
			
			}catch(SQLException exc){
				System.out.println(exc.getMessage());
			}
	}
	public Settings(String Login) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 521);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAccountSettings = new JLabel("Account Setting's");
		lblAccountSettings.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccountSettings.setForeground(new Color(0, 128, 0));
		lblAccountSettings.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 29));
		lblAccountSettings.setBackground(new Color(255, 255, 255));
		lblAccountSettings.setBounds(10, 11, 414, 38);
		contentPane.add(lblAccountSettings);
		
		JLabel setting_username = new JLabel("Username:");
		setting_username.setHorizontalAlignment(SwingConstants.CENTER);
		setting_username.setFont(new Font("Tahoma", Font.PLAIN, 18));
		setting_username.setBounds(10, 60, 105, 21);
		contentPane.add(setting_username);
		
		setting_username_box = new JTextField("");
		setting_username_box.setColumns(10);
		setting_username_box.setBounds(52, 84, 331, 26);
		contentPane.add(setting_username_box);
		
		JLabel settings_password = new JLabel("Password:");
		settings_password.setHorizontalAlignment(SwingConstants.CENTER);
		settings_password.setFont(new Font("Tahoma", Font.PLAIN, 18));
		settings_password.setBounds(10, 121, 105, 14);
		contentPane.add(settings_password);
		
		settings_password_box = new JTextField("");
		settings_password_box.setColumns(10);
		settings_password_box.setBounds(52, 146, 331, 26);
		contentPane.add(settings_password_box);
		
		JLabel settings_firstname = new JLabel("First Name:");
		settings_firstname.setHorizontalAlignment(SwingConstants.CENTER);
		settings_firstname.setFont(new Font("Tahoma", Font.PLAIN, 18));
		settings_firstname.setBounds(10, 183, 105, 14);
		contentPane.add(settings_firstname);
		
		settings_firstname_box = new JTextField("");
		settings_firstname_box.setColumns(10);
		settings_firstname_box.setBounds(52, 208, 331, 26);
		contentPane.add(settings_firstname_box);
		
		JLabel settings_surname = new JLabel("Surname:");
		settings_surname.setHorizontalAlignment(SwingConstants.CENTER);
		settings_surname.setFont(new Font("Tahoma", Font.PLAIN, 18));
		settings_surname.setBounds(10, 245, 105, 14);
		contentPane.add(settings_surname);
		
		settings_surname_box = new JTextField("");
		settings_surname_box.setColumns(10);
		settings_surname_box.setBounds(52, 270, 331, 26);
		contentPane.add(settings_surname_box);
		
		JLabel settings_email = new JLabel("Address Email:");
		settings_email.setFont(new Font("Tahoma", Font.PLAIN, 18));
		settings_email.setBounds(10, 307, 128, 14);
		contentPane.add(settings_email);
		
		settings_email_box = new JTextField("");
		settings_email_box.setColumns(10);
		settings_email_box.setBounds(52, 332, 331, 26);
		contentPane.add(settings_email_box);
		
		ShowAccountData(Login);
		
		JButton backbutton = new JButton("Back");
		backbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Menu mn=new Menu(Login);
				mn.setVisible(true);
			}
		});
		backbutton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		backbutton.setBounds(167, 447, 89, 23);
		contentPane.add(backbutton);
		
		JButton settings_delete_button = new JButton("Delete Account");
		settings_delete_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirm=JOptionPane.showConfirmDialog(null, "are you sure to delete your account? You will lose all of yours data and money !","Delete",JOptionPane.YES_NO_OPTION);
				if(confirm==0) {
				DeleteAccount(Login);
				}
			}
		});
		settings_delete_button.setFont(new Font("Tahoma", Font.PLAIN, 18));
		settings_delete_button.setBounds(129, 413, 175, 23);
		contentPane.add(settings_delete_button);
		
		JButton settings_savechanges_button = new JButton("Save changes");
		settings_savechanges_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ChangeAccountData(Login));{
				dispose();
				JOptionPane.showMessageDialog(null,"Your data have been changed,try to log in again with new data!");
				Login log=new Login();
				log.setVisible(true);
				}
			}
		});
		settings_savechanges_button.setFont(new Font("Tahoma", Font.PLAIN, 18));
		settings_savechanges_button.setBounds(129, 379, 175, 23);
		contentPane.add(settings_savechanges_button);
	}
}
