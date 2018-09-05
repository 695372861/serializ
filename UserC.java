package com.myserializable.serializ;

//实现Cloneable接口来进行引用类型的深拷贝(克隆)在拷贝对象的时候调用重写的clone()方法进行对象的复制，
//Cloneable是一个空的标记接口，重写的clone方法来自Object类
public class UserC implements Cloneable {
    private String name;
    private User user;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        UserC userC=null;
        userC=(UserC) super.clone();
        userC.user=(User) user.clone();
        return userC;
    }
}
