package com.example.myapplicationcinemates1.ui.richieste;

import android.content.Context;
import android.util.Log;

import com.example.myapplicationcinemates1.HomeActivity;
import com.example.myapplicationcinemates1.MainActivity;
import com.example.myapplicationcinemates1.PopUp;
import com.example.myapplicationcinemates1.datamodel.FeedAPIGW;
import com.example.myapplicationcinemates1.datamodel.FeedModel;
import com.example.myapplicationcinemates1.datamodel.RichiesteAPIGW;
import com.example.myapplicationcinemates1.datamodel.RichiesteModel;
import com.example.myapplicationcinemates1.datamodel.UtentiAPIGW;
import com.example.myapplicationcinemates1.datamodel.UtentiModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import static com.example.myapplicationcinemates1.MainActivity.DAO;

public class RichiestePresenter {
    public ArrayList<RichiesteModel> richiesteModelArrayLst;
    public ArrayList<UtentiModel> listafriend;
    public ArrayList<UtentiModel> listafriendintera;

    protected RichiesteAPIGW ra;
    protected FeedAPIGW fa;
    protected UtentiAPIGW ua;
    protected FeedModel fm;
    protected RichiesteModel rm;


    public RichiestePresenter() {
        ra= (RichiesteAPIGW) DAO.getRichiesteDAO();
        ua= (UtentiAPIGW) DAO.getUtentiDAO();
        fa= (FeedAPIGW) DAO.getFeedDAO();
        richiesteModelArrayLst=new ArrayList<RichiesteModel>();
        listafriend=new ArrayList<UtentiModel>();
        listafriendintera=new ArrayList<UtentiModel>();

    }

    public void setListaRichieste( String Userid){
        richiesteModelArrayLst= ra.getListaRichiesteDaAccettare(Userid);
    }

    public void setListaCollegamenti(String Userid){
        Log.d(MainActivity.TAG,"FUNZIONALITA INVIO RICHIESTE ESEGUO API GW per Ricerca degli UTENTI");
        listafriendintera=ua.getListaUtenti(Userid);
    }

    public void accettazioneRichieste(int selected){
        String userid=richiesteModelArrayLst.get(selected).getUserid().toString();
        String dt=richiesteModelArrayLst.get(selected).getDatatime().toString();
        Log.d(MainActivity.TAG,"RICHIESTE position=" + userid);
        ra.modifyRichiesta(userid, dt);
        SimpleDateFormat df= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("Europe/Rome"));
        String descr="l'utente ha accettato la richiesta di collegamento all'utente: " + userid;
        String time = df.format(new Date());
        System.out.println ("sysdate="+ time);
        fm=new FeedModel(HomeActivity.uid, HomeActivity.u.getnickname(),time,descr,
                "RICHIESTA");
        fa.addFeed(fm);

    }

    public void setListaRichiesteAccettate(String Userid){
        richiesteModelArrayLst=ra.getListaRichiesteAccettate(Userid);
    }


    public void invioPopup(UtentiModel u, UtentiModel f, Context c) {
        String msg="INVIANDO RICHIESTA DI COLLEGAMENTO A UTENTE " + f.getnickname() + " , VUOI PROSEGUIRE ?";
        PopUp p =new PopUp(c,msg);
        p.PopUpMsgInvioRichiesta(u,f);
    }

    public void invioRichiesta(UtentiModel u, UtentiModel f) {
        SimpleDateFormat df= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("Europe/Rome"));
        String time = df.format(new Date());
        rm=new RichiesteModel(u.getUserid(), time, u.getnickname(),u.getImmagine(), u.getnome(),
                u.getcognome(),f.getUserid(),f.getnickname(), "0");
        ra.addRichiesta(rm);
        String descr="l'utente ha inviato richiesta di collegamento all'utente: " + f.getUserid();
        fm=new FeedModel(u.getUserid(), u.getnickname(),time,
                descr,"RICHIESTA");
        fa.addFeed(fm);

    }


    public void copialiste() {
        listafriend.clear();
        UtentiModel f;
        for (int i=0;i<listafriendintera.size();i++) {
            f=new UtentiModel(listafriendintera.get(i).getUserid(),listafriendintera.get(i).getnickname(),
                    listafriendintera.get(i).getImmagine(),
                    listafriendintera.get(i).getnome(), listafriendintera.get(i).getcognome(),
                    listafriendintera.get(i).getDatanascita(), "", listafriendintera.get(i).getListapers(),
                        listafriendintera.get(i).getTipoutente() );

            listafriend.add(f);

        }
    }

    public void filtraliste(String t) {
        UtentiModel f;
        listafriend.clear();

        for (int i=0;i<listafriendintera.size();i++) {
            if (listafriendintera.get(i).getnickname().toLowerCase().contains(t.toLowerCase())) {

                f=new UtentiModel(listafriendintera.get(i).getUserid(),listafriendintera.get(i).getnickname(),
                        listafriendintera.get(i).getImmagine(),
                        listafriendintera.get(i).getnome(),listafriendintera.get(i).getcognome(),listafriendintera.get(i).getDatanascita(),"",
                        listafriendintera.get(i).getListapers(),listafriendintera.get(i).getTipoutente());
                listafriend.add(f);
            }
        }
    }
}
