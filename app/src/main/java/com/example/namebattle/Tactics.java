package com.example.namebattle;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

//作戦クラス
public class Tactics implements Serializable {

	
	//フィールド変数
	int number;
	
	//指示を出す
	protected void Action(Player Actionplayer,ArrayList<Player> Attackmember,Party DefenceParty) {

		//オーバーライド
		
		
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
