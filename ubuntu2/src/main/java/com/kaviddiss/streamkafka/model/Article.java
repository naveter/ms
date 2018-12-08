package com.kaviddiss.streamkafka.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name="article", schema = "public")
public class Article {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name="user_id")
    private Long user_id;

    @NotNull
    @Column(name="cat_id")
    private Long cat_id;

    @NotNull
    @Column(name="title")
    private String title;

    @NotNull
    @Column(name="body")
    private String body;

    @NotNull
    @Column(name="created")
    private Date created;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getCat_id() {
        return cat_id;
    }

    public void setCat_id(Long cat_id) {
        this.cat_id = cat_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Article entity = (Article) o;

        return id == entity.getId();
    }

    @Override
    public int hashCode() {
        return (int) (long) id;
    }

    @Override
    public String toString() {
        String[] s = this.getClass().toString().split("\\.");
        String classname = s[s.length - 1];

        return classname + " id=" + id.toString() + ", " + "title=" + title
                + ", catId=" + cat_id + ", userId=" + user_id;
    }

}
