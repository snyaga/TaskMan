package com.project.mytask.taskman.model;

/**
 * Created by Wanjiru Nyaga on 10-Mar-2016.
 */
public class User {

    int Id;
    String name;
    String email;
    String password;

    public  User(){
    }

    public User(int Id, String name, String email, String password){
        this.Id = Id;
        this.name = name;
        this.email = email;
        this.password = password;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
