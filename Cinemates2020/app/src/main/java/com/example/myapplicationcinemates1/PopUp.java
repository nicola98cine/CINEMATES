package com.example.myapplicationcinemates1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.InputType;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplicationcinemates1.datamodel.FeedAPIGW;
import com.example.myapplicationcinemates1.datamodel.FeedModel;
import com.example.myapplicationcinemates1.datamodel.RichiesteAPIGW;
import com.example.myapplicationcinemates1.datamodel.RichiesteModel;
import com.example.myapplicationcinemates1.datamodel.UtentiAPIGW;
import com.example.myapplicationcinemates1.datamodel.UtentiModel;
import com.example.myapplicationcinemates1.ui.richieste.RichiestePresenter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static com.example.myapplicationcinemates1.MainActivity.DAO;

public class PopUp {
    private int esito=0;
    RichiesteModel f2;
    RichiestePresenter rp;
    Context context;
    String msg="";
    String Title = "MESSAGGIO INFORMATIVO";
    AlertDialog alertDialog;
    public int getesito() {
        return esito;
    }

    public PopUp(Context context, String msg) {
        this.context=context;
        this.msg=msg;
        rp=new RichiestePresenter();
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("MESSAGGIO INFORMATIVO");
        alertDialog.setMessage(msg);

    }

    public void PopUpMsg1() {
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    public void PopUpMsg2() {
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "NO",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();

    }

    public void PopUpMsgInvioRichiesta(UtentiModel u,  UtentiModel f) {
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Log.d(MainActivity.TAG,"USERID prima di INVIO RICHIESTA="+u.getUserid()+","+u.getnickname());
                        rp.invioRichiesta(u,f);
                    }
                });
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "NO",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        esito=-2;
                        dialog.dismiss();
                    }
                });
        alertDialog.show();


    }

     public String PopUpDialog(String userid, TextView t){
         String m_Text="LISTA_PERSONALE";
         //System.out.println("L'utente vuole creare la lista Personalizzata, inserisco PopUp");
         AlertDialog.Builder builder =new AlertDialog.Builder(context);
         builder.setTitle("MESSAGGIO INFORMATIVO");
         final EditText input =new EditText(context);
         input.setInputType(InputType.TYPE_CLASS_TEXT);
         builder.setView(input);
         builder.setMessage(msg);
         builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int which) {
                 String m_Text =input.getText().toString();
                 System.out.println("text" + m_Text);
                 if (m_Text != null && !m_Text.isEmpty()) {
                     UtentiAPIGW ud= (UtentiAPIGW) DAO.getUtentiDAO();
                     ud.modifyUtente(userid,m_Text);

                 }
                 t.setText(m_Text);
             }
         });
         builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int which) {
                 dialog.cancel();
             }
         });
         builder.show();
         return m_Text;
     }
}
