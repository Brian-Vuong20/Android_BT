package com.example.nationapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.PictureDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmadrosid.svgloader.SvgLoader;
//import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {
    public ArrayList<Country> countryList;
    public Activity activity;
    public OnItemClickListener mListener;


    public CountryAdapter(ArrayList<Country> countryList, Activity activity, OnItemClickListener onItemClickListener) {
        this.countryList = countryList;
        this.activity = activity;
        this.mListener = onItemClickListener;
    }

    @NonNull
    @Override


    public CountryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_info, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Country country = countryList.get(position);
        String countryName = country.getCountryName();
        double area = country.getArea();
        long population = country.getPopulation();
        holder.mCountry.setText(countryName);

        SvgLoader.pluck().with(activity).setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher).load(country.getFlag(), holder.mImage);

    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mCountry;

        public ImageView mImage;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mCountry = itemView.findViewById(R.id.countryTextView);
            mImage = itemView.findViewById(R.id.country_image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClick(getAdapterPosition());
                }
            });
        }
    }
}
