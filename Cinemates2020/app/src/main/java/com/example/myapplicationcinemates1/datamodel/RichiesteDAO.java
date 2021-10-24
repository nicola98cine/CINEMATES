package com.example.myapplicationcinemates1.datamodel;

import com.example.myapplicationcinemates1.datamodel.FilmModel;
import com.example.myapplicationcinemates1.datamodel.RichiesteModel;
import java.util.ArrayList;

public interface RichiesteDAO {
    public void addRichiesta(RichiesteModel r);
    public ArrayList<RichiesteModel> getListaRichiesteDaAccettare(String userid);
    public ArrayList<RichiesteModel> getListaRichiesteAccettate(String userid);
    public void modifyRichiesta(String userid, String datatime);

}

