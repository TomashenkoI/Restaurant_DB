import DAO.*;

import java.util.Scanner;

/**
 * Created by 7 on 12.08.2016.
 */
public class Main {

    static EmployeeDAO employeeDAO =  new EmployeeDAO();
    static DishesDAO dishesDAO = new DishesDAO();
    static ListOfIngredientsDAO listOfIngredientsDAO = new ListOfIngredientsDAO();
    static ListOfDishesDAO listOfDishesDAO = new ListOfDishesDAO();
    static MenuDAO menuDAO = new MenuDAO();
    static StorageDAO storageDAO = new StorageDAO();


    public static void main(String[] args) {

//        storageDAO.addNewPosition();
        storageDAO.deletePositionByID();

//        listOfDishesDAO.deletePositionByID();
//        listOfDishesDAO.addNewPosition();
//        menuDAO.deletePositionByID();
//        menuDAO.addNewPosition();
//        listOfIngredientsDAO.deletePositionByID();
//        dishesDAO.showAllPositions();
//        dishesDAO.addNewPosition();
//        dishesDAO.deletePositionByID();
//        listOfIngredientsDAO.addNewPosition();
//        loadDriver();
//        dishesDAO.findPositionByName(enteredString());
//        dishesDAO.showAllPositions();
//        employeeDAO.deleteEmployeeByID();
//        employeeDAO.addNewPosition();
//        employeeDAO.getAll().forEach(System.out::println);
//        employeeDAO.findEmployeeByName(enteredString()).forEach(System.out::println);
    }

    private static void loadDriver(){
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Cannot find driver");
            e.printStackTrace();
        }
    }

    public static void execution() {
        System.out.println("Выбирите раздел : ");
        System.out.println("Сотрудники : 1" +
                            "Блюда : 2" +
                            "Меню : 3" +
                            "Заказы : 4" +
                            "Журнал блюд : 5" +
                            "Склад : 6");
        int number = Integer.parseInt(new Scanner(System.in).nextLine());

        switch (number) {
            case 1 :
        }
    }

}