package com.kodilla.tttfx;
import java.io.Serializable;
import java.time.LocalDate;

public class HighScore implements Serializable {
    private static final long serialVersionUID = 1L;
    private String playerName;
    private int gamesPlayed;
    private int gamesWon;
    private LocalDate date;

    public HighScore(String playerName, int gamesPlayed, int gamesWon, LocalDate date) {
        this.playerName = playerName;
        this.gamesPlayed = gamesPlayed;
        this.gamesWon = gamesWon;
        this.date = date;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    @Override
    public String toString() {
        return playerName + "|" + gamesPlayed + "|" + gamesWon + "|" + date;
    }
}
