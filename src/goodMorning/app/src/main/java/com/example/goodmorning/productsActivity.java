package com.example.goodmorning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cursoradapter.widget.SimpleCursorAdapter;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;

public class productsActivity extends AppCompatActivity {
//+
    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    SimpleCursorAdapter userAdapter;
    ListView userList;
    EditText userFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        userList = (ListView)findViewById(R.id.userList);
        userFilter = (EditText)findViewById(R.id.userFilter);

        sqlHelper = new DatabaseHelper(getApplicationContext());
        // создаем базу данных
        sqlHelper.create_db();
    }
    @Override
    public void onResume() {
        super.onResume();
        try {
            db = sqlHelper.open();
            userCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE, null);
            String[] headers = new String[]{DatabaseHelper.COLUMN_NAME, DatabaseHelper.COLUMN_YEAR};
            userAdapter = new SimpleCursorAdapter(this, android.R.layout.two_line_list_item,
                    userCursor, headers, new int[]{android.R.id.text1, android.R.id.text2}, 0);

            // если в текстовом поле есть текст, выполняем фильтрацию
            // данная проверка нужна при переходе от одной ориентации экрана к другой
            if(!userFilter.getText().toString().isEmpty())
                userAdapter.getFilter().filter(userFilter.getText().toString());

            // установка слушателя изменения текста
            userFilter.addTextChangedListener(new TextWatcher() {

                public void afterTextChanged(Editable s) { }

                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                // при изменении текста выполняем фильтрацию
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    userAdapter.getFilter().filter(s.toString());
                }
            });

            // устанавливаем провайдер фильтрации
            userAdapter.setFilterQueryProvider(new FilterQueryProvider() {
                @Override
                public Cursor runQuery(CharSequence constraint) {

                    if (constraint == null || constraint.length() == 0) {

                        return db.rawQuery("select * from " + DatabaseHelper.TABLE, null);
                    }
                    else {
                        return db.rawQuery("select * from " + DatabaseHelper.TABLE + " where " +
                                DatabaseHelper.COLUMN_NAME + " like ?", new String[]{"%" + constraint.toString() + "%"});
                    }
                }
            });

            userList.setAdapter(userAdapter);
        }
        catch (SQLException ex){}
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        // Закрываем подключение и курсор
        db.close();
        userCursor.close();
    }





//    Button button;
//    TextView textView;
//
//
//    private DatabaseHelper mDBHelper;
//    private SQLiteDatabase mDb;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_products);
//
//        mDBHelper = new DatabaseHelper(this);
//
//        try {
//            mDBHelper.updateDataBase();
//        } catch (IOException mIOException) {
//            throw new Error("UnableToUpdateDatabase");
//        }
//
//        try {
//            mDb = mDBHelper.getWritableDatabase();
//        } catch (SQLException mSQLException) {
//            throw mSQLException;
//        }
//
//
//        //Найдем компоненты в XML разметке
//        button = (Button) findViewById(R.id.button);
//        textView = (TextView) findViewById(R.id.textView);
//
//        //Пропишем обработчик клика кнопки
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String product = "";
//
//                Cursor cursor = mDb.rawQuery("SELECT * FROM foodTable", null);
//                cursor.moveToFirst();
//                while (!cursor.isAfterLast()) {
//                    product += cursor.getString(1) + " | ";
//                    cursor.moveToNext();
//                }
//                cursor.close();
//
//                textView.setText(product);
//            }
//        });
//    }
}