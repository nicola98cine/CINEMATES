package com.example.myapplicationcinemates1.ui.feed;

import android.util.Log;

import com.example.myapplicationcinemates1.MainActivity;
import com.example.myapplicationcinemates1.datamodel.FeedAPIGW;
import com.example.myapplicationcinemates1.datamodel.FeedModel;
import com.example.myapplicationcinemates1.datamodel.RichiesteModel;

import java.util.ArrayList;

import static com.example.myapplicationcinemates1.MainActivity.DAO;

public class FeedPresenter {
    public ArrayList<RichiesteModel> richiesteModelArrayLst;
    public ArrayList<RichiesteModel> richiesteModelArrayLst1;
    public ArrayList<FeedModel> feedModelArrayLst;
    public ArrayList<FeedModel> feedModelArrayLstFiltrati;
    public ArrayList<String>  Amici;
    protected FeedAPIGW fa;
    protected FeedModel f;

    public FeedPresenter( ) {
        fa= (FeedAPIGW) DAO.getFeedDAO();
        richiesteModelArrayLst=new ArrayList<RichiesteModel>();
        richiesteModelArrayLst1=new ArrayList<RichiesteModel>();
        feedModelArrayLst=new ArrayList<FeedModel>();
        feedModelArrayLstFiltrati=new ArrayList<FeedModel>();
        Amici=new ArrayList<String>();
    }

    public void AddListaAmici(String Userid2 ) {
        richiesteModelArrayLst  = fa.getListaRichieste(Userid2,"COLLEGAMENTI","1", "USERID2=:topic");
        richiesteModelArrayLst1 = fa.getListaRichieste(Userid2,"COLLEGAMENTI", "1","USERID=:topic");
        for (int j=0;j<richiesteModelArrayLst.size();j++)
        {
            if(!richiesteModelArrayLst.get(j).getUserid().equals(Userid2)) {
                //System.out.println("aggiungo = "+ richiesteModelArrayLst.get(j).getUserid());
                Amici.add(richiesteModelArrayLst.get(j).getUserid());
            }
        }
        for (int i=0; i<richiesteModelArrayLst1.size();i++){
            if(!richiesteModelArrayLst1.get(i).getUserid2().equals(Userid2)) {
                //Log.d(MainActivity.TAG,"aggiungo = "+ richiesteModelArrayLst1.get(i).getUserid());
                Amici.add(richiesteModelArrayLst1.get(i).getUserid2());
            }
        }
    }

    private boolean search( ArrayList<String> a, String s) {
        int i=0;
        boolean trovato=false;
        while (i<a.size() && ! trovato) {
            if(a.get(i).equals(s)) {
                trovato= true;
            }
            i++;
        }
        return trovato;
    }

    public void AddListaFeed( ) {
        FeedAPIGW fd= (FeedAPIGW) DAO.getFeedDAO();
        feedModelArrayLst = fd.getListaFeed();
        String appouser="";
        for (int i=0; i<feedModelArrayLst.size();i++){
            appouser=feedModelArrayLst.get(i).getUserid();
            //System.out.println("appouser="+appouser);
            if(search(Amici, appouser) ) {
                feedModelArrayLstFiltrati.add(feedModelArrayLst.get(i));
            }
        }
    }

}