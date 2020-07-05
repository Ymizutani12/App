package com.example.namebattle;

import java.util.ArrayList;
import java.util.Random;

//命大事に作戦
public class TacticsImportantlife extends Tactics {

	protected TacticsImportantlife(){

	}

	//指示を出す
	protected void Action(Player Actionplayer, ArrayList<Player> Attackmember, Party DefenceParty) {

		//一番HPの少ない人を選択
		Player lowHPplayer =  SelectLowPlayer(Attackmember);

		//魔法があるか、HPが1でも減っている人がいれば回復魔法を探し回復
		if (Actionplayer.GetMagicList().size() > 0 && lowHPplayer.GetMaxHP() - lowHPplayer.GetHP() > 0) {

			for (Magic m : Actionplayer.GetMagicList()) {

				if (m instanceof MagicHeal) {

					if (Actionplayer.GetMP() - m.GetMP() >= 0) {
						
						Actionplayer.HealAction(lowHPplayer,SelectPlayer(DefenceParty));
						
						return;
					}

				}

			}

		}

		//回復に該当しない場合通常攻撃

		Actionplayer.Attack(SelectPlayer(DefenceParty));

		return;

	}

}
