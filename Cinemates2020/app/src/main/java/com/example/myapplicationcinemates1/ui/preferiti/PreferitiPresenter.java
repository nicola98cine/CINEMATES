package com.example.myapplicationcinemates1.ui.preferiti;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplicationcinemates1.HomeActivity;
import com.example.myapplicationcinemates1.MainActivity;
import com.example.myapplicationcinemates1.R;
import com.example.myapplicationcinemates1.datamodel.FilmAPIGW;
import com.example.myapplicationcinemates1.datamodel.FilmModel;
import com.example.myapplicationcinemates1.ui.richieste.RicezioneFragment;

import java.util.ArrayList;
import static com.example.myapplicationcinemates1.MainActivity.DAO;


public class PreferitiPresenter {

    FilmAPIGW fa;
    String idFilm;
    FilmModel f;
    public ArrayList<FilmModel> listaprefintera;
    public ArrayList<FilmModel> listapref;
    Context c;

    public void setContext (Context c ) {
        this.c=c;
    }

    public PreferitiPresenter(String idFilm){
        fa= (FilmAPIGW) DAO.getFilmDAO();
        this.idFilm=idFilm;
        this.c=c;
        listapref=new ArrayList<FilmModel>();
        listaprefintera=new ArrayList<FilmModel>();
    }

    public void onClick(View v) {
        fa.RemoveListaFilm(HomeActivity.uid, idFilm , "LISTAPREFERITI");
        Toast.makeText(c,"il film e' stato eliminato", Toast.LENGTH_LONG).show();

    }

    public void setListaFilm(String Userid){
        listaprefintera=fa.getListaFilm(Userid,"LISTAPREFERITI");
    }

    void copialiste() {
        listapref.clear();
        for (int i=0;i<listaprefintera.size();i++) {
            f=new FilmModel(listaprefintera.get(i).getId(),listaprefintera.get(i).getIView(),
                    listaprefintera.get(i).getFilmName(), listaprefintera.get(i).getFilmDescription(),
                    listaprefintera.get(i).getFilmGenere());
            listapref.add(f);
        }
    }

    void filtraliste(String t) {
       listapref.clear();
        for (int i=0;i<listaprefintera.size();i++) {
            if (listaprefintera.get(i).getFilmName().toLowerCase().contains(t.toLowerCase())) {
                f=new FilmModel(listaprefintera.get(i).getId(),listaprefintera.get(i).getIView(),
                        listaprefintera.get(i).getFilmName(), listaprefintera.get(i).getFilmDescription(),
                       listaprefintera.get(i).getFilmGenere());
               listapref.add(f);
            }
        }
    }
}
