package com.example.namebattle;

public class Magic {

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
	
	
	
}
