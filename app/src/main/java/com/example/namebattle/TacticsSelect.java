package com.example.namebattle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

public class TacticsSelect extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tactics_select);

        final RadioGroup radioGroup = findViewById(R.id.TacticsRadio);
        final Button DecisionButton = findViewById(R.id.Decision);

        DecisionButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                int checkedId = radioGroup.getCheckedRadioButtonId();

                if (checkedId != -1 ) {

                    switch (checkedId){

                        case R.id.Gangan:
                            BattleMain.TacticsNumber = 0 ;
                            break;

                        case R.id.DefenceLow:
                            BattleMain.TacticsNumber = 1 ;
                            break;

                        case R.id.Battiri:
                            BattleMain.TacticsNumber = 2 ;
                            break;

                        case R.id.Syuutyuu:
                            BattleMain.TacticsNumber = 3 ;
                            break;

                        case R.id.ImportantLife:
                            BattleMain.TacticsNumber = 4 ;
                            break;
                    }

                    finish();

                } else {
                    // 何も選択されていない場合の処理
                    Toast.makeText(getApplicationContext(), "作戦を選択してください", Toast.LENGTH_LONG).show();

                }


            }

        });




    }


}
