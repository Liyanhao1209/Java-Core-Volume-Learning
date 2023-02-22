package com.ch5.Reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;
public class Reflection_Field_Method_Constructor {
    public static void printLine(){
        System.out.println("------------------------");
    }

    public static void skipLine(){
        System.out.println("                        ");
    }

    public static void printBlank(){
        System.out.print("    ");
    }
    public static void main(String[] args) throws ClassNotFoundException {
        //输入一个类名
        String name = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter class name");
        name = scanner.next();

        //打印类名超类名
        Class<?> cl = Class.forName(name);
        Class<?> superclass = cl.getSuperclass();
        String modifiers = Modifier.toString(cl.getModifiers());//获取类使用的修饰符
        System.out.println(modifiers+" "+"class"+name);
        if(superclass!=null&&superclass!=Object.class){
            System.out.println("extends "+superclass.getName());
        }
        printLine();

        //打印构造器
        System.out.println("Constructors:");
        printConstructors(cl);
        printLine();

        //打印方法
        System.out.println("Methods:");
        printMethods(cl);
        printLine();

        //打印字段
        System.out.println("Fields:");
        printFields(cl);
    }

    private static void printFields(Class<?> cl) {
        Field[] declaredFields = cl.getDeclaredFields();

        for (Field declaredField : declaredFields) {
            Class<?> type = declaredField.getType();
            String name = declaredField.getName();
            printBlank();
            String modifiers = Modifier.toString(declaredField.getModifiers());
            System.out.print(modifiers+" ");
            System.out.println(type.getName()+" "+name+";");
        }
    }

    private static void printMethods(Class<?> cl) {
        Method[] declaredMethods = cl.getDeclaredMethods();

        for (Method declaredMethod : declaredMethods) {
            Class<?> returnType = declaredMethod.getReturnType();//方法返回类型
            String name = declaredMethod.getName();//方法名

            printBlank();
            String modifiers = Modifier.toString(declaredMethod.getModifiers());
            System.out.print(modifiers+" ");
            System.out.print(name+"(");

            Class<?>[] parameterTypes = declaredMethod.getParameterTypes();
            for (int i = 0; i < parameterTypes.length; i++) {
                if(i>0) System.out.print(",");
                System.out.print(parameterTypes[i].getName());
            }
            System.out.println(");");
        }
    }

    private static void printConstructors(Class<?> cl) {
        Constructor<?>[] declaredConstructors = cl.getDeclaredConstructors();

        for (Constructor<?> declaredConstructor : declaredConstructors) {
            String name = declaredConstructor.getName();
            printBlank();
            String modifiers = Modifier.toString(declaredConstructor.getModifiers());
            System.out.print(modifiers+" ");//打印构造器修饰符
            System.out.print(name+"(");//打印构造器名，

            Class<?>[] parameterTypes = declaredConstructor.getParameterTypes();//获取参数类型数组(可能多个参数)
            for (int i = 0; i < parameterTypes.length; i++) {
                if(i>0) System.out.print(",");
                System.out.print(parameterTypes[i].getName());
            }
            System.out.println(");");
        }
    }
}
