package com.example.namebattle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class creat extends AppCompatActivity {

    EditText editText;
    Player createplayer;
    String job;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat);

        Button returnButton = findViewById(R.id.modoru);
        Button createButton = findViewById(R.id.createOn);
        editText =  findViewById(R.id.input);
        final RadioGroup radioGroup = findViewById(R.id.radioG);



        returnButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });

        createButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();

                CustomOpenHelper helper = new CustomOpenHelper(getApplicationContext());

                SQLiteDatabase db = helper.getWritableDatabase();
                ContentValues values = new ContentValues();

                int checkedId = radioGroup.getCheckedRadioButtonId();

                if (checkedId != -1) {
                    // 選択されているラジオボタンの取得
                    RadioButton radioButton = findViewById(checkedId);// (Fragmentの場合は「getActivity().findViewById」にする)

                    // ラジオボタンのテキストを取得
                    job = radioButton.getText().toString();


                } else {
                    // 何も選択されていない場合の処理
                }

                switch (job){

                    case "戦士":
                        createplayer = new Fighter(text);
                        break;

                    case "魔法使い":
                        createplayer = new Wizard(text);
                        break;

                    case "僧侶":
                        createplayer = new Priest(text);
                        break;

                    default:
                        createplayer = new Fighter(text);


                }

                createplayer = new Fighter(text);

                values.put("name",createplayer.name);
                values.put("job",job);
                values.put("hp",createplayer.hp);
                values.put("mp",createplayer.mp);
                values.put("str",createplayer.str);
                values.put("def",createplayer.def);
                values.put("agi",createplayer.agi);
                values.put("luck",createplayer.luck);
                values.put("create_at",0);



                db.insert("CHARACTERS",null,values);


                Intent intent = new Intent(getApplication(), Complete.class);

                intent.putExtra("NAME",createplayer.name);

                startActivity(intent);
                finish();


            }
        });


    }







}
