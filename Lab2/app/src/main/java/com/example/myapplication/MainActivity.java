package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextView romanText;
    TextView arabicText;

    String input = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        romanText = findViewById(R.id.romanText);
        arabicText = findViewById(R.id.arabicText);


        int[] numberButtons = {
                R.id.btn0, R.id.btn1, R.id.btn2,
                R.id.btn3, R.id.btn4, R.id.btn5,
                R.id.btn6, R.id.btn7, R.id.btn8,
                R.id.btn9
        };

        for (int id : numberButtons) {

            Button btn = findViewById(id);

            btn.setOnClickListener(v -> {

                if (input.matches("[IVXLCDM]+")) {

                    Toast.makeText(
                            this,
                            "Nie mieszaj liczb rzymskich i arabskich",
                            Toast.LENGTH_SHORT
                    ).show();

                    return;
                }

                input += btn.getText().toString();

                updateViews();
            });
        }


        int[] romanButtons = {
                R.id.btnI,
                R.id.btnV,
                R.id.btnX,
                R.id.btnL,
                R.id.btnC,
                R.id.btnD,
                R.id.btnM
        };

        for (int id : romanButtons) {

            Button btn = findViewById(id);

            btn.setOnClickListener(v -> {

                if (input.matches("[0-9]+")) {

                    Toast.makeText(
                            this,
                            "Nie mieszaj liczb rzymskich i arabskich",
                            Toast.LENGTH_SHORT
                    ).show();

                    return;
                }

                input += btn.getText().toString();

                updateViews();
            });
        }


        Button btnB = findViewById(R.id.btnB);

        btnB.setOnClickListener(v -> {

            if (!input.isEmpty()) {

                input = input.substring(
                        0,
                        input.length() - 1
                );

                updateViews();
            }
        });


        Button btnClear = findViewById(R.id.btnCLR);

        btnClear.setOnClickListener(v -> {

            input = "";

            arabicText.setText("Arabic:");
            romanText.setText("Roman:");
        });
    }


    private void updateViews() {

        if (input.isEmpty()) {

            arabicText.setText("Arabic:");
            romanText.setText("Roman:");

            return;
        }


        if (input.matches("[0-9]+")) {

            try {

                int number = Integer.parseInt(input);

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

            } catch (Exception e) {

                Toast.makeText(
                        this,
                        "Błąd liczby",
                        Toast.LENGTH_SHORT
                ).show();
            }
        }


        else if (input.matches("[IVXLCDM]+")) {

            if (!isValidRoman(input)) {

                Toast.makeText(
                        this,
                        "Niepoprawna liczba rzymska",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            int arabic = romanToArabic(input);

            if (arabic < 1 || arabic > 3999) {

                Toast.makeText(
                        this,
                        "Zakres 1-3999",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            arabicText.setText("Arabic: " + arabic);
            romanText.setText("Roman: " + input);
        }
    }


    private boolean isValidRoman(String roman) {

        return roman.matches(
                "^M{0,3}(CM|CD|D?C{0,3})" +
                        "(XC|XL|L?X{0,3})" +
                        "(IX|IV|V?I{0,3})$"
        );
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


    private int romanToArabic(String roman) {

        HashMap<Character, Integer> map =
                new HashMap<>();

        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int result = 0;

        for (int i = 0; i < roman.length(); i++) {

            int current =
                    map.get(roman.charAt(i));

            if (i + 1 < roman.length()) {

                int next =
                        map.get(roman.charAt(i + 1));

                if (current < next) {

                    result -= current;

                } else {

                    result += current;
                }

            } else {

                result += current;
            }
        }

        return result;
    }
}