package com.ch6.Interface.Usage;

import com.ch6.Interface.Impl.TimePrinter;

import javax.swing.*;

public class TimerTest {
    public static void main(String[] args) {
        TimePrinter timePrinter = new TimePrinter();
        //构造一个可以回调timePrinter的计时器，1000ms一次
        Timer timer = new Timer(1000, timePrinter);
        timer.start();//启动定时器

        JOptionPane.showMessageDialog(null,"Quit program?");
        System.exit(0);
    }
}
