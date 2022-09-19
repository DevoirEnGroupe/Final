package ht.mesajem.mesajem.Adapters;

import android.content.Context;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;


import ht.mesajem.mesajem.Models.InfoPost;
import ht.mesajem.mesajem.Models.Post;
import ht.mesajem.mesajem.R;

public class ReceiverInfoAdapter extends RecyclerView.Adapter<ReceiverInfoAdapter.ViewHolder>{


    List<InfoPost> infoposts;
    Context context;



    public ReceiverInfoAdapter(List<InfoPost> infoposts, Context context) {
        this.infoposts = infoposts;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View postView = LayoutInflater.from(context).inflate(R.layout.itemreceived,parent,false);

        return new ViewHolder(postView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        InfoPost infopost = infoposts.get(position);
        holder.bind(infopost);
    }

    @Override
    public int getItemCount() {
        return infoposts.size();
    }
    public void clear() {
        infoposts.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<InfoPost> Infoposts) {
        infoposts.addAll(Infoposts);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView userexped;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);



            userexped = itemView.findViewById(R.id.userexped);
        }

        public void bind(InfoPost infopost) {
            try {
                userexped.setText(infopost.getNom());

            }catch (Exception e){
                Log.e("objectid","nul objectid");
            }


        }

    }
}
