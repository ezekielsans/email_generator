

import java.util.List;
import java.util.Scanner;


import employeeDAO.EmployeeDao;
import model.Employee;

public class Main {

    /*
     * Details of the employee
     * Validating if user email already exist
     *
     * */


    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);


        System.out.println("Enter First Name: ");
        String firstName = scan.next();


        System.out.println("Enter Last Name: ");
        String lastName = scan.next();


        EmployeeDao employeeDao = new EmployeeDao();

        final String emailId = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@gmail.com";
        List<Employee> employeeList = employeeDao.getEmployee();

        Employee emp = checkEmailExist(employeeList, emailId);
        if (emp == null) {
            Employee employee = new Employee(firstName, lastName, emailId);
            employeeDao.saveEmployee(employee);
            System.out.println("Email: " + emailId + "generated! ");

        } else {

            System.out.println("Default " + emailId + " exist. Please provide a unique email id");

            System.out.println("Enter a unique User Email: ");
            String email = "@gmail.com";
            String newEmail = scan.next().toLowerCase() + email;


            //upon re-entering it will generate your new email

            while (true) {
       
                emp = checkEmailExist(employeeList, emailId);
                if (emp != null) {
                    Employee employee = new Employee(firstName, lastName, newEmail);
                    employeeDao.saveEmployee(employee);
                    System.out.println("Email: " + newEmail + " generated!");
                    break;
                } else {

                    System.out.println("tatanungin ka ulit ");
                    System.out.println(newEmail + " exist.Please provide an unique email id");
                    newEmail = scan.next();
                }
            }
        }
        scan.close();

    }

    static Employee checkEmailExist(List<Employee> employeeList, String email) {
        Employee emp = employeeList.stream().filter(e -> email.equals(e.getEmail())).findAny().orElse(null);


        return emp;
    }


}
