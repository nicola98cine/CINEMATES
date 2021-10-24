package com.example.myapplicationcinemates1.ui.richieste.amici;

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

import com.example.myapplicationcinemates1.HomeActivity;
import com.example.myapplicationcinemates1.R;
import com.example.myapplicationcinemates1.datamodel.RichiesteAPIGW;
import com.example.myapplicationcinemates1.databinding.FragmentAmiciBinding;
import com.example.myapplicationcinemates1.datamodel.RichiesteModel;
import com.example.myapplicationcinemates1.ui.richieste.invio.InvioFragment;
import com.example.myapplicationcinemates1.ui.richieste.ricezione.RicezioneFragment;

import java.util.ArrayList;

import static android.view.View.GONE;
import static com.example.myapplicationcinemates1.MainActivity.DAO;

public class AmiciFragment extends Fragment {

    public static AmiciFragment context;
    private FragmentAmiciBinding binding;
    private RecyclerView friendRV;
    private AmiciAdapter adapter;
    public ArrayList<RichiesteModel> richiesteModelArrayLst;
    public ArrayList<RichiesteModel> richiesteModelArrayLst1;
    //protected ApiGateway apigw = new ApiGateway();
    protected RichiesteModel f;
    private Button but;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        System.out.println("FriendFragment buildRecyclerView");

        richiesteModelArrayLst =new ArrayList<RichiesteModel>();
        RichiesteAPIGW rd= (RichiesteAPIGW) DAO.getRichiesteDAO();
        richiesteModelArrayLst=rd.getListaRichiesteAccettate(HomeActivity.uid);

        /*apigw=new ApiGateway();
        String Userid2= Main2Activity.uid;
        String filter1="USERID2=:topic";
        richiesteModelArrayLst = apigw.getListaRichieste(Userid2,"COLLEGAMENTI","1", "USERID2=:topic");
        richiesteModelArrayLst1 = apigw.getListaRichieste(Userid2, "COLLEGAMENTI", "1","USERID=:topic");
        for (int j=0;j<richiesteModelArrayLst1.size();j++)
        {
            richiesteModelArrayLst.add(richiesteModelArrayLst1.get(j));
        }*/

        binding = FragmentAmiciBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        System.out.println("FriendFragment DOPO BINDING FRAGMENT");
        friendRV=binding.idRVFriend;
        but=binding.button3;
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
        Button ricezione=binding.Button;
        ricezione.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentTransaction ft =getActivity().getSupportFragmentManager().beginTransaction();
                Fragment newfrag=new RicezioneFragment();
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
        adapter = new AmiciAdapter(richiesteModelArrayLst, getContext(),this::onFriendSelected);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        friendRV.setHasFixedSize(true);
        friendRV.setLayoutManager(manager);
        friendRV.setAdapter(adapter);
    }

    public void onFriendSelected(RichiesteModel f) {
        System.out.println("friend selected"+f.getnickname());
    }

}