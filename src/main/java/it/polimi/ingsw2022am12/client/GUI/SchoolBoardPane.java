package it.polimi.ingsw2022am12.client.GUI;

import it.polimi.ingsw2022am12.client.Client;
import it.polimi.ingsw2022am12.client.ClientInputHandler;
import it.polimi.ingsw2022am12.client.model.ClientGame;
import it.polimi.ingsw2022am12.client.model.ClientStudent;
import it.polimi.ingsw2022am12.client.model.ClientTeam;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import java.util.ArrayList;
import java.util.Objects;

public class SchoolBoardPane extends GridPane{

    private final String name;
    private final ClientGame myGame;
    private final Client client;

    private StackPane coin;
    private Image towerImage;
    private final GridPane towers;
    private final GridPane grid;
    private final GridPane professors;
    private final ArrayList<StudentButton> entrance;
    private final ArrayList<StudentButton> redDiningStudents;
    private final ArrayList<StudentButton> yellowDiningStudents;
    private final ArrayList<StudentButton> blueDiningStudents;
    private final ArrayList<StudentButton> pinkDiningStudents;
    private final ArrayList<StudentButton> greenDiningStudents;
    private final ArrayList<Button> professorButtons;
    private GridPane redRoom;
    private GridPane blueRoom;
    private GridPane greenRoom;
    private GridPane yellowRoom;
    private GridPane pinkRoom;

    public SchoolBoardPane(String name,Client client){
        super();
        myGame=client.getClientGame();
        this.client = client;
        this.name = name;
        professorButtons = new ArrayList<>();
        entrance = new ArrayList<>();
        redDiningStudents = new ArrayList<>();
        greenDiningStudents = new ArrayList<>();
        blueDiningStudents = new ArrayList<>();
        yellowDiningStudents = new ArrayList<>();
        pinkDiningStudents = new ArrayList<>();

        grid = new GridPane();
        //grid.setGridLinesVisible(true);
        RowConstraints temp = new RowConstraints();
        temp.setVgrow(Priority.ALWAYS);
        temp.setFillHeight(true);
        grid.getRowConstraints().add(temp);

        for (int rowIndex = 0; rowIndex < 5; rowIndex++) {
            RowConstraints rc = new RowConstraints();
            rc.setVgrow(Priority.NEVER) ; // allow row to grow
            rc.setFillHeight(true); // ask nodes to fill height for row
            rc.prefHeightProperty().bind(grid.heightProperty().multiply(0.1));
            grid.getRowConstraints().add(rc);
        }

        RowConstraints temp1 = new RowConstraints();
        temp1.setVgrow(Priority.ALWAYS);
        temp1.setFillHeight(true);
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
            grid.getColumnConstraints().add(cc);
        }

        ArrayList<ClientStudent> entranceStudents = new ArrayList<>(myGame.getSchoolBoardByNick(name).getEntrance().getStudents()) ;
        fillEntrance(entranceStudents);


        grid.hgapProperty().bind(this.widthProperty().divide(65));
        grid.vgapProperty().bind(this.heightProperty().divide(16));

        grid.setMinSize(1,1);
        grid.prefHeightProperty().bind(this.heightProperty());
        grid.prefWidthProperty().bind(this.widthProperty().multiply(0.15));
        this.addColumn(0, grid);
        //end of this

        //diningroom
        StackPane stackRoom = new StackPane();
        GridPane.setHgrow(stackRoom, Priority.NEVER);
        stackRoom.setPickOnBounds(false);
        Button diningRoomButton = new Button();
        diningRoomButton.setBackground(Background.EMPTY);
        diningRoomButton.prefHeightProperty().bind(stackRoom.heightProperty());
        diningRoomButton.prefWidthProperty().bind(stackRoom.widthProperty());
        diningRoomButton.setOnAction(e-> ClientInputHandler.handle("DiningRoom", client));


        GridPane diningRoom = new GridPane();
        diningRoom.setPickOnBounds(false);

        stackRoom.getChildren().addAll(diningRoomButton, diningRoom);

        temp = new RowConstraints();
        temp.setVgrow(Priority.ALWAYS);
        temp.setFillHeight(true);
        diningRoom.getRowConstraints().add(temp);

        for (int rowIndex = 0; rowIndex < 5; rowIndex++) {
            RowConstraints rc = new RowConstraints();
            rc.setVgrow(Priority.NEVER);
            rc.setFillHeight(true);
            rc.prefHeightProperty().bind(diningRoom.heightProperty().multiply(0.1));
            diningRoom.getRowConstraints().add(rc);
        }

