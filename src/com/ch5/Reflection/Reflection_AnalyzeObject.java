package com.ch5.Reflection;

import com.ch5.Reflection.Entity.Employee;

import java.lang.reflect.Field;
import java.util.Date;

public class Reflection_AnalyzeObject {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

        Employee harry = new Employee("Harry Porter", new Date());
        Class<? extends Employee> cl = harry.getClass();
        Field field = cl.getDeclaredField("name");
        //get将返回一个对象，其值为obj的当前字段值
        //将抛出异常，因为name字段是private的
        //可以通过setAccessible来覆盖java的访问控制
        field.setAccessible(true);
        Object o = field.get(harry);
        System.out.println(o.toString());//Harry Porter
        field.set(harry,"porter harry");
        System.out.println(harry.getName());//porter harry
    }
}
