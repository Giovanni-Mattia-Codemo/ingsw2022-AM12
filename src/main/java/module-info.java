module it.polimi.ingsw2022am12 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;

    exports it.polimi.ingsw2022am12.server.model;
    exports it.polimi.ingsw2022am12.server.controller;
    exports it.polimi.ingsw2022am12.server.model.actions;
    exports it.polimi.ingsw2022am12.server;
    exports it.polimi.ingsw2022am12.server.virtualview;

}