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


	/*public Tactics() {
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
	}

	protected Tactics(Parcel in) {
	}

	public static final Creator<Tactics> CREATOR = new Creator<Tactics>() {
		@Override
		public Tactics createFromParcel(Parcel source) {
			return new Tactics(source);
		}

		@Override
		public Tactics[] newArray(int size) {
			return new Tactics[size];
		}
	};*/
}
