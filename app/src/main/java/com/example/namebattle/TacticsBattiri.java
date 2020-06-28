package com.example.namebattle;

import java.util.ArrayList;
import java.util.Random;

import javax.crypto.spec.DESedeKeySpec;

public class TacticsBattiri extends Tactics{

	protected TacticsBattiri(){

		number = 3 ;

	}

	
	//指示を出す
		protected void Action(Player Actionplayer, ArrayList<Player> Attackmember, Party DefenceParty) {

			
			//初期化
			Player lowHPplayer = null;

			Random r = new Random();

			//HPが一番減っている人を選ぶ
			for (Player p : Attackmember) {

				if(lowHPplayer == null && p.GetHP() > 0){
					lowHPplayer = p;
					continue;
				}

				if (lowHPplayer.GetMaxHP() - lowHPplayer.GetHP() < p.GetMaxHP() - p.GetHP() && p.GetHP() > 0) {

					lowHPplayer = p;

				}
			}

			//魔法があるか、HPが半分以上減っている人がいれば回復魔法を探し回復
			if (Actionplayer.GetMagicList().size() > 0 ) {

				for (Magic m : Actionplayer.GetMagicList()) {

					if (m instanceof MagicHeal && lowHPplayer.GetMaxHP() /2 > lowHPplayer.GetHP()) {

						if (Actionplayer.GetMP() - m.GetMP() >= 0) {
							
							Actionplayer.HealAction(lowHPplayer);
							
							return;
						}

					}

				}
				//回復がない場合
				Actionplayer.MagicAction(SelectPlayer(DefenceParty));
				return;

			}

			//回復に該当しない場合通常攻撃

			Actionplayer.Attack(SelectPlayer(DefenceParty));

			return;

		}
	
	
	
}
