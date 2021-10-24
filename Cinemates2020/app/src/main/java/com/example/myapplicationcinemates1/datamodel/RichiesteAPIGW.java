package com.example.myapplicationcinemates1.datamodel;

import com.example.myapplicationcinemates1.ApiGateway;
import com.example.myapplicationcinemates1.datamodel.RichiesteDAO;
import com.example.myapplicationcinemates1.datamodel.RichiesteModel;

import java.util.ArrayList;

public class RichiesteAPIGW extends ApiGateway implements RichiesteDAO {

    public RichiesteAPIGW() {
        super (false);
    }
    public void addRichiesta(RichiesteModel r){
        super.addRichiesta(r,"COLLEGAMENTI");
    }

    public ArrayList<RichiesteModel> getListaRichiesteDaAccettare(String userid){
        ArrayList<RichiesteModel> a; //=new ArrayList<RichiesteModel>();
        String last_User="";
        a=super.getListaRichieste(userid,"COLLEGAMENTI","0", "USERID2=:topic");
        if (a.size()>0) {
            last_User=a.get(0).getUserid();
            for (int j=1;j<a.size();j++) {
                if (a.get(j).getUserid().equals(last_User)) {
                    a.remove(j);
                }
                else {
                    last_User=a.get(j).getUserid();
                }
        }
        }
        return a;

    }
    public ArrayList<RichiesteModel> getListaRichiesteAccettate (String userid) {
        ArrayList<RichiesteModel> richiesteModelArrayLst =new ArrayList<RichiesteModel>();
        ArrayList<RichiesteModel> richiesteModelArrayLst2 =new ArrayList<RichiesteModel>();

        richiesteModelArrayLst=super.getListaRichieste(userid,"COLLEGAMENTI","1", "USERID2=:topic");
        String lastUser="";
        if (richiesteModelArrayLst.size()>0) {
            lastUser=richiesteModelArrayLst.get(0).getUserid();
            for (int j=1;j<richiesteModelArrayLst.size();j++)
            {
                if (richiesteModelArrayLst.get(j).getUserid().equals(lastUser)) {
                    richiesteModelArrayLst.remove(j);
                }
                else {
                    lastUser=richiesteModelArrayLst.get(j).getUserid();
                }
            }
        }

        richiesteModelArrayLst2=super.getListaRichieste(userid,"COLLEGAMENTI","1", "USERID=:topic");
        for (int j=0;j<richiesteModelArrayLst2.size();j++)
        {
            if (!richiesteModelArrayLst2.get(j).getUserid2().equals(lastUser)) {
                richiesteModelArrayLst.add(richiesteModelArrayLst2.get(j));
            }
        }
        return richiesteModelArrayLst;
    }

    public void modifyRichiesta(String userid, String datatime){
        super.ModifyRichiesta(userid, datatime,"COLLEGAMENTI");
    }

}