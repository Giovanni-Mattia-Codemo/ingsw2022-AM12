package it.polimi.ingsw2022am12.client.GUI;

import it.polimi.ingsw2022am12.client.Client;
import it.polimi.ingsw2022am12.client.model.ClientSchoolBoard;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import java.util.ArrayList;

import static javafx.scene.paint.Color.rgb;

/**
 * Class that defines the layout of a scene used to represent a part of our table: it contains the SchoolBoards of our players
 */
public class SchoolBoardView extends ScrollPane{

    private final ArrayList<SchoolBoardContainer> schools;

    /**
     * Constructor method of SchoolBoardView
     *
     * @param client the client that must interact with the scene
     */
    public SchoolBoardView(Client client){
        super();
        schools= new ArrayList<>();
        double schoolRatio = 0.4337708831;
        GridPane box = new GridPane();

        this.setBackground(Background.fill(rgb(51,232,189)));
        ArrayList<ClientSchoolBoard>gameSchools= client.getClientGame().getSchoolBoards();

        //for each school board creates a school board container and the associated label and adds them to a GridPane component
        for(int i=0; i<gameSchools.size();i++){
            ClientSchoolBoard thisSchool = gameSchools.get(i);
            Label name = new Label();
            name.setAlignment(Pos.CENTER);
            name.setMinSize(1.0,1.0);
            name.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            name.setText(thisSchool.getNick());
            box.addRow(i*2, name);
            box.setAlignment(Pos.CENTER);

            SchoolBoardContainer schoolBoard = new SchoolBoardContainer(thisSchool.getNick(),client);
            schools.add(schoolBoard);

            box.addRow((i*2)+1, schoolBoard);

            schoolBoard.prefWidthProperty().bind(Bindings.min(widthProperty(),heightProperty().divide(schoolRatio)));
            schoolBoard.prefHeightProperty().bind(Bindings.min(widthProperty().multiply(schoolRatio),heightProperty()));

        }

        box.setAlignment(Pos.CENTER);
        setContent(box);

    }

    /**
     * refresh method resets all the graphical assets of this pane according to the current state of the game
     */
    public void refresh(){
        for (SchoolBoardContainer s:schools
             ) {
            s.refresh();

        }
    }

}
