package com.example.namebattle;

public class MagicPoison extends Magic {

	//継承コンストラクタ
	public MagicPoison(String name) {
		super(name);

	}

	//消費MPの設定
	protected void MakeMagic() {
		this.mp = 10;
	}

	//魔法の効果の処理
	protected void Effect(Player effectplayer) {

		//毒状態か確認して毒
		if (effectplayer.poison == true) {

			BattleMain.BuildLog(effectplayer.GetName() + "は毒にかかっている");

		} else {

			//毒付与
			effectplayer.poison = true;
			BattleMain.BuildLog(effectplayer.GetName() + "は毒にかかった");
		}

		return;

	}

}
