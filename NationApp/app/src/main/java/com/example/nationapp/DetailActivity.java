package com.example.nationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahmadrosid.svgloader.SvgLoader;

import static com.example.nationapp.MainActivity.*;

public class DetailActivity extends AppCompatActivity {

    Activity activity;
    public ImageView imgView;
    public TextView country_Name;
    public TextView country_Area;
    public TextView country_Population;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        country_Name = findViewById(R.id.contryName);
        country_Area = findViewById(R.id.countryArea);
        country_Population = findViewById(R.id.countryPopulation);
        imgView = findViewById(R.id.imageView3);
        String name =  intent.getStringExtra("name");
        long population = intent.getLongExtra("population", 0);
        double area = intent.getDoubleExtra("area", 0);
        String flag = intent.getStringExtra("flag");
        Log.d("flag", flag);
        country_Area.setText(String.valueOf(area));
        country_Population.setText(String.valueOf(population));
        country_Name.setText(name);

        SvgLoader.pluck().with(this).setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher).load(flag, imgView);
    }
}