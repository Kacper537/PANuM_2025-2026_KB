package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.LinkedHashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextView romanText;
    TextView arabicText;

    String currentNumber = "3999";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        romanText = findViewById(R.id.romanText);
        arabicText = findViewById(R.id.arabicText);

        int[] buttons = {
                R.id.btn0, R.id.btn1, R.id.btn2,
                R.id.btn3, R.id.btn4, R.id.btn5,
                R.id.btn6, R.id.btn7, R.id.btn8,
                R.id.btn9
        };

        for (int id : buttons) {

            Button btn = findViewById(id);

            btn.setOnClickListener(v -> {

                currentNumber += btn.getText().toString();

                updateViews();
            });
        }

        Button btnB = findViewById(R.id.btnB);

        btnB.setOnClickListener(v -> {

            if (!currentNumber.isEmpty()) {

                currentNumber = currentNumber.substring(
                        0,
                        currentNumber.length() - 1
                );

                updateViews();
            }
        });

        Button btnC = findViewById(R.id.btnC);

        btnC.setOnClickListener(v -> {

            currentNumber = "";

            arabicText.setText("Arabic:");
            romanText.setText("Roman:");
        });
    updateViews();
    }

    private void updateViews() {

        if (currentNumber.isEmpty()) {

            arabicText.setText("Arabic:");
            romanText.setText("Roman:");

            return;
        }

        int number = Integer.parseInt(currentNumber);

        if (number < 1 || number > 3999) {

            Toast.makeText(
                    this,
                    "Zakres 1-3999",
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }

        String roman = arabicToRoman(number);

        arabicText.setText("Arabic: " + number);
        romanText.setText("Roman: " + roman);
    }

    private String arabicToRoman(int number) {

        LinkedHashMap<Integer, String> map =
                new LinkedHashMap<>();

        map.put(1000, "M");
        map.put(900, "CM");
        map.put(500, "D");
        map.put(400, "CD");
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");

        StringBuilder result = new StringBuilder();

        for (Map.Entry<Integer, String> item : map.entrySet()) {

            while (number >= item.getKey()) {

                result.append(item.getValue());

                number -= item.getKey();
            }
        }

        return result.toString();
    }
}