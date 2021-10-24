package com.example.myapplicationcinemates1.ui.richieste.ricezione;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicationcinemates1.ApiGateway;
import com.example.myapplicationcinemates1.datamodel.FeedAPIGW;
import com.example.myapplicationcinemates1.HomeActivity;
import com.example.myapplicationcinemates1.R;
import com.example.myapplicationcinemates1.datamodel.FeedModel;
import com.example.myapplicationcinemates1.datamodel.RichiesteModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class RicezioneAdapter extends RecyclerView.Adapter<RicezioneAdapter.ViewHolder> {

    // creating a variable for array list and context.
    private ArrayList<RichiesteModel> richiesteModelArrayList;
    private Context context;
    private FriendsAdapterListener listener;

    // creating a constructor for our variables.
    public RicezioneAdapter(ArrayList<RichiesteModel> richiesteModelArrayList, Context context, FriendsAdapterListener listener) {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ricezione_item, parent, false);
        ImageView i;

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // setting data to our views of recycler view.
        System.out.println("Friend Adapter onBindViewHolder");
        RichiesteModel model = richiesteModelArrayList.get(position);

        //System.out.println("filmname="+model.getFilmName());
        if (model.getnickname()==null) {
            String utente = "utente non presente";
        }
        else {
            String utente= model.getnickname();
        }

        //String path = context.getApplicationContext().getFilesDir().getAbsolutePath();

        System.out.println("model name="+ model.getnome());
        System.out.println("model nickname="+ model.getnickname());

        holder.nome.setText(model.getnome() + " "+ model.getcognome());
        holder.nickname.setText(model.getnickname());
        holder.datetime.setText(model.getDatatime());

        int id=context.getResources().getIdentifier(model.getImmagine(),"drawable", context.getPackageName());
        //int id2=context.getResources().getIdentifier("avatar05","drawable", context.getPackageName());
        System.out.println("id  IN FRIENDSadapter="+Integer.toString(id)+";"+Integer.toString(id));
        holder.imgview.setImageResource(id);

    }

    @Override
    public int getItemCount() {
        // returning the size of array list.
        return richiesteModelArrayList.size();
        //return 20;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ApiGateway apigw=new ApiGateway();

        // creating variables for our views.
        private TextView nickname, nome, cognome, datetime;
        private ImageView imgview;
        private ImageButton but;
        String userid="";
        String dt="";

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our views with their ids.

            but=itemView.findViewById(R.id.imageButton);
            nome = itemView.findViewById(R.id.idPerson);
            nickname = itemView.findViewById(R.id.Nickname);
            imgview = itemView.findViewById((R.id.imageView));
            datetime =itemView.findViewById(R.id.idDate);
            but.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    //listener.onFriendSelected(richiesteModelArrayList.get(ViewHolder.this.getAdapterPosition()));
                    userid=richiesteModelArrayList.get(ViewHolder.this.getAdapterPosition()).getUserid().toString();
                    dt=richiesteModelArrayList.get(ViewHolder.this.getAdapterPosition()).getDatatime().toString();
                    System.out.println("DATATIME =" + dt);
                    System.out.println("RICHIESTE position=" + userid);
                    apigw.ModifyRichiesta(userid, dt,"COLLEGAMENTI");

                    SimpleDateFormat df= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String descr="l'utente ha accettato la richiesta di collegamento all'utente: " + userid;
                    String time = df.format(new Date());
                    System.out.println ("sysdate="+ time);
                    FeedModel fm=new FeedModel(HomeActivity.uid, HomeActivity.u.getnickname(),time,descr,
                            "RICHIESTA");
                    //apigw.addFeed(fm);
                    FeedAPIGW fd=new FeedAPIGW();
                    fd.addFeed(fm);


                }

            });

        }
    }

    public interface FriendsAdapterListener {
        void onFriendSelected(RichiesteModel fm);
    }
}
