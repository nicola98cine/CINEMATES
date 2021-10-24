package com.example.myapplicationcinemates1.ui.richieste.ricezione;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicationcinemates1.ApiGateway;
import com.example.myapplicationcinemates1.HomeActivity;
import com.example.myapplicationcinemates1.R;
import com.example.myapplicationcinemates1.databinding.FragmentRicezioneBinding;
import com.example.myapplicationcinemates1.datamodel.RichiesteModel;
import com.example.myapplicationcinemates1.ui.richieste.amici.AmiciFragment;
import com.example.myapplicationcinemates1.ui.richieste.invio.InvioFragment;

import java.util.ArrayList;

import static android.view.View.GONE;

public class RicezioneFragment extends Fragment {

    public static RicezioneFragment context;
    private FragmentRicezioneBinding binding;
    private RecyclerView friendRV;
    private RicezioneAdapter adapter;
    public ArrayList<RichiesteModel> richiesteModelArrayLst;
    protected ApiGateway apigw = new ApiGateway();
    protected RichiesteModel f;
    private Button but;

    private Button amici;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        System.out.println("FriendFragment buildRecyclerView");

        richiesteModelArrayLst =new ArrayList<RichiesteModel>();
        apigw=new ApiGateway();
        String Userid2= HomeActivity.uid;
        richiesteModelArrayLst = apigw.getListaRichieste(Userid2,"COLLEGAMENTI","0","USERID2=:topic");

        binding = FragmentRicezioneBinding.inflate(inflater, container, false);

        View root = binding.getRoot();
        System.out.println("FriendFragment DOPO BINDING FRAGMENT");
        friendRV=binding.idRVFriend;
        but=binding.Button;
        amici=binding.button3;
        but.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                    FragmentTransaction ft =getActivity().getSupportFragmentManager().beginTransaction();
                    Fragment newfrag=new InvioFragment();
                    ft.replace(R.id.nav_host_fragment_content_main2,newfrag);
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    ft.addToBackStack(null);
                    ft.commit();
            }
        });
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
        //editsearch=(SearchView) binding.search;
        return root;

    }

    @Override
    public void onDestroyView() {
        but.setVisibility(GONE);
        super.onDestroyView();
        binding = null;
    }

    private void buildRecyclerView (String t) {
        System.out.println("FriendFragment buildRecyclerView");
        adapter = new RicezioneAdapter(richiesteModelArrayLst, getContext(),this::onFriendSelected);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        friendRV.setHasFixedSize(true);
        friendRV.setLayoutManager(manager);
        friendRV.setAdapter(adapter);
    }

    public void onFriendSelected(RichiesteModel f) {
        System.out.println("friend selected"+f.getnickname());
    }

}