package com.ch5.Reflection.Entity;

import java.util.Date;

public class Manager extends Employee{
    private double bonus;

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public Manager(String name, Date hireDay) {
        super(name, hireDay);
    }
}
