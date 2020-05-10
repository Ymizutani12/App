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

public class Complete extends AppCompatActivity {


    //各変数の宣言
    private CustomOpenHelper helper;
    private SQLiteDatabase db;
    private TextView textView;
    DateFormat yyyymmddhhmm = new SimpleDateFormat("yyyy/MM/dd HH:mm");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete);


        Button returnButton = findViewById(R.id.modoru);
        Button Return = findViewById(R.id.mouikai);
        Button owari = findViewById(R.id.delete);

        Intent i = getIntent();
        String charaname = i.getStringExtra("NAME");

        if(helper == null){
            helper = new CustomOpenHelper(getApplicationContext());
        }

        if(db == null){
            db = helper.getWritableDatabase();
        }

        //前の画面から受け取った名前のもののステータスを選択
        Cursor cursor = db.query(
                "CHARACTERS",
                new String[] { "name","job","hp","mp","str","def","agi","luck","create_at" },
                "name = ?",
                new String[]{charaname},
                null,
                null,
                null
        );

        cursor.moveToFirst();

        //ステータス表示
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

        cursor.close();

        textView = findViewById(R.id.statustext);
        textView.setText(sbuilder.toString());

        //もう一度作成の処理
        Return.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), creat.class);
                startActivity(intent);
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

        //作成終了の処理
        owari.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }

    //秒を宣言したフォーマットに直すメソッド
    public String convertLongToYyyymmddhhmm(Long date) {
        return yyyymmddhhmm.format(new Date(date));
    }
}
