package com.example.demo.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by gaonl on 2018/9/28.
 */
public class User {

    public static final int INT_31 = 31;

    private Integer id;
    private String name;
    private String password;
    private Long registerDateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Long getRegisterDateTime() {
        return registerDateTime;
    }

    public void setRegisterDateTime(Long registerDateTime) {
        this.registerDateTime = registerDateTime;
    }

    /**
     * 获取用户的注册时间
     * @return 用户的注册时间
     */
    public String getDisplayRegisterDateTime() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return df.format(this.registerDateTime);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        if (!id.equals(user.id)) {
            return false;
        }
        if (!name.equals(user.name)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = INT_31 * result + name.hashCode();
        return result;
    }

    private static final String JSON_FMT = "{\"id\":%s,\"name\":\"%s\"}";

    @Override
    public String toString() {
        return String.format(JSON_FMT, String.valueOf(id), String.valueOf(name));
    }
}
