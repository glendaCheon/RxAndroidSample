package com.hacheon.rxandroidsample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.jakewharton.rxbinding3.view.RxView;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import kotlin.Unit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //rxBinding을 통해 해당 이벤트를 stream화.
        RxView.clicks(findViewById(R.id.button))
                .map(new Function<Object, Object>() {
                    @Override
                    public Object apply(Object o) throws Exception {
                        return "clicked";
                    }
                })
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
