package com.smirti.employeeTaskTracker.utilies;
import com.smirti.employeeTaskTracker.interfaces.EmployeeImp;
import com.smirti.employeeTaskTracker.interfaces.TaskImp;
import com.smirti.employeeTaskTracker.model.Employee;
import com.smirti.employeeTaskTracker.utilies.DbConfig;
import com.smirti.employeeTaskTracker.model.Task;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskTracker {

    static Scanner sc = new Scanner(System.in);

    public static void main(String args[]){
        DbConfig.initializedDatabase();
        EmployeeImp employee = new EmployeeImp();
        TaskImp task = new TaskImp();

        while(true){
            System.out.println("--EmployeeTaskList--");
            System.out.println("1. View All Employees");
            System.out.println("2. Add an Employee");
            System.out.println("3. View Employee tasks");
            System.out.println("4. Add task ");
            System.out.println("5. update task status");
            System.out.println("6. Exist");
            System.out.println("Choose");

            String choice = sc.nextLine().trim();

            switch(choice){
                case "1":
                    ArrayList<Employee> emp = employee.getAllEmployee();
                    if(emp == null){
                        System.out.println("Error retrieving Employee. ");
                    }else if(emp.isEmpty()){
                        System.out.println("No employee found");
                    }else{
                        for(Employee em : emp)
                            System.out.println(em.getName()+", "+ em.getDepartment());
                    }

                    break;

                case "2":
                    System.out.println("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.println("Enter Department ");
                    String department = sc.nextLine();
                    System.out.println(employee.addEmployee(new Employee(0,name,department)));
                    break;
                case "3":
                    ArrayList<Task> ta = task.getAllTracker();
                    if(ta == null){
                        System.out.println("Error retrieving task");
                    } else if (ta.isEmpty()) {
                        System.out.println("No task is found");

                    }else{
                        for(Task tas : ta)
                            System.out.println(tas.getEmployeeId()+", "+tas.getTaskName()+", "+tas.getStatus());
                    }

                case "4":
                    System.out.println("Enter task name: ");
                    String tasks = sc.nextLine();
            }




        }
    }




}
