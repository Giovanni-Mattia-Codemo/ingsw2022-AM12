package it.polimi.ingsw2022am12.client.GUI;

import it.polimi.ingsw2022am12.DiskColor;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.Objects;

public class SchoolBoardPane extends GridPane{

    private String name;
    private final ArrayList<StudentButton> entrance;
    private final ArrayList<StudentButton> redDiningStudents;
    private final ArrayList<StudentButton> yellowDiningStudents;
    private final ArrayList<StudentButton> blueDiningStudents;
    private final ArrayList<StudentButton> pinkDiningStudents;
    private final ArrayList<StudentButton> greenDiningStudents;
    private final ArrayList<Button> professorButtons;
    private final ArrayList<Button> towerButtons;
    private Button redDining;
    private Button blueDining;
    private Button yellowDining;
    private Button greenDining;
    private Button pinkDining;
    private int schoolColor;
    private int mageColor;

    public SchoolBoardPane(String name){
        super();
        this.name = name;
        professorButtons = new ArrayList<>();
        entrance = new ArrayList<>();
        redDiningStudents = new ArrayList<>();
        greenDiningStudents = new ArrayList<>();
        blueDiningStudents = new ArrayList<>();
        yellowDiningStudents = new ArrayList<>();
        pinkDiningStudents = new ArrayList<>();
        towerButtons = new ArrayList<>();

        //stuff to make the entrance grid inside the schoolBoard
        Double rowHeighPerc = 14.0;
        Double borderRowPerc = 5.0;

        GridPane grid = new GridPane();

        RowConstraints temp = new RowConstraints();
        temp.setVgrow(Priority.ALWAYS);
        temp.setFillHeight(true);
        //temp.setPercentHeight(borderRowPerc);
        grid.getRowConstraints().add(temp);

        for (int rowIndex = 0; rowIndex < 5; rowIndex++) {
            RowConstraints rc = new RowConstraints();
            rc.setVgrow(Priority.NEVER) ; // allow row to grow
            rc.setFillHeight(true); // ask nodes to fill height for row
            rc.prefHeightProperty().bind(grid.heightProperty().multiply(0.1));
            // other settings as needed...
            grid.getRowConstraints().add(rc);
        }

        RowConstraints temp1 = new RowConstraints();
        temp1.setVgrow(Priority.ALWAYS);
        temp1.setFillHeight(true);
        //temp1.setPercentHeight(borderRowPerc);
        grid.getRowConstraints().add(temp1);

        ColumnConstraints tmp = new ColumnConstraints();
        tmp.setHgrow(Priority.NEVER);
        tmp.setFillWidth(true);
        tmp.prefWidthProperty().bind(grid.widthProperty().divide(10));
        grid.getColumnConstraints().add(tmp);

        for (int colIndex = 1; colIndex < 3; colIndex++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setHgrow(Priority.NEVER) ; // allow column to grow
            cc.setFillWidth(true); // ask nodes to fill space for column
            cc.prefWidthProperty().bind(grid.heightProperty().divide(10.0));

            // other settings as needed...
            grid.getColumnConstraints().add(cc);
        }
        for(int j= 0; j<2;j++){
            for(int i = 0; i<5; i++){

                StudentButton student = new StudentButton(DiskColor.BLUE);
                grid.add(student, j+1 , i+1);
                entrance.add(student);

            }
        }


        grid.hgapProperty().bind(this.widthProperty().divide(65));
        grid.vgapProperty().bind(this.heightProperty().divide(16));

        grid.setMinSize(1,1);
        grid.prefHeightProperty().bind(this.heightProperty());
        grid.prefWidthProperty().bind(this.widthProperty().multiply(0.15));
        this.addColumn(0, grid);
        //end of this

        //diningroom
        GridPane diningRoom = new GridPane();

        temp = new RowConstraints();
        temp.setVgrow(Priority.ALWAYS);
        temp.setFillHeight(true);
        //temp.setPercentHeight(borderRowPerc);
        diningRoom.getRowConstraints().add(temp);

        for (int rowIndex = 0; rowIndex < 5; rowIndex++) {
            RowConstraints rc = new RowConstraints();
            rc.setVgrow(Priority.NEVER) ; // allow row to grow
            rc.setFillHeight(true); // ask nodes to fill height for row
            rc.prefHeightProperty().bind(diningRoom.heightProperty().multiply(0.1));
            // other settings as needed...
            diningRoom.getRowConstraints().add(rc);
        }

        temp1 = new RowConstraints();
        temp1.setVgrow(Priority.ALWAYS);
        temp1.setFillHeight(true);
        //temp1.setPercentHeight(borderRowPerc);
        diningRoom.getRowConstraints().add(temp1);

        tmp = new ColumnConstraints();
        tmp.setHgrow(Priority.NEVER);
        tmp.setFillWidth(true);
        tmp.prefWidthProperty().bind(diningRoom.widthProperty().divide(20));
        diningRoom.getColumnConstraints().add(tmp);

        for (int colIndex = 1; colIndex < 2; colIndex++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setHgrow(Priority.NEVER) ; // allow column to grow
            cc.setFillWidth(true); // ask nodes to fill space for column
            cc.prefWidthProperty().bind(diningRoom.widthProperty().multiply(0.95));
            // other settings as needed...
            diningRoom.getColumnConstraints().add(cc);
        }

        for(int i = 1; i<6; i++){
            StackPane room = new StackPane();
            GridPane students = new GridPane();
            Button roomButton = new Button();
            roomButton.setMinSize(1.0, 1.0);

            students.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

            students.setMinSize(1.0, 1.0);
            room.setMinSize(1.0, 1.0);

            students.setGridLinesVisible(true);

            roomButton.setBackground(Background.EMPTY);
            roomButton.prefHeightProperty().bind(room.heightProperty());
            roomButton.prefWidthProperty().bind(room.widthProperty());
            room.setAlignment(Pos.CENTER_LEFT);
            room.getChildren().addAll(students, roomButton);
            room.prefHeightProperty().bind(diningRoom.getRowConstraints().get(i).prefHeightProperty());

            temp1 = new RowConstraints();
            temp1.setVgrow(Priority.ALWAYS);
            temp1.setFillHeight(true);
            //temp1.setPercentHeight(borderRowPerc);
            students.getRowConstraints().add(temp1);

            for(int t = 0; t<10; t++){
                ColumnConstraints cc = new ColumnConstraints();
                cc.setHgrow(Priority.NEVER) ; // allow column to grow
                cc.setFillWidth(true); // ask nodes to fill space for column
                cc.prefWidthProperty().bind(room.heightProperty());

                // other settings as needed...
                students.getColumnConstraints().add(cc);
                switch (i) {
                    case 1 -> {
                        StudentButton student = new StudentButton(DiskColor.GREEN);
                        students.add(student, t, 0);
                        greenDiningStudents.add(student);

                    }
                    case 2 ->{
                        StudentButton student = new StudentButton(DiskColor.RED);
                        students.add(student, t, 0);
                        redDiningStudents.add(student);
                    }
                    case 3 ->{
                        StudentButton student = new StudentButton(DiskColor.YELLOW);
                        students.add(student, t, 0);
                        yellowDiningStudents.add(student);
                    }
                    case 4 ->{
                        StudentButton student = new StudentButton(DiskColor.PINK);
                        students.add(student, t, 0);
                        pinkDiningStudents.add(student);
                    }
                    case 5 ->{
                        student = new StudentButton(DiskColor.BLUE);
                        students.add(student, t, 0);
                        blueDiningStudents.add(student);
                    }
                    default -> {
                    }
                }
            }

            students.hgapProperty().bind(this.widthProperty().divide(250));

            diningRoom.add(room, 1, i);

        }


        diningRoom.hgapProperty().bind(this.widthProperty().divide(250));
        diningRoom.vgapProperty().bind(this.heightProperty().divide(16));
        diningRoom.setGridLinesVisible(true);
        diningRoom.setMinSize(1,1);
        diningRoom.prefHeightProperty().bind(this.heightProperty());
        diningRoom.prefWidthProperty().bind(this.widthProperty().multiply(0.55));
        this.addColumn(1, diningRoom);


        //end diningroom


        //start professors
        GridPane professors = new GridPane();

        temp = new RowConstraints();
        temp.setVgrow(Priority.ALWAYS);
        temp.setFillHeight(true);
        //temp.setPercentHeight(borderRowPerc);
        professors.getRowConstraints().add(temp);

        for (int rowIndex = 0; rowIndex < 5; rowIndex++) {
            RowConstraints rc = new RowConstraints();
            rc.setVgrow(Priority.NEVER) ; // allow row to grow
            rc.setFillHeight(true); // ask nodes to fill height for row
            rc.prefHeightProperty().bind(professors.heightProperty().multiply(0.1));
            // other settings as needed...
            professors.getRowConstraints().add(rc);
        }

        temp1 = new RowConstraints();
        temp1.setVgrow(Priority.ALWAYS);
        temp1.setFillHeight(true);
        //temp1.setPercentHeight(borderRowPerc);
        professors.getRowConstraints().add(temp1);

        tmp = new ColumnConstraints();
        tmp.setHgrow(Priority.NEVER);
        tmp.setFillWidth(true);
        tmp.prefWidthProperty().bind(professors.widthProperty().divide(20));
        professors.getColumnConstraints().add(tmp);

        for (int colIndex = 1; colIndex < 2; colIndex++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setHgrow(Priority.NEVER) ; // allow column to grow
            cc.setFillWidth(true); // ask nodes to fill space for column
            cc.prefWidthProperty().bind(professors.heightProperty().multiply(0.10));
            // other settings as needed...
            professors.getColumnConstraints().add(cc);
        }


        Image prof = null;

        for(int i = 1; i<6; i++){
            Button pane= new Button();
            pane.setMinSize(1.0,1.0);
            pane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            switch (i){
                case 1-> prof = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/teacher_green.png")).toString());
                case 2-> prof = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/teacher_red.png")).toString());
                case 3-> prof = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/teacher_yellow.png")).toString());
                case 4-> prof = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/teacher_pink.png")).toString());
                case 5-> prof = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/teacher_blue.png")).toString());
                default->{}
            }
            professorButtons.add(pane);

            BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false,false,true,true);
            Background bGround = new Background(new BackgroundImage(prof, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,BackgroundPosition.CENTER, bSize));
            pane.setBackground(bGround);
            pane.setRotate(90);
            professors.add(pane, 1, i);

        }


