package com.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Zip {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String name;
    private LocalDateTime creationDate;

    public Zip(){
        name = null;
        creationDate = null;
    }

    //public Zip(String n){
//
    //    name = n;
    //    creationDate  = LocalDateTime.now();
    //}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String n){
        if(name==null){
            name = n;
        }
    }

    public LocalDateTime getCreationDate(){
        return creationDate;
    }

    public void setCreationDate(LocalDateTime d){
        if(creationDate==null){
            creationDate= d;
        }
    }

    public void setCurrentCreationDate(){
        if(creationDate==null){
            creationDate= LocalDateTime.now();
        }
    }
}
