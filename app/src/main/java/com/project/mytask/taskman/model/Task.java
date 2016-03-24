package com.project.mytask.taskman.model;

/**
 * Created by Wanjiru Nyaga on 10-Mar-2016.
 */
public class Task {
    int Id;
    String name;
    String description;
    int date;
    int startTime;
    int endTime;
    int duration;
    int projectIdFK;

    public Task(){
    }

    public Task(int Id, String name, String description, int date, int startTime, int endTime, int duration, int projectIdFK){
        this.Id = Id;
        this.name = name;
        this.description = description;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
        this.projectIdFK = projectIdFK;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public int getProjectIdFK() {
        return projectIdFK;
    }

    public void setProjectIdFK(int projectIdFK) {
        this.projectIdFK = projectIdFK;
    }
}

