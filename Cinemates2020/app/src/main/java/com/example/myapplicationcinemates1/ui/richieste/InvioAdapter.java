package com.example.myapplicationcinemates1.ui.richieste;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicationcinemates1.HomeActivity;
import com.example.myapplicationcinemates1.MainActivity;
import com.example.myapplicationcinemates1.R;
import com.example.myapplicationcinemates1.datamodel.UtentiModel;

import java.util.ArrayList;

//public class FriendsAdapter extends RecyclerView.Adapter<com.example.myapplicationcinemates1.ui.Friends.FriendsAdapter.ViewHolder> {

public class InvioAdapter extends RecyclerView.Adapter<InvioAdapter.ViewHolder> {

    // creating a variable for array list and context.
    private ArrayList<UtentiModel> friendModelArrayList;
    private Context context;
    private  FriendsAdapterListener listener;

    // creating a constructor for our variables.
    public InvioAdapter(ArrayList<UtentiModel> friendModelArrayList, Context context, FriendsAdapterListener listener) {
        this.friendModelArrayList = friendModelArrayList;
        this.context=context;
        this.listener=listener;
    }

    // method for filtering our recyclerview items.
    public void filterList(ArrayList<UtentiModel> filterllist) {
        friendModelArrayList = filterllist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // below line is to inflate our layout.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.invio_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // setting data to our views of recycler view.
        int id;
        UtentiModel model = friendModelArrayList.get(position);
        if (model.getUserid()==null) {
            String utente = "utente non presente";
        }
        else {
            String utente= model.getnickname();
        }
        if ((model.getUserid()!=null) &&  (!model.getUserid().isEmpty()) &&  (!model.getUserid().equals(HomeActivity.uid))) {
            Log.d(MainActivity.TAG,"model nickname="+ model.getnickname());
            holder.nome1.setText(model.getnome() + " "+ model.getcognome());
            holder.nickname1.setText(model.getnickname());
            if ((model.getDatanascita() == null) || (model.getDatanascita().isEmpty())) {
                holder.datetime1.setText("data non presente");
            }
            else {
                holder.datetime1.setText(model.getDatanascita());
            }
            if (model.getImmagine() ==null || model.getImmagine().isEmpty() ) {
                id=context.getResources().getIdentifier("avatar00","drawable", context.getPackageName());
            }
            else {
                id=context.getResources().getIdentifier(model.getImmagine(),"drawable", context.getPackageName());
            }
            holder.imgview.setImageResource(id);
        }
    }

    @Override
    public int getItemCount() {
        // returning the size of array list.
        return friendModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // creating variables for our views.
        public TextView nickname1, datetime1, nome1;
        private ImageView imgview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our views with their ids.
            nickname1 = itemView.findViewById(R.id.Nickname);
            datetime1 =itemView.findViewById(R.id.idDate);
            imgview = itemView.findViewById((R.id.imageView));
            nome1=itemView.findViewById(R.id.idPerson);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    listener.onFriendSelected(friendModelArrayList.get(ViewHolder.this.getAbsoluteAdapterPosition()));   //.getAdapterPosition()));
                }
            });

        }
    }

    public interface FriendsAdapterListener {
        void onFriendSelected(UtentiModel fm);
    }
}
