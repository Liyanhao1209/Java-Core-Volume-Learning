package com.ch5.Reflection;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Random;
import com.ch5.Reflection.Entity.Employee;

public class Reflection_Class {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Employee e = new Employee("e",new Date());
        Class<? extends Employee> cl = e.getClass();

        //getName返回类名
        System.out.println(cl.getName());

        //类方法forName获取类名对应Class对象
        String className = "java.util.Random";
        Class<?> aClass = Class.forName(className);

        //Class对象表示的是类型，不是类，可能是类也可能不是
        Class<Random> randomClass = Random.class;
        Class<Integer> integerClass = int.class;
        Class<Double[]> doubleClass = Double[].class;

        //检查类型时，getClass仅当前类才可能通过，但instanceOf子类也可以通过
        if(cl==Employee.class){
            System.out.println(1);
        }
        else if(e instanceof Employee){
            System.out.println(2);
        }

        //getConstructor得到Constructor对象，newInstance构造实例
        Random random = randomClass.getConstructor().newInstance();

    }
}
