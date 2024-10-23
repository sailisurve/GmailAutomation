package com.delete.mail.GmailAutomation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        String emailId ="sailisurve01@gmail.com";
        String password ="bvuo rrde ypjc ztlg";
        String host ="imap.gmail.com";
        String port="993";
    deleteMails(emailId,password,host,port);
    
    }
    
    public static void deleteMails(String emailId, String password, String host, String port) {
    	
    	Properties properties = new Properties();
    	properties.put("mail.imap.host", host);
    	properties.put("mail.imap.port", port);
    	properties.put("mail.imap.ssl.enable", "true");
//    	properties.put("mail.imap.ssl.trust", "*"); // Trust all certificates (for testing)
//    	properties.put("mail.imap.auth.plain.disable", "false"); // Allow plain authentication
    	properties.setProperty("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.setProperty("mail.imap.socketFactory.fallback", "false");
		properties.setProperty("mail.imap.socketFactory.port", String.valueOf(port));
		properties.put("mail.debug", "true");

		Session session = Session.getDefaultInstance(properties);
		try {
			
			Store store = session.getStore("imap");
			store.connect(emailId ,password );
			// opens the inbox folder
				
			Folder inbox=store.getFolder("Inbox");
			inbox.open(Folder.READ_WRITE);
			
			Folder folderBin = store.getFolder("[Gmail]/Trash");
		folderBin.open(Folder.READ_WRITE);
			//System.out.println(inbox.getMessages().length);
			
			Message[] messages =inbox.getMessages();
			
			//Map<String, Integer> map = new HashMap<String, Integer>();
			List<String> list= Arrays.asList("Login","photo");
	
			
			for(Message message : messages ) {
				String subject = message.getSubject();
				for(String item: list ) {
					if(subject.contains(item)) {
						inbox.copyMessages(new Message[] {message }, folderBin);
						System.out.print("Deleted message is "+ subject );
					}
						}
				}
//				if(map.containsKey(subject)) {
//					map.put(subject , map.get(subject)+1);
//					if(map.get(subject) > 3) {
//						System.out.print(subject);}
//					}
//				else {
//					map.put(subject, 1);
//				}
//					
//				}
				
				
			}

						// opens the trash folder
//						Folder folderBin = store.getFolder("[Gmail]/Trash");
//						folderBin.open(Folder.READ_WRITE);
//
//						
		catch(MessagingException e) {
			e.printStackTrace();
		}
		
//		catch (NoSuchProviderException ex) {
//			System.out.println("No provider.");
//			ex.printStackTrace();
		
    	
    	
    	
    }
}

