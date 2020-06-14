package com.example.namebattle;

import java.util.ArrayList;
import java.util.Random;

// プレイヤー：戦士
public class Wizard extends Player {

	// =======================
	// フィールド変数
	// =======================

	// =======================
	// コンストラクタ
	// =======================
	public Wizard(String name) {
		super(name);
	}

	// =======================
	// Getter / Setter
	// =======================
	@Override
	public String GetJOB(){

		return "魔法使い";

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
		this.hp = GetNumber(0, 100) + 50;
		this.mp = GetNumber(5, 50) + 30;;
		this.str = GetNumber(1, 50) + 1;
		this.def = GetNumber(2, 50) + 1;
		this.luck = GetNumber(3, 100) + 1;
		this.agi = GetNumber(4, 40) + 20;
		this.maxhp = this.hp;
		this.maxmp = this.mp;
		
		MagicManegement mane = new MagicManegement();
		this.magiclist = new ArrayList<Magic> (mane.WizardList);
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
    	for(int i=0; i<this.magiclist.size(); i++) {
    		
    		if(list.size()<=1) {
    			magicnumber = 0;
    		}else {
    			magicnumber = r.nextInt(list.size());
    		}
    		
    		if(list.get(magicnumber).mp <= this.GetMP()) {
    			
    			BattleMain.BuildLog(this.GetName() + "の" + list.get(magicnumber).GetNAME() +"!");
    			
    			list.get(magicnumber).Effect(effectplayer);
    			this.mp -= list.get(magicnumber).GetMP();
    			
    			// 倒れた判定
    			if (effectplayer.GetHP() <= 0) {
    				BattleMain.BuildLog(effectplayer.GetName() + "は力尽きた...");
    			}
    			return;
    		}
    	}
    	
    	Attack(effectplayer);
    	
    	//オーバーライド
    	return;
    	
    }
	

	// =======================
	// private メソッド
	// =======================

	// =======================
	// public メソッド
	// =======================
}
