package com.amazonaws.samples;

public class UtentiModel {
	String Userid;
	String Nome;
	String Cognome;
	String Password;
	String DataNascita;
	String TipoUtente;

	
	UtentiModel( String Userid, String Nome,String Cognome,String Password, String DataNascita,String TipoUtente) {
		this.Userid=Userid;
		this.Nome=Nome;
		this.Cognome=Cognome;
		this.Password=Password;
		this.DataNascita=DataNascita;
		this.TipoUtente=TipoUtente;
		
	}
	
	public String getUserid() { return Userid; } 
	public String getNome() { return Nome; } 
	public String getCognome() { return Cognome; } 
	public String getPassword() {return Password;}
	public String getDataNascita() { return DataNascita; } 
	public String getTipo() { return TipoUtente; } 
	
	
	
}
