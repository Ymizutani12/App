package com.example.namebattle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class syousai extends AppCompatActivity {


    //各変数の定義
    private CustomOpenHelper helper;
    private SQLiteDatabase db;
    private TextView textView;
    private String name;
    DateFormat yyyymmddhhmm = new SimpleDateFormat("yyyy/MM/dd HH:mm");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syousai);

        //何番目を取得したか確認
        Intent listNumber = getIntent();
        int position = listNumber.getIntExtra("POSITION",0);

        Button returnButton = findViewById(R.id.modoru);
        Button Delete = findViewById(R.id.delete);

        if(helper == null){
            helper = new CustomOpenHelper(getApplicationContext());
        }

        if(db == null){
            db = helper.getWritableDatabase();
        }


        Cursor cursor = db.query(
                "CHARACTERS",
                new String[] { "name","job","hp","mp","str","def","agi","luck","create_at" },
                null,
                null,
                null,
                null,
                null
        );

        cursor.moveToFirst();

        //カーソルを選択した番号に持っていく
        for (int i = 0; i < position; i++) {
            cursor.moveToNext();
        }

        //表示
        StringBuilder sbuilder = new StringBuilder();

        sbuilder.append(cursor.getString(0));
        sbuilder.append("\n");
        sbuilder.append(cursor.getString(1));
        sbuilder.append("\n\nステータス\nHP\t");
        sbuilder.append(cursor.getInt(2));
        sbuilder.append("\nMP\t");
        sbuilder.append(cursor.getInt(3));
        sbuilder.append("\nSTR\t");
        sbuilder.append(cursor.getInt(4));
        sbuilder.append("\nDEF\t");
        sbuilder.append(cursor.getInt(5));
        sbuilder.append("\nAGI\t");
        sbuilder.append(cursor.getInt(6));
        sbuilder.append("\nLUCK\t");
        sbuilder.append(cursor.getInt(7));
        sbuilder.append("\n\n作成日:");
        sbuilder.append(convertLongToYyyymmddhhmm(cursor.getLong(8)));


        name = cursor.getString(0);

        cursor.close();


        textView = findViewById(R.id.statustext);
        textView.setText(sbuilder.toString());

        //削除メソッドの処理
        Delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                db.delete("CHARACTERS", "name = ?", new String[]{name});

                finish();
            }
        });

        //戻るボタンの処理
        returnButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }

    //秒を指定した日付の型に変換するメソッド
    public String convertLongToYyyymmddhhmm(Long date) {
        return yyyymmddhhmm.format(new Date(date));
    }
}
