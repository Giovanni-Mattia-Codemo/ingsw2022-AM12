package it.polimi.ingsw2022am12.server;

import it.polimi.ingsw2022am12.server.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * RestoreGameTests is a class that tests if the game state can be restored from the saved files correctly
 */
public class RestoreGameTests {

    /**
     * checkSavedIsland creates an instance of an island, an instance a schoolBoard and an instance of the game and checks if
     * the values have been assigned correctly
     */
    @Test
    public void checkSavedIsland(){
        StudentDiskCollection studentDiskCollection = new StudentDiskCollection(300);
        StudentDiskCollection entrance = new StudentDiskCollection(400);
        StudentDiskCollection dining = new StudentDiskCollection(500);
        Team team = new Team();
        SchoolBoard schoolBoard = new SchoolBoard("Player1");
        team.addNick("Player1");
        IslandTileSet is = new IslandTileSet(4, studentDiskCollection, 13, 3, 3, 3, "Player1");
        ArrayList<IslandTileSet> islands = new ArrayList<>();
        islands.add(is);
        islands.add(new IslandTileSet());
        islands.add(new IslandTileSet());
        islands.add(new IslandTileSet());
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Player1");
        nicks.add("Player2");
        Team team1 = new Team();
        team1.addNick("Player2");
        ArrayList<Team> teams = new ArrayList<>(List.of(team, team1));
        ArrayList<StudentDiskCollection> clouds = new ArrayList<>(List.of(new StudentDiskCollection(), new StudentDiskCollection()));
        Bag bag = new Bag();
        ArrayList<SchoolBoard> schoolBoards = new ArrayList<>();
        ArrayList<Assistant> assistants = new ArrayList<>();
        SchoolBoard sch1 = new SchoolBoard("Player2", 2, 1, assistants, entrance, dining, 8, 3);
        schoolBoards.add(schoolBoard);
        schoolBoards.add(sch1);
        String professors[] = new String[5];
        professors[0]="Player1";
        ArrayList<CharacterCard> cards = new ArrayList<>();
        Game game = new Game(nicks, true, bag, 18, schoolBoards, islands, 4, clouds, teams, professors, cards);
        is.setNoEntryCharacter(new NoEntryCollection());

        Assertions.assertEquals(4, is.getID());
        Assertions.assertEquals(studentDiskCollection, is.getStudentCollection());
        Assertions.assertEquals(3, is.getNumOfIslandsInThisSet());
        Assertions.assertEquals(3, is.getTowers().size());
        Assertions.assertEquals(team.getSchoolBoards(), is.getOwningTeam().getSchoolBoards());


    }
}
