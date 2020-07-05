package com.example.namebattle;

import java.util.ArrayList;

//集中攻撃作戦
public class TacticsSyuutyuu extends Tactics {

    protected TacticsSyuutyuu() {

        number = 4;

    }

    //指示を出す
    @Override
    protected void Action(Player Actionplayer, ArrayList<Player> Attackmember, Party DefenceParty) {

        Player defender = null ;

        for(Player p : DefenceParty.GetMembers()){

            if(p.GetHP() > 0){
                defender = p ;
                break;
            }
        }

        Actionplayer.Attack(defender);

        return;

    }

}
