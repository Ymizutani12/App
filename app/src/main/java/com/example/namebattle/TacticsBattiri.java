package com.example.namebattle;

import java.util.ArrayList;
import java.util.Random;

public class TacticsBattiri extends Tactics{

	
	//指示を出す
		protected void Action(Player Actionplayer, ArrayList<Player> Attackmember, Party DefenceParty) {

			
			//初期化
			Player lowHPplayer = Attackmember.get(0);

			Random r = new Random();

			//攻撃するプレイヤーをランダムで決める
			int defnumber = r.nextInt(DefenceParty.GetMembers().size());
			
			
			//HPが一番減っている人を選ぶ
			for (Player p : Attackmember) {

				if (lowHPplayer.maxhp - lowHPplayer.GetHP() < p.maxhp - p.GetHP()) {

					lowHPplayer = p;

				}

			}

			//魔法があるか、HPが1でも減っている人がいれば回復魔法を探し回復
			if (Actionplayer.magiclist.size() > 0 && lowHPplayer.maxhp /2 > lowHPplayer.GetHP()) {

				for (Magic m : Actionplayer.magiclist) {

					if (m instanceof MagicHeal && lowHPplayer.maxhp /2 > lowHPplayer.GetHP()) {

						if (Actionplayer.mp - m.GetMP() >= 0) {
							
							Actionplayer.HealAction(lowHPplayer);
							
							return;
						}

					}

				}
				//回復がない場合
				Actionplayer.MagicAction(DefenceParty.GetMembers().get(defnumber));
				return;

			}

			//回復に該当しない場合通常攻撃

			Actionplayer.Attack(DefenceParty.GetMembers().get(defnumber));

			return;

		}
	
	
	
}
