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
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class creat extends AppCompatActivity {

    //各変数の宣言
    private EditText editText;
    private Player createplayer;
    private String job;
    private int checkedId;
    String text;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat);

        Button returnButton = findViewById(R.id.modoru);
        Button createButton = findViewById(R.id.createOn);
        editText = findViewById(R.id.input);
        final RadioGroup radioGroup = findViewById(R.id.radioG);


        //キャラ作成ボタンの処理
        createButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                text = editText.getText().toString();
                checkedId = radioGroup.getCheckedRadioButtonId();

                if (checkedId != -1 ) {

                    if(!(text.equals(""))){
                        Createmethod();
                    }else{
                        Toast.makeText(getApplicationContext(), "名前が入力されていません", Toast.LENGTH_LONG).show();
                    }

                } else {
                    // 何も選択されていない場合の処理
                    Toast.makeText(getApplicationContext(), "職業が選択されていません", Toast.LENGTH_LONG).show();

                }


            }

        });

        //戻るボタンの処理
        returnButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), charList.class);
                startActivity(intent);
                finish();
            }
        });

    }





    private void Createmethod(){

        // 選択されているラジオボタンの取得
        RadioButton radioButton = findViewById(checkedId);

        // ラジオボタンのテキストを取得
        job = radioButton.getText().toString();

        //　テキストに記述した名前の取得
        String text = editText.getText().toString();

                CustomOpenHelper helper = new CustomOpenHelper(getApplicationContext());

                SQLiteDatabase db = helper.getWritableDatabase();
                ContentValues values = new ContentValues();




                //ラジオボタンに応じてキャラ作成
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


                //各ステータスをそれぞれのテーブルに格納
                Long nowTime = System.currentTimeMillis();

                values.put("name",createplayer.GetName());
                values.put("job",job);
                values.put("hp",createplayer.GetHP());
                values.put("mp",createplayer.GetMP());
                values.put("str",createplayer.GetSTR());
                values.put("def",createplayer.GetDEF());
                values.put("agi",createplayer.GetAGI());
                values.put("luck",createplayer.GetLUCK());
                values.put("create_at",nowTime);

                db.insert("CHARACTERS",null,values);

                Intent intent = new Intent(getApplication(), Complete.class);

                //次の画面でステータスを表示させるため名前を渡す
                intent.putExtra("NAME",createplayer.GetName());

                startActivity(intent);
                finish();


            }




    }


