package com.example.gra;

import java.io.Serializable;

public class Match implements Serializable {

    private int totalGames;
    private int playedGames;

    private int winsX;
    private int winsO;
    private int draws;

    private double pointsX;
    private double pointsO;

    public Match(int totalGames) {
        this.totalGames = totalGames;
    }

    public void addWinX() {
        winsX++;
        playedGames++;
        pointsX += 1;
    }

    public void addWinO() {
        winsO++;
        playedGames++;
        pointsO += 1;
    }

    public void addDraw() {
        draws++;
        playedGames++;

        pointsX += 0.5;
        pointsO += 0.5;
    }

    public int getTotalGames() {
        return totalGames;
    }

    public int getPlayedGames() {
        return playedGames;
    }

    public int getRemainingGames() {
        return totalGames - playedGames;
    }

    public int getWinsX() {
        return winsX;
    }

    public int getWinsO() {
        return winsO;
    }

    public int getDraws() {
        return draws;
    }

    public double getPointsX() {
        return pointsX;
    }

    public double getPointsO() {
        return pointsO;
    }

    public boolean isFinished() {
        return playedGames >= totalGames;
    }
}