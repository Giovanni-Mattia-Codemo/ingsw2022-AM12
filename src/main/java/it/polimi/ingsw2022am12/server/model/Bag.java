package it.polimi.ingsw2022am12.server.model;

import java.util.Random;

/**
 * Class Bag defines the bag from where the students are drawn in various phases of the game
 */
public class Bag extends StudentDiskCollection {

    /**
     * Default constructor for Bag, it calls the constructor of it's parent
     */
    public Bag(){
        super();
    }

    /**
     * Constructor used when the Bag is created starting from a saved game
     * @param studentDiskCollection bag
     */
    public Bag(StudentDiskCollection studentDiskCollection){
        super(studentDiskCollection.getAllStudents(), studentDiskCollection.getID());
    }

    /**
     * Method draw returns a student from the bag if there's at least one left (Hence amount()>0), else returns null
     * The draw implements the Random library to generate a pseudorandom index, which corresponds to the "random" student
     * I've drawn from the bag; the student drawn from the bag is then removed from it
     *
     * @return Student drawn from the bag
     */
    public Student draw() {

            if(this.amount()>0) {
                Random randomValue = new Random();
                Student temporaryStudent = this.getByIndex(randomValue.nextInt(this.amount()));
                this.removeElement(temporaryStudent);
                return temporaryStudent;
            }
            return null;
    }
}
