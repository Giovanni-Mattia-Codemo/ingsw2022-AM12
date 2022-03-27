package it.polimi.ingsw2022am12.server.model;

/**
 * Class Bag defines the bag from where the students ar drawn
 */
public class Bag extends StudentDiskCollection {

    /**
     * Method draw returns a student from the bag id there's at least one left, else returns null
     *
     * @return Student
     */
    public Student draw() {
            if(this.amount()>0) {
                double index = Math.random() * (this.amount()-1);
                Student tmp = this.getByIndex(Math.toIntExact(Math.round(index)));
                this.removeElement(tmp);
                return tmp;
            }
            return null;
    }
}
