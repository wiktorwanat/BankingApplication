package classPackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class AccountBalanceOperation {
	
	public static void change_account_balance(String Login,double amount,String operation) {
		//String operation - could be "-" or "+" to make this function useful also in Transfer.java
		try {
			Connection connect_with_base =BaseConnection.getConnection();
			String basequery="update account.users set AccountBalance=AccountBalance"+operation+"\""+amount+"\" where Login like \""+Login+"\";";
			PreparedStatement statement=connect_with_base.prepareStatement(basequery);
			statement.executeUpdate();
			System.out.println(Login+" account balance has changed by "+operation+" "+amount);
			JOptionPane.showMessageDialog(null, Login+" account balance "+operation+" "+amount);
		}catch(SQLException sqlexc) {
			System.out.println(sqlexc.getMessage());
		}
	}

}
