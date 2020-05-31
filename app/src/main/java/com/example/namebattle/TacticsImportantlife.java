package com.example.namebattle;

import java.util.ArrayList;
import java.util.Random;

//命大事に作戦
public class TacticsImportantlife extends Tactics {

	//フィールド変数

	//指示を出す
	protected void Action(Player Actionplayer, ArrayList<Player> Attackmember, Party DefenceParty) {

		Player lowHPplayer = Attackmember.get(0);

		//HPが一番減っている人を選ぶ
		for (Player p : Attackmember) {

			if (lowHPplayer.maxhp - lowHPplayer.GetHP() < p.maxhp - p.GetHP()) {

				lowHPplayer = p;

			}

		}

		//魔法があるか、HPが1でも減っている人がいれば回復魔法を探し回復
		if (Actionplayer.magiclist.size() > 0 && lowHPplayer.maxhp - lowHPplayer.GetHP() > 0) {

			for (Magic m : Actionplayer.magiclist) {

				if (m instanceof MagicHeal) {

					if (Actionplayer.mp - m.GetMP() >= 0) {
						
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
