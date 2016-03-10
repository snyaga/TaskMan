package com.project.mytask.taskman.model;

/**
 * Created by Wanjiru Nyaga on 10-Mar-2016.
 */
public class Reminder {
    int Id;
    String name;
    int time;

    public Reminder(){
    }
    public Reminder(int Id, String name, int time){
        this.Id = Id;
        this.name = name;
        this.time = time;
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

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
