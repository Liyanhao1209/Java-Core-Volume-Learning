package com.ch6.Interface.Entity;

import java.util.Date;

public class Employee implements Comparable<Employee>,Cloneable{
    private String name;
    private double salary;

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    private Date HireDay;


    public Employee(String name, double salary,Date hireDay) {
        this.name = name;
        this.salary=salary;
        HireDay = hireDay;
    }

    public Employee(String name,Date hireDay){
        this.name=name;
        this.salary=0.0;
        HireDay=hireDay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getHireDay() {
        return HireDay;
    }

    public void setHireDay(Date hireDay) {
        HireDay = hireDay;
    }

    public void raiseSalary(double rate){
        this.salary *= (1+rate);
    }


    @Override
    public int compareTo(Employee o) {
        return Double.compare(salary,o.salary);
    }

    //浅拷贝 子对象引用共享内存
//    @Override
//    public Employee clone() throws CloneNotSupportedException {
//        return (Employee)super.clone();
//    }

    //深拷贝 同时克隆子对象
    @Override
    public Employee clone() throws CloneNotSupportedException {
        Employee clone = (Employee) super.clone();
        clone.HireDay=(Date)this.HireDay.clone();
        return clone;
    }
}
