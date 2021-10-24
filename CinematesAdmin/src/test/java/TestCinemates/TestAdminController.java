package TestCinemates;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.JPasswordField;
import javax.swing.JTextField;
//import org.joda.time.DateTime;


import com.amazonaws.samples.AdminController;
import com.amazonaws.samples.LoginController;


class TestAdminController {
	
	AdminController adc;
	JTextField textdata01;
	JTextField textdata02;
    LoginController lgc;
    JTextField username;
    JPasswordField pw ;

		
	@BeforeEach
	void setUp() throws Exception {
		//junit.jupiter.execution.parallel.enabled = true;
		adc=new AdminController();
		assertNotNull(adc);
		textdata01=new JTextField();
		textdata02=new JTextField();		
		lgc=new LoginController();
		assertNotNull(lgc);
		username=new JTextField();
		pw=new JPasswordField();


	}

	@AfterEach
	void tearDown() throws Exception {
		adc=null;
		assertNull(adc);
		lgc=null;
		assertNull(lgc);
		
	}

	@Test
	public void test001() {
		System.out.println("VERIFICA LOGIN TEST JUNIT001:UTENTE ESISTENTE");
		username.setText("luigi.rossi@alice.it");
		pw.setText("pinco.123");
        boolean lg=lgc.login(username , pw);
		assertTrue("METODO LOGIN non corretto : Login non andata a buon fine con Dati corretti",lg);
	}

	@Test
	public void test002() {
		System.out.println("VERIFICA LOGIN TEST JUNIT002:UTENTE ESISTENTE PWD NON CORRETTA");
		username.setText("luigi.rossi@alice.it");
		pw.setText("pinco123");
        boolean lg=lgc.login(username , pw);
		assertFalse("METODO LOGIN non corretto : Login OK con Dati non corretti", lg);
	}

	@Test
	public void test003() {
		System.out.println("VERIFICA LOGIN TEST JUNIT003:UTENTE NON ESISTENTE");
		username.setText("luigi_rossi@alice.it");
		pw.setText("pinco.123");
        boolean lg=lgc.login(username , pw);
		assertFalse("METODO LOGIN non corretto : Login OK con Dati non corretti", lg);
	}
	
	@Test
	public void test004() {
		System.out.println("VALIDA DATE CASO DI TEST JUNIT004: DATE CORRETTE");
		textdata01.setText("2021-10-01 10:00:00");
		textdata02.setText("2021-10-15 10:00:00");
		long diffdate=adc.validaDate(textdata01, textdata02);
		assertEquals("METODO valida date non corretto",14,diffdate);
	}	

	@Test
	public void test005() {
	
		System.out.println("VALIDA DATE CASO DI TEST JUNIT005: DATA01 NON CORRETTA");
		textdata01.setText("2021 12-01 40:00:00");
		textdata02.setText("2021-10-15 10:00:00");
		long diffdate2=adc.validaDate(textdata01, textdata02);
		assertEquals("METODO valida date non corretto",-1,diffdate2);
	}	
	
	@Test
	public void test006() {
		System.out.println("VALIDA DATE CASO DI TEST JUNIT006: DATA02 NON CORRETTA");
		textdata01.setText("2021-12-01 10:00:00");
		textdata02.setText("2021-12-45 10:00:00");
		long diffdate3=adc.validaDate(textdata01, textdata02);
		assertEquals("METODO valida date non corretto",-1,diffdate3);
	}	

	@Test
	public void test007() {
		System.out.println("VALIDA DATE CASO DI TEST JUNIT007: DATA02 NON CORRETTA");
		textdata01.setText("2021-12-01 40:00:00");
		textdata02.setText("2021-16-15 10 00:00");
		long diffdate4=adc.validaDate(textdata01, textdata02);
		assertEquals("METODO valida date non corretto",-1,diffdate4);
	}	


	
	
}
