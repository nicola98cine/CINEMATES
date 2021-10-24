package com.example.myapplicationcinemates1.datamodel;

import com.example.myapplicationcinemates1.datamodel.FilmModel;
import java.util.ArrayList;

public interface FilmDAO {
    public ArrayList<FilmModel> getListaFilm(String userid, String tipoLista);
    public boolean addListaFilm(String userid, FilmModel f, String tipoLista);
    public void removeListaFilm(String userid, String idFilm,String tipoLista);
}
