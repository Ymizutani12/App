package com.example.namebattle;

import java.util.ArrayList;

// プレイヤー：戦士
public class Fighter extends Player {


	// =======================
	// コンストラクタ
	// =======================
	public Fighter(String name) {
		super(name);
	}

	// =======================
	// Getter / Setter
	// =======================

	//職業名を返す
	@Override
	public String GetJOB(){

		return "戦士";

	}

	// =======================
	// protected メソッド
	// =======================
	/**
	 * 名前(name)からキャラクターに必要なパラメータを生成する
	 */
	@Override
	protected void MakeCharacter() {
		// 戦士のパラメータを名前から生成する
		this.hp = GetNumber(0, 200) + 100;
		this.mp = 0;
		this.str = GetNumber(1, 70) + 30;
		this.def = GetNumber(2, 70) + 30;
		this.luck = GetNumber(3, 100) + 1;
		this.agi = GetNumber(4, 50) + 1;
		this.maxhp = this.hp;
		this.maxmp = this.mp;
		this.magiclist = new ArrayList<>();
		this.paralys = false;
		this.poison = false;
	}

	/**
	 * 対象プレイヤーに攻撃を行う
	 * @param defender : 対象プレイヤー
	 */
	@Override
	public void Attack(Player defender) {
		// 与えるダメージを求める
		BattleMain.BuildLog(GetName() + "の攻撃！");
		int damage = CalcDamage(defender);

		// 求めたダメージを対象プレイヤーに与える
		//ダメージがない場合与えられないを表示
		if (damage <= 0) {

			BattleMain.BuildLog(GetName() + "はダメージを与えられない!");

		} else {
			BattleMain.BuildLog(defender.GetName() + "に" + damage + "のダメージ！");
			defender.Damage(damage);
		}

		// 倒れた判定
		if (defender.GetHP() <= 0) {
			BattleMain.BuildLog(defender.GetName() + "は力尽きた...");
		}
	}


}
