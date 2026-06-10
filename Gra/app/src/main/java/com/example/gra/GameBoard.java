package com.example.gra;

public class GameBoard {

    private char[][] board;

    public GameBoard() {

        board = new char[3][3];

        resetBoard();
    }

    public void resetBoard() {

        for(int i=0;i<3;i++) {

            for(int j=0;j<3;j++) {

                board[i][j] = ' ';
            }
        }
    }

    public boolean makeMove(int row,int col,char player) {

        if(board[row][col] == ' ') {

            board[row][col] = player;
            return true;
        }

        return false;
    }

    public char getCell(int row,int col) {

        return board[row][col];
    }

    public boolean isBoardFull() {

        for(int i=0;i<3;i++) {

            for(int j=0;j<3;j++) {

                if(board[i][j] == ' ') {

                    return false;
                }
            }
        }

        return true;
    }

    public char checkWinner() {

        for(int i=0;i<3;i++) {

            if(board[i][0] != ' ' &&
                    board[i][0] == board[i][1] &&
                    board[i][1] == board[i][2]) {

                return board[i][0];
            }
        }

        for(int i=0;i<3;i++) {

            if(board[0][i] != ' ' &&
                    board[0][i] == board[1][i] &&
                    board[1][i] == board[2][i]) {

                return board[0][i];
            }
        }

        if(board[0][0] != ' ' &&
                board[0][0] == board[1][1] &&
                board[1][1] == board[2][2]) {

            return board[0][0];
        }

        if(board[0][2] != ' ' &&
                board[0][2] == board[1][1] &&
                board[1][1] == board[2][0]) {

            return board[0][2];
        }

        return ' ';
    }

    public void setCell(int row,int col,char value){

        board[row][col] = value;
    }
}