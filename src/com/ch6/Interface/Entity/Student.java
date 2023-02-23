package com.ch6.Interface.Entity;

import com.ch6.Interface.Interface.Named;
import com.ch6.Interface.Interface.Person;

/**
 * com.ch6.Interface.Entity.Student
 * inherits unrelated defaults for getName() from
 * types com.ch6.Interface.Interface.Person and com.ch6.Interface.Interface.Named
 * 接口冲突 必须覆盖方法
 * 如果是接口和类冲突，秉承类优先原则
 */
public class Student implements Person, Named {

    @Override
    public String getName() {
        //意即，指定Person接口，使用它提供的默认实现
        return Person.super.getName();
    }

    public static void main(String[] args) {
        Student student = new Student();
        System.out.println(student.getName());
    }
}
