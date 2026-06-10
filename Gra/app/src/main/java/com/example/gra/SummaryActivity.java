package com.example.gra;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SummaryActivity extends AppCompatActivity {

    TextView tvSummary;
    Button btnAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        tvSummary = findViewById(R.id.tvSummary);
        btnAgain = findViewById(R.id.btnAgain);

        Match match =
                (Match) getIntent().getSerializableExtra("match");

        String winner;

        if(match.getPointsX() > match.getPointsO())
            winner = "Zwycięzca meczu: Gracz X";

        else if(match.getPointsO() > match.getPointsX())
            winner = "Zwycięzca meczu: Gracz O";

        else
            winner = "REMIS MECZU";

        String text =
                "Wygrane X: " + match.getWinsX() +
                        "\nWygrane O: " + match.getWinsO() +
                        "\nRemisy: " + match.getDraws() +
                        "\n\nPunkty X: " + match.getPointsX() +
                        "\nPunkty O: " + match.getPointsO() +
                        "\n\n" + winner;

        tvSummary.setText(text);

        btnAgain.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            SummaryActivity.this,
                            MainActivity.class);

            intent.addFlags(
                    Intent.FLAG_ACTIVITY_CLEAR_TOP);

            startActivity(intent);

            finish();
        });
    }
}