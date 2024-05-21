package com.kodilla.tttfx;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class MainBrain extends Application {
    protected char currentPlayer = 'X';
    protected boolean gameOver;
    protected Button[][] buttons;
    protected int playerXpoints;
    protected int playerOpoints;
    protected int pointLimit;
    protected boolean vsComputer;

    protected Label pXscore;
    protected Label pOscore;

    protected String pXname = "Unknown";
    protected String pOname = "Noname";


    protected GameState loadedGameState;
    protected void setLoadedGameState(GameState loadedGameState) {
        this.loadedGameState = loadedGameState;
    }


    protected void saveGame() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Zapisz grę");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Pliki zapisu gry", "*.sav"));
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
                char[][] board = new char[buttons.length][buttons[0].length];
                for (int i = 0; i < buttons.length; i++) {
                    for (int j = 0; j < buttons[i].length; j++) {
                        board[i][j] = buttons[i][j].getText().isEmpty() ? ' ' : buttons[i][j].getText().charAt(0);
                    }
                }
                GameState gameState = new GameState(board, currentPlayer, playerXpoints, playerOpoints,
                        AppState.getInstance().getPlayerXname(), AppState.getInstance().getPlayerOname(),
                        pointLimit, vsComputer, buttons.length);
                out.writeObject(gameState);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    protected void showRoundWinnerDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Round Over");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.setOnHidden(event -> {
            resetBoard();
        });

        alert.showAndWait();
    }

    protected void showGameWinnerDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.setOnHidden(event -> {
            updateHighScore(currentPlayer == 'X' ? AppState.getInstance().getPlayerXname() : AppState.getInstance().getPlayerOname());
            Stage stage = (Stage) buttons[0][0].getScene().getWindow();
            stage.close();
            StartWindow startWindow = new StartWindow();
            startWindow.start(new Stage());
        });

        alert.showAndWait();
    }

    protected void updateHighScore(String winnerName) {
        List<HighScore> highScores = loadHighScores();
        boolean playerFound = false;

        for (HighScore highScore : highScores) {
            String playerName = highScore.getPlayerName();
            if (playerName != null && playerName.equals(winnerName)) {
                highScore.setGamesPlayed(highScore.getGamesPlayed() + 1);
                highScore.setGamesWon(highScore.getGamesWon() + 1);
                playerFound = true;
                break;
            }
        }

        if (!playerFound) {
            highScores.add(new HighScore(winnerName, 1, 1, LocalDate.now()));
        }

        saveHighScores(highScores);
    }

    protected List<HighScore> loadHighScores() {
        List<HighScore> highScores = new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("highscores.sav"))) {
            highScores = (List<HighScore>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // jeśli plik nie istnieje lub jest pusty, zwróć pustą listę
        }
        return highScores;
    }

    protected void saveHighScores(List<HighScore> highScores) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("highscores.sav"))) {
            out.writeObject(highScores);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void computerMove(int boardSize) {
        Random random = new Random();
        int row, col;
        do {
            row = random.nextInt(boardSize);
            col = random.nextInt(boardSize);
        } while (!buttons[row][col].getText().isEmpty() && !gameOver);

        if (!gameOver) {
            buttons[row][col].setText(String.valueOf(currentPlayer));
            if (checkWin(boardSize)) {
                addScore();
                if (!gameOver) {
                    showRoundWinnerDialog("Player " + currentPlayer + " won round!");
                }
            } else {
                switchPlayer();
            }
        }
    }

    protected boolean checkWin(int boardSize) {
        if (boardSize == 3) {
            return checkWin3x3();
        } else if (boardSize == 10) {
            return checkWin10x10();
        }
        return false;
    }


    /*
    protected void computerMove() {
        Random random = new Random();
        int row, col;
        do {
            row = random.nextInt(buttons.length);
            col = random.nextInt(buttons.length);
        } while (!buttons[row][col].getText().isEmpty() && !gameOver);

        if (!gameOver) {
            buttons[row][col].setText(String.valueOf(currentPlayer));
            if (checkWin3x3()) {
                addScore();
                if (!gameOver) {
                    showRoundWinnerDialog("Player " + currentPlayer + " won round!");
                }
            } else {
                switchPlayer();
            }
        }
    }

     */

    protected boolean isDraw() {
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons.length; j++) {
                if (buttons[i][j].getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    protected void resetBoard() {
        for (Button[] row : buttons) {
            for (Button button : row) {
                button.setText("");
            }
        }
        gameOver = false;
    }
    protected boolean checkWin3x3() {
        for (int i = 0; i < 3; i++) {
            if (checkWinLine3x3(i, 0, 0, 1) || checkWinLine3x3(0, i, 1, 0)) {
                return true;
            }
        }
        if (checkWinLine3x3(0, 0, 1, 1) || checkWinLine3x3(0, 2, 1, -1)) {
            return true;
        }
        return false;
    }

    protected boolean checkWinLine3x3(int row, int col, int dRow, int dCol) {
        String target = buttons[row][col].getText();
        for (int i = 0; i < 3; i++) {
            if (!buttons[row][col].getText().equals(target) || target.isBlank()) {
                return false;
            }
            row += dRow;
            col += dCol;
        }
        return true;
    }

    protected void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }


    protected void addScore() {
        if (currentPlayer == 'X') {
            playerXpoints++;
            pXscore.setText(String.valueOf(playerXpoints));
        } else {
            playerOpoints++;
            pOscore.setText(String.valueOf(playerOpoints));
        }
        if (playerXpoints >= pointLimit || playerOpoints >= pointLimit) {
            gameOver = true;
            showGameWinnerDialog("Player " + currentPlayer + " has won the game!");
        }
    }

    protected void createBoard(GridPane gridPane, int boardSize) {
        buttons = new Button[boardSize][boardSize];

        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                Button button = new Button("");
                buttons[row][col] = button;
                if (boardSize == 3) {
                    button.setPrefSize(100, 100);
                    button.setStyle("-fx-font-size:30");
                } else if (boardSize == 10) {
                    button.setPrefSize(50, 50);
                    button.setStyle("-fx-font-size:20");
                }
                button.setOnAction(event -> {
                    if (button.getText().isEmpty() && !gameOver) {
                        button.setText(String.valueOf(currentPlayer));
                        if (checkWin(boardSize)) {
                            addScore();
                            if (!gameOver) {
                                showRoundWinnerDialog("Player " + currentPlayer + " won round!");
                            }
                        } else if (isDraw()) {
                            gameOver = true;
                            showRoundWinnerDialog("DRAW!");
                        } else {
                            switchPlayer();
                            if (vsComputer && !gameOver) {
                                computerMove(boardSize);
                            }
                        }
                    }
                });
                gridPane.add(button, col, row);
            }
        }
    }

