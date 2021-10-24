package com.example.myapplicationcinemates1.datamodel;

public class UtentiModel {

    private String userid;
    private String nickname;
    private String immagine;
    private String nome;
    private String cognome;
    private String datanascita;
    private String password;
    private String listapers;
    private String tipoutente;


    // creating constructor for our variables.
    public UtentiModel(String userid, String nickname, String immagine, String nome, String cognome, String datanascita,
                       String password, String listapers, String tipoutente) {
        this.nome = nome;
        this.cognome = cognome;
        this.nickname = nickname;
        this.immagine = immagine;
        this.userid = userid;
        this.datanascita=datanascita;
        this.password=password;
        this.listapers=listapers;
        this.tipoutente=tipoutente;
    }
    // creating getter and setter methods.

    public String getImmagine() {
        return immagine;
    }

    public String getListapers() {
        return listapers;
    }

    public void setListapers(String listapers) {
        this.listapers = listapers;
    }

    public String getTipoutente() {
        return tipoutente;
    }

    public void setTipoutente(String listapers) {
        this.tipoutente = tipoutente;
    }

    public void setDatanascita(String datanascita) {
        this.datanascita = datanascita;
    }

    public String getDatanascita() {
        return datanascita;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setIView(String immagine) {
        this.immagine = immagine;
    }

    public String getnome() {
        return nome;
    }

    public String getId() {return userid;}

    public void setId(int filmId) {this.userid=userid;}

    public void setnome(int filmId) {
        this.nome = nome;
    }

    public String getUserid() {
        return userid;
    }

    public String getcognome() {
        return cognome;
    }

    public void setcognome(int filmId) {
        this.cognome = cognome;
    }

    public String getnickname() {
        return nickname;
    }

    public void setNickname(String nick) {
        this.nickname = nick;
    }

    //public String getDatatime() {
      //  return datatime;
   // }

}

