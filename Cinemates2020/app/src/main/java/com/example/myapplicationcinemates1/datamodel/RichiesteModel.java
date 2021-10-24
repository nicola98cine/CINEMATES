package com.example.myapplicationcinemates1.datamodel;

public class RichiesteModel {


    private String userid;
    private String datatime;
    private String nickname;
    private String immagine;
    private String nome;
    private String cognome;
    private String userid2;
    private String nickname2;
    private String stato;



    // creating constructor for our variables.
    public RichiesteModel(String userid, String datatime, String nickname,
                          String immagine, String nome, String cognome, String userid2, String nickname2, String stato) {
        this.nome=nome;
        this.cognome=cognome;
        this.nickname = nickname;
        this.immagine = immagine;
        this.userid=userid;
        this.userid2 = userid2;
        this.datatime = datatime;
        this.nickname2= nickname2;
        this.stato=stato;

    }
    // creating getter and setter methods.

    public String getImmagine() {return immagine;}

    public void setIView(String immagine) {this.immagine=immagine;}

    public String getnome() {return nome;}

    public String getUserid() {return userid;}
    public String getUserid2() {return userid2;}
    public void setUserid2(String userid2) {this.userid2=userid2;}


    public void setnome(int filmId) {this.nome=nome;}

    public String getcognome() {return cognome;}

    public void setcognome(int filmId) {this.cognome=cognome;}

    public String getnickname() {
        return nickname;
    }
    public String getnickname2() {
        return nickname2;
    }
    public void setNickname2(String nick2) {
        this.nickname2 = nick2;
    }
    public void setNickname(String nick) {
        this.nickname = nick;
    }

    public String getDatatime() {
        return datatime;
    }
    public String getStato() {
        return stato;
    }
    public void setStato(String state) {
        this.stato = state;
    }

    }