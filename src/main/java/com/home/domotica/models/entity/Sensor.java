package com.home.domotica.models.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Sensor implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", length = 60)
    private String name;
    @Column(name = "description", length = 200)
    private String description;
    @Column(name = "value")
    private long value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }
}
