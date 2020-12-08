package com.example.goodmorning.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.goodmorning.MyAdapter;
import com.example.goodmorning.R;
import com.example.goodmorning.addTrainActivity;
import com.example.goodmorning.legsActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    ListView listView;
    static int i = 0;
    String mTitle[] = {"НОГИ","РУКИ","Add"};
    String mDescription[] = {"Тренировки для ножек","Тренировки для ручек", "My trains"};
    int images[] = {R.drawable.legs, R.drawable.arm,R.drawable.ic_home_black_24dp};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
//        FloatingActionButton fltActionbuttton = root.findViewById(R.id.floatingActionButton);
//        fltActionbuttton.setImageResource(R.drawable.ic_home_black_24dp);
//        final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        listView = (ListView)root.findViewById(R.id.listView);
        // now create an adapter class
        //FloatingActionButton fltActionbuttton = root.findViewById(R.id.floatingActionButton);
        MyAdapter adapter = new MyAdapter(getActivity(), mTitle, mDescription, images);
        listView.setAdapter(adapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    Intent intent = new Intent(view.getContext(), legsActivity.class);
                    startActivity(intent);
                }
                if(position == 2){
                    Intent intent = new Intent(view.getContext(), addTrainActivity.class);
                    startActivity(intent);
                }
            }
        });

        return root;
    }


}