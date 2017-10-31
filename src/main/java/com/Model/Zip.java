package com.Model;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name="zip")
public class Zip {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String name;
    private LocalDateTime creationDate;

    @OneToMany(mappedBy = "zip", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Doc> docs;

    public Zip(){
        name = null;
        creationDate = null;
        docs = new HashSet<>();

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
        if(n!=null){
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

    public void setProducts(Set<Doc> d){
        docs = d;
    }

    public Set<Doc> getProducts(){
        return docs;
    }

    public void AddDoc(Doc d){
        docs.add(d);
    }

    public void RemoveDoc (Doc d){
        docs.remove(d);
    }

    public void RemoveDoc(Integer idD){
        docs.removeIf(b-> b.getId() == idD);
    }

    public Doc FindDocByID(Integer idD){
               
        for (Doc d : docs) {
            if (d.getId() == idD) return d;
        }
        return null;
    }

    public void setCurrentCreationDate(){
        if(creationDate==null){
            creationDate= LocalDateTime.now();
        }
    }
}
