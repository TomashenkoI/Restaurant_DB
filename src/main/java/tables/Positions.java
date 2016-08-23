package tables;

/**
 * Created by 7 on 20.08.2016.
 */
public class Positions extends Table{

    private int ID;
    private String Name;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public String toString() {
        return "Positions{" +
                "ID= " + ID +
                ", post_title =" + "\t"+ Name +
                '}';
    }
}
