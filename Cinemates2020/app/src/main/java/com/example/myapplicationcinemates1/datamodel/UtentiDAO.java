package com.example.myapplicationcinemates1.datamodel;
import com.example.myapplicationcinemates1.datamodel.FilmModel;
import com.example.myapplicationcinemates1.datamodel.UtentiModel;
import java.util.ArrayList;

public interface UtentiDAO {
    public UtentiModel getUtente(String userid);
    public void addUtente(UtentiModel u);
    public void modifyUtente(String userid, String nomelistapers);
    public void addlogin(String userid, String datatime, String azione);
    public ArrayList<UtentiModel> getListaUtenti(String User);
}
