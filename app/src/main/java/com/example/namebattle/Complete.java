package com.example.namebattle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Complete extends AppCompatActivity {


    private CustomOpenHelper helper;
    private SQLiteDatabase db;
    private TextView textView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete);

        Button returnButton = findViewById(R.id.modoru);
        Button Return = findViewById(R.id.mouikai);
        Button owari = findViewById(R.id.finish);

        Intent i = getIntent();
        String charaname = i.getStringExtra("NAME");

        if(helper == null){
            helper = new CustomOpenHelper(getApplicationContext());
        }

        if(db == null){
            db = helper.getWritableDatabase();
        }

        returnButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });


        Cursor cursor = db.query(
                "CHARACTERS",
                new String[] { "name","job","hp","mp","str","def","agi","luck" },
                "name = ?",
                new String[]{charaname},
                null,
                null,
                null
        );

        cursor.moveToFirst();

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
            sbuilder.append("\n");


        // 忘れずに！
        cursor.close();

        textView = findViewById(R.id.nametext);
        textView.setText(sbuilder.toString());

        Return.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), creat.class);
                startActivity(intent);
                finish();
            }
        });

        owari.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }
}
