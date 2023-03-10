package com.ch5.Reflection.Entity;

import java.util.Date;

public class Employee {
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
}
