package com.ch12.Unsync.Entity;

import java.util.Arrays;

public class Bank {
    private double[] accounts;


    public Bank(int n,double initialBalance){
        accounts = new double[n];
        Arrays.fill(accounts,initialBalance);
    }

    public void transfer(int from,int to,double amount){
        if(accounts[from]<amount){
            return;
        }
        accounts[from]-=amount;
        accounts[to]+=amount;
    }

    public int size(){
        return accounts.length;
    }

}
