package com.ch6.Interface.Impl;

import java.util.Comparator;

public class StringLengthComparator implements Comparator<String> {

    //这个compare要在类实例对象上调用，不像compareTo是在字符串本身上调用
    @Override
    public int compare(String o1, String o2) {
        return o1.length()-o2.length();
    }

}
