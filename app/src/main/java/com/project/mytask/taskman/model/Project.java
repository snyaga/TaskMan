package com.project.mytask.taskman.model;

/**
 * Created by Wanjiru Nyaga on 10-Mar-2016.
 */
public class Project {
    int Id;
    String name;
    int totalTime;

    public Project() {
    }

    public Project(int Id, String name, int totalTime) {
        this.Id = Id;
        this.name = name;
        this.totalTime = totalTime;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


