package tables;

/**
 * Created by 7 on 20.08.2016.
 */
public class Menu extends Table{

    private int ID;
    private int listOfDishes_ID;
    private String name;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getListOfDishes_ID() {
        return listOfDishes_ID;
    }

    public void setListOfDishes_ID(int listOfDishes_ID) {
        this.listOfDishes_ID = listOfDishes_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "ID=" + ID +
                ", listOfDishes_ID=" + listOfDishes_ID +
                ", name='" + name + '\'' +
                '}';
    }
}
