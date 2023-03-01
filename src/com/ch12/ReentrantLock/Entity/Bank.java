package com.ch12.ReentrantLock.Entity;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Bank{
    private double[] accounts;
    private int numOfAccount;
    //临界区保护锁
    private ReentrantLock bankLock;
    private Condition sufficientFunds;

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
        this.bankLock = new ReentrantLock();
        this.sufficientFunds = bankLock.newCondition();
    }


    public void transfer(int from,int to,double money) throws InterruptedException {
        if(from<0||to>=this.numOfAccount){return;}
        /**
         * 我们不一定要已发现当前账户钱不够转的就直接返回，可以等到它钱够了，然后再转
         * 这时候我们需要让一个线程每次获得锁的时候都判断一次钱是否够，钱够就转，不够就继续等
         */
//        if(accounts[from]<money){return;}
        //上锁，保护临界区
        //锁有一个计数，追踪对lock的嵌套调用
        //被锁保护的代码可以调用另一个使用相同锁的方法
        //此时锁计数为1
        bankLock.lock();
        try{
            while(accounts[from]<money){
                sufficientFunds.await();//await、signalAll只能在带条件的锁上调用
            }
            System.out.print(Thread.currentThread());
            accounts[from]-=money;
            System.out.printf(" %10.2f from %d to %d",money,from,to);
            accounts[to]+=money;
            //getTotalBalance时也会加锁，此时lock计数为2，此方法返回，计数变回1
            System.out.printf(" Total Balance: %10.2f%n",getTotalBalance());
            /**
             * 转账完毕调用signalAll激活等待这个条件的所有线程
             * 这些线程重新尝试进入对象，一旦锁被释放，他们中的某个将从await返回，并获得该锁，并继续从暂停处执行
             */
            sufficientFunds.signalAll();
        }finally {
            //解锁
            bankLock.unlock();
        }
        //方法结束，计数为0，释放锁
    }

    private double getTotalBalance() {
        bankLock.lock();
        try{
            double sum = 0;
            for (double account : accounts) {
                sum += account;
            }
            return sum;
        }finally {
            bankLock.unlock();
        }

    }
}
