package com.example.foodsorderapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.*;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public Button order_btn;

    public RadioGroup radioGroupSize;
    public RadioGroup radioGroupCrust;
    public RadioGroup radioGroupBeverage;

    public RadioButton radioSizeButton;
    public RadioButton radioCrustButton;
    public RadioButton radioBeverageButton;

    public CheckBox cMushroom;
    public CheckBox cPineapple;
    public CheckBox cPepperoni;
    public CheckBox cCheese;
    public CheckBox cOnion;
    public CheckBox cBacon;
    public WebView webview;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        order_btn = (Button) findViewById(R.id.Order_btn);
        radioGroupSize = (RadioGroup) findViewById(R.id.radioGroup2);
        radioGroupCrust = (RadioGroup) findViewById(R.id.radioGroup3);
        radioGroupBeverage = (RadioGroup) findViewById(R.id.radioGroup4);


        cBacon = (CheckBox) findViewById(R.id.top_bacon);
        cPepperoni = (CheckBox) findViewById(R.id.top_pepperoni);
        cMushroom = (CheckBox) findViewById(R.id.top_musroom);
        cCheese = (CheckBox) findViewById(R.id.top_cheese);
        cOnion = (CheckBox) findViewById(R.id.top_onion);
        cPineapple = (CheckBox) findViewById(R.id.top_pineapple);




        order_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.SEND_SMS}, 0);
                } else {
                    try {
                        sendSms();
                        Toast.makeText(getApplicationContext(), "Your order is sent", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, orderWebView.class);
                        intent.putExtra("data", sendOrderViaSmS());
                        startActivity(intent);
                    } catch (Exception e) {

                    }


                }
            }
        });
    }
    public String sendOrderViaSmS() {

        ArrayList<String> pizza = new ArrayList<String>();

        //add size to pizza
        try {
            int idSizeGroup = radioGroupSize.getCheckedRadioButtonId();
            radioSizeButton = (RadioButton) findViewById(idSizeGroup);
            String size = (String) radioSizeButton.getText();
            pizza.add(size);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Please choose pizza size", Toast.LENGTH_SHORT).show();
        }

        //add crust to pizza
        try {

            int idCrustGroup = radioGroupCrust.getCheckedRadioButtonId();
            radioCrustButton = (RadioButton) findViewById(idCrustGroup);
            String crust = (String) radioCrustButton.getText();
            pizza.add(crust);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Please choose pizza crust", Toast.LENGTH_SHORT).show();
        }

        //add topping to pizza
        try {
            if (cBacon.isChecked()) {
                pizza.add("Bacon");
            }
            if (cMushroom.isChecked()) {
                pizza.add("Mushroom");
            }
            if (cCheese.isChecked()) {
                pizza.add("Cheese");
            }
            if (cPineapple.isChecked()) {
                pizza.add("Pineapple");
            }
            if (cPepperoni.isChecked()) {
                pizza.add("Pepperoni");
            }
            if (cOnion.isChecked()) {
                pizza.add("Onion");

            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Please choose pizza topping", Toast.LENGTH_SHORT).show();
        }
        //add beverage

        try {
            int id = radioGroupBeverage.getCheckedRadioButtonId();
            radioBeverageButton = (RadioButton) findViewById(id);
            String beverage = (String) radioBeverageButton.getText();
            pizza.add(beverage);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Please choose your free beverage", Toast.LENGTH_SHORT).show();
        }

        String size = "";
        String crust = "";
        String topping = "";
        String beverage = "";
        String order = "";


        size +=  pizza.get(0);
        crust += pizza.get(1);
        beverage += pizza.get(pizza.size() - 1);
        for (int i = 2; i < pizza.size() - 1; i++) {
            topping += pizza.get(i) + " ";
        }

        order += "Size: " + size + "\n" + "Crust: " + crust + "\n" +"Topping: " + topping + "\n" + "Beverage: " + beverage;
        return order;
    }
    public void sendSms() {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage("88888888", null, sendOrderViaSmS(), null, null);
    }
}