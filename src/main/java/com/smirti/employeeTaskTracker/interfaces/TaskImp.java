package com.smirti.employeeTaskTracker.interfaces;

import com.smirti.employeeTaskTracker.model.Task;
import com.smirti.employeeTaskTracker.utilies.DbConfig;
import com.smirti.employeeTaskTracker.utilies.TaskTracker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class TaskImp implements TaskInterface  {

    private Connection connection;
    private boolean connectionError;

    public TaskImp(){
        try{
            this.connection = DbConfig.getdbConnection();
            this.connectionError =false;

        } catch (Exception e) {
            this.connectionError =true;
            System.out.println("Connection Error: ");

        }
    }

    @Override
    public ArrayList<Task> getAllTracker() {

        if(this.connectionError){
            return null;
        }else{
            ArrayList<Task> tracker = new ArrayList<>();
            try{
                try(Statement stm = this.connection.createStatement();
                    ResultSet rs = stm.executeQuery("Select t.id, e.employeeId, t.task_name, t.status "+
                            "FROM task t JOIN employee e on t.employeeId = e.id")){

                    while(rs.next()){
                        tracker.add(new Task(rs.getInt("id"),rs.getInt("employeeId"),
                                rs.getString("taskName"), rs.getString("status")) );
                    }

                }

            } catch (Exception e) {

            }
        }
        return null;
    }

    @Override
    public boolean assignTask(Task task) {
        if(this.connectionError){
            return false;
        }else{
            boolean var4;
            try{
                try(PreparedStatement ps = this.connection.prepareStatement("insert into task (employeeId, taskName, status ) values(?,?,?)")){

                    ps.setInt(1,task.getEmployeeId());
                    ps.setString(2,task.getTaskName());
                    ps.setString(3, task.getStatus());
                    var4 = ps.executeUpdate()>0;

                }
                return var4;

            } catch (Exception e) {
                System.out.println("Error "+e.getMessage());
                return false;

            }finally {
                DbConfig.closeConnection(connection);
            }
        }

    }

    @Override
    public boolean updateTaskStatus(int id, String status) {
        if(this.connectionError){
            return false;
        }

        try(PreparedStatement ps = this.connection.prepareStatement(
                "UPDATE task set status =? where id =?")){
            ps.setString(1,status);
            ps.setInt(2,id);
            return ps.executeUpdate()>0;

        } catch (Exception e) {
            System.out.println("Error: "+ e.getMessage());
            return false;

        }


    }

    @Override
    public boolean deleteTask(int id) {
        if(this.connectionError){
            return false;
        }
        try(PreparedStatement ps = this.connection.prepareStatement(
                "DELETE FROM task WHERE  id=?")){
            ps.setInt(1,id);
            return ps.executeUpdate()>0;


        } catch (Exception e) {
            System.out.println("error "+e.getMessage());
            return false;


        }
    }
}
