package com.example.namebattle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class syousai extends AppCompatActivity {

    private CustomOpenHelper helper;
    private SQLiteDatabase db;
    private TextView textView;
    private String name;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syousai);

        Intent listnumber = getIntent();
        int position = listnumber.getIntExtra("POSITION",0);

        Button returnButton = findViewById(R.id.modoru);
        Button Delete = findViewById(R.id.delete);

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
                null,
                null,
                null,
                null,
                null
        );

        cursor.moveToFirst();

        for (int i = 0; i < position; i++) {
            cursor.moveToNext();
        }

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


        name = cursor.getString(0);

        // 忘れずに！
        cursor.close();




        textView = findViewById(R.id.statustext);
        textView.setText(sbuilder.toString());

        Delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                db.delete("CHARACTERS", "name = ?", new String[]{name});

                Intent intent = new Intent(getApplication(), charList.class);
                startActivity(intent);

                finish();
            }
        });


    }
}
