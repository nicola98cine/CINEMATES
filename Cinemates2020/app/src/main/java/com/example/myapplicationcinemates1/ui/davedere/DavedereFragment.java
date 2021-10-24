package com.example.myapplicationcinemates1.ui.davedere;

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
import com.example.myapplicationcinemates1.HomeActivity;
import com.example.myapplicationcinemates1.MainActivity;
import com.example.myapplicationcinemates1.databinding.FragmentDavedereBinding;
import com.example.myapplicationcinemates1.datamodel.FilmModel;
import com.example.myapplicationcinemates1.ui.home.FilmAdapter;


public class DavedereFragment extends Fragment {
    private RecyclerView filmRV;
    protected SearchView editsearch;
    private FilmAdapter adapter;
    private FragmentDavedereBinding binding;
    Intent openPage1;
    DavederePresenter dv ;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentDavedereBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        dv=new DavederePresenter("0");
        filmRV=binding.idRVFilm;
        editsearch=(SearchView) binding.search;
        String Userid= HomeActivity.uid;
        dv.setListaFilm(Userid);
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
        //System.out.println("buildRecyclerView");
        //System.out.println("query ricerca="+t);

        if (t.isEmpty()) {
            dv.copialiste();
        }
        else {
            dv.filtraliste(t);
        }
        //System.out.println("numelem="+Integer.toString(st.a.size()));
        adapter = new FilmAdapter(dv.listatosee, getContext(), this::onFilmSelected);

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        filmRV.setHasFixedSize(true);
        filmRV.setLayoutManager(manager);
        filmRV.setAdapter(adapter);
    }

    public void onFilmSelected(FilmModel f) {
        Intent intent=new Intent(getActivity(), DavedereDetails.class);
        Log.d(MainActivity.TAG,"film id = "+Integer.toString(f.getId()));
        intent.putExtra("immagine", f.getIView() );
        intent.putExtra("titolo", f.getFilmName() );
        intent.putExtra("trama", f.getFilmDescription() );
        intent.putExtra("genere", f.getFilmGenere());
        intent.putExtra("idfilm", Integer.toString(f.getId()));

        startActivity(intent);
    }

}