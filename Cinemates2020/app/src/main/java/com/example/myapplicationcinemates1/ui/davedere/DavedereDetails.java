package com.example.myapplicationcinemates1.ui.davedere;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplicationcinemates1.R;
import com.example.myapplicationcinemates1.datamodel.FilmAPIGW;
import com.squareup.picasso.Picasso;

import static com.example.myapplicationcinemates1.MainActivity.DAO;

public class DavedereDetails extends AppCompatActivity {
    TextView text1 ;
    TextView text2 ;
    TextView text3 ;
    ImageView iv;
    Button toseebt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_davedere_details);

        text1 =findViewById(R.id.textView);
        text2 =findViewById(R.id.textView2);
        text3 =findViewById(R.id.textView3);
        toseebt=findViewById(R.id.tosee);
        iv=findViewById(R.id.imageView2);
        Bundle datipassati= getIntent().getExtras();
        String img= datipassati.getString("immagine").replace("w185","w300");
        String titolo= datipassati.getString("titolo");
        String trama=  datipassati.getString("trama");
        String genere= datipassati.getString("genere");
        String idFilm=datipassati.getString("idfilm");
        DavederePresenter dv = new DavederePresenter(idFilm);
        Picasso.with(this.getApplicationContext()).load(img).into(iv);
        text2.setText(titolo);
        text3.setText(trama);
        text1.setText(genere);
        dv.setContext(getApplicationContext());
        FilmAPIGW fa = (FilmAPIGW) DAO.getFilmDAO();
        toseebt.setOnClickListener(dv::onClick);

    }

}


