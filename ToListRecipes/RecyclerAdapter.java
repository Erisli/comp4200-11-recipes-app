package com.example.recipes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder>{
    Context context;
    ArrayList<String> recipeImages;
    ArrayList<String> recipeTitles;
    ArrayList<String> recipeDietary;
    ArrayList<Integer> recipeServings;
    ArrayList<Integer> recipePreparation;

    RecyclerAdapter(Context context, ArrayList<String> recipeImages, ArrayList<String> recipeTitles, ArrayList<String> recipeDietary, ArrayList<Integer> recipeServings, ArrayList<Integer> recipePreparation ){
        this.context=context;
        this.recipeImages=recipeImages;
        this.recipeTitles=recipeTitles;
        this.recipeDietary=recipeDietary;
        this.recipeServings=recipeServings;
        this.recipePreparation=recipePreparation;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recipecard_layout, parent, false);

        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.recipeImage.setScaleType(ImageView.ScaleType.FIT_XY);
        new URLImageToBitmap(holder.recipeImage).execute(recipeImages.get((position)));
        holder.recipeTitle.setText(recipeTitles.get(position));
        holder.recipeDiet.setText(recipeDietary.get(position));
        holder.recipeServing.setText(String.valueOf(recipeServings.get(position))+" Servings");
        holder.recipeTime.setText(String.valueOf(recipePreparation.get(position))+" Minutes");
    }

    @Override
    public int getItemCount() {
        return recipeImages.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        CardView recipeCard;
        ImageView recipeImage;
        TextView recipeTitle;
        TextView recipeDiet;
        TextView recipeTime;
        TextView recipeServing;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeCard=itemView.findViewById(R.id.recipeCard);
            recipeImage=itemView.findViewById(R.id.recipeImage);
            recipeTitle=itemView.findViewById(R.id.recipeTitle);
            recipeDiet=itemView.findViewById(R.id.dietary);
            recipeTime=itemView.findViewById(R.id.time);
            recipeServing=itemView.findViewById(R.id.servings);
        }
    }

    public class URLImageToBitmap extends AsyncTask<String, Void, Bitmap> {
        ImageView recipeImageView;

        URLImageToBitmap(ImageView recipeImageView){
            this.recipeImageView=recipeImageView;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap bitmap=null;
            try {
                InputStream url = new URL(strings[0]).openStream();
                bitmap = BitmapFactory.decodeStream(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        protected void onPostExecute(Bitmap image){
            recipeImageView.setImageBitmap(image);
        }

    }
}