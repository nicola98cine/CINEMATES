package com.example.myapplicationcinemates1.ui.personale;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicationcinemates1.ApiGateway;
import com.example.myapplicationcinemates1.HomeActivity;
import com.example.myapplicationcinemates1.MainActivity;
import com.example.myapplicationcinemates1.PopUp;
import com.example.myapplicationcinemates1.R;
import com.example.myapplicationcinemates1.TaskTMDB;
import com.example.myapplicationcinemates1.databinding.FragmentPersonaleBinding;
import com.example.myapplicationcinemates1.datamodel.FilmAPIGW;
import com.example.myapplicationcinemates1.datamodel.FilmModel;
import com.example.myapplicationcinemates1.datamodel.UtentiModel;
import com.example.myapplicationcinemates1.ui.davedere.DavederePresenter;
import com.example.myapplicationcinemates1.ui.home.FilmAdapter;
import com.example.myapplicationcinemates1.ui.preferiti.PreferitiFragment;

import java.util.ArrayList;

import static com.example.myapplicationcinemates1.MainActivity.DAO;

public class PersonalFragment extends Fragment {
    private RecyclerView filmRV;
    private FilmAdapter adapter;
    protected SearchView editsearch;
    PersonalPresenter pp ;
    private FragmentPersonaleBinding binding;
    String listapers="0";
    String Userid="";


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentPersonaleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Userid= HomeActivity.uid;
        pp=new PersonalPresenter("0",this.getContext());
        Log.d(MainActivity.TAG,"Verifica se utente possiede Lista personalizzata: "+Userid);
        TextView t=binding.nomelista;
        pp.setTextView(t);

        listapers=pp.getNomeLista(Userid);

        if (!listapers.equals("0")) {
            filmRV=binding.idRVFilm;
            t.setText(listapers);
            editsearch=(SearchView) binding.search;
            pp.setListaFilm(Userid);
            buildRecyclerView("");
            doSearch();
        }
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private boolean doSearch() {
        editsearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(!query.isEmpty()){
                    buildRecyclerView(query);
                }
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                if(!newText.isEmpty()) {
                    buildRecyclerView(newText);
                }
                return false;
            }
        });
        return true;
    }

    private void buildRecyclerView (String t) {

        if (t.isEmpty()) {
            pp.copialiste();
        }
        else {
            pp.filtraliste(t);
        }
        adapter = new FilmAdapter(pp.listapers, getContext(), this::onFilmSelected);

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        filmRV.setHasFixedSize(true);
        filmRV.setLayoutManager(manager);
        filmRV.setAdapter(adapter);
    }

    public void onFilmSelected(FilmModel f) {
        Intent intent=new Intent(getActivity(), PersonalDetails.class);
        Log.d(MainActivity.TAG,"film id = "+Integer.toString(f.getId()));
        intent.putExtra("immagine", f.getIView() );
        intent.putExtra("titolo", f.getFilmName() );
        intent.putExtra("trama", f.getFilmDescription() );
        intent.putExtra("genere", f.getFilmGenere());
        intent.putExtra("idfilm", Integer.toString(f.getId()));
        startActivity(intent);
    }


}