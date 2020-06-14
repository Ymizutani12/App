package com.example.namebattle;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Magic implements Serializable {

	//インスタンス
	//魔法名
	protected String name;
	//消費MP
	protected int mp;
	
	/**
	 * コンストラクタ
	 * @param name : 魔法名
	 */
	public Magic(String name) {
		this.name = name;

		// 魔法のパラメータ生成
		MakeMagic();
	}
	
	//Get 名前
	public String GetNAME() {
		return this.name;
		
	}
	
	//Get 消費MP
		public int GetMP() {
			return this.mp;
			
		}
	
	
	
	//消費MPの設定
	protected void MakeMagic()
	{
		// オーバーライドして処理を記述してください
	}
	
	
	//魔法の効果の処理
	protected void Effect(Player effectplayer) {
		
		//オーバーライド
		
		
		
	}

/*
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.name);
		dest.writeInt(this.mp);
	}

	protected Magic(Parcel in) {
		this.name = in.readString();
		this.mp = in.readInt();
	}

	public static final Creator<Magic> CREATOR = new Creator<Magic>() {
		@Override
		public Magic createFromParcel(Parcel source) {
			return new Magic(source);
		}

		@Override
		public Magic[] newArray(int size) {
			return new Magic[size];
		}
	};*/
}
