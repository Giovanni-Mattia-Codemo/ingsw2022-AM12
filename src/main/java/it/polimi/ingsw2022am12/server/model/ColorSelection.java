package it.polimi.ingsw2022am12.server.model;

public class ColorSelection implements Selectable {

    private final DiskColor color;

    public ColorSelection(DiskColor color){
        this.color = color;
    }

    public DiskColor getColor(){
       return this.color;
    }

    @Override
    public String getSelectableType() {
        return "Color";
    }
}
