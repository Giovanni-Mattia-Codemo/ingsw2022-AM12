package it.polimi.ingsw2022am12.client;

public class InputMode {
    private int integer;
    private boolean aBoolean;

    public InputMode(int integer, boolean aBoolean){
        this.integer = integer;
        this.aBoolean = aBoolean;
    }

    public int getInteger() {
        return integer;
    }

    public boolean getBoolean(){
        return aBoolean;
    }
}
