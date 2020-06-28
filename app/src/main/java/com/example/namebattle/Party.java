package com.example.namebattle;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Party implements Parcelable {

	// =======================
	// フィールド変数
	// =======================
	
	//パーティが管理するメンバー
	protected ArrayList<Player> members;


	//作戦一覧
	protected ArrayList<Tactics> TacticsList;
	
	
	//現在の作戦番号
	private int Tacticsnumber=0;
	
	// =======================
	// コンストラクタ
	// =======================
	Party() {
	members = new ArrayList<Player>();

	TacticsList = new ArrayList<Tactics>() {

			{
				add(new TacticsAttackonly());
				add(new TacticsFullAttack());
				add(new TacticsBattiri());
				add(new TacticsSyuutyuu());
				add(new TacticsImportantlife());
			}

		};

	}
	// =======================
	// Getter / Setter
	// =======================

	//パーティのプレイヤー名前の表示
	protected String GetPlayerName(int i) {

		if(0 <= i && (members.size()-1) >= i){

			return members.get(i).GetName();

		}else{
			return "なし";
		}


	}

	//パーティのプレイヤー職業の表示
	protected String GetPlayerJob(int i) {

		if(0 <= i && (members.size()-1) >= i){

			return members.get(i).GetJOB();

		}else{
			return "なし";
		}

	}

	//パーティのプレイヤーステータスの表示
	protected String GetPlayerStatus(int i) {

		if(0 <= i && (members.size()-1) >= i){
			return members.get(i).GetStatus();
		}else{
			return "なし";
		}
	}

	protected int GetTacticsNumber(){

		return Tacticsnumber;
	}
	
	/**
	* パーティーメンバーを ArrayList で取得する
	*/
	ArrayList<Player> GetMembers() {
	return members;
	}

	//作戦セット
	protected void SetTacticsNumber(int i){

		Tacticsnumber = i ;

	}


	// =======================
	// protected メソッド
	// =======================
	// =======================
	// private メソッド
	// =======================
	// =======================
	// public メソッド
	// =======================
	/**
	* パーティーにプレイヤーを追加する
	* @param player : 追加するプレイヤー
	*/
	public void AppendPlayer(Player player) {
		
		this.members.add(player);
		return;
		
	}

	
	//プレイヤーに行動させる
	protected void MemberAction(String name,Party DefenceParty) {
		
		for(Player Actionplayer : this.members) {
			
			if(Actionplayer.GetName().equals(name)) {
				
				this.TacticsList.get(Tacticsnumber).Action(Actionplayer,this.members, DefenceParty);
				
			}
			
		}
		
	}

	//ステータスリセット
	protected void Reset(){

		for(Player p : members){

			p.Reset();

		}


	}


	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeList(this.members);
		dest.writeList(this.TacticsList);
		dest.writeInt(this.Tacticsnumber);
	}

	protected Party(Parcel in) {
		this.members = new ArrayList<Player>();
		in.readList(this.members, Player.class.getClassLoader());
		this.TacticsList = new ArrayList<Tactics>();
		in.readList(this.TacticsList, Tactics.class.getClassLoader());
		this.Tacticsnumber = in.readInt();
	}

	public static final Creator<Party> CREATOR = new Creator<Party>() {
		@Override
		public Party createFromParcel(Parcel source) {
			return new Party(source);
		}

		@Override
		public Party[] newArray(int size) {
			return new Party[size];
		}
	};
}
