package it.polimi.ingsw2022am12.client.GUI;

import it.polimi.ingsw2022am12.DiskColor;
import it.polimi.ingsw2022am12.client.Client;
import it.polimi.ingsw2022am12.client.ClientInputHandler;
import it.polimi.ingsw2022am12.client.model.ClientStudent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;

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
            switch (color) {
                case RED -> resource = Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/student_red.png")).toString();
                case BLUE -> resource = Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/student_blue.png")).toString();
                case PINK -> resource = Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/student_pink.png")).toString();
                case YELLOW -> resource = Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/student_yellow.png")).toString();
                case GREEN -> resource = Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/student_green.png")).toString();
                default -> {
                }
            }
        }

        if(resource!=null){
            Image image = new Image(resource);

            ImageView img = new ImageView(image);
            setGraphic(img);
            img.fitHeightProperty().bind(heightProperty());
            img.fitWidthProperty().bind(widthProperty());
            setVisible(true);

        }else{
            setVisible(false);
        }
        setOnAction(e-> ClientInputHandler.handle("Student "+myStudent.getColor()+" "+myStudent.getID(),client));

    }

    public void refresh(){
        setStudentColor(myStudent.getColor());
        if (color == null){
            setDisabled(true);
        }else{
            setDisabled(false);
        }
    }

}
