package com.amazonaws.samples;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

//import javax.swing.JFrame;
import javax.swing.JOptionPane;
//import javax.swing.JPasswordField;
import javax.swing.JTextField;
import org.joda.time.DateTime;

public class AdminController {

	public AdminController() {
	}


	public long validaDate (JTextField textdatainiz,JTextField textdatafine) {
		String data1="";
		String data2="";
		String datainiziale="";
		String datafinale="";
		
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		//DateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd"); 
		
		DateTime dataOggi = new DateTime(new Date());
		DateTime datainiz = new DateTime(dataOggi).minusDays(35);
		
		
		
		datainiziale=sdf.format(datainiz.toDate());
		datafinale=sdf.format(dataOggi.toDate());

		
		boolean esito=true;
		try {
			data1=textdatainiz.getText();
			data2=textdatafine.getText();
			String a1=data1.substring(0,3);		//nust be 202
			String a2=data2.substring(0,3);
		
		String b1=data1.substring(4,5);		//must be -
		String b2=data2.substring(4,5);
		String c1=data1.substring(7,8);		//must be -
		String c2=data2.substring(7,8);
		String d1=data1.substring(10,11);	//must be " "
		String d2=data2.substring(10,11);
		
		String e1=data1.substring(13,14);	//must be :
		String e2=data2.substring(13,14);
		String f1=data1.substring(16,17);	//must be :
		String f2=data2.substring(16,17);
		
		String g1=data1.substring(3,4);
		String g2=data2.substring(3,4);
		String h1=data1.substring(5,7);
		String h2=data2.substring(5,7);
		String l1=data1.substring(8,10);
		String l2=data2.substring(8,10);
		String m1=data1.substring(11,13);
		String m2=data2.substring(11,13);
		String n1=data1.substring(14,16);
		String n2=data2.substring(14,16);
		String o1=data1.substring(17,19);
		String o2=data2.substring(17,19);
				
		//2021-08-19 12:28:10
		
			if (!a1.equals("202") || !a2.equals("202") ) {
				esito=false;
			}
			else if (!b1.equals("-") || !b2.equals("-") || !c1.equals("-") || !c2.equals("-")) {
				esito=false;
			}
			else if (!d1.equals(" ") || !d2.equals(" ")) {
				esito=false;
			}
			else if (!e1.equals(":") || !e2.equals(":") || !f1.equals(":") || !f2.equals(":")) {
				esito=false;
			}
			else if (!isNumeric(g1) || !isNumeric(g2) || !isNumeric(h1) || !isNumeric(h2)) {
				esito=false;
			}
			else if (!isNumeric(l1) || !isNumeric(l2) || !isNumeric(m1) || !isNumeric(m2)) {
				esito=false;
			}
			else if (!isNumeric(n1) || !isNumeric(n2) || !isNumeric(o1) || !isNumeric(o2)) {
				esito=false;
			}
			if (esito) {
				System.out.println("h1,h2,l1,l2="+h1+","+h2+","+l1+","+l2);
				if (Integer.parseInt(h1)>12 || Integer.parseInt(h2)>12  ) {
					esito=false;
				}
				if (Integer.parseInt(l1)>31 || Integer.parseInt(l2)>31  ) {
					esito=false;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			esito=false;
		}
		if (!esito) {
				System.out.println("impostazione date erronea");
				JOptionPane.showMessageDialog(null, "IMPOSTAZIONE DATE INIZIO O FINE NON FORMALMENTE VALIDE, vengono RIPRISTINATE AI VALORI DI DEFAULT", 
					"InfoBox: " + "MESSAGGIO INFORMATIVO",	JOptionPane.INFORMATION_MESSAGE);
			textdatainiz.setText(datainiziale);
			textdatafine.setText(datafinale);
			esito=true;
			return -1;
	
		}
		  //LocalDate date = LocalDate.now();

		long numg=-1;
		long diff=0;
		long minutes=0;
		Date data001=null;
		Date data002=null;
		if (esito) {
	        SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        try {
	        	data001 = dateParser.parse(data1);
	        	data002 = dateParser.parse(data2);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }    
	        diff = data002.getTime() - data001.getTime();
	        minutes = TimeUnit.MILLISECONDS.toMinutes(diff);
	        numg=minutes/(60*24);
	        System.out.println("numg="+numg);
		}
		  
		return numg;
	}

	private boolean isNumeric(String s1) {
		boolean esito=true;
		String a="";
		String b="";
		int l=s1.length();
		a=s1.substring(0,1);
		if (l>1) {
			b=s1.substring(1,2);
		}
		if (!a.equals("0") && !a.equals("1") && !a.equals("2") && !a.equals("3") && !a.equals("4")
				&& !a.equals("5") && !a.equals("6") && !a.equals("7") && !a.equals("8") && !a.equals("9")) {
			esito=false;
		}
		if (esito && l>1) { 		
			if (!b.equals("0") && !b.equals("1") && !b.equals("2") && !b.equals("3") && !b.equals("4")
				&& !b.equals("5") && !b.equals("6") && !b.equals("7") && !b.equals("8") && !b.equals("9")) {
				esito=false;
			}
		}
		
		  
		return esito;
	}

	public void eseguiCalcoli(long l,JTextField textdatainiz,JTextField textdatafine,
			JTextField textutenti,JTextField textaccessi, JTextField textfeed,JTextField textconnessi,
			JTextField textricerche,JTextField textliste, JTextField textaccessigiorno,JTextField textfeedgiorno) {
		try {
			//DAOFactory d=new DAOFactory();
			//UtentiLambda d=(UtentiLambda) DAO.getUtentiDAO();
			//UtentiLambda d=new UtentiLambda();

			String datainiz=textdatainiz.getText().toString();
			String datafine=textdatafine.getText().toString();
			
			int cut=LoginController.u.countUtenti();
			int cacc=LoginController.u.countAccessi(datainiz,datafine);
			int cfeed=LoginController.u.countFeed(datainiz,datafine);
			int ccon=LoginController.u.countConnessi(datainiz, datafine);
			int cric=LoginController.u.countRicerche(datainiz, datafine);
			int clis=LoginController.u.countListe();
			System.out.println(cut);
			String  ct=Integer.toString(cut);
			textutenti.setText(ct);			
			ct= Integer.toString(cacc);
			textaccessi.setText(ct);
			ct= Integer.toString(cfeed);
			textfeed.setText(ct);
			ct=Integer.toString(ccon);
			textconnessi.setText(ct);
			ct=Integer.toString(cric);
			textricerche.setText(ct);
			ct=Integer.toString(clis);
			textliste.setText(ct);
			if(l==0) {
				l++;
			}
			String media_accessi = Long.toString(cacc/l);
			textaccessigiorno.setText(media_accessi);
			String media_feed = Long.toString(cfeed/l);
			textfeedgiorno.setText(media_feed);
			
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	

	
	
}
