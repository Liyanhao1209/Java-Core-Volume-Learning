package com.ch5.Reflection;

import com.ch5.Reflection.Entity.Employee;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

public class Reflection_CallAnyMethodAndConstructor {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Employee harry = new Employee("Harry Porter", 1000.0,new Date());
        Class<? extends Employee> cl = harry.getClass();
        Method getName = cl.getDeclaredMethod("getName");
        //Object invoke(Object obj,Object... args)
        String name = (String) getName.invoke(harry);
        System.out.println(name);
        //还有个getMethod方法，Method getMethod(String name,Class... parameterTypes)
        Method raiseSalary = cl.getMethod("raiseSalary", double.class);
        System.out.println(harry.getSalary());
        raiseSalary.invoke(harry,0.25);
        System.out.println(harry.getSalary());
    }
}
