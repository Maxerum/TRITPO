package com.example.goodmorning;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class caloriesActivity extends AppCompatActivity {

    EditText editWeight;
    EditText editAge;
    EditText editHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calories);
    }

    public void countCalories(View view){
        double amountOfCalories = 88.36 + (13.4 * Integer.parseInt((editWeight.getText().toString())))+ 4.8 * Integer.parseInt(editHeight.getText().toString()) - 5.7 * Integer.parseInt(editAge.getText().toString());
        EditText edText = findViewById(R.id.editTextNumber4);
        edText.setText(Double.toString(amountOfCalories));

    }

    public void onClick(View view){
        //int number = R.id.editTextNumber; System.out.println(number)
        editWeight = findViewById(R.id.editWeight);
        editAge = findViewById(R.id.editAge);
        editHeight = findViewById(R.id.editHeight);
        countCalories(view);
//        Resources res = getResources();
//        String num = res.getString(R.id.editTextNumber);

        //edText.setText(Integer.toString(number));
        //R.id.editTextNumber4 = ;
        //formulas
    }
}