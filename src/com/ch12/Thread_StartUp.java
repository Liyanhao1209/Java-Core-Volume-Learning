package com.ch12;

class Bank{
    private double[] accounts;
    private int numOfAccount;

    public Bank(int numOfAccount,double initMoney){
        this.numOfAccount = numOfAccount;
        accounts = new double[this.numOfAccount];
        //这个赋值是错的 account是accounts[i]的一个副本引用，实际上指向了initMoney,但accounts[i]没有改变指向的内存
//        for (double account : accounts) {
//            account = initMoney;
//        }
        for (int i = 0; i < accounts.length; i++) {
            accounts[i]=initMoney;
        }
    }

    public void transfer(int from,int to,double money){
        if(from<0||to>=this.numOfAccount){return;}
        if(accounts[from]<money){return;}
        System.out.print(Thread.currentThread());
        accounts[from]-=money;
        System.out.printf(" %10.2f from %d to %d",money,from,to);
        accounts[to]+=money;
        System.out.printf(" Total Balance: %10.2f%n",getTotalBalance());
    }

    private double getTotalBalance() {
        double sum = 0;
        for (double account : accounts) {
            sum += account;
        }
        return sum;
    }
}

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
