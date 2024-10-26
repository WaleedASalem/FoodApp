package com.example.foodappwithfirebase.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.foodappwithfirebase.Activity.DetailActivity;
import com.example.foodappwithfirebase.Domain.Foods;
import com.example.foodappwithfirebase.R;

import java.util.ArrayList;

import eightbitlab.com.blurview.BlurView;
import eightbitlab.com.blurview.RenderScriptBlur;

public class ListFoodAdapter extends RecyclerView.Adapter<ListFoodAdapter.viewhoder> {

    ArrayList<Foods> items;
    Context context;

    public ListFoodAdapter(ArrayList<Foods> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ListFoodAdapter.viewhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_list_food, parent, false);

        return new viewhoder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ListFoodAdapter.viewhoder holder, int position) {

        holder.titleTex.setText(items.get(position).getTitle());
        holder.priceTxt.setText("$" + items.get(position).getPrice());
        holder.timeTxt.setText(items.get(position).getTimeValue() + "min");
        holder.starTxt.setText("" + items.get(position).getStar());

        float radius = 10f;
        View decorView = ((Activity) holder.itemView.getContext()).getWindow().getDecorView();
        ViewGroup rootView = (ViewGroup) decorView.findViewById(android.R.id.content);
        Drawable windowBackground = decorView.getBackground();
        holder.blurView.setupWith(rootView, new RenderScriptBlur(holder.itemView.getContext()))
                .setFrameClearDrawable(windowBackground)
                .setBlurRadius(radius);
        holder.blurView.setOutlineProvider(ViewOutlineProvider.BACKGROUND);
        holder.blurView.setClipToOutline(true);
        Glide.with(context)
                .load(items.get(position).getImagePath())
                .transform(new CenterCrop(), new RoundedCorners(30))
                .into(holder.pic);

        Glide.with(context)
                .load(items.get(position).getImagePath())
                .transform(new CenterCrop(), new RoundedCorners(30))
                .into(holder.pic);

        holder.itemView.setOnClickListener(view -> {
            Intent intent= new Intent(context, DetailActivity.class);
            intent.putExtra("object",items.get(position));
            context.startActivity(intent);

        });
    }

    @Override
    public int getItemCount() {

        return 0;
    }

    public class viewhoder extends RecyclerView.ViewHolder {

        TextView titleTex, priceTxt, starTxt, timeTxt;
        ImageView pic;
        BlurView blurView;

        public viewhoder(View inflate) {
            super(inflate);
            titleTex = inflate.findViewById(R.id.titleViewListFood);
            priceTxt = inflate.findViewById(R.id.priceViewListFood);
            starTxt = inflate.findViewById(R.id.starViewListFoodTxt);
            pic = inflate.findViewById(R.id.picViewListFood);
            blurView = inflate.findViewById(R.id.blurViewListFood);

        }
    }
}
