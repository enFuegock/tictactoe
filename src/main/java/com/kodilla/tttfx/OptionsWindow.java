package com.kodilla.tttfx;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class OptionsWindow extends MainBrain {
    private StartWindow startWindow;
    public OptionsWindow(StartWindow startWindow){
        this.startWindow = startWindow;
    }

    @Override
    public void start(Stage optionsStage) {

        Label playerXnameLabel = new Label("Enter Player X name:");
        TextField playerXnameInput = new TextField("");
        Label playerOnameLabel = new Label("Enter Player O name:");
        TextField playerOnameInput = new TextField("");
        Label pointLimitLabel = new Label("Point limit:");
        TextField pointLimitInput = new TextField("3");
        Label pcDifficulty = new Label("Difficulty:");
        RadioButton pcEasy = new RadioButton("Easy");
        RadioButton pcMedium = new RadioButton("Medium");
        RadioButton pcHard = new RadioButton("Hard");
        ToggleGroup difficulties = new ToggleGroup();
        pcEasy.setToggleGroup(difficulties);
        pcMedium.setToggleGroup(difficulties);
        pcHard.setToggleGroup(difficulties);
        Button optionsAccept = new Button("Accept");
        Button optionsCancel = new Button("Cancel");

        optionsAccept.setOnAction(e -> {
            String pXname = playerXnameInput.getText();
            AppState.getInstance().setPlayerXname(pXname);
            String pOname = playerOnameInput.getText();
            AppState.getInstance().setPlayerOname(pOname);
            int pointLimit = Integer.parseInt(pointLimitInput.getText());
            AppState.getInstance().setPointLimit(pointLimit);
            optionsStage.close();
            startWindow.showStartWindow();
        });

        optionsCancel.setOnAction(e -> {
            optionsStage.close();
            startWindow.showStartWindow();
        });

        VBox optionsMenu = new VBox(10, playerXnameLabel, playerXnameInput, playerOnameLabel, playerOnameInput, pointLimitLabel, pointLimitInput, pcDifficulty,
                pcEasy, pcMedium, pcHard, optionsAccept, optionsCancel);
        optionsMenu.setAlignment(Pos.CENTER);

        Scene scene = new Scene(optionsMenu, 300, 600);

        optionsStage.setScene(scene);
        optionsStage.setTitle("Tic Tac Toe - options");
        optionsStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
