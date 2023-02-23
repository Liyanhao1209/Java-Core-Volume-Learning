package com.ch6.Interface.Interface;

public interface Person {
    default String getName(){
        return "A Person";
    };
}
