package com.example.myapplicationcinemates1;

import android.os.StrictMode;
import android.util.Log;

import com.amazonaws.mobileconnectors.apigateway.ApiClientFactory;
import com.example.myapplicationcinemates1.datamodel.FeedModel;
import com.example.myapplicationcinemates1.datamodel.FilmModel;
import com.example.myapplicationcinemates1.datamodel.RichiesteModel;
import com.example.myapplicationcinemates1.datamodel.UtentiModel;

import java.util.ArrayList;

import apiRest_DynamoDB.DynamoDBOperationsClient;
import apiRest_DynamoDB.model.Input;
import apiRest_DynamoDB.model.InputPayload;
import apiRest_DynamoDB.model.InputPayloadItem;
import apiRest_DynamoDB.model.InputPayloadKey;
import apiRest_DynamoDB.model.Output;

public class ApiGateway {
    protected Input body;
    protected InputPayload pl;
    protected InputPayloadItem pli;
    protected InputPayloadKey k;
    protected Output o;
    protected ApiClientFactory factory;
    protected DynamoDBOperationsClient client;

    public ApiGateway(boolean test) {

        if (!test) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        factory=new ApiClientFactory();
        client=factory.build(DynamoDBOperationsClient.class);
        body =new Input();
        pl =new InputPayload();
        pli =new InputPayloadItem();
        k=new InputPayloadKey();
        o=new Output();
    }

    private boolean getFilm(String User, String idFilm, String TableName ) {
        System.out.println("PRIMA ESEC APIKEY in GETFILM, UID="+User+",IDFILM="+idFilm);

        boolean filmpresenteinlista=false;
        k.setUSERID(User);
        k.setIDFILM(idFilm);
        pl.setKey(k);
        body.setOperation("read");
        body.setTableName(TableName);
        body.setPayload(pl);

        o=client.dynamoDBManagerPost(body);

        if ((o.getItem() == null) ) {
            filmpresenteinlista=false;
            System.out.println("FILM NON TROVATO");
        }
        else {
            filmpresenteinlista=true;
            System.out.println("FILM TROVATO");
        }
        return filmpresenteinlista;
    }

    public boolean  addListaFilm(String User, FilmModel f, String Tablename) {
        if (getFilm(User, Integer.toString(f.getId()), Tablename)) {
            return true;
        }

        pli.setUSERID(User);
        pli.setIDFILM(Integer.toString(f.getId()));
        pli.setNOMEFILM(f.getFilmName());
        pli.setTRAMA(f.getFilmDescription());
        pli.setGENERE(f.getFilmGenere());
        pli.setIMMAGINE(f.getIView());
        pl.setItem(pli);

        body.setOperation("create");
        body.setTableName(Tablename);
        body.setPayload(pl);

        client.dynamoDBManagerPost(body);
        return false ;
    }
    public void addRichiesta(RichiesteModel f , String Tablename) {
        pli.setUSERID(f.getUserid());
        pli.setDATATIME(f.getDatatime());
        pli.setCOGNOME(f.getcognome());
        pli.setNICKNAME(f.getnickname());
        pli.setNOME(f.getnome());
        pli.setIMMAGINE(f.getImmagine());
        pli.setUSERID2(f.getUserid2());
        pli.setNICKNAME2(f.getnickname2());
        pli.setSTATO("0");
        pl.setItem(pli);

        body.setOperation("create");
        body.setTableName(Tablename);
        body.setPayload(pl);
        Log.d(MainActivity.TAG,"PRIMA ESEC APIKEY");
        client.dynamoDBManagerPost(body);
        Log.d(MainActivity.TAG,"DOPO ESEC APIKEY");
        return;
    }


    public ArrayList<RichiesteModel> getListaRichieste(String User, String TableName, String Stato, String filter) {
        Log.d(MainActivity.TAG,"PRIMA APIKEY, user=" +  User);

        int nelem=0;
        ArrayList<RichiesteModel> af=new ArrayList<RichiesteModel>();
        RichiesteModel f;
        pl.setParameter(User);
        pl.setFilterExpression(filter);

        body.setOperation("myquery");
        body.setTableName(TableName);
        body.setPayload(pl);

        Log.d(MainActivity.TAG,"PRIMA ESEC APIKEY");
        o=client.dynamoDBManagerPost(body);
        Log.d(MainActivity.TAG,"DOPO ESEC APIKEY");

        if ((o.getItems() == null) ) {
            nelem=0;
            Log.d(MainActivity.TAG,"elementi output null");
        }
        else {
            nelem=o.getItems().size();
            Log.d(MainActivity.TAG,"num elementi =" + Integer.toString(nelem));
        }
        for (int i=0;i<nelem;i++) {
            f=new RichiesteModel(o.getItems().get(i).getUSERID(),
                    o.getItems().get(i).getDATATIME(),
                    o.getItems().get(i).getNICKNAME(),
                    o.getItems().get(i).getIMMAGINE(),
                    o.getItems().get(i).getNOME(),
                    o.getItems().get(i).getCOGNOME(),
                    o.getItems().get(i).getUSERID2(),
                    o.getItems().get(i).getNICKNAME2(),
                    o.getItems().get(i).getSTATO());

                    if (f.getStato().equals(Stato)) {
                        af.add(f);
                    }
        }
        return af;
    }


