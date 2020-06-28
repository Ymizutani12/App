package com.example.namebattle;

public class MagicParalys extends Magic {

	//継承コンストラクタ
	public MagicParalys(String name) {
		super(name);

	}

	//消費MPの設定
	protected void MakeMagic() {
		this.mp = 10;
	}

	//魔法の効果の処理
	protected void Effect(Player effectplayer) {

		//麻痺状態か確認して麻痺
		if(effectplayer.paralys == true) {

			BattleMain.BuildLog(effectplayer.GetName() + "は麻痺にかかっている");
			
		}else {
			
			//麻痺付与
			effectplayer.paralys = true;
			BattleMain.BuildLog(effectplayer.GetName() + "は麻痺にかかった");
		}

		return;

	}

}
