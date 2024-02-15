package com.college.converter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.college.converter.databinding.ActivityMainBinding;

/*
    TODOs:
    In groups of 4, complete the following tasks, 1 for each team member:
    1. Extract all the strings into the strings.xml file and use them in the layout and the activity
    2. Change the theme of the app to a NoActionBar theme and modify the primary colors
    3. Add Log messages at the entry/exit of onCreate() and convertCurrency methods. Level should be Info
    4. Add ViewBinding to the project

    ** Each task must be done in a separate branch and merged to the main branch
    after completion using a Pull Request.
    ** Each task must be done by a different team member.

*/
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding variablebinding;
    String TAG = "Part2";

    private ActivityMainBinding binding;

    static private final Float CONVERSION_RATE = 0.80F;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate method started");
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.convertButton.setOnClickListener(view -> {
            String result = "Conversion Result";
            binding.resultId.setText(result);
        });

        binding.yourTextView.setText("Hello, ViewBinding!");
        
        setContentView(R.layout.activity_main);
        variablebinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(variablebinding.getRoot());

        Button buttonConvert = variablebinding.convertButton;

        buttonConvert.setOnClickListener( view ->  {
            convertCurrency(view);
        } );
        Log.i(TAG, "onCreate method finished"); // Exit log message






    }

    public void convertCurrency(View view) {
        Log.i(TAG, "convertCurrency method started"); // Entry log message

        EditText inputView = variablebinding.entryId;

        String inputAmount = inputView.getText().toString();

        TextView resultView = variablebinding.resultId;

        if (!inputAmount.isEmpty()) {
            Float inputAmountDecimal = Float.valueOf(inputAmount);

            Float resultFloat = inputAmountDecimal * CONVERSION_RATE;

            //resultView.setText( resultFloat + " Euros" );
            String resultString = getString(R.string.result_euros, resultFloat);

            resultView.setText(resultString);

        }
        Log.i(TAG, "convertCurrency method ended"); // Entry log message
    }



}