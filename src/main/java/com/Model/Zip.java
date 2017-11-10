package com.Model;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name="zip")
public class Zip {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    private String name;
    private LocalDateTime creationDate;

    @OneToMany(mappedBy = "zip", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public void setDocs(Set<Doc> d){
        docs = d;
    }

    public Set<Doc> getDocs(){
        return docs;
    }

    public ArrayList<Doc> getOrderedDocs(){
        ArrayList<Doc> aD = new ArrayList<>();
        aD.addAll(docs);
        aD.sort((a, b)-> (int)(a.getId() - b.getId()));
        return  aD;
    }

    public void addDoc(Doc d){
        docs.add(d);
    }

    public void removeDoc (Doc d){
        docs.remove(d);
    }

    public void removeDoc(long idD){
        docs.removeIf(b-> b.getId() == idD);
    }

    public Doc findDocByID(long idD){

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
