package com.example.myapplicationcinemates1.ui.richieste;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicationcinemates1.ApiGateway;
import com.example.myapplicationcinemates1.MainActivity;
import com.example.myapplicationcinemates1.datamodel.FeedAPIGW;
import com.example.myapplicationcinemates1.HomeActivity;
import com.example.myapplicationcinemates1.R;
import com.example.myapplicationcinemates1.datamodel.FeedModel;
import com.example.myapplicationcinemates1.datamodel.RichiesteAPIGW;
import com.example.myapplicationcinemates1.datamodel.RichiesteModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class RicezioneAdapter extends RecyclerView.Adapter<RicezioneAdapter.ViewHolder> {

    // creating a variable for array list and context.
    private ArrayList<RichiesteModel> richiesteModelArrayLst;
    private Context context;
    private FriendsAdapterListener listener;
    private RichiestePresenter rp;
    // creating a constructor for our variables.
    public RicezioneAdapter(String Userid, Context context, FriendsAdapterListener listener) {
        Log.d(MainActivity.TAG,"Userid="+Userid);
        rp=new RichiestePresenter();
        rp.setListaRichieste(Userid);
        richiesteModelArrayLst=copylist(rp.richiesteModelArrayLst);
        this.context=context;
        this.listener=listener;
    }

    private ArrayList<RichiesteModel> copylist(ArrayList<RichiesteModel> a)  {
        ArrayList<RichiesteModel> b=new ArrayList<RichiesteModel>();
        for (int j=0;j<a.size();j++) {
            b.add(a.get(j));
        }
        return b;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // below line is to inflate our layout.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ricezione_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // setting data to our views of recycler view.
        RichiesteModel model = richiesteModelArrayLst.get(position);
        if (model.getnickname()==null) {
            String utente = "utente non presente";
            Log.d(MainActivity.TAG,"utente non presente");
        }
        else {
            String utente= model.getnickname();
        }

        holder.nome.setText(model.getnome() + " " + model.getcognome());
        holder.nickname.setText(model.getnickname());
        holder.datetime.setText(model.getDatatime());
        int id=context.getResources().getIdentifier(model.getImmagine(),"drawable", context.getPackageName());
        holder.imgview.setImageResource(id);
    }

    @Override
    public int getItemCount() {
        return richiesteModelArrayLst.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //ApiGateway apigw=new ApiGateway();
        // creating variables for our views.
        private TextView nickname, nome, datetime;
        private ImageView imgview;
        private ImageButton imbut;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imbut=itemView.findViewById(R.id.imageButton);
            nome = itemView.findViewById(R.id.idPerson);
            nickname = itemView.findViewById(R.id.Nickname);
            imgview = itemView.findViewById((R.id.imageView));
            datetime =itemView.findViewById(R.id.idDate);
            imbut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    //listener.onFriendSelected(richiesteModelArrayList.get(ViewHolder.this.getAdapterPosition()));
                    int selected=ViewHolder.this.getAbsoluteAdapterPosition();
                    rp.accettazioneRichieste(selected);
                    Toast.makeText(context, "Richiesta Accettata ", Toast.LENGTH_LONG).show();
                }

            });

        }
    }

    public interface FriendsAdapterListener {
        void onFriendSelected(RichiesteModel fm);
    }
}
