package classPackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class UserDataCheck {
	
	public static boolean checkUserName(String username) {
		boolean Loginfree=true;
		try {
			Connection Connect=BaseConnection.getConnection();
			String basequery="select Login from account.users where Login like '"+username+"';";
			PreparedStatement statement=Connect.prepareStatement(basequery);
			ResultSet result=statement.executeQuery();
			if(result.next()) {
				Loginfree=false;
			}
			else {
				System.out.println("Login already in use");
			}
			}catch(SQLException exc) {
				System.out.println(exc.getMessage());
			}
		return Loginfree;
		}

	public static boolean EmailValidation(String mail) {
		String regex="^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		Pattern patt_monkey=Pattern.compile(regex);
		Matcher m=patt_monkey.matcher(mail);
		return m.matches();
	}
	public static boolean CheckUserLoginData(String login,String password) {
		PreparedStatement statement;
		boolean DataCorrect=false;
		String basequery="SELECT * FROM account.users WHERE Login=? AND Password=?;";
		ResultSet result;
		
		try {
			statement=BaseConnection.getConnection().prepareStatement(basequery);
			statement.setString(1,login);
			statement.setString(2,password); 
			
			result=statement.executeQuery();
			if(result.next()) {
				DataCorrect=true;
			}
			else {
				System.out.println("wrong login or password");
				JOptionPane.showMessageDialog(null,"wrong password or login! ");
			}
		}catch(SQLException sqlexc) {
			sqlexc.getMessage();
		}
		return DataCorrect;
		
	}
}
