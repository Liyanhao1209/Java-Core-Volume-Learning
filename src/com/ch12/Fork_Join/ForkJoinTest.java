package com.ch12.Fork_Join;

import java.util.concurrent.ForkJoinPool;

public class ForkJoinTest {

    /**
     * fork-join框架
     * if(problems<threshold)
     *      solve problem directly
     * else
     * {
     *     break problem into subProblems
     *     recursively solve each subProblem
     *     combine the results
     * }
     * 简单来说，就是官方封装的一个分治算法框架
     */

    public static void main(String[] args) {
        //采用fork-join框架统计一个数组中有多少个元素满足某个特定的属性
        final int SIZE = 10000;
        double[] numbers = new double[SIZE];
        for (int i = 0; i < SIZE; i++) {
            numbers[i]=Math.random();
        }


        /**
         * boolean test(double x){return x>0.5;}
         * 在给定参数上计算这个动作
         * Evaluates this predicate on the given argument.
         * Params:
         * value – the input argument
         * Returns:
         * 如果参数符合命题，返回真；否则返回假。
         * true if the input argument matches the predicate, otherwise false
         *
         */
        Counter counter = new Counter(numbers, 0, numbers.length, x -> x > 0.5);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(counter);
        System.out.println(counter.join());
    }
}
