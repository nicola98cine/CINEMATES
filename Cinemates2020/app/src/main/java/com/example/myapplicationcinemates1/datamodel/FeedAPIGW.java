package com.example.myapplicationcinemates1.datamodel;

import com.example.myapplicationcinemates1.ApiGateway;

import java.util.ArrayList;

//public class FeedAPIGW implements FeedDAO extends ApiGateway {
public class FeedAPIGW  extends ApiGateway implements FeedDAO {

    public FeedAPIGW() {
        super (false);
    }

    public ArrayList<FeedModel> getListaFeed() {
        return super.getListaFeed("ATTIVITA");
    };

    public void addFeed(FeedModel f){
        super.addFeed(f);
    };

}
