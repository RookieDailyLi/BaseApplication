package com.credithc.baseapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.credithc.commonlib.listener.OnFastClickListener;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableOperator;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(new OnFastClickListener() {
            @Override
            public void noFastClick() {
                test5();
            }
        });
    }

    private void test5() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
            }
        }).compose(new ObservableTransformer<Integer, String>() {
            @Override
            public ObservableSource<String> apply(Observable<Integer> upstream) {
                return upstream.take(2).map(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Exception {
                        return "序号" + integer;
                    }
                });
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.e("onNext",s);

            }
        });
    }

    private void test4() {
        Observable.create(new ObservableOnSubscribe<List<String>>() {

            @Override
            public void subscribe(ObservableEmitter<List<String>> emitter) throws Exception {
                List<String> list = new ArrayList<>();
                list.add("1");
                list.add("2");
                list.add("3");
                list.add("4");
                emitter.onNext(list);
            }
        })
                .lift(new ObservableOperator<String, List<String>>() {
                    @Override
                    public Observer<? super List<String>> apply(final Observer<? super String> observer) throws Exception {
                        return new Observer<List<String>>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(List<String> strings) {
                                observer.onNext(strings.toString());
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        };
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.e("onNext",s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void test3() {
        Observable.just(new User("zhangsan", "001"))
                .flatMap(new Function<User, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(User user) throws Exception {
                        return Observable.just(user.name + "登录成功");
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.e("test3", s);
                    }
                });
    }

    public static class User {
        public User(String name, String id) {
            this.name = name;
            this.id = id;
        }

        String name;
        String id;
    }

    private void test1() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("hello Mr wang");
                emitter.onComplete();
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.e("onNext", s);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.e("onError", throwable.getMessage());
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                Log.e("onComplete", "onComplete");
            }
        });
    }

    private void test2() {
        Observable.fromArray(new Integer[]{1, 2, 3, 4, 5})
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.e("onNext", integer + "");
                    }
                });
    }

}
