package com.example.gra;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {

    private TextView tvCurrentPlayer, tvStats;
    private Button[] buttons = new Button[9];
    private Button btnNewMatch;

    private GameBoard board;
    private Match match;

    private char currentPlayer = 'X';
    private int totalGames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        tvCurrentPlayer = findViewById(R.id.tvCurrentPlayer);
        tvStats = findViewById(R.id.tvStats);
        btnNewMatch = findViewById(R.id.btnNewMatch);

        GridLayout grid = findViewById(R.id.gridBoard);
        for (int i = 0; i < 9; i++) {
            buttons[i] = (Button) grid.getChildAt(i);
            final int index = i;
            buttons[i].setOnClickListener(v -> handleMove(index));
        }


        totalGames = getIntent().getIntExtra("games", 5);

        match = new Match(totalGames);
        board = new GameBoard();

        updateUI();

        btnNewMatch.setOnClickListener(v -> {
            Intent intent = new Intent(GameActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
    private void handleMove(int index) {

        int row = index / 3;
        int col = index % 3;

        if(board.makeMove(row, col, currentPlayer)) {

            buttons[index].setText(String.valueOf(currentPlayer));

            if(currentPlayer == 'X') {
                buttons[index].setTextColor(getResources().getColor(R.color.playerX));
            } else {
                buttons[index].setTextColor(getResources().getColor(R.color.playerO));
            }

            char winner = board.checkWinner();

            if(winner != ' ') {
                if(winner == 'X') match.addWinX();
                else match.addWinO();

                Toast.makeText(this, "Wygrywa " + winner, Toast.LENGTH_SHORT).show();
                nextGame();
                return;
            }

            if(board.isBoardFull()) {
                match.addDraw();
                Toast.makeText(this,"Remis!",Toast.LENGTH_SHORT).show();
                nextGame();
                return;
            }

            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            updateUI();
        }
    }

    private void nextGame() {

        if(match.isFinished()) {
            Intent intent = new Intent(GameActivity.this, SummaryActivity.class);
            intent.putExtra("match", match);
            startActivity(intent);
            finish();
            return;
        }

        board.resetBoard();
        currentPlayer = 'X';

        for(Button b : buttons) b.setText("");

        updateUI();
    }

    private void updateUI() {

        tvCurrentPlayer.setText("Gracz: " + currentPlayer);

        if(currentPlayer == 'X') {
            tvCurrentPlayer.setTextColor(getResources().getColor(R.color.playerX));
        } else {
            tvCurrentPlayer.setTextColor(getResources().getColor(R.color.playerO));
        }

        tvStats.setText(
                "Rozegrane: " + match.getPlayedGames() +
                        "\nPozostało: " + match.getRemainingGames() +
                        "\nX: " + match.getPointsX() + " pkt" +
                        "\nO: " + match.getPointsO() + " pkt"
        );
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putChar("currentPlayer", currentPlayer);
        outState.putSerializable("match", match);

        char[] flatBoard = new char[9];
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                flatBoard[i*3+j] = board.getCell(i,j);

        outState.putCharArray("board", flatBoard);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        currentPlayer = savedInstanceState.getChar("currentPlayer");
        match = (Match) savedInstanceState.getSerializable("match");

        char[] flatBoard = savedInstanceState.getCharArray("board");
        board = new GameBoard();
        for(int i=0;i<9;i++)
            board.setCell(i/3,i%3,flatBoard[i]);

        for(int i=0;i<9;i++)
            buttons[i].setText((flatBoard[i]==' ')?"":String.valueOf(flatBoard[i]));

        updateUI();
    }
}