        temp1 = new RowConstraints();
        temp1.setVgrow(Priority.ALWAYS);
        temp1.setFillHeight(true);
        diningRoom.getRowConstraints().add(temp1);

        tmp = new ColumnConstraints();
        tmp.setHgrow(Priority.NEVER);
        tmp.setFillWidth(true);
        tmp.prefWidthProperty().bind(diningRoom.widthProperty().divide(20));
        diningRoom.getColumnConstraints().add(tmp);

        for (int colIndex = 1; colIndex < 2; colIndex++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setHgrow(Priority.NEVER);
            cc.setFillWidth(true);
            cc.prefWidthProperty().bind(diningRoom.widthProperty().multiply(0.95));
            diningRoom.getColumnConstraints().add(cc);
        }

        for(int i = 0; i<5; i++){
            StackPane room = new StackPane();
            room.setPickOnBounds(false);
            GridPane students= new GridPane();
            students.setPickOnBounds(false);
            switch (i+1){
                case 1-> greenRoom = students;
                case 2-> redRoom = students;
                case 3-> yellowRoom = students;
                case 4-> pinkRoom = students;
                case 5-> blueRoom = students;
            }


            students.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

            students.setMinSize(1.0, 1.0);
            room.setMinSize(1.0, 1.0);

            room.setAlignment(Pos.CENTER_LEFT);
            room.getChildren().addAll(students);
            room.prefHeightProperty().bind(diningRoom.getRowConstraints().get(i+1).prefHeightProperty());

            temp1 = new RowConstraints();
            temp1.setVgrow(Priority.ALWAYS);
            temp1.setFillHeight(true);
            students.getRowConstraints().add(temp1);

            for(int t = 0; t<10; t++){
                ColumnConstraints cc = new ColumnConstraints();
                cc.setHgrow(Priority.NEVER);
                cc.setFillWidth(true);
                cc.prefWidthProperty().bind(room.heightProperty());
                students.getColumnConstraints().add(cc);
            }

            students.hgapProperty().bind(this.widthProperty().divide(250));

            diningRoom.add(room, 1, i+1);

        }
        fillDiningRoom();

        diningRoom.hgapProperty().bind(this.widthProperty().divide(250));
        diningRoom.vgapProperty().bind(this.heightProperty().divide(16));
        diningRoom.setMinSize(1,1);
        diningRoom.prefHeightProperty().bind(this.heightProperty());
        diningRoom.prefWidthProperty().bind(this.widthProperty().multiply(0.55));


        stackRoom.setMinSize(1,1);
        stackRoom.prefHeightProperty().bind(this.heightProperty());
        stackRoom.prefWidthProperty().bind(this.widthProperty().multiply(0.55));
        this.addColumn(1, stackRoom);


        //end diningroom


        //start professors
        professors = new GridPane();

        temp = new RowConstraints();
        temp.setVgrow(Priority.ALWAYS);
        temp.setFillHeight(true);
        professors.getRowConstraints().add(temp);

        for (int rowIndex = 0; rowIndex < 5; rowIndex++) {
            RowConstraints rc = new RowConstraints();
            rc.setVgrow(Priority.NEVER) ; // allow row to grow
            rc.setFillHeight(true); // ask nodes to fill height for row
            rc.prefHeightProperty().bind(professors.heightProperty().multiply(0.1));
            professors.getRowConstraints().add(rc);
        }

        temp1 = new RowConstraints();
        temp1.setVgrow(Priority.ALWAYS);
        temp1.setFillHeight(true);
        professors.getRowConstraints().add(temp1);

        tmp = new ColumnConstraints();
        tmp.setHgrow(Priority.NEVER);
        tmp.setFillWidth(true);
        tmp.prefWidthProperty().bind(professors.widthProperty().divide(20));
        professors.getColumnConstraints().add(tmp);

        for (int colIndex = 1; colIndex < 2; colIndex++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setHgrow(Priority.NEVER);
            cc.setFillWidth(true);
            cc.prefWidthProperty().bind(professors.heightProperty().multiply(0.10));
            professors.getColumnConstraints().add(cc);
        }

        professors.hgapProperty().bind(this.widthProperty().divide(200));
        professors.vgapProperty().bind(this.heightProperty().divide(16));
        professors.setMinSize(1.0,1.0);
        professors.prefHeightProperty().bind(this.heightProperty());
        professors.prefWidthProperty().bind(this.widthProperty().multiply(0.07));
        this.addColumn(2, professors);
        fillProfessors();

