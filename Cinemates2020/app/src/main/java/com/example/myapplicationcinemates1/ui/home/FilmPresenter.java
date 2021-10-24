package com.example.myapplicationcinemates1.ui.home;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.example.myapplicationcinemates1.HomeActivity;
import com.example.myapplicationcinemates1.MainActivity;
import com.example.myapplicationcinemates1.PopUp;
import com.example.myapplicationcinemates1.R;
import com.example.myapplicationcinemates1.TaskTMDB;
import com.example.myapplicationcinemates1.datamodel.FeedAPIGW;
import com.example.myapplicationcinemates1.datamodel.FeedModel;
import com.example.myapplicationcinemates1.datamodel.FilmAPIGW;
import com.example.myapplicationcinemates1.datamodel.FilmModel;
import com.example.myapplicationcinemates1.datamodel.UtentiAPIGW;
import com.example.myapplicationcinemates1.datamodel.UtentiModel;

import org.apache.log4j.chainsaw.Main;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static android.os.SystemClock.sleep;
import static androidx.core.content.ContextCompat.*;
import static com.example.myapplicationcinemates1.MainActivity.DAO;
import static com.google.android.material.internal.ContextUtils.getActivity;

public class FilmPresenter {
    FilmAPIGW fa;
    FeedAPIGW fd;
    UtentiAPIGW ua;

    public TaskTMDB st;
    FeedModel fm;
    FilmModel f;
    DateFormat df;
    Context c;

    public void setContext (Context c ) {
        this.c=c;
    }

    public FilmPresenter(int  IdF, String img, String titolo, String trama) {
        fa= (FilmAPIGW) DAO.getFilmDAO();
        fd= (FeedAPIGW) DAO.getFeedDAO();
        ua= (UtentiAPIGW) DAO.getUtentiDAO();
        st= new TaskTMDB();
        st.onPreExecute();
        f= new FilmModel(IdF, img, titolo, trama, "");
    }

    protected void setListaIniziale() {
            st.a.clear();
            st.settipop(TaskTMDB.tipoper.iniziale);
            st.doInBackground();
            sleep(20);
    }

    protected void searchFilm(String t) {
            st.a.clear();
            st.setQuery(t);
            st.settipop(TaskTMDB.tipoper.search);
            st.doInBackground();
            sleep(20);
            if(st.a.size()==0){
                f=new FilmModel(0,null, "Mannaggia! nessuno film e' stato trovato","Ricerca altri titoli" , "");
                st.a.add(f);
            }

    }

    protected String getGenere() {
        String g="";
        st.setidf(f.getId());
        st.settipop(TaskTMDB.tipoper.getgenere);
        st.doInBackground();
        g=st.getgenere();
        Log.d(MainActivity.TAG,"genere="+g);
        f.setFilmGenere(g);
        return g;
    }

    public void addLogin(String uid)  {
        df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("Europe/Rome"));
        String time = df.format(new Date());
        ua.addLogin(uid,time,"LOGIN");
    }

    public UtentiModel getUtente(String uid)  {
        return ua.getUtente(uid);
    }


    protected void addFeed()  {
        df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("Europe/Rome"));
        String descr="l'utente ha cercato il film: " + f.getFilmName();
        String time = df.format(new Date());
        Log.d(MainActivity.TAG,"sysdate="+ time);
        fm=new FeedModel(HomeActivity.uid, HomeActivity.u.getnickname(),time,descr,"RICERCA");
        fd.addFeed(fm);
    }

    protected void onClickPref(View v) {
        FeedModel fm;
        Log.d(MainActivity.TAG,"premuto button preferiti");
        if (fa.addListaFilm(HomeActivity.uid, f, "LISTAPREFERITI")) {
            System.out.println("Visualizzo Toast Message Film gia presente");
            Toast toast=Toast.makeText(c, "IL FILM E' GIA PRESENTE NELLA LISTA", Toast.LENGTH_LONG);
            toast.show();
        }
        else {
            String descr = "l'utente ha aggiunto  il film: " + f.getFilmName() + "alla LISTA PREFERITI";
            String time = df.format(new Date());
            fm = new FeedModel(HomeActivity.uid, HomeActivity.u.getnickname(), time, descr, "ADDPREF");
            fd.addFeed(fm);
            System.out.println("Visualizzo Toast Message Film aggiunto");
            Toast.makeText(c, "IL FILE E'STATO AGGIUNTO ALLA LISTA" , Toast.LENGTH_LONG).show();
        }
    }
    protected void onClickVedere(View v) {
        FeedModel fm;
        Log.d(MainActivity.TAG,"premuto button davedere");
        if (fa.addListaFilm(HomeActivity.uid, f, "LISTADAVEDERE")) {
            Toast.makeText(c, "IL FILM E' GIA PRESENTE NELLA LISTA", Toast.LENGTH_LONG).show();
        }
        else {
            String descr = "l'utente ha aggiunto  il film: " + f.getFilmName() + "alla LISTA DA VEDERE";
            String time = df.format(new Date());
            fm = new FeedModel(HomeActivity.uid, HomeActivity.u.getnickname(), time, descr, "ADDTOSEE");
            fd.addFeed(fm);
            Toast.makeText(c, "IL FILE E'STATO AGGIUNTO ALLA LISTA" , Toast.LENGTH_LONG).show();
        }
    }

    protected void onClickPersonale(View v) {
        Log.d(MainActivity.TAG,"premuto button da personale");
        UtentiModel u;
        FeedModel fm;
        String listapers="";

        u=ua.getUtente(HomeActivity.uid,"UTENTI");
        if (u==null) {
            Log.d(MainActivity.TAG,"Utente non trovato, uid="+ HomeActivity.uid);
        }
        else {
            listapers=u.getListapers();
            Log.d(MainActivity.TAG,"Utente trovato, listapers="+listapers);
        }
        if (listapers.equals("0")) {
            String msg="LISTA NON ESISTENTE , PER CREARE LA LISTA ACCEDERE AL MENU PERSONALE DAL MENU BAR";
            String nomelista="";
            PopUp p= new PopUp(v.getContext(), msg);
            p.PopUpMsg1();
        }
        if (!listapers.equals("0")) {
            Log.d(MainActivity.TAG,"premuto button personale");
            if (fa.addListaFilm(HomeActivity.uid, f, "LISTAPERSONALE")) {
                Toast.makeText(c, "IL FILM E' GIA PRESENTE NELLA LISTA", Toast.LENGTH_LONG).show();
            }
            else {
                String descr = "l'utente ha aggiunto  il film: " + f.getFilmName() + "alla LISTA PERSONALE";
                String time = df.format(new Date());
                fm = new FeedModel(HomeActivity.uid, HomeActivity.u.getnickname(), time, descr, "ADDPERS");
                fd.addFeed(fm);
                Toast.makeText(c, "IL FILE E'STATO AGGIUNTO ALLA LISTA" , Toast.LENGTH_LONG).show();
            }

        }
    }


}