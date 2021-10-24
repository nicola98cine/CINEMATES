package com.example.myapplicationcinemates1.ui.home;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicationcinemates1.MainActivity;
import com.example.myapplicationcinemates1.R;
import com.example.myapplicationcinemates1.datamodel.FilmModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

//public class FilmAdapter {
//}

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.ViewHolder> {

    // creating a variable for array list and context.
    private ArrayList<FilmModel> filmModelArrayList;
    private Context context;
    private FilmAdapterListener listener;
    // creating a constructor for our variables.
    public FilmAdapter(ArrayList<FilmModel> filmModelArrayList, Context context, FilmAdapterListener listener) {
        this.filmModelArrayList = filmModelArrayList;
        this.context = context;
        this.listener=listener;
    }

    // method for filtering our recyclerview items.
    public void filterList(ArrayList<FilmModel> filterllist) {
        filmModelArrayList = filterllist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // below line is to inflate our layout.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.film_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // setting data to our views of recycler view.
        FilmModel model = filmModelArrayList.get(position);
        Log.d(MainActivity.TAG,"filmId="+Integer.toString(model.getId()));
        String trama="";
        //System.out.println("filmname="+model.getFilmName());
        if (model.getFilmDescription()==null || model.getFilmDescription().isEmpty()) {
            trama="trama non disponibile";
        }
        else {
            trama= model.getFilmDescription();
            if (trama.length()>150) {
                trama=trama.substring(0,150);
            }
        }

        holder.filmName.setText(model.getFilmName());
        holder.filmDesc.setText(trama);
        Picasso.with(context).load(String.valueOf(model.getIView())).into(holder.iView);

    }

    @Override
    public int getItemCount() {
        // returning the size of array list.
        return filmModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // creating variables for our views.
        private TextView filmName, filmDesc;
        private ImageView iView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            filmName = itemView.findViewById(R.id.idfilmName);
            filmDesc = itemView.findViewById(R.id.idFilmDescription);
            iView = itemView.findViewById((R.id.imageView));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    listener.onFilmSelected(filmModelArrayList.get(ViewHolder.this.getAdapterPosition()));
                }
            });

        }
    }

    public interface FilmAdapterListener {void onFilmSelected(
            FilmModel fm);
    }
}
