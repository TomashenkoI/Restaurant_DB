package DAO;

import java.util.Scanner;

/**
 * Created by 7 on 22.08.2016.
 */
public class Requests {

    public static final String URL = "jdbc:postgresql://localhost:5432/restaurant";
    public static final String USER = "postgres";
    public static final String PASSWORD = "1111";

    public static String enteredString() {

        System.out.println("Введите запрос :");
        String enteredString = new Scanner(System.in).nextLine();
        return enteredString;

    }

}
