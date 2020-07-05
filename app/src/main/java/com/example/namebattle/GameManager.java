package com.example.namebattle;

import java.util.ArrayList;
import java.util.Random;

//ゲーム進行クラス
//テキストはバトルメイン画面のバトルログに送る
public class GameManager {


	//フィールド変数
	private Party AllyParty,EnemyParty;
	private ArrayList<Player> SpeedList;
	private int TurnNumber;

	//味方パーティーを渡す
	protected Party GetAlly(){

		return AllyParty;

	}

	//敵パーティーを渡す
	protected Party GetEnemy(){

		return EnemyParty;

	}


	//作戦を変更させる
	protected void SetTactics(int i){

		AllyParty.SetTacticsNumber(i);

		return;
	}


	//コンストラクタ
	public GameManager(Party Party1,Party Party2){

		AllyParty = Party1;
		EnemyParty = Party2;
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


	//1ターン進める
	protected void TurnOne() {


		if(TurnNumber == 0) {
			BattleMain.BuildLog("======= バトル開始 =======\n");
		}

		TurnNumber += 1;

		BattleMain.BuildLog("----------------------------------------------------------------\n");
		BattleMain.BuildLog("--- ターン" + TurnNumber + " ---\n");

		Random r = new Random();

		// ■速い順ばんに攻撃
		for (Player Attacker : SpeedList) {

			if(Attacker.GetHP() > 0 && !LifeJudge() && !(Attacker.ActionJudge())) {

				//プレイヤーがどっちのチームか判定して攻撃
				for (int i = 0; i < AllyParty.GetMembers().size(); i++) {

					if (Attacker.GetName().equals(AllyParty.GetMembers().get(i).GetName())) {

						AllyParty.MemberAction(Attacker.GetName(), EnemyParty);

					}

				}

				for (int i = 0; i < EnemyParty.GetMembers().size(); i++) {

					if (Attacker.GetName().equals(EnemyParty.GetMembers().get(i).GetName())) {

						EnemyParty.SetTacticsNumber(r.nextInt(EnemyParty.TacticsList.size()));

						EnemyParty.MemberAction(Attacker.GetName(), AllyParty);

					}
				}

				//毒ダメージの処理
				Attacker.AfterDamage();

			}

		}


		BattleMain.BuildLog("----------------------------------------------------------------");

		BattleMain.BuildLog("\n");

	}


//どちらかのパーティメンバーがゼロになったら終了
	protected boolean LifeJudge(){

		int count ;
		ArrayList<Party> list = new ArrayList<Party>(){
			{
				add(AllyParty);
				add(EnemyParty);
			}
		};

		//どちらかのパーティが全滅してたらtrue
		for(Party p : list){

			count = 0;

			for(Player deatP : p.GetMembers()){

				if(deatP.GetHP() <= 0){
					count++;
				}
			}
			if(count == 3){
				return true;
			}

		}

		return false;

	}


}
