package com.youfu.sbdemo.domain;

import java.io.Serializable;

public class Teacher implements Serializable {

    private static final long serialVersionUID = 8751282105532159742L;

    private Integer id;
    private String wechat;
    private String name;

    public Teacher() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public Integer getId() {
        return id;
    }
}
