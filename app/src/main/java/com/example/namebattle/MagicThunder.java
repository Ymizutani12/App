package com.example.namebattle;

import java.util.Random;

public class MagicThunder extends Magic{
	
	//継承コンストラクタ
		MagicThunder(String name){
			super(name);
			
		}

		//消費MPの設定
		protected void MakeMagic()
		{
			this.mp= 20;
		}
		
		//魔法の効果の処理
			protected void Effect(Player effectplayer) {
				
				//ダメージ計算
				Random r = new Random();
				int damage = r.nextInt(20) + 10;
				
				effectplayer.Damage(damage);

				BattleMain.BuildLog(effectplayer.GetName() + "に" + damage + "のダメージ");
				
				return;
				
			}
	

}
