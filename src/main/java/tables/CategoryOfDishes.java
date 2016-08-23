package tables;

/**
 * Created by 7 on 20.08.2016.
 */
public class CategoryOfDishes extends Table{

    private int ID;
    private String name;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CategoryOfDishes{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                '}';
    }
}

