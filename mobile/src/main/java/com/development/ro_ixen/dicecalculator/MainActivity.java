package com.development.ro_ixen.dicecalculator;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView.OnEditorActionListener;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity
        implements OnClickListener{

    //widget variables
    private Button calculatorButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculatorButton = (Button) findViewById(R.id.calculatorButton);
        calculatorButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent myIntent = new Intent(this, DiceCalculator.class);
        startActivity(myIntent);
    }
}
