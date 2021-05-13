package com.example.locationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {


    public Button btnWebsite;
    public Button btnMap;
    public Button btnClear;

    public EditText edtLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMap = (Button) findViewById(R.id.btnMap);
        btnWebsite = (Button) findViewById(R.id.btnWeb);
        btnClear = (Button) findViewById(R.id.clearSearch);


        edtLocation = (EditText) findViewById(R.id.eLocation);

        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findOnGoogleMap();
            }
        });

        btnWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findOnWebsite();
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
            }
        });

    }



    public void findOnGoogleMap() {

        String txt = edtLocation.getText().toString();
        if(txt.length() > 0) {
            Uri uriGoogleMap = Uri.parse("geo:0,0?q=" + txt);
            Intent intentGoogleMap = new Intent(Intent.ACTION_VIEW, uriGoogleMap);
            intentGoogleMap.setPackage("com.google.android.apps.maps");
            startActivity(intentGoogleMap);
        } else {
            Toast.makeText(MainActivity.this, "Input field must not be empty", Toast.LENGTH_SHORT).show();
        }
    }


    public void findOnWebsite() {
            String txt = edtLocation.getText().toString();
            if(txt.length() > 0) {
                Intent intentGoogleSearch = new Intent(Intent.ACTION_WEB_SEARCH);
                intentGoogleSearch.putExtra(SearchManager.QUERY, txt);
                startActivity(intentGoogleSearch);
                Log.i("length", String.valueOf(txt.length()));
            } else {
                Toast.makeText(MainActivity.this, "Input field must not be empty", Toast.LENGTH_SHORT).show();
        }
    }
    public void clear() {
        edtLocation.setText(" ");
    }

}