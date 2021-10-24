package com.example.myapplicationcinemates1.ui.davedere;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.myapplicationcinemates1.HomeActivity;
import com.example.myapplicationcinemates1.MainActivity;
import com.example.myapplicationcinemates1.TaskTMDB;
import com.example.myapplicationcinemates1.datamodel.FilmAPIGW;
import com.example.myapplicationcinemates1.datamodel.FilmModel;
import com.example.myapplicationcinemates1.datamodel.RichiesteAPIGW;

import java.util.ArrayList;

import static com.example.myapplicationcinemates1.MainActivity.DAO;

public class DavederePresenter {
    FilmAPIGW dv;
    String idFilm;
    public ArrayList<FilmModel> listatoseeintera;
    public ArrayList<FilmModel> listatosee;
    protected FilmModel f;
    Context c;

    public void setContext(Context c) {this.c=c;}
    public DavederePresenter(String idFilm ){
        dv= (FilmAPIGW) DAO.getFilmDAO();
        this.idFilm=idFilm;
        listatoseeintera=new ArrayList<FilmModel>();
        listatosee=new ArrayList<FilmModel>();
    }
    public void onClick(View v) {

        dv.RemoveListaFilm(HomeActivity.uid, idFilm , "LISTADAVEDERE");
        Toast.makeText(c,"il film e' stato eliminato", Toast.LENGTH_LONG).show();
    }
    public void setListaFilm(String Userid){
        listatoseeintera= dv.getListaFilm(Userid,"LISTADAVEDERE");
    }

    void copialiste() {
        listatosee.clear();
        for (int i=0;i<listatoseeintera.size();i++) {
            f=new FilmModel(listatoseeintera.get(i).getId(),listatoseeintera.get(i).getIView(),
                    listatoseeintera.get(i).getFilmName(), listatoseeintera.get(i).getFilmDescription(),
                    listatoseeintera.get(i).getFilmGenere());
            listatosee.add(f);
        }
    }

    void filtraliste(String t) {
        listatosee.clear();
        for (int i=0;i<listatoseeintera.size();i++) {
            if (listatoseeintera.get(i).getFilmName().toLowerCase().contains(t.toLowerCase())) {
                f=new FilmModel(listatoseeintera.get(i).getId(),listatoseeintera.get(i).getIView(),
                        listatoseeintera.get(i).getFilmName(), listatoseeintera.get(i).getFilmDescription(),
                        listatoseeintera.get(i).getFilmGenere());
                listatosee.add(f);
            }
        }
    }

}
