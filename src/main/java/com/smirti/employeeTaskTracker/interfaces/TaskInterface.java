package com.smirti.employeeTaskTracker.interfaces;

import com.smirti.employeeTaskTracker.model.Task;
import com.smirti.employeeTaskTracker.utilies.TaskTracker;

import java.util.ArrayList;

public interface TaskInterface {

    public ArrayList<Task> getAllTracker();
    public boolean assignTask(Task task);
}
