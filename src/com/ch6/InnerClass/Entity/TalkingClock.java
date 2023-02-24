package com.ch6.InnerClass.Entity;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;

public class TalkingClock {
    private int internal;
    private boolean beep;

    public TalkingClock(int internal, boolean beep) {
        this.internal = internal;
        this.beep = beep;
    }

    public class TimePrinter implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("At the tone, the time is "+
                    Instant.ofEpochMilli(e.getWhen()));
            //内部类访问外围类字段
            //内部类有一个隐式引用，假设为outer，这里的beep相当于outer.beep
            if(beep){
                Toolkit.getDefaultToolkit().beep();
            }
        }
    }
}
