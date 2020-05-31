package com.example.namebattle;

import java.util.ArrayList;
import java.util.Random;

public class TacticsGangan extends Tactics{

	
	//指示を出す
		protected void Action(Player Actionplayer,ArrayList<Player> Attackmember,Party DefenceParty) {
			
			Random r = new Random();

			int defnumber = r.nextInt(DefenceParty.GetMembers().size());

			Actionplayer.Attack(DefenceParty.GetMembers().get(defnumber));

			return;
			
		}
		
	
}
