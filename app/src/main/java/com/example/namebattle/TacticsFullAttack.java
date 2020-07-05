package com.example.namebattle;

import java.util.ArrayList;
import java.util.Random;

//ガンガン攻める作戦
public class TacticsFullAttack extends Tactics{

	protected TacticsFullAttack(){

		number = 2 ;

	}


	protected void Action(Player Actionplayer, ArrayList<Player> Attackmember, Party DefenceParty) {

		Player defender = SelectPlayer(DefenceParty);

		if(Actionplayer.GetMagicList().size() > 0 && Actionplayer.GetMP() > 10){

			Actionplayer.MagicAction(defender);
			return;

		}

		Actionplayer.Attack(defender);
		
		return;
		
		
	}
	
}
