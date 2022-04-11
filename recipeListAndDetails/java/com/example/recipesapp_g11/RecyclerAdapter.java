package com.example.recipesapp_g11;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder>{
    Context context;
    ArrayList<String> recipeImages;
    ArrayList<String> recipeTitles;
    ArrayList<String> recipeIngredients;
    ArrayList<String> recipeInstructions;
    ArrayList<String> recipeDietary;
    ArrayList<Integer> recipeServings;
    ArrayList<Integer> recipePreparation;
     RecyclerAdapter(Context context, ArrayList<String> recipeImages, ArrayList<String> recipeTitles, ArrayList<String> recipeDietary, ArrayList<Integer> recipeServings, ArrayList<Integer> recipePreparation,ArrayList<String> recipeIngredients,ArrayList<String> recipeInstructions ){
        this.context=context;
        this.recipeIngredients = recipeIngredients;

         this.recipeInstructions = recipeInstructions;

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
        holder.ingredients.setText(recipeIngredients.get(position));
        holder.recipeServing.setText(String.valueOf(recipeServings.get(position))+" Servings");
        holder.recipeTime.setText(String.valueOf(recipePreparation.get(position))+" Minutes");
        holder.recipeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valuessaver.ingredients = recipeIngredients.get(position);
                valuessaver.instructionlist = recipeInstructions.get(position);
              //  valuessaver.ingarray  = MainActivity.ingradient.get(position);
                valuessaver.sevinglist=  recipeServings.get(position).toString();
        //        valuessaver.instructionlist = valuessaver.instruction[position];
       //         valuessaver.nutritionlist =valuessaver.nutrition[position];
           //     Log.d("recyclersize", String.valueOf(valuessaver.ingarray.length()));
                Intent intent = new Intent(context, Detailproduct.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                context.startActivity(intent);
            }
        });
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
        TextView ingredients;
        TextView recipeServing;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeCard=itemView.findViewById(R.id.recipeCard);
            recipeImage=itemView.findViewById(R.id.recipeImage);
            recipeTitle=itemView.findViewById(R.id.recipeTitle);
            recipeDiet=itemView.findViewById(R.id.dietary);
            recipeTime=itemView.findViewById(R.id.time);
            ingredients=itemView.findViewById(R.id.ingredients);
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