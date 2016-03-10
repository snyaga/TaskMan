package com.project.mytask.taskman.model;

/**
 * Created by Wanjiru Nyaga on 10-Mar-2016.
 */
public class Expense {
    int Id;
    String description;
    int amount;

    public Expense(){
    }

    public Expense(int Id, String description, int amount){
        this.Id = Id;
        this.description = description;
        this.amount = amount;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
