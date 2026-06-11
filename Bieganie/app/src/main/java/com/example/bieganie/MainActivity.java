package com.example.bieganie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText tempo, predkosc, dystans, dystansCel, czasMin;
    TextView wynik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tempo = findViewById(R.id.tempo);
        predkosc = findViewById(R.id.predkosc);
        dystans = findViewById(R.id.dystans);
        dystansCel = findViewById(R.id.dystansCel);
        czasMin = findViewById(R.id.czasMin);
        wynik = findViewById(R.id.wynik);

        findViewById(R.id.b1).setOnClickListener(v -> zTempa());
        findViewById(R.id.b2).setOnClickListener(v -> zPredkosci());
        findViewById(R.id.b3).setOnClickListener(v -> wyznacz());

        if (savedInstanceState != null) {
            wynik.setText(savedInstanceState.getString("wynik"));
        }
    }

    void zTempa() {
        try {

            double t = Double.parseDouble(tempo.getText().toString());
            double d = Double.parseDouble(dystans.getText().toString());

            if (t <= 0) {
                wynik.setText("Tempo musi być większe od 0.");
                return;
            }

            if (d <= 0) {
                wynik.setText("Dystans musi być większy od 0.");
                return;
            }

            double p = 60.0 / t;

            wynik.setText(
                    "Prędkość: " + String.format("%.2f", p) + " km/h\n\n" +
                            "Maraton: " + czas(t * 42.195) + "\n" +
                            "Półmaraton: " + czas(t * 21.0975) + "\n" +
                            "Dystans: " + czas(t * d)
            );

        } catch (NumberFormatException e) {
            wynik.setText("Błąd: wpisz poprawne liczby.");
        }
    }

    void zPredkosci() {
        try {

            double p = Double.parseDouble(predkosc.getText().toString());
            double d = Double.parseDouble(dystans.getText().toString());

            if (p <= 0) {
                wynik.setText("Prędkość musi być większa od 0.");
                return;
            }

            if (d <= 0) {
                wynik.setText("Dystans musi być większy od 0.");
                return;
            }

            double t = 60.0 / p;

            wynik.setText(
                    "Tempo: " + String.format("%.2f", t) + " min/km\n\n" +
                            "Maraton: " + czas(t * 42.195) + "\n" +
                            "Półmaraton: " + czas(t * 21.0975) + "\n" +
                            "Dystans: " + czas(t * d)
            );

        } catch (NumberFormatException e) {
            wynik.setText("Błąd: wpisz poprawne liczby.");
        }
    }

    void wyznacz() {
        try {

            double d = Double.parseDouble(dystansCel.getText().toString());
            double c = Double.parseDouble(czasMin.getText().toString());

            if (d <= 0) {
                wynik.setText("Dystans musi być większy od 0.");
                return;
            }

            if (c <= 0) {
                wynik.setText("Czas musi być większy od 0.");
                return;
            }

            double t = c / d;
            double p = 60.0 / t;

            wynik.setText(
                    "Tempo: " + String.format("%.2f", t) + " min/km\n" +
                            "Prędkość: " + String.format("%.2f", p) + " km/h"
            );

        } catch (NumberFormatException e) {
            wynik.setText("Błąd: wpisz poprawne liczby.");
        }
    }

    String czas(double min) {

        int s = (int) (min * 60);

        int h = s / 3600;
        int m = (s % 3600) / 60;
        int sek = s % 60;

        return h + " h " + m + " min " + sek + " s";
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("wynik", wynik.getText().toString());
    }
}