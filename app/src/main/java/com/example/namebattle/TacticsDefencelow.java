package com.example.namebattle;

import java.util.ArrayList;

public class TacticsDefencelow extends Tactics{

	
	protected void Action(Player Actionplayer, ArrayList<Player> Attackmember, Party DefenceParty) {

		Player lowDEFplayer = DefenceParty.GetMembers().get(0);

		//HPが一番減っている人を選ぶ
		for (Player p : DefenceParty.GetMembers()) {

			if (lowDEFplayer.GetDEF() > p.GetDEF()) {

				lowDEFplayer = p;

			}

		}
		
		Actionplayer.Attack(lowDEFplayer);
		
		return;
		
		
	}
	
}
