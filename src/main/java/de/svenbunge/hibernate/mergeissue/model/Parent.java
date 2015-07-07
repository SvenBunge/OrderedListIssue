package de.svenbunge.hibernate.mergeissue.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Parent {

    @Id
    @GeneratedValue
    private long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @OrderColumn
    private List<Entry> myList = new ArrayList<>();

    public long getId() {
        return id;
    }

    public List<Entry> getMyList() {
        return myList;
    }

    public void setMyList(List<Entry> myList) {
        this.myList = myList;
    }
}
