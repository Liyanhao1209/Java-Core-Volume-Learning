package com.ch5.Reflection.Entity;

import java.util.Date;

public class Employee {
    private String name;
    private Date HireDay;

    public Employee(String name, Date hireDay) {
        this.name = name;
        HireDay = hireDay;
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
}
