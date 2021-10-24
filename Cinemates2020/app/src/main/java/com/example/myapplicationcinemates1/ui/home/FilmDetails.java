package com.example.myapplicationcinemates1.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplicationcinemates1.MainActivity;
import com.example.myapplicationcinemates1.R;
import com.squareup.picasso.Picasso;

public class FilmDetails extends AppCompatActivity {
    Button bpref;
    Button bsee;
    Button bpersonal;
    TextView text1 ;
    TextView text2 ;
    TextView text3 ;
    ImageView iv;

    int Idfilm=0;
    String titolo;
    String trama;
    String genere;
    String img;

    //@SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_details);

        text1 =findViewById(R.id.textView);
        text2 =findViewById(R.id.textView2);
        text3 =findViewById(R.id.textView3);
        iv=findViewById(R.id.imageView2);
        bpref=findViewById(R.id.prefer);
        bsee=findViewById(R.id.tosee);
        bpersonal=findViewById(R.id.personal);

        Bundle datipassati= getIntent().getExtras();
        img= datipassati.getString("immagine").replace("w185","w300");
        titolo= datipassati.getString("titolo");
        trama=  datipassati.getString("trama");
        Idfilm= Integer.parseInt(datipassati.getString("idfilm"));
        Log.d(MainActivity.TAG,"idf="+Idfilm);
        FilmPresenter fp=new FilmPresenter(Idfilm, img, titolo, trama);
        genere=fp.getGenere();
        fp.addFeed();

        Picasso.with(this.getApplicationContext()).load(img).into(iv);
        text2.setText(titolo);
        text3.setText(trama);
        text1.setText(genere);

        fp.setContext(FilmDetails.this);
        bpref.setOnClickListener(fp::onClickPref) ;
        bsee.setOnClickListener(fp::onClickVedere) ;
        bpersonal.setOnClickListener(fp::onClickPersonale) ;

    }
 }

