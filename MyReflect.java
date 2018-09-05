package com.myserializable.serializ;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MyReflect {
    public static Object getObj(Object object, String strObj) {
        String[] strArr = null;
        Class obj = (Class) object.getClass();//reportVo1是一个实体类
        if (strObj != null) {
            strArr = strObj.split(",");
        } else {
            return obj;
        }
        Field[] fs = obj.getDeclaredFields();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < fs.length; i++) {
            Field f = fs[i];
            f.setAccessible(true); //设置些属性是可以访问的
            String val = f.getName();//得到此属性的名称

            try {
                //得到属性的实例
                Object fInstance=f.get(object);
                //如果得到的属性是静态的则不能使用实例对象进行或取，应使用Class对象进行获取，obj是Class类的对象
                //Object fStatic=f.get(obj);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            for (int j = 0; j < strArr.length; j++) {
                if ((strArr[j].indexOf(val + ":") != -1)) {
                    String str = strArr[j].substring(strArr[j].indexOf(":") + 1);

                    try {
                        f.set(object, str);
                    } catch (java.lang.IllegalAccessException e) {
                        System.out.println(e.getMessage());
                    } finally {

                    }
                }

            }
        }
        return object;
    }
    //如果要得到一个类的静态属性要使用的是类的名称，不能使用类的实例
    public Object getStaticProperty(String className, String fieldName)
            throws Exception {
        Class ownerClass = Class.forName(className);

        Field field = ownerClass.getField(fieldName);

        Object property = field.get(ownerClass);

        return property;
    }

    //执行某个类中的某个方法
    public static void execMethod(String cName,String mName,Object... param)
    {
        try {
            Class cla=Class.forName(cName);
            Method method=cla.getMethod(mName,param.getClass());
            //如果有返回值可以用Object来接收method.invoke的返回值
            method.invoke(cla.newInstance(),param);
            //如果调用的是静态方法第一个参数设置为null即可
            //method.invoke(null,param);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

}
