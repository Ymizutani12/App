package com.example.namebattle;

import java.util.ArrayList;

//攻撃のみ
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
