package com.example.myapplicationcinemates1.datamodel;

public class FilmModel {
    private int filmId;
    private String filmIv;
    private String filmName;
    private String filmDescription;
    private String filmGenere;

    // creating constructor for our variables.
    public FilmModel(int filmId, String filmIv, String filmName, String filmDescription, String filmGenere) {
        this.filmId=filmId;
        this.filmIv=filmIv;
        this.filmName = filmName;
        this.filmDescription = filmDescription;
        this.filmGenere=filmGenere;
    }

    public FilmModel(String id) {
    }
    // creating getter and setter methods.

    public String getIView() {return filmIv;}

    public void setIView(String filmIv) {this.filmIv=filmIv;}

    public int getId() {return filmId;}

    public void setId(int filmId) {this.filmId=filmId;}

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public String getFilmDescription() {
        return filmDescription;
    }

    public void setFilmDescription(String filmDescription) {
        this.filmDescription = filmDescription;
    }

    public String getFilmGenere() {
        return filmGenere;
    }

    public void setFilmGenere(String filmGenere) {
        this.filmGenere = filmGenere;
    }
}