    public ArrayList<UtentiModel> getListaUtenti(String User, String TableName) {
        Log.d(MainActivity.TAG,"PRIMA APIKEY");
        int nelem=0;
        ArrayList<UtentiModel> af=new ArrayList<UtentiModel>();
        UtentiModel f = null;
        body.setOperation("list");
        body.setTableName(TableName);
        body.setPayload(pl);

        Log.d(MainActivity.TAG,"PRIMA ESEC APIKEY");
        o=client.dynamoDBManagerPost(body);
        Log.d(MainActivity.TAG,"DOPO ESEC APIKEY");

        if ((o.getItems() == null) ) {
            nelem=0;
            System.out.println("elementi output null");
        }
        else {
            nelem=o.getItems().size();
            //System.out.println("elemento 01="+o.getItems().get(0).getNOMEFILM());
        }
        for (int i=0;i<nelem;i++) {
            String tipout=o.getItems().get(i).getTIPOUTENTE();
            if (tipout.equals("mobile")) {
                f=new UtentiModel(o.getItems().get(i).getUSERID(),
                        o.getItems().get(i).getNICKNAME(),
                        o.getItems().get(i).getIMMAGINE(),
                        o.getItems().get(i).getNOME(),
                        o.getItems().get(i).getCOGNOME(),o.getItems().get(i).getDATANASCITA(), "",
                        o.getItems().get(i).getLISTAPERS(),
                        o.getItems().get(i).getTIPOUTENTE());
                    //System.out.println("uid, datanasc="+o.getItems().get(i).getUSERID()+ ","+o.getItems().get(i).getDATANASCITA() );
                    af.add(f);
                }
        }
        return af;
    }

    public UtentiModel getUtente(String User, String TableName) {
        //Log.d(MainActivity.TAG,"PRIMA ESEC APIKEY");
        System.out.println("PRIMA ESEC APIKEY in GETUTENTE, UID="+User);
        int nelem;
        UtentiModel f = null;
        k.setUSERID(User);
        pl.setKey(k);
        body.setOperation("read");
        body.setTableName(TableName);
        body.setPayload(pl);

        //Log.d(MainActivity.TAG,"PRIMA ESEC APIKEY:"+User);
        o=client.dynamoDBManagerPost(body);
        //Log.d(MainActivity.TAG,"DOPO ESEC APIKEY");

        if ((o.getItem() == null) ) {
            nelem=0;
            System.out.println("GETUTENTE : UTENTE NON TROVATO");
            //Log.d(MainActivity.TAG,"elementi output null");
        }
        else {
            nelem=1;
        }
        for (int i=0;i<nelem;i++) {
            f=new UtentiModel(o.getItem().getUSERID(),
                    o.getItem().getNICKNAME(),
                    o.getItem().getIMMAGINE(),
                    o.getItem().getNOME(),
                    o.getItem().getCOGNOME(),"", "",
                    o.getItem().getLISTAPERS(), o.getItem().getTIPOUTENTE() );
        }
        return f;
    }

    public ArrayList<FilmModel> getListaFilm(String User, String TableName) {
        Log.d(MainActivity.TAG,"PRIMA ESEC APIKEY");
        int nelem=0;
        ArrayList<FilmModel> af=new ArrayList<FilmModel>();
        FilmModel f;
        pl.setParameter(User);

        pl.setFilterExpression("USERID=:topic");
        body.setOperation("myquery");
        body.setTableName(TableName);
        body.setPayload(pl);
        Log.d(MainActivity.TAG,"PRIMA ESEC APIKEY");
        o=client.dynamoDBManagerPost(body);
        Log.d(MainActivity.TAG,"DOPO ESEC APIKEY");

        if ((o.getItems() == null) ) {
            nelem=0;
            Log.d(MainActivity.TAG,"elementi output null");
        }
        else {
            nelem=o.getItems().size();
        }
        for (int i=0;i<nelem;i++) {
            f=new FilmModel(Integer.parseInt(o.getItems().get(i).getIDFILM()),o.getItems().get(i).getIMMAGINE(),o.getItems().get(i).getNOMEFILM(),
                    o.getItems().get(i).getTRAMA(), o.getItems().get(i).getGENERE());
            af.add(f);
        }
        return af;
    }

