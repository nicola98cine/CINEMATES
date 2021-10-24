package com.example.myapplicationcinemates1.ui.feed;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicationcinemates1.MainActivity;
import com.example.myapplicationcinemates1.R;
import com.example.myapplicationcinemates1.datamodel.FeedModel;

import java.util.ArrayList;

//public class FriendsAdapter extends RecyclerView.Adapter<com.example.myapplicationcinemates1.ui.Friends.FriendsAdapter.ViewHolder> {

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {

    // creating a variable for array list and context.
    private ArrayList<FeedModel> feedModelArrayList;
    private Context context;
    private  FeedAdapterListener listener;

    // creating a constructor for our variables.
    public FeedAdapter(ArrayList<FeedModel> feedModelArrayList, Context context, FeedAdapterListener listener) {
        this.feedModelArrayList = feedModelArrayList;
        this.context=context;
        this.listener=listener;
    }

    // method for filtering our recyclerview items.
    public void filterList(ArrayList<FeedModel> filterllist) {
        feedModelArrayList = filterllist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // below line is to inflate our layout.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feeds_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // setting data to our views of recycler view.
        //System.out.println("Feed Adapter onBindViewHolder");
        FeedModel model = feedModelArrayList.get(position);

        //System.out.println("filmname="+model.getFilmName());
        if (model.getnickname()==null) {
            String utente = "utente non presente";
        }
        else {
            String utente= model.getnickname();
        }
        Log.d(MainActivity.TAG,"model nickname="+ model.getnickname());

        holder.nickname1.setText(model.getnickname());
        holder.datetime1.setText(model.getDatatime());
        holder.descrizione1.setText(model.getDescription());
    }

    @Override
    public int getItemCount() {
        // returning the size of array list.
        return feedModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our views.
        public TextView nickname1, datetime1, descrizione1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our views with their ids.
            nickname1 = itemView.findViewById(R.id.Nickname);
            datetime1 = itemView.findViewById(R.id.Datatime);
            descrizione1=itemView.findViewById(R.id.Description);

        }
    }

    public interface FeedAdapterListener {
        void onFeedSelected(FeedModel fm);
    }
}
