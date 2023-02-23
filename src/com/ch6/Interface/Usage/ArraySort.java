package com.ch6.Interface.Usage;

import com.ch6.Interface.Impl.StringLengthComparator;

import java.util.Arrays;

public class ArraySort {
    public static void main(String[] args) {
        String[] friends =
                {
                        "Peter",
                        "Paul",
                        "Gilfoyle",
                        "Nelson",
                };
        Arrays.sort(friends,new StringLengthComparator());
        System.out.println(Arrays.toString(friends));
    }
}
