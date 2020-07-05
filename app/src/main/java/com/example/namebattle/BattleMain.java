package com.example.namebattle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BattleMain extends AppCompatActivity {

    //static変数

    //バトルログ
    static TextView Log;
    //味方作戦セット用
    static int TacticsNumber;


    Party AllyParty;
    Party EnemyParty;
    GameManager GameMaster;
    TextView MemberText1, MemberText2, MemberText3;
    TextView EnemyText1 , EnemyText2 , EnemyText3 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_main);

        Button TacticsSelectChange = findViewById(R.id.TacticsButton);
        Button NextTurnButton = findViewById(R.id.NextButton);
        AllyParty = getIntent().getParcelableExtra("ALLYPARTY");
        EnemyParty = getIntent().getParcelableExtra("ENEMYPARTY");

        //ゲーム進行クラスがない場合作成
        if(GameMaster == null ){
            GameMaster = new GameManager(AllyParty,EnemyParty);
        }

        Log = findViewById(R.id.BattleLog);
        TacticsNumber = 0;

        //初期の作戦セット画面
        Intent intent = new Intent(getApplication(), TacticsSelect.class);
        startActivity(intent);

        //初期ステータス表示
        MemberText1 = findViewById(R.id.MemberBox1);
        MemberText2 = findViewById(R.id.MemberBox2);
        MemberText3 = findViewById(R.id.MemberBox3);
        EnemyText1 = findViewById(R.id.EnemyBox1);
        EnemyText2 = findViewById(R.id.EnemyBox2);
        EnemyText3 = findViewById(R.id.EnemyBox3);

        MemberText1.setText(StatusBuilder(GameMaster.GetAlly().members.get(0)));
        MemberText2.setText(StatusBuilder(GameMaster.GetAlly().members.get(1)));
        MemberText3.setText(StatusBuilder(GameMaster.GetAlly().members.get(2)));

        EnemyText1.setText(StatusBuilder(GameMaster.GetEnemy().members.get(0)));
        EnemyText2.setText(StatusBuilder(GameMaster.GetEnemy().members.get(1)));
        EnemyText3.setText(StatusBuilder(GameMaster.GetEnemy().members.get(2)));


        //ボタンが押されたら作戦セット画面へ
        TacticsSelectChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplication(), TacticsSelect.class);
                startActivity(intent);

            }
        });

        //次のターンへ進める
        NextTurnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GameMaster.TurnOne();

                //ステータス更新
                MemberText1.setText(StatusBuilder(GameMaster.GetAlly().members.get(0)));
                MemberText2.setText(StatusBuilder(GameMaster.GetAlly().members.get(1)));
                MemberText3.setText(StatusBuilder(GameMaster.GetAlly().members.get(2)));

                EnemyText1.setText(StatusBuilder(GameMaster.GetEnemy().members.get(0)));
                EnemyText2.setText(StatusBuilder(GameMaster.GetEnemy().members.get(1)));
                EnemyText3.setText(StatusBuilder(GameMaster.GetEnemy().members.get(2)));

                //どちらかのパーティーが全滅したらバトル結果へ
                if(GameMaster.LifeJudge()){
                    Intent i = new Intent(getApplicationContext(),Result.class);

                    i.putExtra("ALLYPARTY", GameMaster.GetAlly());

                    i.putExtra("ENEMYPARTY",  GameMaster.GetEnemy());

                    startActivity(i);
                    finish();
                }

            }
        });


    }


    @Override
    protected void onResume() {
        super.onResume();

        //作戦の反映、画面更新
        GameMaster.SetTactics(TacticsNumber);
        TextView tactics = findViewById(R.id.Tactics);

        switch (GameMaster.GetAlly().GetTacticsNumber()){

            case 0:
                tactics.setText("攻撃のみ");
                break;

            case 1:
                tactics.setText("ガンガン攻撃");
                break;

            case 2:
                tactics.setText("バランスよく");
                break;

            case 3:
                tactics.setText("集中攻撃");
                break;

            case 4:
                tactics.setText("命を大事に");
                break;

        }


    }


    //ステータス表示のテンプレ
    private StringBuilder StatusBuilder(Player p){

        StringBuilder builder = new StringBuilder();
        builder.append(p.GetName());
        builder.append("\n");
        builder.append("HP " + p.GetHP() + "/" + p.GetMaxHP());
        builder.append("\n");
        builder.append("MP " + p.GetMP() + "/" + p.GetMaxMP());
        builder.append("\n");

        if(p.GetParalys()){
            builder.append("麻痺");
        }

        builder.append("　");

        if(p.GetPoison()){
            builder.append("毒");
        }

        return  builder;

    }

    //バトルログの更新メソッド
    protected static void BuildLog(String log){
        StringBuilder BuilderLog = new StringBuilder();

        BuilderLog.append(log + "\n");
        BuilderLog.append(Log.getText().toString());

        Log.setText(BuilderLog);

        return;
    }

}
