package com.example.namebattle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //キャラ作成ボタンの処理
        Button CharButton = findViewById(R.id.charbutton);
        CharButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //キャラ作成画面へ
                Intent intent = new Intent(getApplication(), charList.class);
                startActivity(intent);

            }

        });

        //バトル開始ボタンの処理
        Button BattleButton = findViewById(R.id.battlebutton);
        BattleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //パーティ編成の画面へ
                Intent intent = new Intent(getApplication(), PartyFormation.class);
                startActivity(intent);

            }

        });

    }
}
