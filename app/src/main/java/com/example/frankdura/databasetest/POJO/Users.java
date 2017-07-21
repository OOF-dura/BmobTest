package com.example.frankdura.databasetest.POJO;

import cn.bmob.v3.BmobObject;

/**
 * Created by frankdura on 2017/7/20.
 */

public class Users extends BmobObject {
    private String id; //学号
    private String password; //
    private String name;
    private String realname;
    private String email;
    private String work; //用户类型 ： 学生 老师  工作人员


    public Users() {
        setTableName("Users");
    }


    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getWork() {

        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
