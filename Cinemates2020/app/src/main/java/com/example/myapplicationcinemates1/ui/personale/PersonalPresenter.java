package com.example.myapplicationcinemates1.ui.personale;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplicationcinemates1.HomeActivity;
import com.example.myapplicationcinemates1.MainActivity;
import com.example.myapplicationcinemates1.PopUp;
import com.example.myapplicationcinemates1.datamodel.FilmAPIGW;
import com.example.myapplicationcinemates1.datamodel.FilmModel;
import com.example.myapplicationcinemates1.datamodel.RichiesteAPIGW;
import com.example.myapplicationcinemates1.datamodel.UtentiAPIGW;
import com.example.myapplicationcinemates1.datamodel.UtentiModel;

import java.util.ArrayList;

import static com.example.myapplicationcinemates1.MainActivity.DAO;

public class PersonalPresenter {
    protected FilmAPIGW  pa;
    protected UtentiAPIGW ua;

    public ArrayList<FilmModel> listapersintera;
    public ArrayList<FilmModel> listapers;
    protected FilmModel f;
    Context c;
    protected TextView t;

    String idFilm;

    public void setContext(Context c) {this.c=c;}

    public PersonalPresenter(String idFilm, Context c){
        this.idFilm=idFilm;
        pa = (FilmAPIGW) DAO.getFilmDAO();
        ua = (UtentiAPIGW) DAO.getUtentiDAO();
        listapersintera=new ArrayList<FilmModel>();
        listapers=new ArrayList<FilmModel>();
        this.c=c;
    }
    public void onClick(View v) {
        pa.RemoveListaFilm(HomeActivity.uid, idFilm , "LISTAPERSONALE");
        Toast.makeText(c,"il film e' stato eliminato", Toast.LENGTH_LONG).show();
    }

    public void setListaFilm(String Userid){
        listapersintera= pa.getListaFilm(Userid,"LISTAPERSONALE");
    }


    void copialiste() {
        listapers.clear();
        for (int i=0;i<listapersintera.size();i++) {
            f=new FilmModel(listapersintera.get(i).getId(),listapersintera.get(i).getIView(),
                    listapersintera.get(i).getFilmName(), listapersintera.get(i).getFilmDescription(),
                    listapersintera.get(i).getFilmGenere());
            listapers.add(f);
        }
    }

    void filtraliste(String t) {
        listapers.clear();
        for (int i=0;i<listapersintera.size();i++) {
            if (listapersintera.get(i).getFilmName().toLowerCase().contains(t.toLowerCase())) {
                f=new FilmModel(listapersintera.get(i).getId(),listapersintera.get(i).getIView(),
                        listapersintera.get(i).getFilmName(), listapersintera.get(i).getFilmDescription(),
                        listapersintera.get(i).getFilmGenere());
                listapers.add(f);
            }
        }
    }

    public void setTextView(TextView t){
        this.t=t;
    }

    String getNomeLista(String Userid) {
        String nomeLista="-1";
        UtentiModel u;
        u=ua.getUtente(Userid, "UTENTI");
        if (u==null) {
            Log.d(MainActivity.TAG,"Utente non trovato, uid="+Userid);
        }
        else {
            nomeLista=u.getListapers();
            Log.d(MainActivity.TAG,"Utente trovato, listapers="+listapers);
        }
        if (nomeLista.equals("0")) {
            String msg="LISTA NON ESISTENTE , INDICARE NOME";
            String nomelista="";
            PopUp p= new PopUp(c, msg);
            nomelista=p.PopUpDialog(Userid, t );
            Log.d(MainActivity.TAG,"nomelista acquisito nel pop-up Dialog="+nomelista);
        }

        return nomeLista;
    }

}
