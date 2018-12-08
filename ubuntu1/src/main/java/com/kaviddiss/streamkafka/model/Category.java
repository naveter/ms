package com.kaviddiss.streamkafka.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="category", schema = "public")
public class Category {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name="name")
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category entity = (Category) o;

        return id == entity.id;
    }

    @Override
    public int hashCode() {
        return (int) (long) id;
    }

    @Override
    public String toString() {
        String[] s = this.getClass().toString().split("\\.");
        String classname = s[s.length - 1];

        return classname + " id:" + id.toString() + ", " + "name=" + name;
    }

}
