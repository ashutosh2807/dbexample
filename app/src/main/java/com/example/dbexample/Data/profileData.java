package com.example.dbexample.Data;

import kotlinx.coroutines.Job;

public class profileData {
    int ID;
    String Name;
    int Age;
    String Phone;
    String JobDesc;

    public profileData(int ID, String Name,String Phone, int Age,  String JobDesc) {
        this.ID = ID;
        this.Name = Name;
        this.Age = Age;
        this.Phone = Phone;
        this.JobDesc = JobDesc;
    }

    public int getProfileID(){
        return  this.ID;
    }
    public String getPhone(){
        return  this.Phone;
    }
    public String getName(){
        return  this.Name;
    }
    public int getAge(){
        return this.Age;
    }

    public String getJobDesc(){
        return  this.JobDesc;
    }
}
