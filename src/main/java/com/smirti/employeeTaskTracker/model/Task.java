package com.smirti.employeeTaskTracker.model;

public class Task {
    private int id;
    private int employeeId;
    private String taskName;
    private String status;

    public Task(int id, int employeeId, String taskName, String status) {
        this.id = id;
        this.employeeId = employeeId;
        this.taskName = taskName;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String toString(){
        return "[" + id + "] " + employeeId + "TaskName (: " + taskName + ")" +"Status: "+ status +")";
    }
}
