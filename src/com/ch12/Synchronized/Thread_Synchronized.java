package com.ch12.Synchronized;

import com.ch12.ReentrantLock.Entity.Bank;

public class Thread_Synchronized {
    public static final int NUM=100;
    public static final double INITIAL_BALANCE = 1000;
    public static final double MAX_AMOUNT = 1000;
    public static final int DELAY = 10;
    public static void main(String[] args) {
        Bank bank = new Bank(NUM, INITIAL_BALANCE);

        for (int i = 0; i < NUM; i++) {
            int from = i;
            Runnable runnable = () -> {
                try {
                    while (true) {
                        int to = (int) (NUM * Math.random());
                        double amount = MAX_AMOUNT * Math.random();
                        bank.transfer(from, to, amount);
                        Thread.sleep((int) (DELAY * Math.random()));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            Thread thread = new Thread(runnable);
            thread.start();
        }
    }
}
