package com.example.nationapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {

    public CountryAdapter adapter;

    public RecyclerView mRecyclerview;

    public ArrayList<Country> countryList;

    public static final String EXTRA_URL = "flag";
    public static final String EXTRA_COUNTRY_NAME = "countryName";
    public static final String EXTRA_COUNTRY_POPULATION = "countryPopulation";
    public static final String EXTRA_COUNTRY_AREA = "countryArea";
    public RequestQueue queue;
    String url = "https://restcountries.eu/rest/v2/all";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerview = findViewById(R.id.recyclerView);

        countryList = new ArrayList<>();

        getData();
        getRecyclerview();

    }
    public void getData() {
        queue = Volley.newRequestQueue(MainActivity.this);

        JsonArrayRequest array = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i = 0; i < response.length(); i++) {

                    try {
                        JSONObject object = response.getJSONObject(i);
                        String countryName = object.getString("name");
                        long population = object.getLong("population");
                        double area = object.getDouble("area");
                        String flag = object.getString("flag");


                        countryList.add(new Country(countryName, population, area, flag));


                        getRecyclerview();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(array);
    }
    public void getRecyclerview(){

        adapter = new CountryAdapter(countryList, MainActivity.this, this);
        LinearLayoutManager manager = new LinearLayoutManager(this);

        mRecyclerview.setHasFixedSize(true);

        mRecyclerview.setLayoutManager(manager);


        mRecyclerview.setAdapter(adapter);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, DetailActivity.class);
        Country country = countryList.get(position);

        intent.putExtra("name", country.getCountryName());
        intent.putExtra("population", country.getPopulation());
        intent.putExtra("area", country.getArea());
        intent.putExtra("flag", country.getFlag());
        startActivity(intent);
    }
}
