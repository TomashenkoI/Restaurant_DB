package DAO;

import tables.Employee;
import tables.Positions;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

import static DAO.Requests.*;

/**
 * Created by 7 on 20.08.2016.
 */
public class EmployeeDAO implements TableDAO<Employee> {

    private DataSource dataSource;

    public static int MAX_ID;


    public void enteringInformation(Statement statement) throws SQLException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Выбирите номер позиции :");
        int position = Integer.parseInt(scanner.nextLine());

        System.out.println("Введите имя :");
        String firstName = scanner.nextLine();

        System.out.println("Введите фамилию :");
        String lastName = scanner.nextLine();

        System.out.println("Введите дату рождения (dd.mm.yyyy) :");
        String dateOfBirth = scanner.nextLine();

        System.out.println("Введите номер телефона :");
        String phoneNumber = scanner.nextLine();

        System.out.println("Введите зарплату :");
        double salary = Double.parseDouble(scanner.nextLine());

        int maxID = ++MAX_ID;
        String sql = "INSERT INTO employees VALUES (" + maxID +", '" + firstName + "', '" + lastName + "', '" +
                    dateOfBirth +  "', '" + phoneNumber + "', " + position + ", " + salary + ")";

        statement.execute(sql);
    }

    public void addNewPosition(){
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            String sql = "SELECT MAX(ID) FROM employees";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                MAX_ID = resultSet.getInt(1);
            }
            sql = "SELECT * FROM positions";
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Positions positions = PositionsDAO.createPosition(resultSet);
                System.out.println(positions.toString());
            }
                enteringInformation(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showAllPositions() {

        ArrayList<Employee> employees = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            String sql = "SELECT * FROM employees";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Employee employee = createObject(resultSet);
                employees.add(employee);
            }
            employees.forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Exception occured while connecting to DB : " + e);
            throw new RuntimeException(e);
        }

    }

    public void findPositionByName(String name) {

        ArrayList<Employee> employees = new ArrayList<>();

            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                Statement statement = connection.createStatement()){
                String sql = "SELECT * FROM employees WHERE firstName LIKE '%" + name + "%'";
                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {
                    Employee employee = createObject(resultSet);
                    employees.add(employee);
                }

                if (!employees.isEmpty()) {
                    System.out.println("Список сотрудников с именем = '" + name + "' :");
                } else {
                    System.out.println("Нет совпадений по данному запросу!");
                }
                employees.forEach(System.out::println);
            } catch (SQLException e1) {
                e1.printStackTrace();
                throw new RuntimeException();
            }

    }


    public Employee createObject(ResultSet resultSet) throws SQLException {

        Employee employee = new Employee();

        employee.setID(resultSet.getInt("ID"));
        employee.setPosition(resultSet.getInt("position_ID"));
        employee.setFirstName(resultSet.getString("firstName"));
        employee.setLastName(resultSet.getString("lastName"));
        employee.setDateOfBirth(resultSet.getString("dateOfBirth"));
        employee.setPhoneNumber(resultSet.getString("phoneNumber"));
        employee.setSalary(resultSet.getDouble("salary"));

        return employee;

    }

    public void deletePositionByID() {

        showAllPositions();

            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                Statement statement = connection.createStatement()) {

                System.out.println("Введите номер сотрудника для удаления: ");
                String enteredString = new Scanner(System.in).nextLine();

                String sql = "DELETE FROM employees WHERE id = " + enteredString;
                statement.executeQuery(sql);
                System.out.println("Сотрудник удален!");
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
    }

    public void showAllCooks() {

        ArrayList<Employee> employees = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            String sql = "SELECT * FROM employees WHERE position_id = 2";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Employee employee = createObject(resultSet);
                employees.add(employee);
            }
            employees.forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Exception occured while connecting to DB : " + e);
            throw new RuntimeException(e);
        }
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
