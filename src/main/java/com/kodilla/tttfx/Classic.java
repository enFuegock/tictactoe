package com.kodilla.tttfx;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Classic extends MainBrain {
    public Classic(boolean vsComputer) {
        this.vsComputer = vsComputer;
    }

    @Override
    public void start(Stage primaryStage) {
        if (loadedGameState != null) {
            AppState.getInstance().setPlayerXname(loadedGameState.getPlayerXname());
            AppState.getInstance().setPlayerOname(loadedGameState.getPlayerOname());
            pointLimit = loadedGameState.getPointLimit();
            playerXpoints = loadedGameState.getPlayerXpoints();
            playerOpoints = loadedGameState.getPlayerOpoints();
            currentPlayer = loadedGameState.getCurrentPlayer();
            vsComputer = loadedGameState.isVsComputer();
        } else {
            pointLimit = AppState.getInstance().getPointLimit();
        }


        Label playerXLabel = new Label("Player X");
        Label playerXnameLabel = new Label(AppState.getInstance().getPlayerXname());
        Label playerXpointsLabel = new Label("POINTS:");
        pXscore = new Label(String.valueOf(playerXpoints));
        VBox vboxLeft = new VBox();
        vboxLeft.setPrefWidth(100);
        vboxLeft.setAlignment(Pos.CENTER);
        vboxLeft.getChildren().addAll(playerXLabel, playerXnameLabel, playerXpointsLabel, pXscore);

        Label playerOLabel = new Label("Player O");
        Label playerOnameLabel = new Label(AppState.getInstance().getPlayerOname());
        Label playerOpointsLabel = new Label("POINTS:");
        pOscore = new Label(String.valueOf(playerOpoints));
        VBox vboxRight = new VBox();
        vboxRight.setPrefWidth(100);
        vboxRight.setAlignment(Pos.CENTER);
        vboxRight.getChildren().addAll(playerOLabel, playerOnameLabel, playerOpointsLabel, pOscore);

        GridPane gridPane = new GridPane();
        createBoard(gridPane, 3);

        if (loadedGameState != null) {
            char[][] board = loadedGameState.getBoard();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] != ' ') {
                        buttons[i][j].setText(String.valueOf(board[i][j]));
                    }
                }
            }
        }

        Button backButton = new Button("Back to Menu");
        backButton.setOnAction(e -> {
            Stage stage = (Stage) buttons[0][0].getScene().getWindow();
            stage.close();
            StartWindow startWindow = new StartWindow();
            startWindow.start(new Stage());
        });

        Button saveButton = new Button("Save Game");
        saveButton.setOnAction(e -> saveGame());

        VBox mainBox = new VBox();
        HBox boxBottom = new HBox();
        boxBottom.setAlignment(Pos.CENTER);
        boxBottom.setPrefHeight(100);
        boxBottom.getChildren().addAll(backButton, saveButton);

        HBox boxTop = new HBox();
        boxTop.setPrefHeight(300);
        boxTop.getChildren().addAll(vboxLeft, gridPane, vboxRight);

        mainBox.getChildren().addAll(boxTop, boxBottom);
        Scene scene = new Scene(mainBox, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Tic Tac Toe - Classic");
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
