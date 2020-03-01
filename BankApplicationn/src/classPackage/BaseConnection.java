package classPackage;

import java.sql.Connection;
import java.sql.DriverManager;

public class BaseConnection {

	public static Connection getConnection() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			String SQLUser="root";
			String SQLPassword="1234567890";
			Connection connect=DriverManager.getConnection("jdbc:mysql://localhost:3306/account",SQLUser, SQLPassword);
			System.out.println("connected with datebase");
			return connect;
		}
		catch(Exception exc) {
			System.out.println(exc.getMessage());
			return null;
		}
	}
}
