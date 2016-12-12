package com.development.ro_ixen.dicecalculator;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Random;

public class DiceCalculator extends AppCompatActivity
        implements OnClickListener, OnLongClickListener{

    //widget variables
    private Button oneButton, twoButton, threeButton, fourButton, fiveButton, sixButton, sevenButton, eightButton, nineButton, zeroButton;
    private Button plusButton, minusButton;
    private Button dButton;
    private Button goButton;
    private Button historyButton;
    private TextView calculationView;
    private TextView resultView;

    //instanced variables
    private String calculationString = "";
    private String resultString = "";
    private String[] calculationArray;

    //SharedPreferences Object
    private SharedPreferences savedValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice_calculator);

        //initialize interacting widgets
        oneButton = (Button) findViewById(R.id.oneButton);
        twoButton = (Button) findViewById(R.id.twoButton);
        threeButton = (Button) findViewById(R.id.threeButton);
        fourButton = (Button) findViewById(R.id.fourButton);
        fiveButton = (Button) findViewById(R.id.fiveButton);
        sixButton = (Button) findViewById(R.id.sixButton);
        sevenButton = (Button) findViewById(R.id.sevenButton);
        eightButton = (Button) findViewById(R.id.eightButton);
        nineButton = (Button) findViewById(R.id.nineButton);
        zeroButton = (Button) findViewById(R.id.zeroButton);
        plusButton = (Button) findViewById(R.id.plusButton);
        minusButton = (Button) findViewById(R.id.minusButton);
        dButton = (Button) findViewById(R.id.dButton);
        goButton = (Button) findViewById(R.id.goButton);
        historyButton = (Button) findViewById(R.id.historyButton);
        calculationView = (TextView) findViewById(R.id.calculationView);
        resultView = (TextView) findViewById(R.id.resultValue);

        //set this to button listeners
        oneButton.setOnClickListener(this);
        twoButton.setOnClickListener(this);
        threeButton.setOnClickListener(this);
        fourButton.setOnClickListener(this);
        fiveButton.setOnClickListener(this);
        sixButton.setOnClickListener(this);
        sevenButton.setOnClickListener(this);
        eightButton.setOnClickListener(this);
        nineButton.setOnClickListener(this);
        zeroButton.setOnClickListener(this);
        plusButton.setOnClickListener(this);
        minusButton.setOnClickListener(this);
        dButton.setOnClickListener(this);
        historyButton.setOnClickListener(this);
        calculationView.setOnClickListener(this);

        calculationView.setOnLongClickListener(this);

        //initialize saved values object
        savedValues = getSharedPreferences("SavedValues", MODE_PRIVATE);

    }

    @Override
    public void onPause() {
        Editor editor = savedValues.edit();
        editor.putString("calculationString", calculationString);
        editor.putString("resultString", resultString);
        editor.apply();

        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();

        calculationString = savedValues.getString("calculationString", "");
        resultString = savedValues.getString("resultString", "");

        calculationView.setText(calculationString);
        resultView.setText(resultString);
    }

    @Override
    public boolean onLongClick(View v) {
        if (v.getId() == R.id.calculationView) {
            clearCalculationView();
            return true;
        }
        return false;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.calculationView:
                backspaceCalculationView();
                break;
            case R.id.oneButton:
                addNumToCalc(1);
                break;
            case R.id.twoButton:
                addNumToCalc(2);
                break;
            case R.id.threeButton:
                addNumToCalc(3);
                break;
            case R.id.fourButton:
                addNumToCalc(4);
                break;
            case R.id.fiveButton:
                addNumToCalc(5);
                break;
            case R.id.sixButton:
                addNumToCalc(6);
                break;
            case R.id.sevenButton:
                addNumToCalc(7);
                break;
            case R.id.eightButton:
                addNumToCalc(8);
                break;
            case R.id.nineButton:
                addNumToCalc(9);
                break;
            case R.id.zeroButton:
                addNumToCalc(0);
                break;
            case R.id.dButton:
                addDToCalc();
                break;
            case R.id.plusButton:
                addPlusToCalc();
                break;
            case R.id.minusButton:
                addMinusToCalc();
                break;
            case R.id.goButton:
                goCalculate();
                break;

        }
    }

    public void clearCalculationView() {
        calculationString = "";
        calculationView.setText(calculationString);
    }

    public void backspaceCalculationView() {
        if(calculationString.length() == 0) return;
        int sub = 1;
        if(calculationString.charAt(calculationString.length()-1) == ' ') sub = 3;
        calculationString = calculationString.substring(0, calculationString.length()-sub);
        calculationView.setText(calculationString);
    }

    public void addNumToCalc (int num) {
        calculationString += num;
        calculationView.setText(calculationString);
    }

    public void addDToCalc () {
        String[] testArr = calculationString.split(" ");
        String test = testArr[testArr.length-1].trim();
        if (!test.contains("d")) {
            calculationString = calculationString + "d";
            calculationView.setText(calculationString);
        }
    }

    public void addPlusToCalc() {
        String[] testArr = calculationString.split(" ");
        String test = testArr[testArr.length-1].trim();
        if (test.contains("+") || test.contains("-")) {
            backspaceCalculationView();
            addPlusToCalc();
        } else {
            calculationString = calculationString + " + ";
            calculationView.setText(calculationString);
        }
    }

    public void addMinusToCalc() {
        String[] testArr = calculationString.split(" ");
        String test = testArr[testArr.length-1].trim();
        if (test.contains("+") || test.contains("-")) {
            backspaceCalculationView();
            addMinusToCalc();
        } else {
            calculationString = calculationString + " - ";
            calculationView.setText(calculationString);
        }
    }

    public void goCalculate() {
        calculationArray = calculationString.split(" ");
        if (calculationArray[calculationArray.length-1].equals("+") ||
                calculationArray[calculationArray.length-1].equals("-")) return;
        int result = 0;
        for (int i = 0; i < calculationArray.length; i++) {
            if (calculationArray[i].equals("+")) {
                result += diceRoll(calculationArray[++i]);
            } else if (calculationArray[i].equals("-")) {
                result -= diceRoll(calculationArray[++i]);
            } else {
                result += diceRoll(calculationArray[i]);
            }
        }
        resultString = "Result: " + result;
        System.out.println(result);
        System.out.println(resultString);
        resultView.setText(resultString);
        calculationView.setText(resultString);
        //calculationString = calculationView.getText().toString();
    }

    public int parseCalc() {
        int result;
        if (calculationArray[0].equals("+")) {
            result = diceRoll(calculationArray[1]);
            calculationArray = Arrays.copyOfRange(calculationArray, 2, calculationArray.length);
            return result;
        } else if (calculationArray[0].equals("-")) {
            result = -(diceRoll(calculationArray[1]));
            calculationArray = Arrays.copyOfRange(calculationArray, 2, calculationArray.length);
            return result;
        } else {
            result = diceRoll(calculationArray[0]);
            calculationArray = Arrays.copyOfRange(calculationArray, 1, calculationArray.length);
            return result;
        }

    }

    public int diceRoll (String dice) {
        if (dice.contains("d")) {
            String[] parse = dice.split("d");
            int numOfDice = Integer.parseInt(parse[0]);
            int typeOfDie = Integer.parseInt(parse[1]);
            int result = 0;
            for (int i = 0; i < numOfDice; i++) {
                result += dieRoll(typeOfDie);
            }
            return result;
        }
        return Integer.parseInt(dice);
    }

    public int dieRoll (int dieType) {
        return (int) (Math.random() * dieType) + 1;
    }

}