        professors.hgapProperty().bind(this.widthProperty().divide(200));
        professors.vgapProperty().bind(this.heightProperty().divide(16));
        professors.setGridLinesVisible(true);
        professors.setMinSize(1.0,1.0);
        professors.prefHeightProperty().bind(this.heightProperty());
        professors.prefWidthProperty().bind(this.widthProperty().multiply(0.07));
        this.addColumn(2, professors);


        //end professors

        //begin towers

        GridPane towers = new GridPane();

        temp = new RowConstraints();
        temp.setVgrow(Priority.ALWAYS);
        temp.setFillHeight(true);
        //temp.setPercentHeight(borderRowPerc);
        towers.getRowConstraints().add(temp);

        for (int rowIndex = 0; rowIndex < 4; rowIndex++) {
            RowConstraints rc = new RowConstraints();
            rc.setVgrow(Priority.NEVER) ; // allow row to grow
            rc.setFillHeight(true); // ask nodes to fill height for row
            rc.prefHeightProperty().bind(towers.heightProperty().divide(8.0));
            // other settings as needed...
            towers.getRowConstraints().add(rc);
        }

        temp1 = new RowConstraints();
        temp1.setVgrow(Priority.ALWAYS);
        temp1.setFillHeight(true);
        //temp1.setPercentHeight(borderRowPerc);
        towers.getRowConstraints().add(temp1);

