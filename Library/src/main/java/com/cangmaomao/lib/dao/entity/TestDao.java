package com.cangmaomao.lib.dao.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class TestDao {

    @Id
    private long id;

    private String name;

    private int age;

    @Generated(hash = 1309126619)
    public TestDao(long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Generated(hash = 1692582752)
    public TestDao() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
