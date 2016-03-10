package com.project.mytask.taskman.model;

/**
 * Created by Wanjiru Nyaga on 10-Mar-2016.
 */
public class Client {
    int Id;
    String name;
    String email;

    public Client(){
    }

    public Client(int Id, String name, String email){
        this.Id = Id;
        this.name = name;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

