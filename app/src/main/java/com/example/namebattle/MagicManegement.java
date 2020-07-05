package com.example.namebattle;

import java.util.ArrayList;

//魔法管理
public class MagicManegement {

	//メンバ変数
	protected ArrayList<Magic> WizardList;
	protected ArrayList<Magic> PriestList;
	protected ArrayList<Magic> KnightList;

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

		//騎士の魔法リスト
		KnightList = new ArrayList<Magic>() {
			{
				add(new MagicHeal("ヒール"));
			}
		};

	}

}
