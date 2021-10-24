package com.example.myapplicationcinemates1.ui.preferiti;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplicationcinemates1.R;
import com.example.myapplicationcinemates1.ui.richieste.InvioFragment;
import com.squareup.picasso.Picasso;

public class PreferitiDetails extends AppCompatActivity {
    TextView text1 ;
    TextView text2 ;
    TextView text3 ;
    ImageView iv;
    Button preferbutt;
    String idFilm;
    Context c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferiti_details);

        text1 =findViewById(R.id.textView);
        text2 =findViewById(R.id.textView2);
        text3 =findViewById(R.id.textView3);
        iv=findViewById(R.id.imageView2);
        preferbutt=findViewById(R.id.prefer);
        Bundle datipassati= getIntent().getExtras();
        String img= datipassati.getString("immagine").replace("w185","w300");
        String titolo= datipassati.getString("titolo");
        String trama=  datipassati.getString("trama");
        String genere= datipassati.getString("genere");
        idFilm= datipassati.getString("idfilm");

        Picasso.with(this.getApplicationContext()).load(img).into(iv);
        text2.setText(titolo);
        text3.setText(trama);
        text1.setText(genere);

        PreferitiPresenter pf= new PreferitiPresenter(idFilm);
        pf.setContext(getApplicationContext());
        preferbutt.setOnClickListener(pf::onClick);
    }

}
