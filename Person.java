package com.myserializable.serializ;

public class Person {
    public String name;
    public String age;

    public String method()
    {
        return "数据流中的方法";
    }
    @Override
    public String toString() {
        return "一个数据流中的class文件";
    }
}