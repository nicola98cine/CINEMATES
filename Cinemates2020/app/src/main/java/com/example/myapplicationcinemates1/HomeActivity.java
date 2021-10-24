package com.example.myapplicationcinemates1;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.myapplicationcinemates1.databinding.ActivityMain2Binding;
import com.example.myapplicationcinemates1.datamodel.UtentiModel;
import com.example.myapplicationcinemates1.ui.home.FilmPresenter;
import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class HomeActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMain2Binding binding;
    public static String uid;
    private FilmPresenter fp;
    String time="";
    SimpleDateFormat df;
    public static UtentiModel u;
    public static String nick;
    public static final String TipoUtente="mobile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle datipassati= getIntent().getExtras();

        uid= datipassati.getString("userid");
        Log.d(MainActivity.TAG,"UIDMAIN2"+uid);

        fp=new FilmPresenter(0,null,null,null);
        fp.addLogin(uid);
        u=fp.getUtente(uid);

        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain2.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.personal,  R.id.nav_friends ,
                R.id.nav_friendsfeed)
                .setOpenableLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main2);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        //setupDrawerContent(navigationView);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //System.out.println("MAIN2 ONCREATE OPTION");
        getMenuInflater().inflate(R.menu.main2, menu);

        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        //System.out.println("OnSUpportNavigateUp");
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main2);
        //System.out.println("navc"+ navController.getCurrentDestination().toString());
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
