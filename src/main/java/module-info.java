module it.polimi.ingsw2022am12 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    opens it.polimi.ingsw2022am12.client.GUI;

    exports it.polimi.ingsw2022am12;
    exports it.polimi.ingsw2022am12.server.model.actions;
    exports it.polimi.ingsw2022am12.server.controller;
    exports it.polimi.ingsw2022am12.server.virtualview;
    exports it.polimi.ingsw2022am12.client.CLI;
    exports it.polimi.ingsw2022am12.client.model;
    exports it.polimi.ingsw2022am12.client;
    exports it.polimi.ingsw2022am12.server.model;
    exports it.polimi.ingsw2022am12.updateFlag;

}