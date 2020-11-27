package com.credithc.baseapp.global;

import java.util.concurrent.Callable;

/**
 * @author liyong
 * @date 2020/11/17 10:10
 * @description
 */
public class FibonaciiCallableCreator implements Callable<FibonaciiCallableCreator> {
    static int tastCount = 0;
    int first = 0;
    int second = 1;
    int n = 3;
    int[] fibonaciiArray;
    int taskId = tastCount++;

    public FibonaciiCallableCreator(int n) {
        this.n = n;
        fibonaciiArray = new int[n];
        fibonaciiArray[0] = first;
        fibonaciiArray[1] = second;
    }

    @Override
    public FibonaciiCallableCreator call() throws InterruptedException {
        for (int i = 2; i < n; i++) {
            int fiboN = first + second;
            first = second;
            second = fiboN;
            fibonaciiArray[i] = fiboN;
            if (taskId == 0) {
//                Thread.sleep(500);
            }
            Thread.yield();
        }
        return this;
    }

    public int[] getFibonaciiArray() {
        return fibonaciiArray;
    }

    public int getTaskId() {
        return taskId;
    }
}
