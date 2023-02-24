package com.ch6.lambda;

import javax.swing.*;
import java.awt.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

public class lambda_FunctionalInterface {
    public static void main(String[] args) {
        //对于只有一个抽象方法的接口，需要该接口的实现对象时，可以提供lambda表达式
        String[] words = {"bag","problem","services"};
        //重写了超类Object类中任意一个public方法的方法并不算接口中的抽象方法。所以equal不算，仅有compare,则comparator是函数式接口
        Arrays.sort(words,
                (first,second)->first.length()-second.length());

        //lambda表达式转换为接口
        Timer timer = new Timer(1000,
                event -> {
                    System.out.println("At the tone, the time is " +
                            Instant.ofEpochMilli(event.getWhen()));
                    Toolkit.getDefaultToolkit().beep();
                });
//        timer.start();
//        JOptionPane.showMessageDialog(null,"Quit program?");
//        System.exit(0);

        //甚至有些方法中的参数是一个接口实现类，这个接口仅仅专门用来传递lambda表达式的
        /**
         * @FunctionalInterface
         * public interface Predicate<T> {
         *      * Evaluates this predicate on the given argument.
         *      *
         *      * @param t the input argument
         *      * @return {@code true} if the input argument matches the predicate,
         *      * otherwise {@code false}
         *
         *      boolean test (T t);
         */
        //java.util下的predicate被用来在arrayList中删除元素
        ArrayList<Object> list = new ArrayList<>();
        list.add(null);
        list.removeIf(e->e==null);
    }
}
