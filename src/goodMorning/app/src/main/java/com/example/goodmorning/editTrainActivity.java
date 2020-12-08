package com.example.goodmorning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class editTrainActivity extends AppCompatActivity {
//
       EditText nameBox;
//    EditText yearBox;
    Button delButton;
    Button saveButton;

    trainDatabaseHelper sqlHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    long userId = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_train);

        delButton = findViewById(R.id.deleteButton);
        saveButton = findViewById(R.id.saveButton);

        System.out.println("бляяяяяяяяяяяяяяяяяяяяяяяя");
        CheckBox[] example = {findViewById(R.id.sitBox),findViewById(R.id.jumpOver),findViewById(R.id.checkBox4),findViewById(R.id.checkBox5),findViewById(R.id.checkBox6),findViewById(R.id.checkBox)};
//        example[0] = findViewById(R.id.sitBox);
//        example[1] = findViewById(R.id.jumpOver);
//        example[2] = findViewById(R.id.checkBox4);
//        example[3] = findViewById(R.id.checkBox5);
//        example[4] = findViewById(R.id.checkBox6);
//        example[5] = findViewById(R.id.checkBox);

        nameBox = findViewById(R.id.editTextTextPersonName);
        sqlHelper = new trainDatabaseHelper(this);
        db = sqlHelper.getWritableDatabase();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userId = extras.getLong("id");
        }

        String[] results;
        String delimeter = ",";
        if (userId > 0) {
            // получаем элемент по id из бд
            userCursor = db.rawQuery("select * from " + trainDatabaseHelper.TABLE + " where " +
                    trainDatabaseHelper.COLUMN_ID + "=?", new String[]{String.valueOf(userId)});
            userCursor.moveToFirst();
            results = userCursor.getString(2).split(delimeter);

            System.out.println("б");
            System.out.println(results[0]);
            for(int i = 0; i < results.length; i++){
                System.out.println("БЛZ");
                for( int j = 0; j < 6; j++ ){
                    System.out.println("БЛZ");
                    System.out.println(example[j].getText());
                    if(results[i].equals(example[j].getText()) ){
                        System.out.println("БЛ");
                        example[j].setEnabled(true);
//                        example.setActivated(true);
                        example[j].setChecked(true);
                        break;
                    }
                }
            }
            nameBox.setText(userCursor.getString(1));
//            nameBox.setText(userCursor.getString(1));
//            yearBox.setText(String.valueOf(userCursor.getInt(2)));
            userCursor.close();
        } else {
            // скрываем кнопку удаления
            delButton.setVisibility(View.GONE);
        }

    }

    public void save(View view){
        ContentValues cv = new ContentValues();
        cv.put(trainDatabaseHelper.COLUMN_NAME, nameBox.getText().toString());
        CheckBox[] example = {findViewById(R.id.sitBox),findViewById(R.id.jumpOver),findViewById(R.id.checkBox4),findViewById(R.id.checkBox5),findViewById(R.id.checkBox6),findViewById(R.id.checkBox)};
        String result = new String();
        for(int i = 0 ; i < 6; i++){
            if(example[i].isChecked()){
                result += example[i].getText().toString();
                result += ',';
            }
        }
        cv.put(trainDatabaseHelper.COLUMN_EXE, result);

        if (userId > 0) {
            db.update(trainDatabaseHelper.TABLE, cv, trainDatabaseHelper.COLUMN_ID + "=" + String.valueOf(userId), null);
        } else {
            db.insert(trainDatabaseHelper.TABLE, null, cv);
        }
        goHome();
    }

    public void delete(View view){
        db.delete(trainDatabaseHelper.TABLE, "_id = ?", new String[]{String.valueOf(userId)});
        goHome();
    }

    private void goHome(){
        // закрываем подключение
        db.close();
        // переход к главной activity
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
}