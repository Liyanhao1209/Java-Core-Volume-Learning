package com.ch5.Reflection;

import java.lang.reflect.Array;

public class Reflection_GenericArray {
    public static void main(String[] args) {
        int[] a = {1,2,3,4,5};
        a=(int[]) goodCopyOf(a,10);
    }

    //实际上这个方法可以扩展任意类型的数组
    private static Object goodCopyOf(Object a, int newLength) {
        Class<?> cl = a.getClass();
        if(!cl.isArray()) return null;
        Class<?> componentType = cl.getComponentType();
        int length = Array.getLength(a);
        Object newArray = Array.newInstance(componentType, newLength);
        System.arraycopy(a,0,newArray,0,Math.min(length,newLength));
        return newArray;
    }
}
