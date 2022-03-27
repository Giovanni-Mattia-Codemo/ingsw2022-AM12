module it.polimi.ingsw2022am12 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens it.polimi.ingsw2022am12.JavaFX to javafx.fxml;
    exports it.polimi.ingsw2022am12.JavaFX;

}