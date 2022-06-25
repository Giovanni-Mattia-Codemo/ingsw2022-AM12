package it.polimi.ingsw2022am12.client.GUI;

import it.polimi.ingsw2022am12.DiskColor;
import it.polimi.ingsw2022am12.client.Client;
import it.polimi.ingsw2022am12.client.ClientInputHandler;
import it.polimi.ingsw2022am12.client.model.ClientStudent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import java.util.HashMap;
import java.util.Objects;

/**
 * StudentButton class represents the visualization of a student as a button, with a graphic attached
 */
public class StudentButton extends Button{

    private DiskColor color;
    private final Client client;
    private ClientStudent myStudent;
    private static HashMap<DiskColor, Image> images;
    private ImageView myView;

    /**
     * Constructor method of StudentButton class
     * @param student the student of the client I need to visualize
     * @param client the client that owns the student
     */
    public StudentButton(ClientStudent student, Client client){

        super();
        generateImages();
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
     * generates the images needed for the buttons once for all of them
     */
    private void generateImages(){
        if(images==null) {

            images = new HashMap<>();
            for (DiskColor d:DiskColor.values()){

                Image resource = null;
                switch (d) {
                    case RED -> resource = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/student_red.png")).toString()) ;
                    case BLUE -> resource = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/student_blue.png")).toString()) ;
                    case PINK -> resource = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/student_pink.png")).toString());
                    case YELLOW -> resource = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/student_yellow.png")).toString());
                    case GREEN -> resource = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/student_green.png")).toString());
                    default -> {
                    }
                }
                images.put(d, resource);
            }

        }
    }


    /**
     * Setter method for the student's color, also sets visibility to false if color is null, and updates the button action based on the new color
     * @param color DiskColor chosen
     */
    private void setStudentColor(DiskColor color){
        this.color = color;
        if(color!=null){
        Image resource = images.get(color);
        if(myView==null){
            //assigns a new graphic element to the button

            myView = new ImageView(resource);
            setGraphic(myView);
            myView.fitHeightProperty().bind(heightProperty());
            myView.fitWidthProperty().bind(widthProperty());

        }else{
            //changes image of the graphic
            myView.setImage(resource);
        }
            setVisible(true);
        }
        else{

            setVisible(false);
        }
        setOnAction(e-> ClientInputHandler.handle("Student "+myStudent.getColor()+" "+myStudent.getID(),client));

    }

    /**
     * refresh resets all the graphics and functions for the studentButton
     */
    public void refresh(){
        setStudentColor(myStudent.getColor());
        setDisabled(color == null);
    }

    /**
     * ties this button to a new student
     * @param student newStudent
     */
    public void setNewStudent(ClientStudent student){
        myStudent=student;
    }

}
