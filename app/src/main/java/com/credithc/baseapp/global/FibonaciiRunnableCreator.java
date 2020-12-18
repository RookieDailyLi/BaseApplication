package com.credithc.baseapp.global;

import java.util.Arrays;

/**
 * @author liyong
 * @date 2020/11/17 10:10
 * @description
 */
public class FibonaciiRunnableCreator implements Runnable {
    static int tastCount = 0;
    int first = 0;
    int second = 1;
    int n = 3;
    int[] fibonaciiArray;
    int id = tastCount++;

    public FibonaciiRunnableCreator(int n) {
        this.n = n;
        fibonaciiArray = new int[n];
        fibonaciiArray[0] = first;
        fibonaciiArray[1] = second;
//        System.out.println(String.format("Thread #(%d) fibonaciiArray = %s", id, Arrays.toString(fibonaciiArray)));
    }

    @Override
    public void run() {
//        System.out.println(String.format("Thread #(%d) fibonaciiArray[0] = %d", id, fibonaciiArray[0]));
//        System.out.println(String.format("Thread #(%d) fibonaciiArray[1] = %d", id, fibonaciiArray[1]));

        for (int i = 2; i < n; i++) {
            int fiboN = first + second;
            first = second;
            second = fiboN;
            fibonaciiArray[i] = fiboN;
//            System.out.println(String.format("Thread #(%d) fibonaciiArray[%d] = %d", id, i, fibonaciiArray[i]));
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
//                System.out.println(String.format("Thread #(%d) is interrupted, Exception = %s",id,e.getMessage()));
                e.printStackTrace();
                break;
            }
            finally {
                System.out.println(String.format("Thread #(%d) finally ...",id));
            }
        }
        System.out.println(String.format("Thread #(%d) fibonaciiArray = %s", id, Arrays.toString(fibonaciiArray)));
    }


}
