package com.kodilla.tttfx;

import java.io.Serializable;

public class GameState implements Serializable {
    private char[][] board;
    private char currentPlayer;
    private int playerXpoints;
    private int playerOpoints;
    private String playerXname;
    private String playerOname;
    private int pointLimit;
    private boolean vsComputer;
    private int boardSize;

    public GameState(char[][] board, char currentPlayer, int playerXpoints, int playerOpoints, String playerXname, String playerOname, int pointLimit, boolean vsComputer, int boardSize) {
        this.board = board;
        this.currentPlayer = currentPlayer;
        this.playerXpoints = playerXpoints;
        this.playerOpoints = playerOpoints;
        this.playerXname = playerXname;
        this.playerOname = playerOname;
        this.pointLimit = pointLimit;
        this.vsComputer = vsComputer;
        this.boardSize = boardSize;
    }

    public char[][] getBoard() {
        return board;
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    public int getPlayerXpoints() {
        return playerXpoints;
    }

    public int getPlayerOpoints() {
        return playerOpoints;
    }

    public String getPlayerXname() {
        return playerXname;
    }

    public String getPlayerOname() {
        return playerOname;
    }

    public int getPointLimit() {
        return pointLimit;
    }

    public boolean isVsComputer() {
        return vsComputer;
    }

    public int getBoardSize() {
        return boardSize;
    }
}
