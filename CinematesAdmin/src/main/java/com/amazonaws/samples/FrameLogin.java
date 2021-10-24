package com.amazonaws.samples;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JPasswordField;
import com.amazonaws.samples.UtentiModel;


public class FrameLogin extends JFrame {
	static FrameConsole f2;
    
	private JPanel contentPane;
	private JTextField textField_username;
	private JTextField textField_mail;
	private JTextField textField_nome;
	private JTextField textField_Dataelab;
	private JTextField textField_cognome;
	private JPasswordField passwordField;
	private JTextField textField_datanascita;
	private JPasswordField passwordField_1;
	

	 
	private boolean signup() {
		return Main.lc.signup( textField_mail,  textField_nome, textField_cognome, textField_datanascita, passwordField_1);
	}

	private boolean login() {
		return Main.lc.login ( textField_username,  passwordField);
	}

 
		/**
	 * Create the frame
	 */
	public FrameLogin() {
		final int altezzatxtf=25;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 707, 457);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 205));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone( "Europe/Rome"),java.util.Locale.ITALY);
		Date dataOggi = calendar.getTime();
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		System.out.println("data oggi: "+ sdf.format(dataOggi));
		
		
		JLabel lblTitolo = new JLabel("CINEMATES CONSOLE AMMINISTRATIVA - GDL 04");
		lblTitolo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTitolo.setBounds(23, 0, 427, 30);
		contentPane.add(lblTitolo);
		
		JLabel lblLogin = new JLabel("ESEGUI LOGIN");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLogin.setBounds(23, 90, 123, altezzatxtf);
		contentPane.add(lblLogin);
		
		JLabel lblSignup = new JLabel("ESEGUI SIGNUP");
		lblSignup.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSignup.setBounds(304, 90, 131, altezzatxtf);
		contentPane.add(lblSignup);
		
		JLabel lblusername = new JLabel("USERNAME");
		lblusername.setBounds(23, 155, 78, altezzatxtf);
		contentPane.add(lblusername);
		
		JLabel lblpwd = new JLabel("PASSWORD");
		lblpwd.setBounds(23, 190, 78, altezzatxtf);
		contentPane.add(lblpwd);
		
		JLabel lblnome = new JLabel("NOME");
		lblnome.setBounds(314, 190, 68, altezzatxtf);
		contentPane.add(lblnome);
		
		JLabel lblcognome = new JLabel("COGNOME");
		lblcognome.setBounds(314, 225, 95, altezzatxtf);
		contentPane.add(lblcognome);
		
		JLabel lblpwd2 = new JLabel("PASSWORD");
		lblpwd2.setBounds(312, 260, 78, altezzatxtf);
		contentPane.add(lblpwd2);

		JLabel lblMail = new JLabel("MAIL ADDRESS");
		lblMail.setBounds(314, 155, 103, altezzatxtf);
		contentPane.add(lblMail);
		
		
		JLabel lblDatanasc = new JLabel("DATANASCITA");
		lblDatanasc.setBounds(314, 295, 95, altezzatxtf);
		contentPane.add(lblDatanasc);

		textField_username = new JTextField();
		textField_username.setBounds(111, 155, 183, altezzatxtf);
		contentPane.add(textField_username);
		textField_username.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(111, 190, 183, altezzatxtf);
		contentPane.add(passwordField);

		
		textField_mail = new JTextField();
		textField_mail.setColumns(10);
		textField_mail.setBounds(427, 155, 191, altezzatxtf);
		contentPane.add(textField_mail);
		
		textField_nome = new JTextField();
		textField_nome.setColumns(10);
		textField_nome.setBounds(427, 190, 191, altezzatxtf);
		contentPane.add(textField_nome);

		textField_cognome = new JTextField();
		textField_cognome.setColumns(10);
		textField_cognome.setBounds(427, 225, 191, altezzatxtf);
		contentPane.add(textField_cognome);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(427, 260, 191, altezzatxtf);
		contentPane.add(passwordField_1);
		
		textField_datanascita = new JTextField();
		textField_datanascita.setColumns(10);
		textField_datanascita.setBounds(427, 295, 191, altezzatxtf);
		contentPane.add(textField_datanascita);
		

		textField_Dataelab = new JTextField();
		textField_Dataelab.setColumns(10);
		textField_Dataelab.setBounds(460, 38, 148, altezzatxtf);
		contentPane.add(textField_Dataelab);
		textField_Dataelab.setText(sdf.format(dataOggi));
		textField_Dataelab.setEditable(false);
		
		JLabel lblDataelab = new JLabel("DATA ELABORAZIONE");
		lblDataelab.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDataelab.setBounds(300, 32, 145, altezzatxtf);
		contentPane.add(lblDataelab);
		
		JButton btnSignup = new JButton("SIGNUP");
		btnSignup.setBounds(314, 356, 175, 38);
		contentPane.add(btnSignup);

		//LoginController lc=new LoginController();
		
		btnSignup.addActionListener (new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("premuto bottone Signup");
				signup();
				
			}
		});
		
				
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.setBounds(23, 247, 175, 38);
		contentPane.add(btnLogin);
		
		btnLogin.addActionListener (new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("premuto bottone Login");
				boolean esito=login();
				if (esito) {
			        f2=new FrameConsole();
					f2.setVisible(true);
				}
			
			}
		});
		
		
	}
}
