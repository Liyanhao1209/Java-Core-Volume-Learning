package com.ch12.Synchronized.Entity;

public class Bank {
    private double[] accounts;
    private int numOfAccount;

    public Bank(int numOfAccount,double initMoney){
        this.numOfAccount = numOfAccount;
        accounts = new double[this.numOfAccount];
        for (int i = 0; i < accounts.length; i++) {
            accounts[i]=initMoney;
        }
    }

    //加了synchronized后就相当于把ReentrantLock的显式锁变成了一个隐式锁
    public synchronized void transfer(int from,int to,double money) throws InterruptedException {
        if(from<0||to>=this.numOfAccount){return;}
        while(accounts[from]<money){
            wait();//相当于条件await
        }
        System.out.print(Thread.currentThread());
        accounts[from]-=money;
        System.out.printf(" %10.2f from %d to %d",money,from,to);
        accounts[to]+=money;
        System.out.printf(" Total Balance: %10.2f%n",getTotalBalance());
        notifyAll();//相当于signalAll
    }

    private synchronized double getTotalBalance() {
        double sum = 0;
        for (double account : accounts) {
            sum += account;
        }
        return sum;
    }
}