/*
    protected void createBoard(GridPane gridPane) {
        buttons = new Button[3][3];

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Button button = new Button("");
                buttons[row][col] = button;
                button.setPrefSize(100, 100);
                button.setStyle("-fx-font-size:30");
                button.setOnAction(event -> {
                    if (button.getText().isEmpty() && !gameOver) {
                        button.setText(String.valueOf(currentPlayer));
                        if (checkWin3x3()) {
                            addScore();
                            if (!gameOver) {
                                showRoundWinnerDialog("Player " + currentPlayer + " won round!");
                            }
                        } else if (isDraw()) {
                            gameOver = true;
                            showRoundWinnerDialog("DRAW!");
                        } else {
                            switchPlayer();
                            if (vsComputer && !gameOver) {
                                computerMove();
                            }
                        }
                    }
                });
                gridPane.add(button, col, row);
            }
        }
    }

    protected void createBoard10x10(GridPane gridPane) {
        buttons = new Button[10][10];

        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                Button button = new Button("");
                buttons[row][col] = button;
                button.setPrefSize(50, 50);
                button.setStyle("-fx-font-size:20");
                button.setOnAction(event -> {
                    if (button.getText().isEmpty() && !gameOver) {
                        button.setText(String.valueOf(currentPlayer));
                        if (checkWin10x10()) {
                            addScore();
                            if (!gameOver) {
                                showRoundWinnerDialog("Player " + currentPlayer + " won round!");
                            }
                        } else if (isDraw()) {
                            gameOver = true;
                            showRoundWinnerDialog("DRAW!");
                        } else {
                            switchPlayer();
                            if (vsComputer && !gameOver) {
                                computerMove10x10();
                            }
                        }
                    }
                });
                gridPane.add(button, col, row);
            }
        }
    }




 */
    protected void computerMove10x10() {

        Random random = new Random();
        int row, col;
        do {
            row = random.nextInt(buttons.length);
            col = random.nextInt(buttons.length);
        } while (!buttons[row][col].getText().isEmpty() && !gameOver);

        if (!gameOver) {
            buttons[row][col].setText(String.valueOf(currentPlayer));
            if (checkWin10x10()) {
                gameOver = true;
                System.out.println("Player " + currentPlayer + " wins!");
            } else {
                switchPlayer();
            }
        }
    }




    protected boolean checkWin10x10() {
        for (int i = 0; i <= 5; i++) {
            for (int j = 0; j <= 5; j++) {
                if (checkWinLine10x10(i, j, 0, 1)) {
                    return true;
                }
            }
        }
        for (int i = 0; i <= 5; i++) {
            for (int j = 0; j <= 5; j++) {
                if (checkWinLine10x10(j, i, 1, 0)) {
                    return true;
                }
            }
        }
        for (int i = 0; i <= 5; i++) {
            for (int j = 0; j <= 5; j++) {
                if (checkWinLine10x10(i, j, 1, 1) || checkWinLine10x10(i, 9 - j, 1, -1)) {
                    return true;
                }
            }
        }
        return false;
    }
    protected boolean checkWinLine10x10(int row, int col, int dRow, int dCol) {
        String target = buttons[row][col].getText();
        for (int i = 0; i < 5; i++) {
            if (!buttons[row][col].getText().equals(target) || buttons[row][col].getText().isBlank()) {
                return false;
            }
            row += dRow;
            col += dCol;
        }
        return true;
    }

    @Override
    public void start(Stage primaryStage) {
    }
}
