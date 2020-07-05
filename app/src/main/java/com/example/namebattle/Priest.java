package com.example.namebattle;

import java.util.ArrayList;
import java.util.Random;

// プレイヤー：僧侶
public class Priest extends Player {

	// =======================
	// フィールド変数
	// =======================

	// =======================
	// コンストラクタ
	// =======================
	public Priest(String name) {

		super(name);

	}

	// =======================
	// Getter / Setter
	// =======================
	@Override
	public String GetJOB(){

		return "僧侶";

	}

	// =======================
	// protected メソッド
	// =======================
	/**
	 * 名前(name)からキャラクターに必要なパラメータを生成する
	 */
	@Override
	protected void MakeCharacter() {
		// 魔法使いのパラメータを名前から生成する
		this.hp = GetNumber(0, 120) + 80;
		this.mp = GetNumber(5, 30) + 20;
		this.str = GetNumber(1, 60) + 10;
		this.def = GetNumber(2, 60) + 10;
		this.luck = GetNumber(3, 100) + 1;
		this.agi = GetNumber(4, 40) + 20;
		this.maxhp = this.hp;
		this.maxmp = this.mp;

		MagicManegement mane = new MagicManegement();
		this.magiclist = new ArrayList<Magic>(mane.PriestList);

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

	//魔法行動
	protected void MagicAction(Player effectplayer) {

		Random r = new Random();
		ArrayList<Magic> list = new ArrayList<Magic>(this.magiclist);
		int magicnumber=0;

		//魔法をランダム選択してmp確認して使用
		//mpがない場合は他に使える魔法があるか探す
		//回復魔法が選択された場合自分にかける
		for(int i=0; i<this.magiclist.size(); i++) {

			if(list.size()<=1) {
				magicnumber = 0;
			}else {
				magicnumber = r.nextInt(list.size());
			}

			if(list.get(magicnumber).mp <= this.GetMP()) {

				if(list.get(magicnumber) instanceof MagicHeal){

					HealAction(this,effectplayer);
					return;
				}else{
					BattleMain.BuildLog(this.GetName() + "の" + list.get(magicnumber).GetNAME() +"!");

					list.get(magicnumber).Effect(effectplayer);
					this.mp -= list.get(magicnumber).GetMP();

					return;

				}

			}

			list.remove(magicnumber);

		}

		Attack(effectplayer);

		// 倒れた判定
		if (effectplayer.GetHP() <= 0) {
			BattleMain.BuildLog(effectplayer.GetName() + "は力尽きた...");
		}

		//オーバーライド
		return;
	}

	//回復行動
	protected void HealAction(Player effectplayer, Player defender) {

		//回復を探し指定されたプレイヤーに回復魔法を使う
		for(Magic m : this.magiclist) {

			if(m instanceof MagicHeal && m.GetMP()<= this.mp) {

				BattleMain.BuildLog(this.GetName() + "の回復魔法");
				m.Effect(effectplayer);

				this.mp -= m.GetMP();

				return;

			}

		}


		Attack(defender);
		return;

	}

}
