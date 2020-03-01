package classPackage;

import java.util.Properties;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.PasswordAuthentication;




public class Mail {
	
	//function send email through Gmail SSL , need to give access to your email from which you send to "less secure apps"
	public static void SendEmail(String sendto,String FirstName) {
		String sendfrom="add there your email";
		String Password="add there your email account password";
		Properties prop=new Properties();
		prop.put("mail.smtp.host","smtp.gmail.com");
		prop.put("mail.smtp.socketFactory.port", "465");    
        prop.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");    
        prop.put("mail.smtp.auth", "true");    
        prop.put("mail.smtp.port", "465");   
        Session session=Session.getDefaultInstance(prop, new javax.mail.Authenticator(){protected PasswordAuthentication getPasswordAuthentication(){ return new PasswordAuthentication(sendfrom,Password);}});

        String sub="Banking Application";
        String msg="Hi "+FirstName+"! Thank's for your registration in our banking application!";
        try {
        	System.out.println("email sie wysyla");
        	MimeMessage message=new MimeMessage(session);
        	message.addRecipient(Message.RecipientType.TO,new InternetAddress(sendto));
        	message.setSubject(sub);
        	message.setText(msg);
        	Transport.send(message);
    	
        }catch(MessagingException messexc) {
        	throw new RuntimeException(messexc);
        }
	}

}
