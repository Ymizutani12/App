package com.example.namebattle;

import java.util.ArrayList;
import java.util.Random;

public class TacticsGangan extends Tactics{

	protected TacticsGangan(){

		number = 1 ;

	}


	//指示を出す
	@Override
		protected void Action(Player Actionplayer,ArrayList<Player> Attackmember,Party DefenceParty) {
			
			Random r = new Random();

			int defnumber;

			while(true){

				defnumber = r.nextInt(DefenceParty.GetMembers().size());

				if(DefenceParty.GetMembers().get(defnumber).GetHP() > 0 ){
					break;
				}

			}

			Actionplayer.Attack(DefenceParty.GetMembers().get(defnumber));

			return;
			
		}

}
