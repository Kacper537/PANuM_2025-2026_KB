package com.example.konwerterwartociijednostek;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Spinner spinnerType;
    Spinner spinnerFrom;
    Spinner spinnerTo;

    EditText editValue;

    TextView textResult;

    Button buttonConvert;

    String[] conversionTypes = {
            "Systemy liczbowe",
            "Waluty",
            "Długość",
            "Pole powierzchni"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerType = findViewById(R.id.spinnerType);
        spinnerFrom = findViewById(R.id.spinnerFrom);
        spinnerTo = findViewById(R.id.spinnerTo);

        editValue = findViewById(R.id.editValue);

        textResult = findViewById(R.id.textResult);

        buttonConvert = findViewById(R.id.buttonConvert);

        ArrayAdapter<String> typeAdapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_item,
                        conversionTypes);

        typeAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        spinnerType.setAdapter(typeAdapter);

        updateSpinners();

        spinnerType.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> parent,
                                               View view,
                                               int position,
                                               long id) {

                        updateSpinners();
                        convert();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

        spinnerFrom.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> parent,
                                               View view,
                                               int position,
                                               long id) {

                        convert();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

        spinnerTo.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> parent,
                                               View view,
                                               int position,
                                               long id) {

                        convert();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

        editValue.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s,
                                          int start,
                                          int count,
                                          int after) {

            }

            @Override
            public void onTextChanged(CharSequence s,
                                      int start,
                                      int before,
                                      int count) {

                convert();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        buttonConvert.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                convert();
            }
        });
    }

    private void updateSpinners() {

        String selectedType =
                spinnerType.getSelectedItem().toString();

        String[] values;

        switch (selectedType) {

            case "Systemy liczbowe":

                values = new String[]{
                        "DEC",
                        "BIN",
                        "BASE4",
                        "OCT",
                        "HEX"
                };

                break;

            case "Waluty":

                values = new String[]{
                        "PLN",
                        "USD",
                        "EUR",
                        "GBP",
                        "CHF"
                };

                break;

            case "Długość":

                values = new String[]{
                        "mm",
                        "cm",
                        "in",
                        "ft",
                        "yd",
                        "m",
                        "km"
                };

                break;

            default:

                values = new String[]{
                        "mm²",
                        "cm²",
                        "m²",
                        "km²",
                        "ar",
                        "ha"
                };

                break;
        }

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_item,
                        values);

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        spinnerFrom.setAdapter(adapter);
        spinnerTo.setAdapter(adapter);
    }

    private void convert() {

        try {

            String value =
                    editValue.getText().toString().trim();

            if(value.isEmpty()) {

                textResult.setText("---");
                return;
            }

            String type =
                    spinnerType.getSelectedItem().toString();

            String from =
                    spinnerFrom.getSelectedItem().toString();

            String to =
                    spinnerTo.getSelectedItem().toString();

            if(type.equals("Systemy liczbowe")) {

                int fromBase = getBase(from);
                int toBase = getBase(to);

                if(!BaseConverter.isValid(
                        value,
                        fromBase)) {

                    textResult.setText(
                            "Błędna liczba");
                    return;
                }

                String result =
                        BaseConverter.convert(
                                value,
                                fromBase,
                                toBase);

                textResult.setText(result);

                return;
            }

            double number =
                    Double.parseDouble(
                            value.replace(",", "."));

            double result = 0;

            if(type.equals("Waluty")) {

                result =
                        CurrencyConverter.convert(
                                number,
                                from,
                                to);
            }

            else if(type.equals("Długość")) {

                result =
                        LengthConverter.convert(
                                number,
                                from,
                                to);
            }

            else if(type.equals(
                    "Pole powierzchni")) {

                result =
                        AreaConverter.convert(
                                number,
                                from,
                                to);
            }

            textResult.setText(
                    String.format(
                            Locale.getDefault(),
                            "%.3f",
                            result));

        }

        catch (Exception e) {

            textResult.setText(
                    "Błędne dane");
        }
    }

    private int getBase(String baseName) {

        switch (baseName) {

            case "BIN":
                return 2;

            case "BASE4":
                return 4;

            case "OCT":
                return 8;

            case "HEX":
                return 16;

            default:
                return 10;
        }
    }
}