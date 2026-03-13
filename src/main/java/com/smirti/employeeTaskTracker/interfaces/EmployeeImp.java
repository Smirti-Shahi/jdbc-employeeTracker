package com.smirti.employeeTaskTracker.interfaces;

import com.smirti.employeeTaskTracker.model.Employee;
import com.smirti.employeeTaskTracker.utilies.DbConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class EmployeeImp implements EmployeeInterface {

    private Connection connection;
    private boolean connectionError;

    public EmployeeImp(){
        try{
            this.connection = DbConfig.getdbConnection();
            this.connectionError =false;

        } catch (Exception e) {
            this.connectionError =true;
            System.out.println("connection failed: " +e.getMessage());


        }
    }

    @Override
    public ArrayList<Employee> getAllEmployee() {
        if(this.connectionError){
            return null;
        }else{
            ArrayList<Employee> employee = new ArrayList<>();
            try{
                try(Statement stm = this.connection.createStatement();
                    ResultSet rs = stm.executeQuery("select * from employee");

                        ){

                    while(rs.next()){
                        employee.add(new Employee(rs.getInt("id"), rs.getString("name"), rs.getString("department")));
                    }

                }
                return null;

            } catch (Exception e) {
                System.out.println("Error: "+e.getMessage());
                return null;

            }
            finally {
                DbConfig.closeConnection(connection);
            }
        }

    }

    @Override
    public boolean addEmployee(Employee emp) {
        if(this.connectionError){
            return false;
        }else{
            boolean var3;
            try{
                try(PreparedStatement ps = this.connection.prepareStatement("insert into employee(name, department) values (?,?)")){
                    ps.setString(1, emp.getName());
                    ps.setString(2, emp.getDepartment());
                    var3 = ps.executeUpdate() >0;

                }

                return var3;

            } catch (Exception e) {
                System.out.println("error: "+e.getMessage());
                return false;
            }finally {
                DbConfig.closeConnection(connection);
        }
        }
    }


}
