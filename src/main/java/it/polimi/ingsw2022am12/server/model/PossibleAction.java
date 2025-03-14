package it.polimi.ingsw2022am12.server.model;

import it.polimi.ingsw2022am12.server.controller.ControlMessages;
import it.polimi.ingsw2022am12.updateFlag.Flag;
import it.polimi.ingsw2022am12.updateFlag.UpdateFlag;
import it.polimi.ingsw2022am12.server.model.actions.ActionStep;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

/**
 * Abstract class PossibleAction defines the type of every possible action a player can do during the game
 */
public abstract class PossibleAction {

    private final int requiredInputs;
    protected Map<Integer, ArrayList<Selectable>> selectables;
    protected Map<Integer, Selectable> score;
    
    

    /**
     * "Constructor" Method of PossibleAction class
     *
     * @param requiredInputs number of inputs required
     */
    public PossibleAction(int requiredInputs){
        this.requiredInputs=requiredInputs;
        this.selectables = new HashMap<>();
        this.score = new HashMap<>();
    }

    /**
     * Method checkInputValidity checks if I'm using the correct type and number of inputs required by my action
     *
     * @param input my chosen inputs
     * @return ActionStep number of inputs needed by my PossibleAction
     */
    public ActionStep checkInputValidity(ArrayList<Selectable> input){

            if(input.size()==0||input.size()>requiredInputs){
                return ActionStep.NOTOK;
            }
            for(int i= 0; i< selectables.size(); i++){
                if(score.containsKey(i-1)||i==0){
                    for (Selectable s: selectables.get(i)
                    ) {
                        for (Selectable in:input
                        ) {
                            if(s.isEqual(in)){
                                score.put(i,in);
                            }
                        }
                    }
                }
            }
            if(score.size()==0){
                return ActionStep.NOTOK;
            }else if (score.size()<requiredInputs&&input.size()==score.size()){
                return ActionStep.HALFOK;
            }else if (score.size()==input.size()&&score.size()==requiredInputs){
                return ActionStep.OK;
            }
        return ActionStep.NOTOK;
    }

    /**
     * setSelectables method sets the selectable mapping values
     *
     * @param game instance of my game
     */
    public void setSelectables(Game game){}

    /**
     * getUserSelectionsMessage returns a message based on what the user wants to select
     * @return ControlMessages message
     */
    public ArrayList<ControlMessages> getUserSelectionsMessage(){
        return null;
    }

    /**
     * getSelectables method returns an array of the selectable objects contained in the map
     *
     * @return ArrayList</Selectable>
     */
    public ArrayList<Selectable> getSelectables(){
        ArrayList<Selectable> result = new ArrayList<>();
        for (int i = 0; i < selectables.size(); i++) {
            if(!score.containsKey(i)){
                result.addAll(selectables.get(i));
            }
        }
        return result;
    }

    /**
     * Method useAction activates my desired move
     *
     * @param game the game that is being played currently
     */
    public void useAction(Game game){

    }

    /**
     * getUpdates method gets a FULLGAME update
     *
     * @param game the instance of my game
     * @return the list of UpdateFlags
     */
    public ArrayList<UpdateFlag> getUpdates(Game game){
        ArrayList<UpdateFlag> result = new ArrayList<>();
        result.add(new UpdateFlag(Flag.FULLGAME));
        return result;
    }

}
