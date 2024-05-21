package com.kodilla.tttfx;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;
import java.util.List;

public class StartWindow extends MainBrain {
    private Stage startWindow;

    @Override
    public void start(Stage startWindow) {
        this.startWindow = startWindow;

        Button newGame = new Button("New game");
        CheckBox playWithPC = new CheckBox("play with computer?");
        Button loadGame = new Button("Load game");
        Button optionsButton = new Button("Options");
        Button highScores = new Button("High Scores");
        Button exit = new Button("Exit");

        newGame.setOnAction(event -> {
            boolean playWithComputer = playWithPC.isSelected();
            if (playWithComputer) {
                StartMenuPvE menu = new StartMenuPvE();
                menu.start(new Stage());
            } else {
                StartMenuPvP menu = new StartMenuPvP();
                menu.start(new Stage());
            }
            startWindow.hide();
        });

        optionsButton.setOnAction(event -> {
            OptionsWindow optionsWindow = new OptionsWindow(this);
            optionsWindow.start(new Stage());
            startWindow.hide();
        });

        loadGame.setOnAction(e -> {
            loadGame();
        });

        highScores.setOnAction(event -> {
            showHighScores();
        });

        VBox vboxMid = new VBox(10, newGame, playWithPC, loadGame, optionsButton, highScores, exit);
        vboxMid.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vboxMid, 300, 400);

        startWindow.setScene(scene);
        startWindow.setTitle("Tic Tac Toe - Start Window");
        startWindow.show();
    }

    protected void loadGame() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Wczytaj grę");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Pliki zapisu gry", "*.sav"));
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                GameState gameState = (GameState) in.readObject();
                setLoadedGameState(gameState);

                // Przywróć stan gry
                currentPlayer = gameState.getCurrentPlayer();
                playerXpoints = gameState.getPlayerXpoints();
                playerOpoints = gameState.getPlayerOpoints();
                pointLimit = gameState.getPointLimit();
                vsComputer = gameState.isVsComputer();

                // Odtwórz planszę
                int boardSize = gameState.getBoardSize();
                buttons = new Button[boardSize][boardSize];
                GridPane gridPane = new GridPane();
                createBoard(gridPane, boardSize);
                char[][] board = gameState.getBoard();
                for (int i = 0; i < boardSize; i++) {
                    for (int j = 0; j < boardSize; j++) {
                        buttons[i][j].setText(board[i][j] == ' ' ? "" : String.valueOf(board[i][j]));
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


    private void showHighScores() {
        List<HighScore> highScores = loadHighScores();

        Stage highScoresStage = new Stage();
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        StringBuilder sb = new StringBuilder();

        for (HighScore highScore : highScores) {
            sb.append(highScore.toString()).append("\n");
        }
        textArea.setText(sb.toString());
        vbox.getChildren().add(textArea);
        Scene scene = new Scene(vbox, 400, 300);
        highScoresStage.setScene(scene);
        highScoresStage.setTitle("High Scores");
        highScoresStage.show();
    }

    public void showStartWindow() {
        startWindow.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}