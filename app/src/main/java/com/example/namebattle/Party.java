package com.example.namebattle;

import java.util.ArrayList;

public class Party {

	// =======================
	// フィールド変数
	// =======================
	
	//パーティが管理するメンバー
	protected ArrayList<Player> members;
	
	//作戦一覧
	private ArrayList<Tactics> TacticsList = new ArrayList<Tactics>() {
		
		{
			add(new TacticsDefencelow());
			add(new TacticsGangan());
			add(new TacticsBattiri());
			add(new TacticsSyuutyuu());
			add(new TacticsImportantlife());
		}
		
	}; 
	
	
	//現在の作戦番号
	private int Tacticsnumber=0;
	
	// =======================
	// コンストラクタ
	// =======================
	Party() {
	members = new ArrayList<Player>();
	}
	// =======================
	// Getter / Setter
	// =======================

	//パーティのプレイヤーステータスの表示
	protected String GetPlayerName(int i) {

		if(0 <= i && (members.size()-1) >= i){

			return members.get(i).GetName();

		}else{
			return "なし";
		}


	}

	//パーティのプレイヤーステータスの表示
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
	
	/**
	* パーティーメンバーを ArrayList で取得する
	*/
	ArrayList<Player> GetMembers() {
	return members;
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
	/**
	* パーティーからプレイヤーを離脱させる
	* @param player : 離脱させるプレイヤー
	*/
	public void RemovePlayer(Player player) {
		
		this.members.remove(player);
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
	
	
}
