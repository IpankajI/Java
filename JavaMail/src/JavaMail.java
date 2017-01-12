import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;

import java.util.Properties;

import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class JavaMail {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final String username="kmrpnk@gmail.com";
		final String password="******";// change accordingly
		String host="smtp.gmail.com";
		String From="kmrpnk@gmail.com";
		Properties props=System.getProperties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");
		Session session=Session.getInstance(props, new javax.mail.Authenticator(){
			protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication(username,password);
			}
		});
		Message message=new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(From));
			InternetAddress[] addresses={new InternetAddress("pankaj.kumar.mec13@itbhu.ac.in"),
			new InternetAddress("fuyangchuyang@gmail.com")};	//recipient addresses
			message.addRecipients(Message.RecipientType.TO, addresses);
			message.setSubject("first mail with javamail api");
			
			BodyPart bodypart=new MimeBodyPart();
			bodypart.setText("this is not final");
			Multipart multipart=new MimeMultipart();
			multipart.addBodyPart(bodypart);
			bodypart=new MimeBodyPart();
			DataSource source=new FileDataSource("C:/Users/you/Desktop/workspace/main.cpp");
			bodypart.setDataHandler(new DataHandler(source));
			bodypart.setFileName("file");
			multipart.addBodyPart(bodypart);
			message.setContent(multipart);
			
			Transport.send(message);
			System.out.print("message sent successfully");
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
