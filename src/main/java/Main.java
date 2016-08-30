import DAO.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

/**
 * Created by 7 on 12.08.2016.
 */
public class Main {

    private EmployeeDAO employeeDAO;
    static DishesDAO dishesDAO = new DishesDAO();
    static OrdersDAO ordersDAO = new OrdersDAO();
    static ListOfIngredientsDAO listOfIngredientsDAO = new ListOfIngredientsDAO();
    static ListOfDishesDAO listOfDishesDAO = new ListOfDishesDAO();
    static MenuDAO menuDAO = new MenuDAO();
    static StorageDAO storageDAO = new StorageDAO();
    static Requests requests = new Requests();

    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
        Main main = applicationContext.getBean("main", Main.class);
        main.execution();

    }

    public void execution() {

        Scanner scanner = new Scanner(System.in);
        boolean flag = true;

        System.out.println();
        System.out.println("Выбирите раздел : ");
        System.out.println("1 : Сотрудники" + "\n" +
                "2 : Блюда" + "\n" +
                "3 : Меню" + "\n" +
                "4 : Заказы " + "\n" +
                "5 : Журнал блюд" + "\n" +
                "6 : Склад" + "\n" +
                "0 : Выйти из приложения ");
        int number = Integer.parseInt(scanner.nextLine());

        while (flag) {
            switch (number) {
                case 1:
                    System.out.println("Выбирите действие :" + "\n" +
                            "1 : посмотреть всех сотрудников" + "\n" +
                            "2 : добавить сотрудника " + "\n" +
                            "3 : удалить сотрудника" + "\n" +
                            "4 : найти сотрудника по имени" + "\n" +
                            "0 : выход");
                    number = Integer.parseInt(scanner.nextLine());
                    flag = switchCases(flag, number, employeeDAO);
                    break;
                case 2:
                    System.out.println("Выбирите действие :" + "\n" +
                            "1 : посмотреть все блюда" + "\n" +
                            "2 : добавить блюдо " + "\n" +
                            "3 : удалить блюдо" + "\n" +
                            "4 : найти блюдо по названию" + "\n" +
                            "0 : выход");
                    number = Integer.parseInt(scanner.nextLine());
                    flag = switchCases(flag, number, dishesDAO);
                    break;
                case 3 :
                    System.out.println("Выбирите действие :" + "\n" +
                            "1 : посмотреть все меню" + "\n" +
                            "2 : добавить меню " + "\n" +
                            "3 : удалить меню" + "\n" +
                            "4 : найти меню по названию" + "\n" +
                            "0 : выход");
                    number = Integer.parseInt(scanner.nextLine());
                    flag = switchCases(flag, number, menuDAO);
                    break;
                case 4 :
                    System.out.println("Выбирите действие :" + "\n" +
                            "1 : посмотреть все заказы" + "\n" +
                            "2 : добавить меню " + "\n" +
                            "3 : удалить меню" + "\n" +
                            "4 : найти меню по названию" + "\n" +
                            "0 : выход");
                    number = Integer.parseInt(scanner.nextLine());
                    flag = switchCases(flag, number, ordersDAO);
                    break;
                case 5 :
                    System.out.println();
                    break;
            }
        }

    }

    private static boolean switchCases(boolean flag, int number, TableDAO tableDAO) {
        switch (number) {
            case 1:
                tableDAO.showAllPositions();
                break;
            case 2:
                tableDAO.addNewPosition();
                break;
            case 3:
                tableDAO.deletePositionByID();
                break;
            case 4:
                tableDAO.findPositionByName(requests.enteredString());
                break;
            case 0:
                flag = false;
        }
        return flag;
    }

}