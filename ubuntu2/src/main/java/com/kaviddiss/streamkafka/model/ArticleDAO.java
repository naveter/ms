package com.kaviddiss.streamkafka.model;

import java.io.Serializable;
import java.util.Date;

public class ArticleDAO implements Serializable {

    private Long id;
    private Long user_id;
    private Long cat_id;
    private String title;
    private String body;
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
    public String toString() {
        String[] s = this.getClass().toString().split("\\.");
        String classname = s[s.length - 1];

        return classname + " id=" + id.toString() + ", " + "title=" + title
                + ", catId=" + cat_id + ", userId=" + user_id;
    }
}
