/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mailAgent;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import static mailAgent.MailExpert.generateMailMessage;
 
/**
 * @author suhag
 * 
 */
 
public class MailAutoPassword {
 
	static Properties mailServerProperties;
	static Session getMailSession;
	static MimeMessage generateMailMessage;
 
	public static void main(String args[]) throws AddressException, MessagingException {
//		generateAndSendEmail();
		System.out.println("\n\n ===> Your Java Program has just sent an Email successfully. Check your email..");
	}
 
	public static void generateAndSendEmail(String emailID, String password) throws AddressException, MessagingException {
 
            try {
                // Step1
                System.out.println("\n 1st ===> setup Mail Server Properties..");
                mailServerProperties = System.getProperties();
                mailServerProperties.put("mail.smtp.port", "587");
                mailServerProperties.put("mail.smtp.auth", "true");
                mailServerProperties.put("mail.smtp.starttls.enable", "true");
                System.out.println("Mail Server Properties have been setup successfully..");
                
                // Step2
                System.out.println("\n\n 2nd ===> get Mail Session..");
                getMailSession = Session.getDefaultInstance(mailServerProperties, null);
                generateMailMessage = new MimeMessage(getMailSession);
                generateMailMessage.setFrom(new InternetAddress("admin@potter.com", "Potter"));// add email credentials
                
                // add email and generate html for the mail
                generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailID));
//                for(String em: emailIDs)
//                {
//                    generateMailMessage.addRecipient(Message.RecipientType.BCC, new InternetAddress(em));
//                }
//		generateMailMessage.addRecipient(Message.RecipientType.BCC, new InternetAddress("suhagba@gmail.com"));
//                generateMailMessage.addRecipient(Message.RecipientType.BCC, new InternetAddress("puru.bhagat@yahoo.com"));
//                generateMailMessage.addRecipient(Message.RecipientType.BCC, new InternetAddress("adityakedia1995@gmail.com"));
                generateMailMessage.setSubject("Reset Password");
                String emailBody = "Please update your password once you log in" + "<br><br>" + "Your new auto generated password is "+password+" <br><br>Regards, <br>Mylo Admin";
                generateMailMessage.setContent(emailBody, "text/html");
                System.out.println("Mail Session has been created successfully..");
                
                // Step3
                System.out.println("\n\n 3rd ===> Get Session and Send mail");
                Transport transport = getMailSession.getTransport("smtp");
                
                // Enter your correct gmail UserID and Password
                // if you have 2FA enabled then provide App Specific Password
		transport.connect("smtp.gmail.com", "admin@potter.com", "oghnmosrspplzmwp");// add email credentials 
                
                
                transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
                transport.close();
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(MailAutoPassword.class.getName()).log(Level.SEVERE, null, ex);
            }
	}
}