        tmp = new ColumnConstraints();
        tmp.setHgrow(Priority.NEVER);
        tmp.setFillWidth(true);
        tmp.prefWidthProperty().bind(towers.widthProperty().divide(8));
        towers.getColumnConstraints().add(tmp);

        for (int colIndex = 1; colIndex < 3; colIndex++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setHgrow(Priority.NEVER) ; // allow column to grow
            cc.setFillWidth(true); // ask nodes to fill space for column
            cc.prefWidthProperty().bind(towers.heightProperty().divide(8.0));

            // other settings as needed...
            towers.getColumnConstraints().add(cc);
        }
        for(int j= 0; j<2;j++){
            for(int i = 0; i<4; i++){

                Button tower = new Button();
                Image towerImage = null;
                int col = 0;
                switch (col){
                    case 0-> towerImage = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/white_tower.png")).toString());
                    case 1-> towerImage = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/grey_tower.png")).toString());
                    case 2-> towerImage = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/black_tower.png")).toString());
                    default -> {}
                }
                tower.setBackground(Background.EMPTY);
                ImageView img = new ImageView(towerImage);
                img.fitHeightProperty().bind(tower.heightProperty());
                img.fitWidthProperty().bind(tower.widthProperty());
                tower.setGraphic(img);

                tower.setMinSize(1.0,1.0);
                tower.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                towers.add(tower, j+1 , i+1);
                towerButtons.add(tower);

            }
        }


        towers.hgapProperty().bind(this.widthProperty().divide(55));
        towers.vgapProperty().bind(this.heightProperty().divide(28));
        towers.setGridLinesVisible(true);

        towers.setMinSize(1,1);
        towers.prefHeightProperty().bind(this.heightProperty());
        towers.prefWidthProperty().bind(this.widthProperty().multiply(0.20));
        this.addColumn(3, towers);

        //end towers


        BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false,false,true,true);
        Image image = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/Plancia_DEF2.png")).toString());
        Background background = new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, bSize));
        this.setBackground(background);

        this.setGridLinesVisible(true);
        GridPane.setFillHeight(this, true);
        GridPane.setFillWidth(this, true);
        double h = image.getHeight()/5;
        double w = image.getWidth()/5;

        this.setMinHeight(1);
        this.setMinWidth(1);
        this.setPrefSize(w, h);
        this.setMaxWidth(Double.POSITIVE_INFINITY);
        this.setMaxHeight(Double.POSITIVE_INFINITY);
        this.setAlignment(Pos.CENTER_LEFT);

        entrance.get(0).setVisible(false);

    }

}
