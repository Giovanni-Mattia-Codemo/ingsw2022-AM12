module it.polimi.ingsw2022am12 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens it.polimi.ingsw2022am12.JavaFX to javafx.fxml;
    exports it.polimi.ingsw2022am12.JavaFX;
    exports it.polimi.ingsw2022am12.exceptions;
    exports it.polimi.ingsw2022am12.server.model;
    exports it.polimi.ingsw2022am12.server.controller;
    exports it.polimi.ingsw2022am12.server.model.actions;

}