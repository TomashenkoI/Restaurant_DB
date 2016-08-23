package tables;

import java.util.Date;

/**
 * Created by 7 on 12.08.2016.
 */
public class Orders extends Table{

    private int ID;
    private int employeeID;
    private int listOfDishesID;
    private int tableNumber;
    private Date date;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }


    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public int getListOfDishesID() {
        return listOfDishesID;
    }

    public void setListOfDishesID(int listOfDishesID) {
        this.listOfDishesID = listOfDishesID;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "ID=" + ID +
                ", employeeID=" + employeeID +
                ", listOfDishesID=" + listOfDishesID +
                ", tableNumber=" + tableNumber +
                ", date='" + date + '\'' +
                '}';
    }
}
