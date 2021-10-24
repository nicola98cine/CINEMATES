package com.example.myapplicationcinemates1.ui.personale;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplicationcinemates1.R;
import com.example.myapplicationcinemates1.datamodel.FilmAPIGW;
import com.squareup.picasso.Picasso;

import static com.example.myapplicationcinemates1.MainActivity.DAO;

public class PersonalDetails extends AppCompatActivity {
    TextView text1 ;
    TextView text2 ;
    TextView text3 ;
    ImageView iv;
    Button personal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listapersonal_details);

        text1 =findViewById(R.id.textView);
        text2 =findViewById(R.id.textView2);
        text3 =findViewById(R.id.textView3);
        personal=findViewById(R.id.plst);
        iv=findViewById(R.id.imageView2);
        Bundle datipassati= getIntent().getExtras();
        String img= datipassati.getString("immagine").replace("w185","w300");
        String titolo= datipassati.getString("titolo");
        String trama=  datipassati.getString("trama");
        String genere= datipassati.getString("genere");
        String idFilm=datipassati.getString("idfilm");
        PersonalPresenter p= new PersonalPresenter(idFilm,this.getApplicationContext());
        Picasso.with(this.getApplicationContext()).load(img).into(iv);
        text2.setText(titolo);
        text3.setText(trama);
        text1.setText(genere);
        p.setContext(getApplicationContext());
        personal.setOnClickListener(p::onClick) ;

    }

}


