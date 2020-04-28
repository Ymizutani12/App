package com.example.namebattle;

import java.util.Random;

public class MagicFire extends Magic {

	//継承コンストラクタ
	MagicFire(String name) {
		super(name);

	}

	//消費MPの設定
	protected void MakeMagic() {
		this.mp = 10;
	}

	//魔法の効果の処理
	protected void Effect(Player effectplayer) {

		//ダメージ計算
		Random r = new Random();
		int damage = r.nextInt(20) + 10;

		effectplayer.Damage(damage);

		System.out.println(effectplayer.GetName() + "に" + damage + "のダメージ");

		return;

	}

}
