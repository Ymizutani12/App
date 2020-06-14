package com.example.namebattle;

import java.util.ArrayList;
import java.util.Random;

//命大事に作戦
public class TacticsImportantlife extends Tactics {

	protected TacticsImportantlife(){

	}

	//指示を出す
	protected void Action(Player Actionplayer, ArrayList<Player> Attackmember, Party DefenceParty) {

		Player lowHPplayer = Attackmember.get(0);

		//HPが一番減っている人を選ぶ
		for (Player p : Attackmember) {

			if (lowHPplayer.GetMaxHP() - lowHPplayer.GetHP() < p.GetMaxHP() - p.GetHP()) {

				lowHPplayer = p;

			}

		}

		//魔法があるか、HPが1でも減っている人がいれば回復魔法を探し回復
		if (Actionplayer.GetMagicList().size() > 0 && lowHPplayer.GetMaxHP() - lowHPplayer.GetHP() > 0) {

			for (Magic m : Actionplayer.GetMagicList()) {

				if (m instanceof MagicHeal) {

					if (Actionplayer.GetMP() - m.GetMP() >= 0) {
						
						Actionplayer.HealAction(lowHPplayer);
						
						return;
					}

				}

			}

		}

		//回復に該当しない場合通常攻撃
		Random r = new Random();

		int defnumber = r.nextInt(DefenceParty.GetMembers().size());

		Actionplayer.Attack(DefenceParty.GetMembers().get(defnumber));

		return;

	}

}
