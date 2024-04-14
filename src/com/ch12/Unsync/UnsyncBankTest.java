package com.ch12.Unsync;


import com.ch12.Unsync.Entity.Bank;

public class UnsyncBankTest {
    public static void main(String[] args) {
        Bank bank = new Bank(100, 1000);

        for (int i = 0; i < 100; i++) {
            int from = i;
            Runnable r = ()->{
                try{
                    for(int j=0;j<10;j++){
                        int to = (int)(bank.size()*Math.random());
                        double amount = 1000*Math.random();
                        bank.transfer(from,to,amount);
                        Thread.sleep((int)(10*Math.random()));
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            };
            Thread thread = new Thread(r);
            thread.start();
        }

        System.out.println(bank);
    }
}
