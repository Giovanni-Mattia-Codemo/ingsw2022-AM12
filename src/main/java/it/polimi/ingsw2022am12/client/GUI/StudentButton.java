package it.polimi.ingsw2022am12.client.GUI;

import it.polimi.ingsw2022am12.DiskColor;
import it.polimi.ingsw2022am12.client.Client;
import it.polimi.ingsw2022am12.client.ClientInputHandler;
import it.polimi.ingsw2022am12.client.model.ClientStudent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import java.util.HashMap;
import java.util.Objects;

/**
 * StudentButton class represents the visualization of a student as a button, with a graphic attached
 */
public class StudentButton extends Button{

    private DiskColor color;
    private Client client;
    private ClientStudent myStudent;

    /**
     * Constructor method of StudentButton class
     * @param  student the color of my student
     */
    public StudentButton(ClientStudent student, Client client){

        super();
        myStudent = student;
        this.client = client;
        setMinWidth(1.0);
        setMinHeight(1.0);
        setMaxHeight(Double.MAX_VALUE);
        setMaxWidth(Double.MAX_VALUE);
        setBackground(Background.EMPTY);
        setStudentColor(myStudent.getColor());
        }

     /**
     * Getter method for color
     * @return DiskColor color
     */
    public DiskColor getColor() {
        return myStudent.getColor();
    }

    /**
     *
     * @param color DiskColor chosen
     */
    private void setStudentColor(DiskColor color){
        this.color = color;
        String resource = null;
        if(color!=null){
        Image resource = images.get(color);
        if(myView==null){

            myView = new ImageView(resource);
            setGraphic(myView);
            myView.fitHeightProperty().bind(heightProperty());
            myView.fitWidthProperty().bind(widthProperty());

        }else{

            myView.setImage(resource);
        }
            setVisible(true);
        }
        else{

            setVisible(false);
        }
        setOnAction(e-> ClientInputHandler.handle("Student "+myStudent.getColor()+" "+myStudent.getID(),client));

    }

    public void refresh(){
        setStudentColor(myStudent.getColor());
        setDisabled(color == null);
    }

}
