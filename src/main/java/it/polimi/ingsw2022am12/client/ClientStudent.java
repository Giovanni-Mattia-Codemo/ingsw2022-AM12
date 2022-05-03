package it.polimi.ingsw2022am12.client;

public class ClientStudent {
    private String color;
    private int ID;

    public ClientStudent(String color, int ID){
        this.color = color;
        this.ID = ID;
    }

    public ClientStudent(){

    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getColor() {
        return color;
    }

    public int getID() {
        return ID;
    }
}
