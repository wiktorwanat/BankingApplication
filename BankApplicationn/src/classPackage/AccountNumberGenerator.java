package classPackage;

import java.util.Random;

public class AccountNumberGenerator {
	
	public static String setAccountNumber() {
		int n=0;
		String number="";
		Random value;
		for(int i=0;i<8;i++) {
			value=new Random();
				n=value.nextInt(10);
				number+=Integer.toString(n);
		}
		return number;
	}
}
