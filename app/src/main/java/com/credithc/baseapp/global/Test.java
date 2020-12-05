package com.credithc.baseapp.global;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author liyong
 * @date 2020/11/17 10:11
 * @description
 */
public class Test {

    public static void main(String[] args) {
        int[] array = new int[]{20, 19, 18, 17, 16, 15, 14, 13,71, 12,-2, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1,56};
        new Thread() {
            @Override
            public void run() {
                quickSort(array, 0, array.length - 1);
                super.run();
            }
        }.start();
//        System.out.println(String.format("The result of sort from small to large is %s", Arrays.toString(quickSort2(array, 0, array.length - 1))));
    }

    public static int[] quickSort(int[] srcArray, int left, int right) {
        int i = left;
        int j = right;
        int middleIndex = (left + right) / 2;
        int middleValue = srcArray[middleIndex];
        while (i < j) {
            //找出左边比中间值大的元素
            while (srcArray[i] < middleValue && i < j) {
                i++;
            }

            //找出右边比中间值小的元素
            while (srcArray[j] > middleValue && i < j) {
                j--;
            }

            //交换找到的左右两个元素
            if (i < j) {
                int temp = srcArray[i];
                srcArray[i] = srcArray[j];
                srcArray[j] = temp;
            }

            if(i == j){
                i++;
                j--;
            }

            System.out.println(Arrays.toString(srcArray));

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (left < j) {
            //递归左半边数组
            quickSort(srcArray, left,  j);
        }

        if (right > i) {
            //递归右半边数组
            quickSort(srcArray, i, right);
        }
        return srcArray;
    }

    public static void quickSort2(int[] arr, int low, int high) {
        int i, j, temp, t;
        if (low > high) {
            return ;
        }
        i = low;
        j = high;
        //temp就是基准位
        temp = arr[low];

        while (i < j) {
            //先看右边，依次往左递减
            while (temp <= arr[j] && i < j) {
                j--;
            }
            //再看左边，依次往右递增
            while (temp >= arr[i] && i < j) {
                i++;
            }
            //如果满足条件则交换
            if (i < j) {
                t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
            }

        }
        //最后将基准为与i和j相等位置的数字交换
        arr[low] = arr[i];
        arr[i] = temp;
        System.out.println(Arrays.toString(arr));

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //递归调用左半数组
        quickSort2(arr, low, j - 1);
        //递归调用右半数组
        quickSort2(arr, j + 1, high);
    }


    /**
     * 选择排序
     *
     * @param srcArray
     * @return
     */
    public static int[] selectSort(int[] srcArray) {
        for (int i = 0; i < srcArray.length; i++) {
            int minValue = srcArray[i];
            int minIndex = i;
            for (int j = i; j < srcArray.length; j++) {
                if (minValue > srcArray[j]) {
                    minValue = srcArray[j];
                    minIndex = j;
                }
            }
            int temp = srcArray[i];
            srcArray[i] = minValue;
            srcArray[minIndex] = temp;

        }
        return srcArray;
    }

    /**
     * 冒泡排序
     *
     * @param srcArray
     * @return
     */
    public static int[] bubbleSort1(int[] srcArray) {
        int n = srcArray.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (srcArray[j] > srcArray[j + 1]) {
                    int temp = srcArray[j];
                    srcArray[j] = srcArray[j + 1];
                    srcArray[j + 1] = temp;
                }
            }
        }
        return srcArray;
    }

    public static int[] bubbleSort2(int[] srcArray) {
        int length = srcArray.length;
        for (int i = 1; i < length; i++) {
            for (int j = length - 1; j >= i; j--) {
                if (srcArray[j] < srcArray[j - 1]) {
                    int temp = srcArray[j];
                    srcArray[j] = srcArray[j - 1];
                    srcArray[j - 1] = temp;
                }
            }
        }
        return srcArray;
    }


    /**
     * 插入排序
     *
     * @param srcArray
     * @return
     */
    public static int[] insertSort1(int[] srcArray) {
        for (int i = 0; i < srcArray.length; i++) {
            int minValue = srcArray[i];
            for (int j = i; j >= 0; j--) {
                if (minValue < srcArray[j]) {
                    srcArray[j + 1] = srcArray[j];
                    srcArray[j] = minValue;
                }
            }
        }
        return srcArray;
    }

    public static int[] insertSort2(int[] srcArray) {
        int length = srcArray.length;
        for (int i = 0; i < length; i++) {
            int minValue = srcArray[i];
            for (int j = i; j >= 0; j--) {
                if (minValue < srcArray[j]) {
                    srcArray[j + 1] = srcArray[j];
                    srcArray[j] = minValue;
                }
            }
        }
        return srcArray;
    }


    /**
     * 希尔排序
     *
     * @param srcArray
     * @return
     */
    public static int[] shellSort1(int[] srcArray) {
        int n = srcArray.length;
        int gap = n / 2;
        while (gap > 0) {
            for (int i = gap; i < n; i++) {
                int minValue = srcArray[i];
                int j = i;
                while (j - gap >= 0 && minValue < srcArray[j - gap]) {
                    int temp = srcArray[j];
                    srcArray[j] = srcArray[j - gap];
                    srcArray[j - gap] = temp;
                    j = j - gap;
                }
            }
            gap = gap / 2;
        }
        return srcArray;
    }

    public static int[] shellSort2(int[] srcArray) {
        int gap = srcArray.length / 2;
        while (gap > 0) {
            for (int i = gap; i < srcArray.length; i++) {
                int j = i;
                int minValue = srcArray[j];
                while (j - gap >= 0 && minValue < srcArray[j - gap]) {
                    srcArray[j] = srcArray[j - gap];
                    srcArray[j - gap] = minValue;
                    j = j - gap;
                }

            }
            gap = gap / 2;
        }
        return srcArray;
    }

    public void test1() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public static boolean patternPhone(String phone) {
        String regex = "^1[0-9]{10}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phone);
        boolean isMatch = matcher.matches();
        System.out.println(String.format("%s %s a cellphone number", phone, isMatch ? "is" : "is not"));
        return isMatch;
    }

    public static boolean patternIDCardNo(String idCardNo) {
        String regex = "^\\d{15}|\\d{17}[xX0-9]$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(idCardNo);
        boolean isMatch = matcher.matches();
        System.out.println(String.format("%s %s a ID Card number", idCardNo, isMatch ? "is" : "is not"));
        return isMatch;
    }

    public static void pattern(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        boolean match = matcher.matches();
        int count = 0;
        String replacement = "|";
        StringBuffer sb = new StringBuffer();
        int groupCount = matcher.groupCount();
        System.out.println(String.format("groupCount = %d", groupCount));
        while (matcher.find()) {
            count++;
            int start = matcher.start();
            int end = matcher.end();
            System.out.println(String.format("start = %d, end = %d, count = %d, group(0) = %s, group(1) = %s, group(2) = %s", start, end, count, matcher.group(0), matcher.group(1), matcher.group(2)));
            matcher.appendReplacement(sb, replacement);
        }
        matcher.appendTail(sb);
        System.out.println(String.format("replaced result = %s", sb));
        String result = matcher.replaceAll(replacement);
        System.out.println(String.format("%s match %s  = %s, replacement = %s, replace result = %s"
                , input, regex, match ? "true" : "false", replacement, result));

    }

    public static void multipleThread() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 3; i++) {
            executorService.submit(new FibonaciiRunnableCreator(10 + 2 * i));
        }
        executorService.shutdown();

    }

}
