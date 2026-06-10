package com.example.gra;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Spinner spinnerGames;
    Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerGames = findViewById(R.id.spinnerGames);
        btnStart = findViewById(R.id.btnStart);

        Integer[] values = {5,10,20};

        ArrayAdapter<Integer> adapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_item,
                        values);

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        spinnerGames.setAdapter(adapter);

        btnStart.setOnClickListener(v -> {

            int games =
                    (Integer) spinnerGames.getSelectedItem();

            Intent intent =
                    new Intent(
                            MainActivity.this,
                            GameActivity.class);

            intent.putExtra("games",games);

            startActivity(intent);
        });
    }
}