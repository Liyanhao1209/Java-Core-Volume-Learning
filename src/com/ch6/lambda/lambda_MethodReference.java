package com.ch6.lambda;

import javax.swing.*;
import java.nio.file.DirectoryStream;

public class lambda_MethodReference {
    public static void main(String[] args) {
        //System.out::println为方法引用，指示编译器生成一个函数式接口的实例，覆盖该接口的抽象方法来调用给定方法
        //本例中，会生成一个ActionListener,其actionPerformed(ActionEvent e)会调用System.out.println(e)方法
        Timer timer = new Timer(1000,
                System.out::println);

        /**
         * ::的三种用法(但都是分隔方法名与对象或类名的)
         * 1.object::instanceMethod
         * 2.Class::instanceMethod
         * 3.Class:staticMethod
         */

        //需要注意的是，当lambda表达式的体仅调用一个方法而不做其他操作，才能把lambda重写为方法引用
        //这里的lambda表达式就不能转换为method reference
        {
            DirectoryStream.Filter<String> stringFilter = (String s) -> {
                return s.length() == 0;
            };
        }
    }
}
