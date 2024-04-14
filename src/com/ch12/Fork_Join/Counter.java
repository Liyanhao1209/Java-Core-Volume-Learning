package com.ch12.Fork_Join;

import java.util.concurrent.RecursiveTask;
import java.util.function.DoublePredicate;

public class Counter extends RecursiveTask<Integer> {
    public static final int THRESHOLD = 1000;
    private double[] values;
    private int from;
    private int to;
    private DoublePredicate filter;

    public Counter(double[] values, int from, int to, DoublePredicate filter) {
        this.values = values;
        this.from = from;
        this.to = to;
        this.filter = filter;
    }

    @Override
    protected Integer compute(){
        if(to-from<THRESHOLD){
            int count = 0;
            for(int i = from;i<to;i++){
                if(filter.test(values[i])){
                    count++;
                }
            }
            return count;
        }
        else{
            int mid = (from+to)/2;
//            System.out.println("subTask from "+from +" to "+mid);
            Counter first = new Counter(values, from, mid, filter);
//            System.out.println("subTask from "+mid +" to "+to);
            Counter second = new Counter(values, mid, to, filter);
            invokeAll(first,second);
//            System.out.println(first.join()+second.join());
            return first.join()+second.join();
        }
    }
}
