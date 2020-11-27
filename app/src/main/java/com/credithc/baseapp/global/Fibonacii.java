package com.credithc.baseapp.global;

/**
 * @author liyong
 * @date 2020/11/17 11:04
 * @description
 */
public class Fibonacii {
    public static int recursionN(int n) {
        if (n == 1) {
            return 0;
        }

        if (n == 2) {
            return 1;
        }

        return recursionN(n - 1) + recursionN(n - 2);

    }

    public static int cycleN(int n) {
        if (n == 1) {
            return 0;
        }

        if (n == 2) {
            return 1;
        }

        int first = 0;
        int second = 1;
        int fiboN = 0;
        for (int i = 2; i < n; i++) {
            fiboN = first + second;
            first = second;
            second = fiboN;
        }
        return fiboN;
    }
}
