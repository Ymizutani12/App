package com.example.namebattle;

import java.util.ArrayList;

public class TacticsAttackonly extends Tactics{

	protected TacticsAttackonly(){

		number = 1 ;

	}

	//指示を出す
	@Override
		protected void Action(Player Actionplayer,ArrayList<Player> Attackmember,Party DefenceParty) {
			
			Player defender = SelectPlayer(DefenceParty);

			Actionplayer.Attack(defender);

			return;
			
		}

}
