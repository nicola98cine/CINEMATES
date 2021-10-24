package com.amazonaws.samples;
//import java.awt.BorderLayout;
//import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.joda.time.DateTime;
//import org.json.JSONObject;
//import com.amazonaws.services.inspector.model.Locale;
import org.joda.time.Days;

//import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.SystemColor;

public class FrameConsole extends JFrame {

	public String datainiziale="";
	public String datafinale="";
	private JPanel contentPane;
	private JTextField textdataelab;
	private JTextField textdatainiz;
	private JTextField textdatafine;
	
	private JTextField textutenti;
	private JTextField textconnessi;
	private JTextField textaccessi;
	private JTextField textaccessigiorno;
	private JTextField textliste;
	private JTextField textricerche;
	private JTextField textfeed;
	private JTextField textfeedgiorno;
	/**
	 * Create the frame.
	 */

	
	private long validaDate() {
		long l=0;
		l=Main.ac.validaDate(textdatainiz,textdatafine);
		return l;
	}
	
	private void eseguiCalcoli(long l) {
	
		Main.ac.eseguiCalcoli(l,textdatainiz,textdatafine,
				textutenti, textaccessi, textfeed,textconnessi,
				textricerche, textliste, textaccessigiorno,textfeedgiorno) ;
	
	}
	
		
	public FrameConsole() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 681, 450);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 205));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitolo = new JLabel("CINEMATES CONSOLE AMMINISTRATIVA - GDL 04");
		lblTitolo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTitolo.setBounds(23, 0, 427, 30);
		contentPane.add(lblTitolo);
		
		JLabel lblutentiTotali = new JLabel("#TOTALE UTENTI ");
		lblutentiTotali.setBounds(23, 176, 123, 25);
		contentPane.add(lblutentiTotali);
		
		JLabel lblutentiConnessi_1 = new JLabel("#UTENTI CONNESSI");
		lblutentiConnessi_1.setBounds(23, 212, 128, 25);
		contentPane.add(lblutentiConnessi_1);
		
		JLabel lblmediaGiornaliera = new JLabel("MEDIA GIORNALIERA");
		lblmediaGiornaliera.setBounds(286, 212, 123, 25);
		contentPane.add(lblmediaGiornaliera);
		
		JLabel lblutentiAccessi = new JLabel("#TOTALE ACCESSI");
		lblutentiAccessi.setBounds(286, 176, 123, 25);
		contentPane.add(lblutentiAccessi);
		
		JLabel lbltotliste = new JLabel("#TOTALE LISTE");
		lbltotliste.setBounds(23, 250, 108, 25);
		contentPane.add(lbltotliste);
		
		JLabel lblRicerche = new JLabel("#TOTALE RICERCHE");
		lblRicerche.setBounds(23, 286, 123, 25);
		contentPane.add(lblRicerche);
		
		JLabel lblFeed = new JLabel("#TOTALE FEED");
		lblFeed.setBounds(289, 245, 108, 30);
		contentPane.add(lblFeed);
		
		textutenti = new JTextField();
		textutenti.setBounds(156, 178, 86, 20);
		contentPane.add(textutenti);
		textutenti.setColumns(10);
		
		textconnessi = new JTextField();
		textconnessi.setBounds(156, 214, 86, 20);
		contentPane.add(textconnessi);
		textconnessi.setColumns(10);
		
		textaccessi = new JTextField();
		textaccessi.setColumns(10);
		textaccessi.setBounds(448, 178, 86, 20);
		contentPane.add(textaccessi);
		
		textaccessigiorno = new JTextField();
		textaccessigiorno.setColumns(10);
		textaccessigiorno.setBounds(448, 214, 86, 20);
		contentPane.add(textaccessigiorno);
		
		textliste = new JTextField();
		textliste.setColumns(10);
		textliste.setBounds(156, 252, 86, 20);
		contentPane.add(textliste);
		
		textricerche = new JTextField();
		textricerche.setColumns(10);
		textricerche.setBounds(156, 286, 86, 20);
		contentPane.add(textricerche);
		
		textfeed = new JTextField();
		textfeed.setColumns(10);
		textfeed.setBounds(448, 250, 86, 20);
		contentPane.add(textfeed);
		
		textfeedgiorno = new JTextField();
		textfeedgiorno.setColumns(10);
		textfeedgiorno.setBounds(448, 286, 86, 20);
		contentPane.add(textfeedgiorno);
		
		textdataelab = new JTextField();
		textdataelab.setColumns(10);
		textdataelab.setBounds(480, 45, 140, 24);
		contentPane.add(textdataelab);
		
		JLabel lbldatainiz = new JLabel("DATA INIZIO RICERCA");
		lbldatainiz.setBounds(23, 89, 130, 25);
		contentPane.add(lbldatainiz);
		
		JLabel lbldatafine = new JLabel("DATA FINE RICERCA");
		lbldatafine.setBounds(327, 89, 130, 25);
		contentPane.add(lbldatafine);
		
		textdatainiz = new JTextField();
		textdatainiz.setColumns(10);
		textdatainiz.setBounds(156, 89, 140, 24);
		contentPane.add(textdatainiz);
		
		textdatafine = new JTextField();
		textdatafine.setColumns(10);
		textdatafine.setBounds(480, 89, 140, 24);
		contentPane.add(textdatafine);

		
		JLabel lbldataelab = new JLabel("DATA ELABORAZIONE");
		lbldataelab.setBounds(327, 41, 130, 25);
		contentPane.add(lbldataelab);
		
		JButton btnElabora = new JButton("ELABORA");
		btnElabora.setBounds(41, 346, 150, 38);
		contentPane.add(btnElabora);
		
		JLabel lblmediagio = new JLabel("MEDIA GIORNALIERA");
		lblmediagio.setBounds(289, 282, 135, 28);
		contentPane.add(lblmediagio);
		
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		//DateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd"); 
		
		DateTime dataOggi = new DateTime(new Date());
		DateTime datainiz = new DateTime(dataOggi).minusDays(35);
		
		System.out.println("data oggi: "+ sdf.format(dataOggi.toDate()));
		//String s=sdf2.format(dataOggi.toDate());
		//System.out.println("data oggi troncata: "+ s);
		
		datainiziale=sdf.format(datainiz.toDate());
		datafinale=sdf.format(dataOggi.toDate());
		
		textdataelab.setText(datainiziale);
		textdataelab.setEditable(false);
		textdatainiz.setText(datainiziale);
		textdatafine.setText(datafinale);
				
		
				
		btnElabora.addActionListener (new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("premuto bottone elabora");
				long result=validaDate();
				if (result>0) {
					eseguiCalcoli(result);
				}
			}
		});
				
	}
}
