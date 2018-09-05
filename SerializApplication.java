package com.myserializable.serializ;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

@SpringBootApplication
public class SerializApplication {

	public static void main(String[] args) {
		SpringApplication.run(SerializApplication.class, args);
		//serializable();
		//		unSerializable();
		//使用的时候将Person类放在C盘跟目录，将所在包的那行代码进行删除进行删除，并且用javac进行编译，
		testClassLoader();
	}

	public static void serializable()
	{
		User user=new User();
		user.setName("hollis");
		user.setAge(23);
		user.setGender("male");
		User.nullName="sss";
		user.setBirthday(new Date());
		System.out.println(user.toString());

		ObjectOutputStream oos=null;
		try {
			oos=new ObjectOutputStream(new FileOutputStream("tempfile"));
			oos.writeObject(user);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(oos!=null)
			{
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public static void unSerializable()
	{
		File file =new File("tempfile");
		ObjectInputStream ois=null;
		try {
			ois=new ObjectInputStream(new FileInputStream(file));
			User newUer=(User)ois.readObject();
			System.out.println(newUer.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			if(ois!=null)
			{
				try {
					ois.close();

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public static void testClassLoader()
	{
		MyClassLoader mcl = new MyClassLoader("C:/Person.class");
		Class<?> clazz = null;
		try {
			clazz = Class.forName("Person", true, mcl);
			Object obj = clazz.newInstance();
			Method method=clazz.getMethod("method");
			//调用流中类的toString方法
			System.out.println(obj);
			//调用流的类的某个方法名的方法
			System.out.println(method.invoke(obj));
			//得到类加载器的位置
			System.out.println(obj.getClass().getClassLoader());//打印出我们的自定义类加载器
			System.out.println(obj.getClass());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

	}
}
