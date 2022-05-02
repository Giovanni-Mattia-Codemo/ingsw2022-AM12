package it.polimi.ingsw2022am12.client;

public class ClientStudent {
    private String color;
    private int ID;
    public ClientStudent(String color, int ID){
        this.color = color;
        this.ID = ID;
    }

    public String getColor() {
        return color;
    }

    public int getID() {
        return ID;
    }
}
