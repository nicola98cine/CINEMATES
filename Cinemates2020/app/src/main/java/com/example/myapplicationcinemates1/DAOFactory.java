package com.example.myapplicationcinemates1;
import android.content.Context;

import com.example.myapplicationcinemates1.datamodel.FeedAPIGW;
import com.example.myapplicationcinemates1.datamodel.FeedDAO;
import com.example.myapplicationcinemates1.datamodel.FilmAPIGW;
import com.example.myapplicationcinemates1.datamodel.FilmDAO;
import com.example.myapplicationcinemates1.datamodel.RichiesteAPIGW;
import com.example.myapplicationcinemates1.datamodel.RichiesteDAO;
import com.example.myapplicationcinemates1.datamodel.UtentiAPIGW;
import com.example.myapplicationcinemates1.datamodel.UtentiDAO;

public class DAOFactory {

    String db;
    private static DAOFactory theDAO;
    public static synchronized DAOFactory getDAOInstance(Context context) {
        if (theDAO==null)
            theDAO= new DAOFactory(context);
        return theDAO;
    }

    private DAOFactory(Context context) {
        db=context.getApplicationContext().getResources().getString(R.string.database);
    }

    public DAOFactory() {
        db="AWS";
    }

    public FeedDAO getFeedDAO() {
        if (db.equals("AWS"))
            return new FeedAPIGW() ;
        return null;
    }
    public UtentiDAO getUtentiDAO() {
        if (db.equals("AWS"))
            return new UtentiAPIGW(false) ;
        return null;
    }
    public FilmDAO getFilmDAO() {
        if (db.equals("AWS"))
            return new FilmAPIGW(false) ;
        return null;
    }
    public RichiesteDAO getRichiesteDAO() {
        if (db.equals("AWS"))
            return new RichiesteAPIGW() ;
        return null;
    }

}
