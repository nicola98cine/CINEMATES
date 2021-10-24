package com.example.myapplicationcinemates1.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicationcinemates1.ApiGateway;
import com.example.myapplicationcinemates1.HomeActivity;
import com.example.myapplicationcinemates1.MainActivity;
import com.example.myapplicationcinemates1.TaskTMDB;
import com.example.myapplicationcinemates1.databinding.FragmentHomeBinding;
import com.example.myapplicationcinemates1.datamodel.FilmModel;

import static android.os.SystemClock.sleep;

public class HomeFragment extends Fragment {

    private RecyclerView filmRV;
    protected SearchView editsearch;
    private FilmAdapter adapter;
    private FilmPresenter fp;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.d(MainActivity.TAG, "HOME FRAGMENT ");
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Log.d(MainActivity.TAG,"uid="+ HomeActivity.uid);
        filmRV=binding.idRVFilm;
        editsearch=(SearchView) binding.search;
        fp=new FilmPresenter(0,"","","");
        fp.setListaIniziale();
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
                //System.out.println("listener 1 query="+query);
                if(!query.isEmpty()){
                    buildRecyclerView(query);
                }
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                //System.out.println("listener 2 text="+newText);
                if(!newText.isEmpty()) {
                    buildRecyclerView(newText);
                }
                return false;
            }
        });
        return true;
    }

    private void buildRecyclerView (String t) {
        sleep(100);
        //System.out.println("t="+t);
        if (!t.isEmpty()) {
            fp.searchFilm(t);

        }
        adapter = new FilmAdapter(fp.st.a, getContext(),this::onFilmSelected);

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        filmRV.setHasFixedSize(true);
        filmRV.setLayoutManager(manager);
        filmRV.setAdapter(adapter);
    }

    public void onFilmSelected(FilmModel f) {
        Log.d(MainActivity.TAG,"film selected id film="+f.getId() + ";" + f.getFilmName());
        Intent intent=new Intent(getActivity(),FilmDetails.class);
        intent.putExtra("immagine", f.getIView() );
        intent.putExtra("titolo", f.getFilmName() );
        intent.putExtra("trama", f.getFilmDescription() );
        intent.putExtra("genere", f.getFilmGenere());
        intent.putExtra( "idfilm", Integer.toString(f.getId()));
        startActivity(intent);
    }


}