        //end professors

        //begin towers

        towers = new GridPane();

        temp = new RowConstraints();
        temp.setVgrow(Priority.ALWAYS);
        temp.setFillHeight(true);
        towers.getRowConstraints().add(temp);

        for (int rowIndex = 0; rowIndex < 4; rowIndex++) {
            RowConstraints rc = new RowConstraints();
            rc.setVgrow(Priority.NEVER);
            rc.setFillHeight(true);
            rc.prefHeightProperty().bind(towers.heightProperty().divide(8.0));
            towers.getRowConstraints().add(rc);
        }

        temp1 = new RowConstraints();
        temp1.setVgrow(Priority.ALWAYS);
        temp1.setFillHeight(true);
        towers.getRowConstraints().add(temp1);

        tmp = new ColumnConstraints();
        tmp.setHgrow(Priority.NEVER);
        tmp.setFillWidth(true);
        tmp.prefWidthProperty().bind(towers.widthProperty().divide(8));
        towers.getColumnConstraints().add(tmp);

        for (int colIndex = 1; colIndex < 3; colIndex++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setHgrow(Priority.NEVER);
            cc.setFillWidth(true);
            cc.prefWidthProperty().bind(towers.heightProperty().divide(8.0));
            towers.getColumnConstraints().add(cc);
        }

        towers.hgapProperty().bind(this.widthProperty().divide(55));
        towers.vgapProperty().bind(this.heightProperty().divide(28));
        towers.setMinSize(1,1);
        towers.prefHeightProperty().bind(this.heightProperty());
        towers.prefWidthProperty().bind(this.widthProperty().multiply(0.20));
        this.addColumn(3, towers);
        fillTowers();
        //end towers


        BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false,false,true,true);
        Image image = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/Plancia_DEF2.png")).toString());
        Background background = new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, bSize));
        this.setBackground(background);
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

        refresh();
    }

    public void refresh(){

        fillEntrance(myGame.getSchoolBoardByNick(name).getEntrance().getStudents());

        for (StudentButton b:entrance
             ) {
            b.refresh();
        }

        fillDiningRoom();
        fillTowers();

        for(int i=0; i<5; i++){
            ArrayList<StudentButton> studentButtons = null;
            switch (i+1){
                case 1-> studentButtons = greenDiningStudents;
                case 2-> studentButtons =  redDiningStudents;
                case 3-> studentButtons = yellowDiningStudents;
                case 4-> studentButtons =  pinkDiningStudents ;
                case 5-> studentButtons = blueDiningStudents  ;
            }
            for(StudentButton s: studentButtons){
                s.refresh();
            }
        }

        for (int i=0;i<5;i++){
            if(myGame.getProfessors()[i].equals(name)){
                boolean found = false;
                for(Button b: professorButtons){
                    if(GridPane.getRowIndex(b)==i+1){
                        found = true;
                        break;
                    }
                }
                if(!found){
                    Image prof = null;
                    Button pane= new Button();
                    pane.setMinSize(1.0,1.0);
                    pane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                    switch (i+1){
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
                    professors.add(pane, 1, i+1);

                }
            }else{
                Button found = null;
                for(Button b: professorButtons){
                    if(GridPane.getRowIndex(b)==i+1){
                        found = b;
                        break;
                    }
                }
                if(found!=null){
                    professors.getChildren().remove(found);
                    professorButtons.remove(found);
                }
            }
        }
        updateCoins();
    }

    public void updateCoins(){
        if(coin == null){
            coin = new StackPane();
            coin.setMinSize(1.0, 1.0);
            coin.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            ImageView coinImage = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/coin_sprite.png")).toString()));
            coinImage.fitHeightProperty().bind(coin.heightProperty());
            coinImage.fitWidthProperty().bind(coin.widthProperty());
            Label number = new Label();
            number.setAlignment(Pos.CENTER);
            number.setMinSize(1.0, 1.0);
            number.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            number.prefHeightProperty().bind(coin.heightProperty());
            number.prefWidthProperty().bind(coin.widthProperty());
            coin.getChildren().addAll(coinImage, number);
            coin.setAlignment(Pos.CENTER);
            grid.add(coin, 1, 1);
        }
        ((Label)coin.getChildren().get(1)).setText(""+client.getClientGame().getSchoolBoardByNick(name).getCoins());

    }

    private void fillEntrance(ArrayList<ClientStudent> students){
        int difference = students.size()-entrance.size();
        int maxEntranceRows = myGame.getOrderedNicks().size()==3?5:4;
        int maxPos = -1;
        for(StudentButton s: entrance){
            int pos = GridPane.getRowIndex(s)-1 + GridPane.getColumnIndex(s)==2?0:maxEntranceRows;
            if(pos>maxPos){
                maxPos = pos;
            }
        }
        for(int i=0; i<difference;i++){
           ClientStudent tmp = students.get(students.size()-i-1);
           StudentButton newButton = new StudentButton(tmp, client);
           int firstAvailablePos;
           if(maxPos<maxEntranceRows-1){
               firstAvailablePos = maxPos+1;
           }else{
               firstAvailablePos = Math.max(maxPos+1, maxEntranceRows);
           }
           grid.add(newButton, firstAvailablePos>=maxEntranceRows?1:2,firstAvailablePos<maxEntranceRows?firstAvailablePos+1:firstAvailablePos%maxEntranceRows+2);
           entrance.add(newButton);
           maxPos=firstAvailablePos;
        }
    }

    private void fillProfessors(){
        Image prof = null;

        for(int i = 1; i<6; i++){
            String professor = myGame.getProfessors()[i-1];
            if(!professor.equals(name)){
                break;
            }
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
    }


    private void fillTowers(){

        int towersTotal = myGame.getSchoolBoardByNick(name).getTowers();
        towers.getChildren().removeAll();

        if(towerImage == null) {
            ArrayList<ClientTeam> teams = myGame.getTeams();
            int col = 0;
            for (ClientTeam t : teams) {
                if (t.getPlayer1().equals(name) || t.getPlayer2().equals(name)) {
                    col = teams.indexOf(t);
                }
            }

            switch (col) {
                case 0 -> towerImage = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/white_tower.png")).toString());
                case 1 -> towerImage = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/grey_tower.png")).toString());
                case 2 -> towerImage = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/black_tower.png")).toString());
                default -> {
                }
            }
        }

        for(int i= 0; i<4;i++){

            for(int j = 0; j<2; j++){

                if(getNodeByCoordinate(i+1, j+1)==null){
                    Button tower = new Button();
                    tower.setMinSize(1.0,1.0);
                    tower.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                    towers.add(tower, j+1 , i+1);
                    tower.setBackground(Background.EMPTY);
                    ImageView img = new ImageView(towerImage);
                    img.fitHeightProperty().bind(tower.heightProperty());
                    img.fitWidthProperty().bind(tower.widthProperty());
                    tower.setGraphic(img);
                }

                Button tower = (Button) getNodeByCoordinate(i+1, j+1);
                if (tower != null) {
                    tower.setVisible(towersTotal > 0);
                }

                towersTotal--;
            }
        }
    }

    private void fillDiningRoom() {
        ArrayList<ClientStudent> diningStudents = new ArrayList<>(myGame.getSchoolBoardByNick(name).getDiningRooms().getStudents());
        for (int i = 0; i < 5; i++) {
            GridPane students= null;
            ArrayList<StudentButton> studentButtons = null;
            switch (i+1){
                case 1->{ students = greenRoom; studentButtons = greenDiningStudents;  }
                case 2->{ students=redRoom;studentButtons =  redDiningStudents; }
                case 3->{ students = yellowRoom;studentButtons = yellowDiningStudents;  }
                case 4->{ students=pinkRoom;studentButtons =  pinkDiningStudents ;}
                case 5-> {students=blueRoom;studentButtons = blueDiningStudents  ;}
            }

            for (int t = 0; t < 10; t++) {
                boolean alreadyPresent = false;
                for (StudentButton s: studentButtons){
                    if(GridPane.getColumnIndex(s)==t){
                        alreadyPresent = true;
                        break;
                    }
                }

                ClientStudent realStudent = null;
                for (ClientStudent s : diningStudents) {
                    if (s.getColor().getValue() == i) {
                        realStudent = s;
                        diningStudents.remove(s);
                        break;
                    }
                }
                if(realStudent!=null){
                    if(!alreadyPresent){
                        assignStudentButtonOfColor(i,t,realStudent);

                    }else {
                        student.setNewStudent(realStudent);
                    }
                }
            }
        }
    }

    private Node getNodeByCoordinate(int row, int column) {
        for (Node node : towers.getChildren()) {
            if(GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column){
                return node;
            }
        }
        return null;
    }
}
