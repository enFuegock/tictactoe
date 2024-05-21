package com.kodilla.tttfx;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StartMenuPvE extends Application {

    @Override
    public void start(Stage PvEwindow) {
        Label playerLabel = new Label("Player vs Computer");

        Button pveClassic = new Button("Classic PvP (3x3)");
        pveClassic.setOnAction(event -> {
            Classic ticTacToe = new Classic(true);
            ticTacToe.start(new Stage());
            PvEwindow.close(); // Zamknięcie okna startowego po uruchomieniu gry
        });

        Button pveToFive = new Button("To Five (10x10)");
        pveToFive.setOnAction(event -> {
            ToFive ticTacToe = new ToFive(true);
            ticTacToe.start(new Stage());
            PvEwindow.close(); // Zamknięcie okna startowego po uruchomieniu gry
        });

        VBox vboxMid = new VBox(10, playerLabel, pveClassic, pveToFive);
        vboxMid.setAlignment(Pos.CENTER);


        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(20);

        gridPane.addColumn(0, vboxMid);

        Scene scene = new Scene(gridPane, 300, 200);

        PvEwindow.setScene(scene);
        PvEwindow.setTitle("Tic Tac Toe - PvE");
        PvEwindow.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
