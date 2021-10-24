package com.example.myapplicationcinemates1.datamodel;

import com.example.myapplicationcinemates1.ApiGateway;

import java.util.ArrayList;

public class FilmAPIGW extends ApiGateway implements FilmDAO {

    public FilmAPIGW(boolean test) {
        super (test);
    }
    public ArrayList<FilmModel> getListaFilm(String userid, String tipoLista){
        return super.getListaFilm(userid,tipoLista);
    }
    public boolean addListaFilm(String userid, FilmModel f, String tipoLista) {
      return  super.addListaFilm(userid, f, tipoLista);

    }
    public void removeListaFilm(String userid, String idFilm, String tipoLista){
        super.RemoveListaFilm(userid,idFilm,tipoLista);
    }

}



