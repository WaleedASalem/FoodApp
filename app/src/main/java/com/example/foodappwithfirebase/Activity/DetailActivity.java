package com.example.foodappwithfirebase.Activity;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.foodappwithfirebase.Domain.Foods;
import com.example.foodappwithfirebase.Helper.ManagmentCart;
import com.example.foodappwithfirebase.R;
import com.example.foodappwithfirebase.databinding.ActivityDetailBinding;

import eightbitlab.com.blurview.RenderScriptBlur;

public class DetailActivity extends BaseActivity {

    private ActivityDetailBinding binding;
    private Foods object;
    private int num=1;
    private ManagmentCart managmentCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDetailBinding.inflate(getLayoutInflater());


        setContentView(binding.getRoot());
        managmentCart= new ManagmentCart(this);

        getBundleExtra();
        setVariable();
        setBlurEffect();

    }

    private void setBlurEffect() {
        float radius = 10f ;
        View decorView = (this).getWindow().getDecorView();
        ViewGroup rootView=(ViewGroup) decorView.findViewById(android.R.id.content);
        Drawable windowBackground = decorView.getBackground();

        binding.blurView.setupWith(rootView,new RenderScriptBlur(this))
                .setFrameClearDrawable(windowBackground)
                .setBlurRadius(radius);
        binding.blurView.setOutlineProvider(ViewOutlineProvider.BACKGROUND);
        binding.blurView.setClipToOutline(true);

        binding.blurView2.setupWith(rootView,new RenderScriptBlur(this))
                .setFrameClearDrawable(windowBackground)
                .setBlurRadius(radius);
        binding.blurView2.setOutlineProvider(ViewOutlineProvider.BACKGROUND);
        binding.blurView2.setClipToOutline(true);

    }


    private void setVariable() {
        binding.backBtn.setOnClickListener(v -> finish() );
        Glide.with(DetailActivity.this)
                .load(object.getImagePath())
                .into(binding.pic);
        binding.priceDetailTxt.setText("$"+object.getPrice());
        binding.titleDetailTxt.setText(object.getTitle());
        binding.discriptionTxt.setText(object.getDescription());
        binding.timeDetailTxt.setText(object.getTimeId());
        binding.ratingBar.setRating((float) object.getStar());
        binding.totalTxt.setText((num* object.getPrice())+"$");

        binding.plusItem.setOnClickListener(view -> {
            num=num+1;
            binding.numItemTxt.setText(num+"");
            binding.totalTxt.setText((num* object.getPrice())+"$");
        });

        binding.minusItem.setOnClickListener(view -> {

            if (num>0){
                num =num-1;
                binding.numItemTxt.setText(num+"");
                binding.totalTxt.setText((num* object.getPrice())+"$");
            }

        });

        binding.addBtn.setOnClickListener(view -> {
            object.setNumberInCart(num);
            managmentCart.insertFood(object);
        });

    }

    private void getBundleExtra() {
        object = (Foods) getIntent().getSerializableExtra("object");
    }
}