package com.eureka.model;

import java.io.Serializable;

/**
 * Created by NCP-605 on 2017/7/5.
 */
public class User implements Serializable{
    private static final long serialVersionUID = 1717420842011846358L;
    private String id;
    private String name;
    private Integer age;

    public User(){}
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}
