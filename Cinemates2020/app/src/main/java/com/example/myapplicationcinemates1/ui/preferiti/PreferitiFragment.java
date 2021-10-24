package com.example.myapplicationcinemates1.ui.preferiti;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicationcinemates1.ApiGateway;
import com.example.myapplicationcinemates1.HomeActivity;
import com.example.myapplicationcinemates1.R;
import com.example.myapplicationcinemates1.TaskTMDB;
import com.example.myapplicationcinemates1.databinding.FragmentPreferitiBinding;
import com.example.myapplicationcinemates1.datamodel.FilmAPIGW;
import com.example.myapplicationcinemates1.datamodel.FilmModel;
import com.example.myapplicationcinemates1.ui.home.FilmAdapter;

import java.util.ArrayList;

import static com.example.myapplicationcinemates1.MainActivity.DAO;


public class PreferitiFragment extends Fragment {
    private RecyclerView filmRV;
    protected SearchView editsearch;
    private FilmAdapter adapter;
    private FragmentPreferitiBinding binding;
    Intent openPage1;
    PreferitiPresenter pf ;
    static FragmentActivity frag_activity;

    public View onCreateView(@NonNull LayoutInflater inflater,
                                    ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentPreferitiBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        pf=new PreferitiPresenter("0" );
        frag_activity=getActivity();
        System.out.println("GETACTIVITY"+frag_activity.toString());
        System.out.println("GETCONTEXT"+this.getContext().toString());
        System.out.println("GETAPPCONTEXT"+this.getContext().getApplicationContext().toString());


        filmRV=binding.idRVFilm;
        editsearch=(SearchView) binding.search;
        String Userid= HomeActivity.uid;
        pf.setListaFilm(Userid);
        buildRecyclerView("");
        doSearch();
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
                //filter(newText);
                return false;
            }
        });
        return true;
    }

    private void buildRecyclerView (String t) {
        if (t.isEmpty()) {
            pf.copialiste();
        }
        else {
            pf.filtraliste(t);
        }
        adapter = new FilmAdapter(pf.listapref, getContext(), this::onFilmSelected);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        filmRV.setHasFixedSize(true);
        filmRV.setLayoutManager(manager);
        filmRV.setAdapter(adapter);
    }

    public void onFilmSelected(FilmModel f) {
        //Intent intent=new Intent(getActivity(), PreferitiDetails.class);
        Intent intent=new Intent(frag_activity, PreferitiDetails.class);
        intent.putExtra("immagine", f.getIView() );
        intent.putExtra("titolo", f.getFilmName() );
        intent.putExtra("trama", f.getFilmDescription() );
        intent.putExtra("genere", f.getFilmGenere());
        intent.putExtra("idfilm", Integer.toString(f.getId()));
        startActivity(intent);
    }


}