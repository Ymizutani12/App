package com.example.namebattle;

import java.util.ArrayList;

public class TacticsDefencelow extends Tactics{

	protected TacticsDefencelow(){

		number = 2 ;

	}


	protected void Action(Player Actionplayer, ArrayList<Player> Attackmember, Party DefenceParty) {

		Player lowDEFplayer = DefenceParty.GetMembers().get(0);

		//HPが一番減っている人を選ぶ
		for (Player p : DefenceParty.GetMembers()) {

			if (lowDEFplayer.GetDEF() > p.GetDEF() && p.GetHP() > 0) {

				lowDEFplayer = p;

			}

		}
		
		Actionplayer.Attack(lowDEFplayer);
		
		return;
		
		
	}
	
}
