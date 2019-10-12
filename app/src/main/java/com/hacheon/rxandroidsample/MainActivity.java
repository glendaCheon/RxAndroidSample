package com.hacheon.rxandroidsample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.jakewharton.rxbinding3.view.RxView;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import kotlin.Unit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //rxBinding을 통해 해당 이벤트를 stream화.
        RxView.clicks(findViewById(R.id.button))
                .subscribeOn(AndroidSchedulers.mainThread())//위치 어딨든 상관없으나, 구동될 때 뭘로 시작할 건질 정하는것임.
                .doOnNext(new Consumer<Object>() {
                    @Override
                    public void accept(Object unit) throws Exception {
                        Log.d("Main",Thread.currentThread().getName());
                    }
                })
                .observeOn(Schedulers.io())
                .map(new Function<Object, Object>() {
                    @Override
                    public Object apply(Object o) throws Exception {
                        return "clicked";
                    }
                })
                .doOnNext(new Consumer<Object>() {
                    @Override
                    public void accept(Object unit) throws Exception {
                        Log.d("Main",Thread.currentThread().getName());
                    }
                })
//                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())//madin thread로 변경
                .subscribe(new Consumer<Object>(){
                    @Override
                    public void accept(Object o) throws Exception { //onNext가 된 것.
                        Toast.makeText(MainActivity.this, o.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

        //일반적인 클릭
//        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
//                                                         @Override
//                                                         public void onClick(View v) {
//                                                             Observable.just("click")
//                                                                     .subscribe(new Consumer<String>() {
//                                                                                    @Override
//                                                                                    public void accept(String s) throws Exception {
//                                                                                        Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
//                                                                                    }
//                                                                                }
//                                                                     );
//                                                         }
//                                                     }


//        );
    }


}
