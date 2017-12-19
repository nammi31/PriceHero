package com.lubnasweety.pricehero.completed;


import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lubnasweety.pricehero.Booking;
import com.lubnasweety.pricehero.R;
import com.lubnasweety.pricehero.backEnd.Shop;

import java.util.ArrayList;

/**
 * Created by Asus on 12/19/2017.
 */

class ShopHolder extends RecyclerView.ViewHolder {
    TextView productPrice;
    TextView storeName;
    TextView storeLocation;
    TextView productAvailable;
    TextView productOffer;
    ImageView productImage;
    Button bookNow;
    View view;


    public ShopHolder(View itemView) {
        super(itemView);
        productPrice = itemView.findViewById(R.id.price_each);
        storeName = itemView.findViewById(R.id.store_name_each);
        storeLocation = itemView.findViewById(R.id.store_location_each);
        productAvailable = itemView.findViewById(R.id.available_each);
        productOffer = itemView.findViewById(R.id.offer_each);
        bookNow = itemView.findViewById(R.id.book_now);
        productImage = itemView.findViewById(R.id.productImage);
        view = itemView;
    }
}

public class RecyclerShopListAdapter extends RecyclerView.Adapter<ShopHolder> {
    ArrayList<Shop> shopArrayList;
    Activity activity;
    LayoutInflater inflater;

    public RecyclerShopListAdapter(ArrayList<Shop> shopArrayList, Activity activity) {
        this.shopArrayList = shopArrayList;
        this.activity = activity;
        inflater = activity.getLayoutInflater();
    }

    @Override
    public ShopHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.details_each_shop, null);
        ShopHolder holder = new ShopHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ShopHolder holder, int position) {
        Shop shop = shopArrayList.get(position);

        holder.productPrice.setText("Price: " + shop.getProductPrice().toString());
        holder.storeName.setText(shop.getStoreName());
        holder.storeLocation.setText( shop.getStoreLocation());
        holder.productAvailable.setText("Available: " + shop.getProductQuantity());
        holder.productOffer.setText("Offer: " + shop.getProductOffers());
        Glide.with(activity).load(shop.getImageUrl()).into(holder.productImage);

        holder.bookNow.setOnClickListener(e->{
            Intent goToBooking = new Intent(activity, Booking.class);
            activity.startActivity(goToBooking);
        });

    }

    @Override
    public int getItemCount() {

        return shopArrayList.size();
    }
}