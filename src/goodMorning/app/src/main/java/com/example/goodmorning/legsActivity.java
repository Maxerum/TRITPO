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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.goodmorning.ui.home.HomeFragment;

public class legsActivity extends AppCompatActivity {

    ListView listView;
    String mTitle[] = {"НОВИЧОК", "СРЕДНЯЧОК"};
    String mDescription[] = {"ты лох и только скачал это приложение","уже где-то на турниках висел бро"};
    int images[] = {R.drawable.legs, R.drawable.arm};
    MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legs);

        listView = (ListView)findViewById(R.id.listView);

        adapter = new MyAdapter(getApplicationContext(), mTitle, mDescription, images);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, final long id) {
                if (position == 0) {
                    new CountDownTimer(2000, 1000) {

                        //Здесь обновляем текст счетчика обратного отсчета с каждой секундой
                        public void onTick(long millisUntilFinished) {
                            System.out.println("SAVE ME");
                        }

                        //Задаем действия после завершения отсчета (высвечиваем надпись "Бабах!"):
                        public void onFinish() {
                            //System.out.println("DOLBOEB");
                            Intent intent = new Intent(view.getContext(), legsExercizeActivity.class);
                            intent.putExtra("id", id);
                            startActivity(intent);
                        }
                    }.start();
                }
                if(position == 1){
                    new CountDownTimer(2000, 1000) {

                        //Здесь обновляем текст счетчика обратного отсчета с каждой секундой
                        public void onTick(long millisUntilFinished) {
                            System.out.println("SAVE ME");
                        }

                        //Задаем действия после завершения отсчета (высвечиваем надпись "Бабах!"):
                        public void onFinish() {
                            //System.out.println("DOLBOEB");
                            Intent intent = new Intent(view.getContext(), legsExercizeActivity.class);
                            intent.putExtra("id", id);
                            startActivity(intent);
                        }
                    }.start();
                }
            }
                    //Intent intent = new Intent(view.getContext(), legsActivity.class);
                    //startActivity(intent);
        });
    }






//    public class MyTimer extends CountDownTimer
//    {
//        private View viewTimer ;
//        public MyTimer(long millisInFuture, long countDownInterval, View view)
//        {
//            super(millisInFuture, countDownInterval);
//            viewTimer = view;
//        }
//
//        @Override
//        public void onFinish()
//        {
//            Intent intent = new Intent(viewTimer.getContext(), legsExercizeActivity.class);
//            startActivity(intent);
//        }
//
//        public void onTick(long millisUntilFinished)
//        {
//
//        }
//
//    }

}