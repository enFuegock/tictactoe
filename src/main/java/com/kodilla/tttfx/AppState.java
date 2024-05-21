package com.kodilla.tttfx;

public class AppState extends MainBrain {
    private static AppState instance;
    private String playerXname = "Player1";
    private String playerOname = "Player2";
    private int pointLimit;
    private AppState() { }

    public static AppState getInstance() {
        if (instance == null) {
            instance = new AppState();
        }
        return instance;
    }

    public String getPlayerXname() {
        return pXname;
    }

    public void setPlayerXname(String pXname) {
        this.pXname = pXname;
    }
    public String getPlayerOname() {
        return pOname;
    }

    public void setPlayerOname(String pOname) {
        this.pOname = pOname;
    }
    public int getPointLimit() {
        return pointLimit;
    }

    public void setPointLimit(int pointLimit) {
        this.pointLimit = pointLimit;
    }
}

