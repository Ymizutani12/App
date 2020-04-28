package com.example.namebattle;

import java.util.ArrayList;

public class MagicManegement {

	//メンバ変数
	//魔法使いのリスト
	protected ArrayList<Magic> WizardList;
	protected ArrayList<Magic> PriestList;

	//コンストラクタ
	public MagicManegement() {

		//魔法使いの魔法リスト
		WizardList = new ArrayList<Magic>() {
			{
				add(new MagicFire("ファイアー"));
				add(new MagicThunder("サンダー"));

			}
		};

		//僧侶の魔法リスト
		PriestList = new ArrayList<Magic>() {
			{
				add(new MagicHeal("ヒール"));
				add(new MagicParalys("パライズ"));
				add(new MagicPoison("ポイズン"));

			}
		};

	}

}
