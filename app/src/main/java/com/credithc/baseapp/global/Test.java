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
        int[] array = new int[]{36,18,21,5,4,6,7,9,3,2,1,4,6,7,9,3,2,1,4,6,7,9,3,2,1};
        System.out.println(String.format("The result of sort from small to large is %s", Arrays.toString(insertSort(array))));
    }

    public static int[] insertSort(int[] srcArray) {
        for (int i = 0; i < srcArray.length; i++) {
            int minValue = srcArray[i];
            for (int j = i; j >= 0; j--) {
                if(minValue < srcArray[j]){
                    srcArray[j+1] = srcArray[j];
                    srcArray[j] = minValue;
                }
            }
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
