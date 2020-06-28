package com.example.namebattle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    Party AllyParty;
    Party EnemyParty;


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Button Remuch = findViewById(R.id.RemuchButton);
        Button NextBattle = findViewById(R.id.NextBattleButton);
        Button BattleFinish = findViewById(R.id.BattleFinishButton);

        AllyParty = getIntent().getParcelableExtra("ALLYPARTY");
        EnemyParty = getIntent().getParcelableExtra("ENEMYPARTY");

        TextView MemberText1 = findViewById(R.id.MemberBox1);
        TextView MemberText2 = findViewById(R.id.MemberBox2);
        TextView MemberText3 = findViewById(R.id.MemberBox3);
        TextView EnemyText1 = findViewById(R.id.EnemyBox1);
        TextView EnemyText2 = findViewById(R.id.EnemyBox2);
        TextView EnemyText3 = findViewById(R.id.EnemyBox3);

        MemberText1.setText(StatusBuilder(AllyParty.members.get(0)));
        MemberText2.setText(StatusBuilder(AllyParty.members.get(1)));
        MemberText3.setText(StatusBuilder(AllyParty.members.get(2)));

        EnemyText1.setText(StatusBuilder(EnemyParty.members.get(0)));
        EnemyText2.setText(StatusBuilder(EnemyParty.members.get(1)));
        EnemyText3.setText(StatusBuilder(EnemyParty.members.get(2)));

        MemberText1.setBackgroundResource(AllyParty.members.get(0).GetHP() <= 0 ? R.drawable.text_waku_dre : R.drawable.text_waku);
        MemberText2.setBackgroundResource(AllyParty.members.get(1).GetHP() <= 0 ? R.drawable.text_waku_dre : R.drawable.text_waku);
        MemberText3.setBackgroundResource(AllyParty.members.get(2).GetHP() <= 0 ? R.drawable.text_waku_dre : R.drawable.text_waku);

        EnemyText1.setBackgroundResource(EnemyParty.members.get(0).GetHP() <= 0 ? R.drawable.text_waku_dre : R.drawable.text_waku);
        EnemyText2.setBackgroundResource(EnemyParty.members.get(1).GetHP() <= 0 ? R.drawable.text_waku_dre : R.drawable.text_waku);
        EnemyText3.setBackgroundResource(EnemyParty.members.get(2).GetHP() <= 0 ? R.drawable.text_waku_dre : R.drawable.text_waku);

        ImageView image = findViewById(R.id.imageView);

        if(AllyParty.GetMembers().get(0).GetHP() <= 0 && AllyParty.GetMembers().get(1).GetHP() <= 0 && AllyParty.GetMembers().get(1).GetHP() <= 0 ){

            image.setImageResource(R.drawable.pose_lose_boy);

        }else{

            image.setImageResource(R.drawable.pose_win_boy);

        }


        //再戦
        Remuch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), BattleMain.class);

                AllyParty.Reset();
                intent.putExtra("ALLYPARTY", AllyParty);

                EnemyParty.Reset();
                intent.putExtra("ENEMYPARTY",  EnemyParty);

                startActivity(intent);
                finish();
            }
        });

        //次のバトルへ
        NextBattle.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //対戦終了の処理
        BattleFinish.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });


    }

    private StringBuilder StatusBuilder(Player p){

        StringBuilder builder = new StringBuilder();
        builder.append(p.GetName());
        builder.append("\n");
        builder.append("HP " + p.GetHP() + "/" + p.GetMaxHP());
        builder.append("\n");
        builder.append("MP " + p.GetMP() + "/" + p.GetMaxMP());
        builder.append("\n");

        return  builder;

    }

}
