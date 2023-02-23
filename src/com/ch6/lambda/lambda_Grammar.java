package com.ch6.lambda;

import java.util.Comparator;

public class lambda_Grammar {
    public static void main(String[] args) {

        Comparator<String> stringComparator =
                (String first, String second) -> {
                    if (first.length() < second.length()) return -1;
                    else if (first.length() > second.length()) return 1;
                    else return 0;
                };

        //如果可以推断出参数类型，可以忽略类型
        Comparator<String> comp = (first,second)->{
                    return first.length()-second.length();
                };

        int compare = comp.compare("Paul George", "Peter");
        System.out.println(compare);

    }
}
