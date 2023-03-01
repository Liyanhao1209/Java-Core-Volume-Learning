package com.ch12.ReentrantLock;


import com.ch12.ReentrantLock.Entity.Bank;

public class Thread_RaceCondition {
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

        /**
         * 打了一堆没按打印格式来的东西，帐还不平。
         * 这就是竞态条件
         * 举个例子，假设有两个线程，线程1把accounts[to]从5000增加到5500（但此时还在寄存器中，没有写回）
         * 此时它的运行权被抢占，线程2把accounts[to]从5000（因为还没写回）增加到5900，写回。
         * 最后线程1拿回运行权，把5500写回，这样明明accounts[to]应该是5000+500+900=6400，但实际上是5500，少900，帐就不平了
         * 这一切都是因为不同步的问题，我们应该让线程1执行完再让出运行权，让线程2执行。
         */
    }
}
