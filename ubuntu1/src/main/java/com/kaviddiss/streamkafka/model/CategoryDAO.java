package com.kaviddiss.streamkafka.model;

import java.io.Serializable;

public class CategoryDAO implements Serializable {

    private Long id;
    private String name;

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

    @Override
    public String toString() {
        String[] s = this.getClass().toString().split("\\.");
        String classname = s[s.length - 1];

        return classname + " id:" + id.toString() + ", " + "name='" + name;
    }


}
