package com.example.tempConverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodorder.R;

public class MainActivity extends AppCompatActivity {
    public Button CButton;
    public Button FButton;
    public Button clearButton;
    public EditText mCelcius;
    public EditText mFahrenheit;

    tempConverter temp = new tempConverter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CButton = (Button) findViewById(R.id.C_Button);
        FButton = (Button) findViewById(R.id.F_Button);
        clearButton = (Button) findViewById(R.id.clear_Button);

        mCelcius = (EditText) findViewById(R.id.degC);
        mFahrenheit = (EditText) findViewById(R.id.degF);


        CButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConvertC();
            }
        });
        FButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConvertF();
            }
        });
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearField();
            }
        });

    }
    public void isEmpty() {
        Context c = getApplicationContext();
        CharSequence ch = "Please enter the field";

        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(c, ch, duration);
        toast.show();
    }
    public void ConvertC() {
        try {
            temp.setC(Float.parseFloat(mCelcius.getText().toString()));
            float convertC = 0;
            convertC = (float) (temp.getC() * 1.8 + 32);
            mFahrenheit.setText(String.valueOf(convertC));
        } catch (Exception e) {
            isEmpty();
        }
    }
    public void ConvertF() {
        try {
            temp.setF(Float.parseFloat(mFahrenheit.getText().toString()));
            float convertF = 0;
            convertF = (float)((temp.getF() - 32) * 0.5556);
            mCelcius.setText(String.valueOf(convertF));
        }catch (Exception e) {
            isEmpty();
        }
    }
    public void clearField() {
        mFahrenheit.setText("");
        mCelcius.setText("");

    }

}