package com.project.mytask.taskman.model;

/**
 * Created by Wanjiru Nyaga on 10-Mar-2016.
 */
public class Project {
    int Id;
    String name;
    int totalTime;
    int clientIdFK;

    public Project() {
    }

    public Project(int Id, String name, int totalTime, int clientIdFK) {
        this.Id = Id;
        this.name = name;
        this.totalTime = totalTime;
        this.clientIdFK = clientIdFK;
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

    public int getClientIdFK() {
        return clientIdFK;
    }

    public void setClientIdFK(int clientIdFK) {
        this.clientIdFK = clientIdFK;
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return name;
    }
}


