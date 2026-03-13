package com.smirti.employeeTaskTracker.interfaces;

import com.smirti.employeeTaskTracker.model.Employee;

import java.util.ArrayList;

public interface EmployeeInterface {

    public ArrayList<Employee> getAllEmployee();

    public boolean addEmployee(Employee emp);
}
