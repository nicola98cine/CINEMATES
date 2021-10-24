package com.example.myapplicationcinemates1.ui.feed;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicationcinemates1.ApiGateway;
import com.example.myapplicationcinemates1.MainActivity;
import com.example.myapplicationcinemates1.datamodel.FeedAPIGW;
import com.example.myapplicationcinemates1.HomeActivity;
import com.example.myapplicationcinemates1.databinding.FragmentFeedBinding;
import com.example.myapplicationcinemates1.datamodel.FeedModel;
import com.example.myapplicationcinemates1.datamodel.RichiesteModel;
import com.example.myapplicationcinemates1.ui.richieste.RichiestePresenter;

import java.util.ArrayList;

import static com.example.myapplicationcinemates1.MainActivity.DAO;

public class FeedFragment extends Fragment {

    private FragmentFeedBinding binding;
    private RecyclerView feedRV;
    private FeedAdapter adapter;
    FeedPresenter fp ;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        Log.d(MainActivity.TAG,"FeedFragment buildRecyclerView");
        String Userid2= HomeActivity.uid;
        System.out.println("Userid2="+Userid2);
        fp=new FeedPresenter();
        fp.AddListaAmici(Userid2);

        binding = FragmentFeedBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Log.d(MainActivity.TAG,"FeedFragment DOPO BINDING FRAGMENT");

        feedRV=binding.idRVFeed;
        buildRecyclerView("");
        return root;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private void buildRecyclerView (String t) {
        fp.AddListaFeed();
        adapter = new FeedAdapter(fp.feedModelArrayLstFiltrati, getContext(), this::onFeedSelected );
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        feedRV.setHasFixedSize(true);
        feedRV.setLayoutManager(manager);
        feedRV.setAdapter(adapter);

    }

    public void onFeedSelected(FeedModel f) {
        //System.out.println("friend selected"+f.getnickname());
    }
}