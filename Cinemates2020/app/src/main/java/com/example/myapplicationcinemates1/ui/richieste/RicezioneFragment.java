package com.example.myapplicationcinemates1.ui.richieste;

import android.os.Bundle;
import android.util.Log;
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
import com.example.myapplicationcinemates1.MainActivity;
import com.example.myapplicationcinemates1.R;
import com.example.myapplicationcinemates1.databinding.FragmentRicezioneBinding;
import com.example.myapplicationcinemates1.datamodel.RichiesteModel;

import static android.view.View.GONE;

public class RicezioneFragment extends Fragment {

    private RecyclerView friendRV;
    private Button invio;
    private Button amici;
    //RichiestePresenter rp ;
    private FragmentRicezioneBinding binding;
    private RicezioneAdapter adapter;
    public static RicezioneFragment context;
    String Userid;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.d(MainActivity.TAG,"FriendFragment buildRecyclerView");
        Userid= HomeActivity.uid;
        binding = FragmentRicezioneBinding.inflate(inflater, container, false);

        View root = binding.getRoot();
        System.out.println("FriendFragment DOPO BINDING FRAGMENT");
        friendRV=binding.idRVFriend;
        invio=binding.Button;
        amici=binding.button3;
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
        return root;

    }

    @Override
    public void onDestroyView() {
        invio.setVisibility(GONE);
        super.onDestroyView();
        binding = null;
    }

    private void buildRecyclerView (String t) {

        adapter = new RicezioneAdapter(Userid,  getContext(),this::onFriendSelected);

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        friendRV.setHasFixedSize(true);
        friendRV.setLayoutManager(manager);
        friendRV.setAdapter(adapter);
    }

    public void onFriendSelected(RichiesteModel f) {
        System.out.println("friend selected"+f.getnickname());
    }

}