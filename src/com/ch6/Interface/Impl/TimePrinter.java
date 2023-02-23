package com.ch6.Interface.Impl;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;
import javax.swing.*;

public class TimePrinter implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        //event.getWhen()返回当前时间,ofEpochMilli得到一个可读的描述
        System.out.println("At the tone, the time is "
        + Instant.ofEpochMilli(e.getWhen()));
        //发出声音
        Toolkit.getDefaultToolkit().beep();
    }
}
