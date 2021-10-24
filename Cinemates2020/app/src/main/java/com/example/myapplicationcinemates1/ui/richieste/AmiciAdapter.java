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

import com.example.myapplicationcinemates1.ApiGateway;
import com.example.myapplicationcinemates1.HomeActivity;
import com.example.myapplicationcinemates1.MainActivity;
import com.example.myapplicationcinemates1.R;
import com.example.myapplicationcinemates1.datamodel.RichiesteModel;
import com.example.myapplicationcinemates1.datamodel.UtentiModel;

import java.util.ArrayList;


public class AmiciAdapter extends RecyclerView.Adapter<AmiciAdapter.ViewHolder> {

    // creating a variable for array list and context.
    private ArrayList<RichiesteModel> richiesteModelArrayList;
    private Context context;
    private FriendsAdapterListener listener;
    private UtentiModel um;

    // creating a constructor for our variables.
    public AmiciAdapter(ArrayList<RichiesteModel> richiesteModelArrayList, Context context, FriendsAdapterListener listener) {
        this.richiesteModelArrayList = richiesteModelArrayList;
        this.context=context;
        this.listener=listener;
    }

    // method for filtering our recyclerview items.
    public void filterList(ArrayList<RichiesteModel> filterllist) {
        richiesteModelArrayList = filterllist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // below line is to inflate our layout.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.amici_item, parent, false);
        ImageView i;
        return new ViewHolder(view);
    }

    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // setting data to our views of recycler view.
        Log.d(MainActivity.TAG,"Friend Adapter onBindViewHolder");
        RichiesteModel model = richiesteModelArrayList.get(position);
        if (model.getnickname()==null) {
            Log.d(MainActivity.TAG,"utente non presente");
        }
        else {
            String utente= model.getnickname();
        }
        Log.d(MainActivity.TAG,"model nickname="+ model.getnickname2());

        if (HomeActivity.uid.equals(model.getUserid2())) {
            holder.nome.setText(model.getnome() + " "+ model.getcognome());
            holder.nickname.setText(model.getnickname());
            holder.datetime.setText(model.getDatatime());
            int id=context.getResources().getIdentifier(model.getImmagine(),"drawable", context.getPackageName());
            holder.imgview.setImageResource(id);
        }
        else if (HomeActivity.uid.equals(model.getUserid())) {
            ApiGateway apigw=new ApiGateway(false);
            um=apigw.getUtente(model.getUserid2(),"UTENTI");
            holder.nome.setText(um.getnome() + " "+ um.getcognome());
            holder.nickname.setText(um.getnickname());
            holder.datetime.setText(model.getDatatime());
            int id=context.getResources().getIdentifier(um.getImmagine(),"drawable", context.getPackageName());
            Log.d(MainActivity.TAG,"id  IN FRIENDSadapter="+Integer.toString(id)+";"+Integer.toString(id));
            holder.imgview.setImageResource(id);

        }

    }

    @Override
    public int getItemCount() {
        // returning the size of array list.
        return richiesteModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // creating variables for our views.
        private TextView nickname, nome,  datetime;
        private ImageView imgview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.idPerson);
            nickname = itemView.findViewById(R.id.Nickname);
            imgview = itemView.findViewById((R.id.imageView));
            datetime =itemView.findViewById(R.id.idDate);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    listener.onFriendSelected(richiesteModelArrayList.get(ViewHolder.this.getAdapterPosition()));
                }
            });


        }
    }

    public interface FriendsAdapterListener {
            void onFriendSelected(RichiesteModel fm);
        }
    }

