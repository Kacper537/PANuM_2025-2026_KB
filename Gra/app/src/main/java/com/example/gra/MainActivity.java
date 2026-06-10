package com.example.gra;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etGames;
    private Button btnStart;
    private TextView tvGameTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvGameTitle = findViewById(R.id.tvGameTitle);
        etGames = findViewById(R.id.etGames);
        btnStart = findViewById(R.id.btnStart);

        btnStart.setOnClickListener(v -> {
            String input = etGames.getText().toString().trim();

            if (input.isEmpty()) {
                etGames.setError("Podaj liczbę gier");
                return;
            }

            int games;
            try {
                games = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                etGames.setError("Niepoprawna liczba");
                return;
            }

            if (games <= 0) {
                etGames.setError("Liczba gier musi być większa od 0");
                return;
            }

            Intent intent = new Intent(MainActivity.this, GameActivity.class);
            intent.putExtra("games", games);
            startActivity(intent);
        });
    }
}