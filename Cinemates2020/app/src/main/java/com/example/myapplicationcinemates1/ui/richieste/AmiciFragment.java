package com.example.myapplicationcinemates1.ui.richieste;

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
import com.example.myapplicationcinemates1.databinding.FragmentAmiciBinding;
import com.example.myapplicationcinemates1.datamodel.RichiesteModel;
import static android.view.View.GONE;

public class AmiciFragment extends Fragment {

    public static AmiciFragment context;
    private FragmentAmiciBinding binding;
    private RecyclerView friendRV;
    private AmiciAdapter adapter;
    RichiestePresenter rp ;
    private Button invio;
    private Button ricezione;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        System.out.println("FriendFragment buildRecyclerView");
        rp=new RichiestePresenter();
        rp.setListaRichiesteAccettate(HomeActivity.uid);

        binding = FragmentAmiciBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        System.out.println("FriendFragment DOPO BINDING FRAGMENT");
        friendRV=binding.idRVFriend;
        invio=binding.button3;
        invio.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentTransaction ft =getActivity().getSupportFragmentManager().beginTransaction();
                Fragment newfrag=new InvioFragment();
                ft.replace(R.id.nav_host_fragment_content_main2,newfrag);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        ricezione=binding.Button;
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
        return root;

    }

    @Override
    public void onDestroyView() {
        invio.setVisibility(GONE);
        super.onDestroyView();
        binding = null;
    }

    private void buildRecyclerView (String t) {
        adapter = new AmiciAdapter(rp.richiesteModelArrayLst, getContext(),this::onFriendSelected);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        friendRV.setHasFixedSize(true);
        friendRV.setLayoutManager(manager);
        friendRV.setAdapter(adapter);
    }

    public void onFriendSelected(RichiesteModel f) {
        //System.out.println("friend selected"+f.getnickname());
    }

}