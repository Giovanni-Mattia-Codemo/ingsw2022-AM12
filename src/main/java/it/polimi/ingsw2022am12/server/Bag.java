package it.polimi.ingsw2022am12.server;

/**
 * Class Bag defines the bag from where the students ar drawn
 */
public class Bag extends StudentDiskCollection{

    /**
     * Method draw returns a student from the bag id there's at least one left, else returns null
     *
     * @return Student
     */
    public Student draw() {
            if(this.amount()>0) {
                Student tmp = this.getByIndex(Math.round(((int) (Math.random() * (this.amount()-1)))));
                this.removeElement(tmp);
                return tmp;
            }
            return null;
    }
}
