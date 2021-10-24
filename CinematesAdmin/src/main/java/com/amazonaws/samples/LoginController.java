package com.amazonaws.samples;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

//import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.joda.time.DateTime;

public class LoginController {
	public static DAOFactory DAO;
	public static UtentiLambda u;
	
	public LoginController() {
        System.out.println("STEP01: Inizializzo la DAOFactory");
        DAO=DAOFactory.getDAOInstance();
		u=(UtentiLambda) DAO.getUtentiDAO();
		
	}

	boolean signup(JTextField textField_mail, JTextField textField_nome, JTextField textField_cognome, 
			JTextField textField_datanascita, JPasswordField passwordField_1) {
		boolean esito=false;
		
		String mail="";
		String nome="";
		String cognome="";
		String pw="";
		String datanascita="";
				
		System.out.println("valore campi="+textField_mail.getText() + ","+ textField_nome.getText()+","+textField_cognome.getText()+
				","+passwordField_1.getText());
				
		if (textField_mail.getText() !=null && !textField_mail.getText().isEmpty() ) {
			mail=textField_mail.getText().toString();
		}
		if (textField_nome.getText() !=null && !textField_nome.getText().isEmpty() ) {
			nome=textField_nome.getText().toString();
		}
		if (textField_cognome.getText() !=null && !textField_cognome.getText().isEmpty() ) {
			cognome=textField_cognome.getText().toString();
		}
		if (passwordField_1.getText() !=null && !passwordField_1.getText().isEmpty() ) {
			pw=passwordField_1.getText().toString();
		}
		if (textField_datanascita.getText() !=null && !textField_datanascita.getText().isEmpty() ) {
			datanascita=textField_datanascita.getText().toString();
		}

		System.out.println("valore campi="+mail + ","+nome+","+cognome+","+datanascita);
		
		if (mail.isEmpty()  || nome.isEmpty() || cognome.isEmpty() || 
				pw.isEmpty() ||  datanascita.isEmpty() ) {
			esito=false	;
			System.out.println("campo vuoto");
			JOptionPane.showMessageDialog(null, "REGISTRAZIONE TERMINATA CON ESITO NEGATIVO , REINSERIRE CAMPI FORM", 
					"InfoBox: " + "MESSAGGIO INFORMATIVO", JOptionPane.INFORMATION_MESSAGE);
			//System.out.println("valore campi="+textField_mail.getText() + ","+textField_nome.getText()+","+textField_cognome.getText()+
			//		","+textField_datanascita.getText());

			textField_mail.setText("");
			textField_nome.setText("");
			textField_cognome.setText("");
			passwordField_1.setText("");
			textField_datanascita.setText("");
		
		}
		else if (!mail.contains("@") ) {
			esito=false	;
			System.out.println("mail erronea");
			JOptionPane.showMessageDialog(null, "REGISTRAZIONE KO: MAIL ERRONEA , REINSERIRE CAMPI FORM", 
					"InfoBox: " + "MESSAGGIO INFORMATIVO", JOptionPane.INFORMATION_MESSAGE);
			//System.out.println("valore campi="+textField_mail.getText() + ","+textField_nome.getText()+","+textField_cognome.getText()+
			//		","+textField_datanascita.getText());

			textField_mail.setText("");
			textField_nome.setText("");
			textField_cognome.setText("");
			passwordField_1.setText("");
			textField_datanascita.setText("");
			
		}
		else {
			System.out.println("registrazione corretta");
			JOptionPane.showMessageDialog(null, "REGISTRAZIONE CONCLUSA CON ESITO POSITIVO ", 
					"InfoBox: " + "MESSAGGIO INFORMATIVO", JOptionPane.INFORMATION_MESSAGE);
			esito=true;
			textField_nome.setText("");
			textField_cognome.setText("");
			passwordField_1.setText("");
			
			UtentiModel us= new UtentiModel(mail , nome , cognome, pw , datanascita, "Admin");
			try {
				//UtentiLambda u=(UtentiLambda) DAO.getUtentiDAO();
				String s=u.addUtente(us );
				System.out.println(s);
			  }
			catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			   }
			
		}
	
		return esito;
	}

	public boolean login(JTextField textField_username, JPasswordField passwordField) {
		boolean esito=true;
		String mail="";
		String pw="";
		System.out.println("LOGINMETODO");
				
		if (textField_username.getText() !=null && !textField_username.getText().isEmpty() ) {
			mail=textField_username.getText().toString();
		}
		
		if (passwordField.getText() !=null && !passwordField.getText().isEmpty() ) {
			pw=passwordField.getText().toString();
		}
	
		
		if (mail.isEmpty() || pw.isEmpty() ) {
			esito=false	;
			System.out.println("campo vuoto");
			JOptionPane.showMessageDialog(null, "LOGIN TERMINATO CON ESITO NEGATIVO , REINSERIRE CAMPI FORM", 
					"InfoBox: " + "MESSAGGIO INFORMATIVO", JOptionPane.INFORMATION_MESSAGE);
			System.out.println("valore campi="+textField_username.getText() + ","+passwordField.getText());
			textField_username.setText("");
			passwordField.setText("");
		
		}
		else if (!mail.contains("@") ) {
			esito=false	;
			System.out.println("mail erronea");
			JOptionPane.showMessageDialog(null, "LOGIN KO: MAIL ERRONEA , REINSERIRE CAMPI FORM", 
					"InfoBox: " + "MESSAGGIO INFORMATIVO", JOptionPane.INFORMATION_MESSAGE);
			//System.out.println("valore campi="+textField_mail.getText() + ","+textField_nome.getText()+","+textField_cognome.getText()+
			//		","+textField_datanascita.getText());

			textField_username.setText("");			
			passwordField.setText("");
		}
		else {
			System.out.println("login corretto");
			
			
			try {
				//DAOFactory d=new DAOFactory();
				//UtentiLambda u=(UtentiLambda) DAO.getUtentiDAO();
				String s1=u.getUtente( mail);
				System.out.println("pwdatabase"+s1+",pw="+pw);
				esito=s1.equals(pw);
				if (esito) {
					JOptionPane.showMessageDialog(null, "LOGIN TERMINATA CON ESITO POSITIVO", "InfoBox: " + "MESSAGGIO INFORMATIVO", 
							JOptionPane.INFORMATION_MESSAGE);
					esito=true;
					textField_username.setText("");
					passwordField.setText("");
				
				}
				else {
					esito=false;
					textField_username.setText("");
					passwordField.setText("");
					JOptionPane.showMessageDialog(null, "LOGIN TERMINATA CON ESITO NEGATIVO: CREDENZIALI NON VALIDE", "InfoBox: " + "MESSAGGIO INFORMATIVO", JOptionPane.INFORMATION_MESSAGE);
					
				}
			  }
			catch (Exception e1) {
				// TODO Auto-generated catch block
				esito=false;
				e1.printStackTrace();
			   }
		}
		
		return esito;
	}

	
}
