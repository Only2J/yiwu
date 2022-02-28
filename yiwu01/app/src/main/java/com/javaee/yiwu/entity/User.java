package com.javaee.yiwu.entity;

import cn.bmob.v3.BmobUser;

public class User extends BmobUser {
    //BmobUser中已近写了username、password、email、mobilePhoneNumber等属性
    //所以在这里我们继承过来
    //如果需要其他属性，在这里也可以写，比如：qq
    private String qq;

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }
}
