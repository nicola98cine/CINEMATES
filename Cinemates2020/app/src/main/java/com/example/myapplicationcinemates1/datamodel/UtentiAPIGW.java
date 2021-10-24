package com.example.myapplicationcinemates1.datamodel;

import com.example.myapplicationcinemates1.ApiGateway;

import java.util.ArrayList;
//import java.util.ArrayList;

public class UtentiAPIGW extends ApiGateway implements UtentiDAO {

    public UtentiAPIGW(boolean test) {
        super(test);
    }

    public UtentiModel getUtente(String userid){
        return super.getUtente(userid , "UTENTI");
    }
    public void addUtente(UtentiModel u){
        super.addUtente(u);
    }
    public void modifyUtente(String userid, String nomelistapers) {
        super.ModifyUtente(userid, nomelistapers,"UTENTI");
    }
    public ArrayList<UtentiModel> getListaUtenti(String User){
      return  super.getListaUtenti(User, "UTENTI");
    }
    public void addlogin(String userid, String datatime, String azione){
        super.addLogin(userid,datatime,azione);
    }
}

