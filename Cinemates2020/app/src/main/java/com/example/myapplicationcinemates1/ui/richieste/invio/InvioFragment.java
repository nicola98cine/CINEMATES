package com.example.myapplicationcinemates1.ui.richieste.invio;

import android.os.Bundle;
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
import com.example.myapplicationcinemates1.PopUp;
import com.example.myapplicationcinemates1.R;
import com.example.myapplicationcinemates1.TaskTMDB;
import com.example.myapplicationcinemates1.datamodel.UtentiAPIGW;
import com.example.myapplicationcinemates1.databinding.FragmentInvioBinding;
import com.example.myapplicationcinemates1.datamodel.RichiesteModel;
import com.example.myapplicationcinemates1.datamodel.UtentiModel;
import com.example.myapplicationcinemates1.ui.richieste.amici.AmiciFragment;
import com.example.myapplicationcinemates1.ui.richieste.ricezione.RicezioneFragment;

import java.util.ArrayList;

import static android.view.View.GONE;
import static com.example.myapplicationcinemates1.MainActivity.DAO;

public class InvioFragment extends Fragment {
    private RecyclerView friendsRV;
    private InvioAdapter adapter;
    protected TaskTMDB st;
    protected SearchView editsearch;

    //private GalleryViewModel galleryViewModel;
    private FragmentInvioBinding binding;
    //protected ApiGateway apigw = new ApiGateway();
    protected ArrayList<UtentiModel> listafriendintera;
    protected ArrayList<UtentiModel> listafriend;
    protected UtentiModel f;
    protected RichiesteModel f2;
    int caso=1;

    Button but;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //galleryViewModel = new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentInvioBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        friendsRV=binding.idRVFriend;
        editsearch=(SearchView) binding.search;

        //listapref=new ArrayList<FilmModel>();
        //apigw=new ApiGateway();
        listafriend=new ArrayList<UtentiModel>();
        listafriendintera=new ArrayList<UtentiModel>();

        System.out.println("FUNZIONALITA INVIO RICHIESTE ESEGUO API GW per Ricerca degli UTENTI");
        String Userid= HomeActivity.uid;
        UtentiAPIGW ud= (UtentiAPIGW) DAO.getUtentiDAO();
        //listafriendintera=ud.getListaUtenti(Main2Activity.uid);
        listafriendintera=ud.getListaUtenti(Userid);

        //listafriendintera = apigw.getListaUtenti(Userid,"UTENTI");

        //String newkey="";
        //st= new SearchTask();
        //st.onPreExecute();
        but=binding.Button;
        but.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                System.out.println("toogle abilitato");
                but.setVisibility(GONE);
                FragmentTransaction ft =getActivity().getSupportFragmentManager().beginTransaction();
                Fragment newfrag=new RicezioneFragment();
                ft.replace(R.id.nav_host_fragment_content_main2,newfrag);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.addToBackStack(null);
                ft.commit();

            }
        });
        Button amici=binding.button3;
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
        but.setVisibility(GONE);
        super.onDestroyView();
        binding = null;
    }

    private void copialiste() {
        listafriend.clear();
        for (int i=0;i<listafriendintera.size();i++) {
            f=new UtentiModel(listafriendintera.get(i).getUserid(),listafriendintera.get(i).getnickname(),
                    listafriendintera.get(i).getImmagine(),
                    listafriendintera.get(i).getnome(), listafriendintera.get(i).getcognome(),
                    listafriendintera.get(i).getDatanascita(), "",listafriendintera.get(i).getListapers(),listafriendintera.get(i).getTipoutente() );

            listafriend.add(f);

        }
    }

    private void filtraliste(String t) {
        listafriend.clear();
        for (int i=0;i<listafriendintera.size();i++) {
           // if (listafriendintera.get(i).getId().toLowerCase().contains(t.toLowerCase())) {
            if (listafriendintera.get(i).getnickname().toLowerCase().contains(t.toLowerCase())) {

                f=new UtentiModel(listafriendintera.get(i).getUserid(),listafriendintera.get(i).getnickname(),
                        listafriendintera.get(i).getImmagine(),
                        listafriendintera.get(i).getnome(),listafriendintera.get(i).getcognome(),listafriendintera.get(i).getDatanascita(),"",
                        listafriendintera.get(i).getListapers(),listafriendintera.get(i).getTipoutente());
                listafriend.add(f);
            }
        }
    }

    private boolean doSearch() {
        editsearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                System.out.println("listener 1 query="+query);
                if(!query.isEmpty()){
                    buildRecyclerView(query);
                }
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                System.out.println("listener 2 text="+newText);
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
        //courseModalArrayList.clear();
        System.out.println("buildRecyclerView");
        System.out.println("query ricerca="+t);

        if (t.isEmpty()) {
            copialiste();
        }
        else {
            filtraliste(t);
        }
        //System.out.println("numelem="+Integer.toString(st.a.size()));
        adapter = new InvioAdapter(listafriend, getContext(), this::onFriend2Selected);
        //adapter = new FilmAdapter(st.a, HomeFragment.this,this::onFilmSelected);

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        friendsRV.setHasFixedSize(true);
        friendsRV.setLayoutManager(manager);
        friendsRV.setAdapter(adapter);
    }

    public void onFriend2Selected(UtentiModel f) {

        String msg="INVIANDO RICHIESTA DI COLLEGAMENTO A UTENTE " + f.getnickname() + " , VUOI PROSEGUIRE ?";
        PopUp p =new PopUp(this.getContext(),msg);
        p.PopUpMsgInvioRichiesta(HomeActivity.u,f);
        //p.PopUpMsg(this.getContext(),msg,2, 2, Main2Activity.u,f);

    }

}
