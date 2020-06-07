package com.example.namebattle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BattleMain extends AppCompatActivity {

    static Party AllyParty;
    Party EnemyParty;
    static TextView Log;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_main);

        Button TacticsSelectChange = findViewById(R.id.TacticsButton);
        AllyParty = getIntent().getParcelableExtra("ALLYPARTY");
        EnemyParty = getIntent().getParcelableExtra("ENEMYPARTY");
        Log = findViewById(R.id.BattleLog);

        TacticsSelectChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplication(), TacticsSelect.class);

                intent.putExtra("ALLYPARTY", AllyParty);

                startActivity(intent);

            }
        });



    }


    @Override
    protected void onResume() {
        super.onResume();

        TextView MemberText1 = findViewById(R.id.MemberBox1),MemberText2 = findViewById(R.id.MemberBox2),MemberText3 = findViewById(R.id.MemberBox3);
        TextView EnemyText1 = findViewById(R.id.EnemyBox1),EnemyText2 = findViewById(R.id.EnemyBox2),EnemyText3 = findViewById(R.id.EnemyBox3);

        AllyParty = getIntent().getParcelableExtra("ALLYPARTY");
        EnemyParty = getIntent().getParcelableExtra("ENEMYPARTY");

        MemberText1.setText(StatusBuilder(AllyParty.members.get(0)));
        MemberText2.setText(StatusBuilder(AllyParty.members.get(1)));
        MemberText3.setText(StatusBuilder(AllyParty.members.get(2)));


        EnemyText1.setText(StatusBuilder(EnemyParty.members.get(0)));
        EnemyText2.setText(StatusBuilder(EnemyParty.members.get(1)));
        EnemyText3.setText(StatusBuilder(EnemyParty.members.get(2)));



        TextView tactics = findViewById(R.id.Tactics);

        switch (AllyParty.GetTacticsNumber()){

            case 0:
                tactics.setText("攻撃のみ");
                break;

            case 1:
                tactics.setText("防御低い敵に集中");
                break;

            case 2:
                tactics.setText("バッチリ行動");
                break;

            case 3:
                tactics.setText("集中");
                break;

            case 4:
                tactics.setText("命を大事に");
                break;

        }




    }


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


    protected void SetTactics(int i){

        AllyParty.SetTacticsNumber(i);

    }

    protected static void BuildLog(String log){
        StringBuilder BuilderLog = new StringBuilder();

        BuilderLog.append(Log.getText().toString());
        BuilderLog.append(log);

        return;
    }

}
