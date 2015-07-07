package de.svenbunge.hibernate.mergeissue.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Entry {

    @Id
    @GeneratedValue
    private long id;

    private String value;

    protected Entry() {
    }

    public Entry(String value) {
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
