package com.example.namebattle;

import android.widget.TextView;

import java.util.ArrayList;

public class GameManager {


	//フィールド変数
	private Party AllyParty,EnemyParty;
	private ArrayList<Player> SpeedList;
	private TextView BattleLog;
	private int TurnNumber;


	public GameManager(Party Party1,Party Party2,TextView t){

		AllyParty = Party1;
		EnemyParty = Party2;
		BattleLog = t;
		TurnNumber = 0;

		SpeedList = new ArrayList<Player>() {
			{
				add(AllyParty.GetMembers().get(0));
				add(AllyParty.GetMembers().get(1));
				add(AllyParty.GetMembers().get(2));
				add(EnemyParty.GetMembers().get(0));
				add(EnemyParty.GetMembers().get(1));
				add(EnemyParty.GetMembers().get(2));
			}
		};

		//スピードが速い順のリストを作成
		Player p;
		for (int i = 0; i < SpeedList.size(); i++) {
			for (int j = i + 1; j < SpeedList.size(); j++) {

				if (SpeedList.get(i).GetAGI() < SpeedList.get(j).GetAGI()) {

					p = SpeedList.get(j);
					SpeedList.remove(j);
					SpeedList.add(i, p);

				}
			}
		}

	}


	protected void TurnOne() {

		StringBuilder log = new StringBuilder();

		log.append(BattleLog.getText().toString());

		log.append("======= バトル開始 =======\n");

		TurnNumber = TurnNumber++;

		log.append("--------------------------------\n");
		log.append("--- ターン" + TurnNumber + " ---\n");

		// ■速い順ばんに攻撃
		for (Player Attacker : SpeedList) {

			//プレイヤーがどっちのチームか判定して攻撃
			for (int i = 0; i < AllyParty.GetMembers().size(); i++) {

				if (Attacker.GetName().equals(AllyParty.GetMembers().get(i).GetName())) {

					AllyParty.MemberAction(Attacker.GetName(), EnemyParty);

					//死亡者の確認と除名
					for (Player deadP : EnemyParty.GetMembers()) {

						if (deadP.GetHP() <= 0) {
							EnemyParty.RemovePlayer(deadP);
							break;
						}

					}
				}
			}

			for (int i = 0; i < EnemyParty.GetMembers().size(); i++) {

				if (Attacker.GetName().equals(EnemyParty.GetMembers().get(i).GetName())) {

					EnemyParty.MemberAction(Attacker.GetName(), EnemyParty);

					for (Player deadP : EnemyParty.GetMembers()) {

						if (deadP.GetHP() <= 0 ) {
							EnemyParty.RemovePlayer(deadP);
							break;
						}

					}
				}
			}

		}

		log.append("--------------------------------");

		log.append("\n");

		// ==================================================
		// 終了処理
		// ==================================================
	}

//どちらかのパーティメンバーがゼロになったら終了
			if (AllyParty.GetMembers().size() <= 0 || EnemyParty.GetMembers().size() <= 0) {


					}


}
