package com.example.myapplicationcinemates1.datamodel;
import com.example.myapplicationcinemates1.datamodel.FeedModel;
import java.util.ArrayList;

public interface FeedDAO {
    public ArrayList<FeedModel> getListaFeed();
    public void addFeed(FeedModel f);
}
