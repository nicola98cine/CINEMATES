package com.example.myapplicationcinemates1;

import android.content.Context;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.util.Log;
import android.widget.ImageView;

import com.example.myapplicationcinemates1.datamodel.FilmModel;

import java.util.ArrayList;
import java.util.List;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.TmdbSearch;
import info.movito.themoviedbapi.model.Genre;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.MovieImages;
import info.movito.themoviedbapi.model.core.MovieResultsPage;

public class TaskTMDB extends AsyncTask<Void, Void, Void> {
   TmdbApi api;
   String movieTitle;
   ImageView iv;
   TmdbSearch s;
   Context c;
   public static final String myApikey="1928ec9053b8c9bd1461521b16382d00";
   public static final String myUrl="https://api.themoviedb.org/3/";
   public static final String s0="https://image.tmdb.org/t/p/w185";
   public ArrayList<FilmModel> a;
   private String query;
   private TmdbMovies movies;
   private List<Genre> lg;
   public enum tipoper {search, getgenere, iniziale, other} ;
   private tipoper tipop= tipoper.search;
   private int fid;
   private String genere="";
   protected FilmModel fm;
   private int fid1=242;
   private int fid2=24428;
   private int fid3=285;
   private int fid4=770;

    MovieImages m;
    protected void setImageView(ImageView i) {
       iv=i;
   }
    protected void setContext(Context con) {
       c=con;
   }
    public void setQuery(String q) {
       query=q;
   }
    public void settipop (tipoper t) { tipop=t;  }
    public void setidf (int id) { fid=id;  }
    public void setidf1 (int id) { fid1=id;  }
    public void setidf2 (int id) { fid2=id;  }
    public void setidf3 (int id) { fid3=id;  }
    public void setidf4 (int id) { fid4=id;  }

    public String getgenere () { return genere;  }

    @Override
    public void onPreExecute() {
        Log.d(MainActivity.TAG,"onPreexecute");
       StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
       StrictMode.setThreadPolicy(policy);
       try {
           api = new TmdbApi(myApikey);
           movies=api.getMovies();
           a=new ArrayList<FilmModel>(50);
           s=api.getSearch();
           lg=api.getGenre().getGenreList("it");
       }
       catch (Exception e) {
           e.printStackTrace();
       }
   }
   private String searchGenre(int idgen) {
       String g="";
       int i=0;
       int s=lg.size();
       boolean trovato=false;
       //System.out.println("ricerca genere");
       while (i<s && !trovato) {
           if (lg.get(i).getId()==idgen) {
               trovato=true;
               g=lg.get(i).getName();
           }
           i++;
       }
       return g;
   }

   @Override
   public Void doInBackground(Void... voids) {
       String SIView ="";
       int numg=0;
       int count=0;
       MovieResultsPage l;
       List<MovieDb> t1;
       MovieDb m;
       String overview="";

       if (tipop== tipoper.search) {
           Log.d(MainActivity.TAG,"doinBackground search");
           l=s.searchMovie(query, 0, "it", false, 0);
           t1=l.getResults();
           count=t1.size();
           //System.out.println("num film caricati="+Integer.toString(count));

           for (int i=0;i<count;i++) {
               if( t1.get(i).getPosterPath()== null){
                   SIView=s0;
               }
               else if  (!t1.get(i).getPosterPath().isEmpty()) {
                   SIView=s0 + t1.get(i).getPosterPath();
               }
               fid=t1.get(i).getId();
               //System.out.println("task search fid="+fid);

               if( t1.get(i).getOverview()== null){
                   overview="";
               }
               else if  (t1.get(i).getOverview().isEmpty()) {
                   overview= "";
               }
               else {
                   overview= t1.get(i).getOverview();
               }
               a.add(new FilmModel(fid, SIView, t1.get(i).getTitle(), overview, genere));
           }
       }
       else if (tipop== tipoper.getgenere) {
           Log.d(MainActivity.TAG,"doinBackground getgenere, fid="+fid);
           m=movies.getMovie(fid, "it", TmdbMovies.MovieMethod.credits);
           if (m.getGenres().isEmpty()) {
               Log.d(MainActivity.TAG,"lista generi vuota");
               numg=0;
               genere="";
           }
           else {
               numg=m.getGenres().size();
           }
           if (numg>0) {
               genere=searchGenre(m.getGenres().get(0).getId());
           }
       }
       else if (tipop== tipoper.iniziale) {
           Log.d(MainActivity.TAG,"doinBackground inziale, fid="+fid);
           m=movies.getMovie(fid1, "it", TmdbMovies.MovieMethod.credits);
           SIView=s0;
           if( m.getPosterPath()== null){
           }
           else if  (!m.getPosterPath().isEmpty()) {
               SIView=s0 + m.getPosterPath();
           }
           a.add(new FilmModel(fid1, SIView, m.getTitle(), m.getOverview(), ""));
           m=movies.getMovie(fid2, "it", TmdbMovies.MovieMethod.credits);
           SIView=s0;
           if( m.getPosterPath()== null){
           }
           else if  (!m.getPosterPath().isEmpty()) {
               SIView=s0 + m.getPosterPath();
           }
           a.add(new FilmModel(fid2, SIView, m.getTitle(), m.getOverview(), ""));
           m=movies.getMovie(fid3, "it", TmdbMovies.MovieMethod.credits);
           SIView=s0;
           if( m.getPosterPath()== null){
           }
           else if  (!m.getPosterPath().isEmpty()) {
               SIView=s0 + m.getPosterPath();
           }
           a.add(new FilmModel(fid3, SIView, m.getTitle(), m.getOverview(), ""));
           m=movies.getMovie(fid4, "it", TmdbMovies.MovieMethod.credits);
           SIView=s0;
           if( m.getPosterPath()== null){
           }
           else if  (!m.getPosterPath().isEmpty()) {
               SIView=s0 + m.getPosterPath();
           }
           a.add(new FilmModel(fid4, SIView, m.getTitle(), m.getOverview(), ""));

       }
       else {
           Log.d(MainActivity.TAG,"doinBackground all");
           l=s.searchMovie(query, 0, "it", false, 0);
           t1=l.getResults();
           count=t1.size();
           //System.out.println("num film caricati="+Integer.toString(count));

           for (int i=0;i<count;i++) {
               if( t1.get(i).getPosterPath()== null){
                   SIView=s0;
               }
               else if  (!t1.get(i).getPosterPath().isEmpty()) {
                    SIView=s0 + t1.get(i).getPosterPath();
               }
               fid=t1.get(i).getId();
               m=movies.getMovie(fid, "it", TmdbMovies.MovieMethod.credits);
               overview=m.getOverview();
               if (m.getGenres().isEmpty()) {
                   System.out.println("lista generi vuota");
                   numg=0;
                   genere="";
               }
               else {
                   numg=m.getGenres().size();
               }
               if (numg>0) {
                   genere=searchGenre(m.getGenres().get(0).getId());
               }
               a.add(new FilmModel(fid, SIView, t1.get(i).getTitle(), overview,genere));
           }
       }

       return null;
   }

   @Override
   protected void onPostExecute(Void aVoid) {
       //System.out.println(movieTitle);

   }
}
