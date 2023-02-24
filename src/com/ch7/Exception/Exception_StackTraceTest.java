package com.ch7.Exception;

import java.util.Scanner;

public class Exception_StackTraceTest {

    public static int factorial(int n){
        System.out.println("factorial("+n+")");
        StackWalker instance = StackWalker.getInstance();
        instance.forEach(
                (action)->{
                    System.out.println(action);
                }
        );
        int r;
        if(n<=1) r=1;
        else r = n * factorial(n-1);
        System.out.println("return "+r);
        return r;
    }

    public static void main(String[] args) {
        //try-with-resource 自动关闭流
        try(Scanner scanner = new Scanner(System.in))
        {
            System.out.println("Enter n: ");
            int n = scanner.nextInt();
            factorial(n);
        }
    }
}
