package com.ch12.ReentrantLock;

import com.ch12.ReentrantLock.Entity.Bank;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;



public class Thread_StartUp {

    public static final int DELAY=10;
    public static final int STEPS = 100;
    public static final double MAX_AMOUNT = 1000;


    public static void main(String[] args) {
        Bank bank = new Bank(4, 10000);

        Runnable task1 = () -> {
            try {
                for (int i = 0; i < STEPS; i++) {
                    double amount = MAX_AMOUNT * Math.random();
                    bank.transfer(0, 1, amount);
                    Thread.sleep((int) (DELAY * Math.random()));
                }
            }catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        Runnable task2 = () -> {
            try {
                for (int i = 0; i < STEPS; i++) {
                    double amount = MAX_AMOUNT * Math.random();
                    bank.transfer(2, 3, amount);
                    Thread.sleep((int) (DELAY * Math.random()));
                }
            }catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        new Thread(task1).start();
        new Thread(task2).start();

        /**
         * Thread[#24,Thread-1,5,main]Thread[#23,Thread-0,5,main]     872.98 from 0 to 1     138.29 from 2 to 3 Total Balance:   40000.00
         *  Total Balance:   39861.71
         *  没上同步锁的后果，以后再说
         */
    }
}
