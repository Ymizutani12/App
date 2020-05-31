package com.example.namebattle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BattleStart extends AppCompatActivity {

    private CustomOpenHelper helper;
    private SQLiteDatabase db;

    //ステータスを表示するためのリスト
    private ArrayList<String> name ;
    private ArrayList<String> job ;
    private ArrayList<String> status ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_start);

        Intent intent = getIntent();
        ArrayList<String> MemberList = intent.getStringArrayListExtra("MEMBERLIST");




        //何も入ってなければいれる
        if(helper == null){
            helper = new CustomOpenHelper(getApplicationContext());
        }

        if(db == null){
            db = helper.getWritableDatabase();
        }


        //データテーブルから取り出すもの、条件を指定
        Cursor cursor = db.query(
                "CHARACTERS",
                new String[] { "name","job","hp","mp","str","def","agi" },
                "name = ? or name = ? or name = ?",
                new String[]{MemberList.get(0),MemberList.get(1),MemberList.get(2)},
                null,
                null,
                null
        );

        //リスト初期化
        name = new ArrayList<String>(){};
        job = new ArrayList<String>(){};
        status = new ArrayList<String>(){};

        cursor.moveToFirst();


        //入ってるデータをそれぞれのリストへ追加
        for (int i = 0; i < cursor.getCount(); i++) {
            name.add(cursor.getString(0));
            job.add(cursor.getString(1));
            status.add("HP:" + cursor.getInt(2)
                    + " MP:" + cursor.getInt(3)
                    + " STR:" + cursor.getInt(4)
                    + " DEF:" + cursor.getInt(5)
                    + " AGI:" + cursor.getInt(6));

            cursor.moveToNext();
        }

        cursor.close();

        //画面のテキストボックス宣言
        TextView MemberName1 = findViewById(R.id.MemberNameBox1),MemberName2 = findViewById(R.id.MemberNameBox2),MemberName3 = findViewById(R.id.MemberNameBox3);
        TextView MemberJob1 = findViewById(R.id.MemberJobBox1),MemberJob2 = findViewById(R.id.MemberJobBox2),MemberJob3 = findViewById(R.id.MemberJobBox3);
        TextView MemberStatus1 = findViewById(R.id.MemberStatusBox1),MemberStatus2 = findViewById(R.id.MemberStatusBox2),MemberStatus3 = findViewById(R.id.MemberStatusBox3);

        final TextView EnemyName1 = findViewById(R.id.EnemyNameBox1),EnemyName2 = findViewById(R.id.EnemyNameBox2),EnemyName3 = findViewById(R.id.EnemyNameBox3);
        final TextView EnemyJob1 = findViewById(R.id.EnemyJobBox1),EnemyJob2 = findViewById(R.id.EnemyJobBox2),EnemyJob3 = findViewById(R.id.EnemyJobBox3);
        final TextView EnemyStatus1 = findViewById(R.id.EnemyStatusBox1),EnemyStatus2 = findViewById(R.id.EnemyStatusBox2),EnemyStatus3 = findViewById(R.id.EnemyStatusBox3);

        //自分のパーティ作成
        Party AllyParty = new Party();

        for(int i=0; i<name.size()-1; i++){

            switch (job.get(i)){

                case "戦士":
                    AllyParty.AppendPlayer(new Fighter(name.get(i)));
                    break;

                case "魔法使い":
                    AllyParty.AppendPlayer(new Wizard(name.get(i)));
                    break;

                case "僧侶":
                    AllyParty.AppendPlayer(new Priest(name.get(i)));
                    break;

                default:
                    AllyParty.AppendPlayer(new Fighter(name.get(i)));
                    break;

            }

        }

        MemberName1.setText(name.get(0));
        MemberName2.setText(name.get(1));
        MemberName3.setText(name.get(2));
        MemberJob1.setText(job.get(0));
        MemberJob2.setText(job.get(1));
        MemberJob3.setText(job.get(2));
        MemberStatus1.setText(status.get(0));
        MemberStatus2.setText(status.get(1));
        MemberStatus3.setText(status.get(2));


        //敵パーティ作成
        Party EnemyParty = new EnemyList().GetEnemy();

        EnemyName1.setText(EnemyParty.GetPlayerName(0));
        EnemyName2.setText(EnemyParty.GetPlayerName(1));
        EnemyName3.setText(EnemyParty.GetPlayerName(2));
        EnemyJob1.setText(EnemyParty.GetPlayerJob(0));
        EnemyJob2.setText(EnemyParty.GetPlayerJob(1));
        EnemyJob3.setText(EnemyParty.GetPlayerJob(2));
        EnemyStatus1.setText(EnemyParty.GetPlayerStatus(0));
        EnemyStatus2.setText(EnemyParty.GetPlayerStatus(1));
        EnemyStatus3.setText(EnemyParty.GetPlayerStatus(2));


        //戻るボタンの処理
        Button returnButton = findViewById(R.id.modoru);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //相手再選択処理
        Button reselection = findViewById(R.id.ReselectionButton);

        reselection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //敵パーティ作成
                Party EnemyParty = new EnemyList().GetEnemy();

                EnemyName1.setText(EnemyParty.GetPlayerName(0));
                EnemyName2.setText(EnemyParty.GetPlayerName(1));
                EnemyName3.setText(EnemyParty.GetPlayerName(2));
                EnemyJob1.setText(EnemyParty.GetPlayerJob(0));
                EnemyJob2.setText(EnemyParty.GetPlayerJob(1));
                EnemyJob3.setText(EnemyParty.GetPlayerJob(2));
                EnemyStatus1.setText(EnemyParty.GetPlayerStatus(0));
                EnemyStatus2.setText(EnemyParty.GetPlayerStatus(1));
                EnemyStatus3.setText(EnemyParty.GetPlayerStatus(2));

            }
        });

        //バトル開始画面へ
        Button buttlestart = findViewById(R.id.StartButton);
        buttlestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplication(), BattleStart.class);
                startActivity(intent);

            }
        });


    }

}