    protected ArrayList<FeedModel> getListaFeed(String TableName) {
        Log.d(MainActivity.TAG,"PRIMA ESEC APIKEY");
        int nelem=0;
        ArrayList<FeedModel> af=new ArrayList<FeedModel>();
        FeedModel f;
        body.setOperation("list");
        body.setTableName(TableName);
        body.setPayload(pl);

        Log.d(MainActivity.TAG,"PRIMA ESEC APIKEY");
        o=client.dynamoDBManagerPost(body);
        Log.d(MainActivity.TAG,"DOPO ESEC APIKEY");

        if ((o.getItems() == null) ) {
            nelem=0;
            Log.d(MainActivity.TAG,"elementi output null");
        }
        else {
            nelem=o.getItems().size();
            System.out.println("elemento 01="+o.getItems().get(0).getNOMEFILM());
        }
        for (int i=0;i<nelem;i++) {
            f=new FeedModel(o.getItems().get(i).getUSERID(),
                    o.getItems().get(i).getNICKNAME(),o.getItems().get(i).getDATATIME(),
                    o.getItems().get(i).getDESCRIZIONE(),o.getItems().get(i).getTIPOATTIVITA());
            af.add(f);
        }
        return af;
    }

    public void RemoveListaFilm(String UserId, String IdFilm, String TableName)
    {

        k.setUSERID(UserId);
        k.setIDFILM(IdFilm);
        pl.setKey(k);

        body.setOperation("delete");
        body.setTableName(TableName);
        body.setPayload(pl);
        client.dynamoDBManagerPost(body);
    }

    public void addUtente(UtentiModel user)
    {
        pli.setUSERID(user.getUserid());
        pli.setNOME(user.getnome());
        pli.setCOGNOME(user.getcognome());
        pli.setNICKNAME(user.getnickname());
        pli.setPASSWORD(user.getPassword());
        pli.setDATANASCITA(user.getDatanascita());
        pli.setIMMAGINE(user.getImmagine());
        pli.setLISTAPERS(user.getListapers());
        pli.setTIPOUTENTE(user.getTipoutente());

        pl.setItem(pli);
        body.setTableName("UTENTI");
        body.setOperation("create");
        body.setPayload(pl);

        client.dynamoDBManagerPost(body);
        Log.d(MainActivity.TAG,"INSERITO UTENTE");
    }

    public void ModifyUtente(String userid, String nomelistapers, String Tablename)
    {
        pl.setUpdateExpression("set LISTAPERS =:val");
        pl.setParameter(nomelistapers);
        pl.setVar(userid);
        body.setTableName(Tablename);
        body.setOperation("myupdate");
        body.setPayload(pl);

        client.dynamoDBManagerPost(body);
        Log.d(MainActivity.TAG,"UPDATED UTENTE");
    }
    public void ModifyRichiesta(String userid, String datatime, String Tablename)
    {

        pl.setUpdateExpression("set STATO =:val");
        pl.setParameter(datatime);
        pl.setVar(userid);
        //pl.setItem(pli);
        body.setTableName(Tablename);
        body.setOperation("myupdate2");
        body.setPayload(pl);

        client.dynamoDBManagerPost(body);
        Log.d(MainActivity.TAG,"UPDATED RICHIESTA");
    }


    public void addLogin(String userid, String Datatime, String Azione)
    {
        pli.setUSERID(userid);
        pli.setDATATIME(Datatime);
        pli.setAZIONE(Azione);

        pl.setItem(pli);
        body.setTableName("ACCESSI");
        body.setOperation("create");
        body.setPayload(pl);
        client.dynamoDBManagerPost(body);
        Log.d(MainActivity.TAG,"INSERITO LOGIN");

    }

    protected void addFeed(FeedModel f)
    {
        pli.setUSERID(f.getUserid());
        pli.setDATATIME(f.getDatatime());
        pli.setNICKNAME(f.getnickname());
        pli.setDESCRIZIONE(f.getDescription());
        pli.setTIPOATTIVITA(f.getTipoattivita());
        pl.setItem(pli);
        body.setTableName("ATTIVITA");
        body.setOperation("create");
        body.setPayload(pl);
        client.dynamoDBManagerPost(body);
        Log.d(MainActivity.TAG,"INSERITO FEED");
    }

}

