package wave.spring.security;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.TimeZone;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import wave.spring.Constants.AdminConstantsI;
import wave.spring.Constants.SystemConstants;
import wave.spring.dao.SystemDao;
import wave.spring.dao.SystemDaoI;
import wave.spring.model.EmailDetails;
import wave.spring.model.VechileAttributes;

public class Security implements SecurityI {
	//added by Gaurav Srivastava
	public HashMap<String,String> generateCaptcha(){
		HashMap<String,String> map = new HashMap();
		StringBuffer sb = new StringBuffer();
		String[] alpha = {"a","b","c","d","e","0","1","2","3","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z",
				"A","B","#","C","D","E","F","G","H","I","J","K","L","M","N","$","&","@","#","O","P","Q","R","S","T","U","V","W","X","Y","Z",
				"0","1","2","3","4","5","6","7","8","9","1","9","4","8","4","%","@","0","3","&"};
		Random randoms = new Random();
		//sb.append(randoms.nextInt(80) + 1);
		for(int i = 0 ; i <5 ; i++) {                           // Random value selection from the above alpha String array
			sb.append(alpha[randoms.nextInt(80)]);
		}
		String captchaValue = sb.toString();
		map.put(SystemConstants.CAPTCHA, captchaValue);
		return map;
	}
	
	//added by Gaurav Sriavastava
	// encrypter
	public String valueEncrptyer(String value) {
		String message = "";
		String[] strValue = value.split("");
		for(String s : strValue) {
			if(s.equals("a") || s.equals("Z")) {
				message = message+"be5W8b";
			}else if(s.equals("b") || s.equals("Y")) {
				message = message+"Rnx9P2";
			}else if(s.equals("c") || s.equals("X")) {
				message = message+"rI1@An";
			}else if(s.equals("d") || s.equals("W")) {
				message = message+"Ne$l5y";
			}else if(s.equals("e") || s.equals("V")) {
				message = message+"*nO6RE";
			}else if(s.equals("f") || s.equals("U")) {
				message = message+"&Brn24";
			}else if(s.equals("g") || s.equals("T")) {
				message = message+"Ibr&et";
			}else if(s.equals("h") || s.equals("S")) {
				message = message+"PpLlR5";
			}else if(s.equals("i") || s.equals("R")) {
				message = message+"T%h#rb";
			}else if(s.equals("j") || s.equals("Q")) {
				message = message+"QAZplm";
			}else if(s.equals("k") || s.equals("P")) {
				message = message+"tysk%&";
			}else if(s.equals("l") || s.equals("O")) {
				message = message+"pl@oIi";
			}else if(s.equals("m") || s.equals("N")) {
				message = message+"rToles";
			}else if(s.equals("n") || s.equals("M")) {
				message = message+"trskPt";
			}else if(s.equals("o") || s.equals("L")) {
				message = message+"ul#UIi";
			}else if(s.equals("q") || s.equals("K")) {
				message = message+"u%%owy";
			}else if(s.equals("r") || s.equals("J")) {
				message = message+"%ysp%&";
			}else if(s.equals("s") || s.equals("I")) {
				message = message+"0l@U1i";
			}else if(s.equals("t") || s.equals("H")) {
				message = message+"169le%";
			}else if(s.equals("u") || s.equals("G")) {
				message = message+"aKvg%";
			}else if(s.equals("v") || s.equals("F")) {
				message = message+"#t8U0i";
			}else if(s.equals("w") || s.equals("E")) {
				message = message+"bT583%";
			}else if(s.equals("x") || s.equals("D")) {
				message = message+"tyKk%&";
			}else if(s.equals("y") || s.equals("C")) {
				message = message+"#lU&MIi";
			}else if(s.equals("z") || s.equals("B")) {
				message = message+"bT&3#%";
			}else if(s.equals("a") || s.equals("P")) {
				message = message+"837TtR%";
			}else if(s.equals("1") || s.equals("5")) {
				message = message+"029ut%";
			}else if(s.equals("2") || s.equals("6")) {
				message = message+"8iTyR%";
			}else if(s.equals("3") || s.equals("7")) {
				message = message+"pP7*tR";
			}else if(s.equals("4") || s.equals("8") || s.equals("0")) {
				message = message+"8kYr*6";
			}else if(s.equals("5") || s.equals("9")) {
				message = message+"837OoI";
			}else if(s.equals(" ") || s.equals("?")) {
				message = message+"&$Yu*I";
			}
		}
		return message;
	}
	
	public String sendMail(HashMap<String, String> map) {
		String MSG = "";
		LocalTime time = LocalTime.now();
		LocalDate date = LocalDate.now();
		String dateTime = time.toString()+" "+date.toString();
		EmailDetails emailDetails = new EmailDetails();
		try {
			String to = map.get(AdminConstantsI.TO);
			String from = SystemConstants.ADMIN_SENDER_MAIL_ID;
			String subject = map.get(AdminConstantsI.SUBJECT);
			final String password = SystemConstants.SENDER_PASSWORD;
			String msg = map.get(AdminConstantsI.MESSAGE);
			//to save mail details
			emailDetails.setSourceEmailId(from);
			emailDetails.setDestinationEmailId(to);
			emailDetails.setSubject(subject);
			emailDetails.setMessage(msg);
			emailDetails.setDateTime(dateTime);

			Properties props = new Properties();
			props.setProperty(SystemConstants.MAIL_TRANSPORT_PROTOCOL, "smtp");
			props.setProperty(SystemConstants.MAIL_HOST, SystemConstants.SMPT_GMAIL_COM);    
			props.put(SystemConstants.MAIL_SMTP_AUTH, SystemConstants.TRUE);         
			props.put(SystemConstants.MAIL_SMTP_PORT, "465");			
			props.put(SystemConstants.MAIL_DEBUG, SystemConstants.TRUE);       
			props.put(SystemConstants.MAIL_SMTP_SOCKET_FACTORY_PORT, "465");    
			props.put(SystemConstants.MAIL_SMTP_SOCKET_FACTORY_CLASS, SystemConstants.JAVAX_NET_SSL);     
			props.put(SystemConstants.MAIL_SMTP_SOCKET_FACTORY_FALL_BACK, SystemConstants.FALSE);    
			Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(from, password);
				}
			});

			// session.setDebug(true);
			Transport transport = session.getTransport();
			InternetAddress addressFrom = new InternetAddress(from);

			MimeMessage message = new MimeMessage(session);
			message.setSender(addressFrom);
			message.setSubject(subject);
			message.setContent(msg, "text/plain");
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			transport.connect();
			Transport.send(message);
			transport.close();
			MSG = SystemConstants.ACTIVE;
			emailDetails.setEmailStatus(AdminConstantsI.MAIL_SENT);		
		} catch (MessagingException mex) {
			mex.printStackTrace();
			MSG = SystemConstants.INACTIVE;
			emailDetails.setEmailStatus(AdminConstantsI.MAIL_WAITING);		
		}
		SystemDao systemDao = new SystemDao();
		try {
			systemDao.saveEmailDetails(emailDetails);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return MSG;
	}
	
	//added by Gaurav Srivastava
	public List<VechileAttributes> getVechileList(){
		List<VechileAttributes> vechiles = new ArrayList<VechileAttributes>();
		SystemDaoI systemDao = new SystemDao();
		vechiles = systemDao.getVechileList();
		return vechiles;
	}

}
