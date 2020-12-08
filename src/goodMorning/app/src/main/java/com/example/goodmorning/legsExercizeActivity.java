package com.example.goodmorning;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import pl.droidsonroids.gif.GifImageView;

public class legsExercizeActivity extends AppCompatActivity {

    TextView textView ;
    GifImageView gifImageView;
    int imgs[] = {R.drawable.sit,R.drawable.run,R.drawable.jump,R.drawable.jump2} ;
    int imgs1[] = {R.drawable.jump } ;
    static int i = 0;
    long id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legs_exercize);
        textView = (TextView) findViewById(R.id.exeNum);
        gifImageView = (GifImageView) findViewById(R.id.exeGif);
            new CountDownTimer(12000, 1000) {

                //Здесь обновляем текст счетчика обратного отсчета с каждой секундой
                public void onTick(long millisUntilFinished) {
                    i++;
                    System.out.println("SAVE ME"+ i );

                    if(i % 3 == 0){
                        Bundle extras = getIntent().getExtras();
                        if (extras != null) {
                            id = extras.getLong("id");
                        }
                        //gifImageView = R.drawable.run;
                       if(id == 0) {
                           gifImageView.setImageResource(imgs[i / 3]);
                           textView.setText("Упражение /10");
                       }
                        if(id == 1) {
                                gifImageView.setImageResource(imgs1[i/3]);
                                textView.setText("Упражение /10");
                        }


                    }
                }

                //Задаем действия после завершения отсчета (высвечиваем надпись "Бабах!"):
                public void onFinish() {
                    System.out.println("FINISN ME");
                    //gifImageView.setImageResource(imgs[i/2-1]);
                    textView.setText("Упражение /10");

                    setContentView(R.layout.finish_exercize);
//                    try {
//                        Thread.sleep(3000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }


//                    textView = (TextView) findViewById(R.id.exeNum);
//                    gifImageView = (GifImageView) findViewById(R.id.exeGif);
//                    //gifImageView = R.drawable.run;
//                    gifImageView.setImageResource(imgs[i]);
//                    textView.setText("Упражение " + (i + 1) + "/10");
//                    i++;
                    i = 0;
                }
            }.start();

    }

}