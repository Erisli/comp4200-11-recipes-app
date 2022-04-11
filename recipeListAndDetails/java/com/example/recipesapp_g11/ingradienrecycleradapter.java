package com.example.recipesapp_g11;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ingradienrecycleradapter extends RecyclerView.Adapter<ingradienrecycleradapter.ItemViewHolder>{
    Context context;
    ArrayList<String> namelist;
    ArrayList<String> vollist;
    ingradienrecycleradapter(Context context, ArrayList<String> namelist, ArrayList<String> vollist){
        this.context=context;
        this.namelist=namelist;
        this.vollist=vollist;

    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.ingradientitem_layout, parent, false);

        return new ItemViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
            holder.NAME.setText(namelist.get(position));
            holder.VOLUME.setText(vollist.get(position));
    }

    @Override
    public int getItemCount() {
        Log.d("adaptersize", String.valueOf(namelist.size()));
        return namelist.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{

          TextView NAME,VOLUME;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            NAME = itemView.findViewById(R.id.ingnametxt_id);
            VOLUME = itemView.findViewById(R.id.ingvoltxt_id);

        }
    }

}
