package com.example.namebattle;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

//作戦クラス
public class Tactics implements Serializable {

	
	//フィールド変数
	int number;
	
	//指示を出す
	protected void Action(Player Actionplayer,ArrayList<Player> Attackmember,Party DefenceParty) {

		//オーバーライド
		
	}

	//攻撃するキャラをランダムで選ぶ
	protected Player SelectPlayer(Party DefenceParty){

		Random r = new Random();

		int defnumber;

		while(true){

			defnumber = r.nextInt(DefenceParty.GetMembers().size());

			if(DefenceParty.GetMembers().get(defnumber).GetHP() > 0 ){
				break;
			}

		}

		return DefenceParty.GetMembers().get(defnumber);
	}

	//一番HPが減っているキャラを選択
	protected Player SelectLowPlayer(ArrayList<Player> list){

		Player lowHPplayer = null;

		//HPがあるプレイヤーを格納
		for (Player p : list) {

			if(lowHPplayer == null && p.GetHP() > 0){
			 	lowHPplayer = p;
				break;
			}
		}

		//HPが0以外で一番低いプレイヤーを選ぶ
		for (Player p : list) {

			if (lowHPplayer.GetMaxHP() - lowHPplayer.GetHP() < p.GetMaxHP() - p.GetHP() && p.GetHP() > 0) {

				lowHPplayer = p;

			}

		}

		return lowHPplayer;

	}

}
