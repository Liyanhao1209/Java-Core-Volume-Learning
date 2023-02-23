package com.ch6.Interface.Interface;

public interface Named {
    //default提供默认方法
    default String getName(){
        return getClass().getName() + "_" + hashCode();
    }
}
