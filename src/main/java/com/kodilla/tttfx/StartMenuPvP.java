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

public class StartMenuPvP extends Application {

    @Override
    public void start(Stage PvPwindow) {
        Label playerLabel = new Label("Player vs Player");

        Button pvpClassic = new Button("Classic PvP (3x3)");
        pvpClassic.setOnAction(event -> {
            Classic ticTacToe = new Classic(false);
            ticTacToe.start(new Stage());
            PvPwindow.close();
        });

        Button pvpToFive = new Button("To Five (10x10)");
        pvpToFive.setOnAction(event -> {
            ToFive ticTacToe = new ToFive(false);
            ticTacToe.start(new Stage());
            PvPwindow.close();
        });

        VBox vboxMid = new VBox(10, playerLabel, pvpClassic, pvpToFive);
        vboxMid.setAlignment(Pos.CENTER);


        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(20);

        gridPane.addColumn(0, vboxMid);

        Scene scene = new Scene(gridPane, 300, 200);

        PvPwindow.setScene(scene);
        PvPwindow.setTitle("Tic Tac Toe - Start Window");
        PvPwindow.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
