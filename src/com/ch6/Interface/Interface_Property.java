package com.ch6.Interface;

import com.ch6.Interface.Entity.Employee;

import java.util.Date;

public class Interface_Property {
    public static void main(String[] args) {
        //不能使用new实例化接口
//        Comparable<Object> objectComparable = new Comparable<>();//error
        //但可以声明接口的变量
        Comparable x;
        //该变量引用实现该接口的类对象
        x = new Employee("harry",new Date());
        //可以使用instanceof检查一个对象是否实现了某个特定的接口
        if(x instanceof Employee){
            System.out.println("implements Employee");
        }
        //接口可以继承，可以包含常量(public static final)
        //java单继承，但是可以实现多个接口（或者说接口就是为了多重继承而生的
    }
}
