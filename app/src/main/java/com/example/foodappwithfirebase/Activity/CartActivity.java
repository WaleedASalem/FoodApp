package com.example.foodappwithfirebase.Activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.foodappwithfirebase.Adapter.CartAdapter;
import com.example.foodappwithfirebase.Helper.ManagmentCart;
import com.example.foodappwithfirebase.R;
import com.example.foodappwithfirebase.databinding.ActivityCartBinding;

import eightbitlab.com.blurview.RenderScriptBlur;

public class CartActivity extends BaseActivity {
    private ActivityCartBinding binding;
    private RecyclerView.Adapter adapter;
    private ManagmentCart managmentCart;
    private double tax;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_cart);
        managmentCart = new ManagmentCart(this);
        setVariable();
        calculateCart();
        initList();
        setBlurEffect();
    }

    private void setBlurEffect() {
        float radius = 10f ;
        View decorView = (this).getWindow().getDecorView();
        ViewGroup rootView=(ViewGroup) decorView.findViewById(android.R.id.content);
        Drawable windowBackground = decorView.getBackground();

        binding.cartBlurView.setupWith(rootView,new RenderScriptBlur(this))
                .setFrameClearDrawable(windowBackground)
                .setBlurRadius(radius);
        binding.cartBlurView.setOutlineProvider(ViewOutlineProvider.BACKGROUND);
        binding.cartBlurView.setClipToOutline(true);

        binding.totalBlurView.setupWith(rootView,new RenderScriptBlur(this))
                .setFrameClearDrawable(windowBackground)
                .setBlurRadius(radius);
        binding.totalBlurView.setOutlineProvider(ViewOutlineProvider.BACKGROUND);
        binding.totalBlurView.setClipToOutline(true);

    }

    private void initList() {
    if (managmentCart.getListCart().isEmpty()){
        binding.emptyTxt.setVisibility(View.VISIBLE);
        binding.scrollView.setVisibility(View.GONE);
    }else {

        binding.emptyTxt.setVisibility(View.GONE);
        binding.scrollView.setVisibility(View.VISIBLE);
    }

    binding.cartRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
    adapter = new CartAdapter(managmentCart.getListCart(),CartActivity.this, () -> calculateCart());
    binding.cartRecyclerView.setAdapter(adapter);
    }

    private void calculateCart() {
        double percentTax=0.02;
        double delivery=10;
        tax = Math.round(managmentCart.getTotalFee()*percentTax*100.0)/100.0;

        double total = Math.round((managmentCart.getTotalFee()+tax+delivery)*100.0)/100.0;
        double itemTotal = Math.round(managmentCart.getTotalFee()*100.0)/100.0;

        binding.subtotalTxr.setText("$"+itemTotal);
        binding.totalTaxCartTxt.setText("$"+tax);
        binding.deliveryTxt.setText("$"+delivery);
        binding.totalCartTxt.setText("$"+total);

    }

    private void setVariable() {

        binding.backCart.setOnClickListener(view -> finish());
    }
}