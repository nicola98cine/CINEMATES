package com.example.myapplicationcinemates1.datamodel;

public class FeedModel {
    private String userid;
    private String nickname;
    private String datatime;
    private String description;
    private String tipoattivita;


    // creating constructor for our variables.
    public FeedModel(String userid, String nickname, String datatime,  String description,
                     String tipoattivita) {

        this.nickname = nickname;
        this.userid=userid;
        this.datatime = datatime;
        this.description =description;
        this.tipoattivita =tipoattivita;

    }
    // creating getter and setter methods.

    public String getTipoattivita() {
        return tipoattivita;
    }

    public void setTipoattivita(String tipoattivita) {
        this.tipoattivita = tipoattivita;
    }

    public String getnickname() {
        return nickname;
    }

    public void setNickname(String nick) {
        this.nickname = nick;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String descr) {
        this.nickname = descr;
    }

    public String getDatatime() {
        return datatime;
    }

    public String getUserid() {
        return userid;
    }

}