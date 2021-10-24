package com.example.myapplicationcinemates1.ui.richieste;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicationcinemates1.HomeActivity;
import com.example.myapplicationcinemates1.MainActivity;
import com.example.myapplicationcinemates1.PopUp;
import com.example.myapplicationcinemates1.R;
import com.example.myapplicationcinemates1.TaskTMDB;
import com.example.myapplicationcinemates1.databinding.FragmentInvioBinding;
import com.example.myapplicationcinemates1.datamodel.UtentiModel;

import static android.view.View.GONE;

public class InvioFragment extends Fragment {
    private RecyclerView friendsRV;
    protected SearchView editsearch;
    private InvioAdapter adapter;
    RichiestePresenter rp ;
    private FragmentInvioBinding binding;
    Button ricezione;
    Button amici;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentInvioBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        friendsRV=binding.idRVFriend;
        editsearch=(SearchView) binding.search;

        rp=new RichiestePresenter();
        rp.setListaCollegamenti(HomeActivity.uid);
        ricezione=binding.Button;
        ricezione.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d(MainActivity.TAG,"toogle abilitato");
                FragmentTransaction ft =getActivity().getSupportFragmentManager().beginTransaction();
                Fragment newfrag=new RicezioneFragment();
                ft.replace(R.id.nav_host_fragment_content_main2,newfrag);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.addToBackStack(null);
                ft.commit();

            }
        });
        amici=binding.button3;
        amici.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction ft =getActivity().getSupportFragmentManager().beginTransaction();
                Fragment newfrag=new AmiciFragment();
                ft.replace(R.id.nav_host_fragment_content_main2,newfrag);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        buildRecyclerView("");
        doSearch();
        return root;

    }

    @Override
    public void onDestroyView() {
        //ricezione.setVisibility(GONE);
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
            rp.copialiste();
        }
        else {
            rp.filtraliste(t);
        }
        //System.out.println("numelem="+Integer.toString(st.a.size()));
        adapter = new InvioAdapter(rp.listafriend, getContext(), this::onFriendSelected);

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        friendsRV.setHasFixedSize(true);
        friendsRV.setLayoutManager(manager);
        friendsRV.setAdapter(adapter);
    }

    public void onFriendSelected(UtentiModel f) {
        rp.invioPopup(HomeActivity.u,f,this.getContext());
    }

}
