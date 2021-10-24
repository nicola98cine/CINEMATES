package com.amazonaws.samples;
import java.util.List;

public interface UtentiDAO {
	
	public int countUtenti() ; 
	public int countListe() ;
	public int countAccessi(String datainiz, String datafine) ;
	public int countFeed(String datainiz, String datafine) ;
	public int countRicerche(String datainiz, String datafine) ;
	public int countConnessi(String datainiz, String datafine) ;
	public String addUtente(UtentiModel u) ;
	public String getUtente(String  userid) ;
	
}
