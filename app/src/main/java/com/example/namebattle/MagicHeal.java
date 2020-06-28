package com.example.namebattle;

public class MagicHeal extends Magic{

	
	//継承コンストラクタ
		MagicHeal(String name){
			super(name);
			
		}
		
		

		//消費MPの設定
		protected void MakeMagic()
		{
			this.mp= 20;
		}
		

		//魔法の効果の処理
			protected void Effect(Player effectplayer) {
				
				//回復計算
				int kaihuku = 50;
				
				effectplayer.Heal(kaihuku);

				BattleMain.BuildLog(effectplayer.GetName() + "は" + kaihuku + "の回復");
				
				return;
				
			}
	
	
	
	
}
