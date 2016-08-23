package tables;

import java.util.Date;

/**
 * Created by 7 on 12.08.2016.
 */
public class CookedDishes extends Table{

    private int ID;
    private int dishID;
    private int employeeID;
    private int orderID;
    private Date date;


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getDishID() {
        return dishID;
    }

    public void setDishID(int dishID) {
        this.dishID = dishID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "CookedDishes{" +
                "ID=" + ID +
                "dishID=" + dishID +
                ", employeeID=" + employeeID +
                ", orderID=" + orderID +
                ", date='" + date + '\'' +
                '}';
    }
}
