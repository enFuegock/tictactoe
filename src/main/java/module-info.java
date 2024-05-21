module com.kodilla.tttfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.kodilla.tttfx to javafx.fxml;
    exports com.kodilla.tttfx;
}