module it.polimi.ingsw2022am12 {
    requires javafx.controls;
    requires javafx.fxml;



    opens it.polimi.ingsw2022am12.JavaFX to javafx.fxml;
    exports it.polimi.ingsw2022am12.JavaFX;
    exports it.polimi.ingsw2022am12.server.adapter;

}