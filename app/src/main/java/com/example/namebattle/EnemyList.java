package com.example.namebattle;

import java.util.ArrayList;
import java.util.Random;

public class EnemyList {

    ArrayList<String> list = new ArrayList<String>(){};

    public EnemyList(){

        list.add("ドリアール");
        list.add("アーミュー");
        list.add("ジャスカー");
        list.add("トバイモン");
        list.add("ジャイシー");
        list.add("ベネテリー");
        list.add("ゲイブラッド");
        list.add("デーヴィッド");
        list.add("ニコラリー");
        list.add("ジョナンド");
        list.add("パトリック");
        list.add("ルフレット");
        list.add("クスタント");
        list.add("ホレス");
        list.add("フェビアン");
        list.add("アーローム");
        list.add("ヴァレッド");
        list.add("ルドウエン");
        list.add("エセルイス");
        list.add("コーニエル");
        list.add("モイモレク");
        list.add("ルコシエル");
        list.add("ルーレプト");
        list.add("ラシアダド");
        list.add("ベザル");
        list.add("アメルカス");
        list.add("アムニエン");
        list.add("オロバリル");
        list.add("ウァサゴー");
        list.add("ベリアモン");
        list.add("エギビゴル");
        list.add("アドラース");
        list.add("フィステマ");
        list.add("ダンタムズ");
        list.add("ウリクサス");
        list.add("ベルファス");
        list.add("リバイモン");
        list.add("ウェパズズ");
        list.add("アグナック");
        list.add("セエレ");
        list.add("ダイアニー");
        list.add("シャローナ");
        list.add("ドライーズ");
        list.add("リンジャー");
        list.add("カーラ");
        list.add("リザベティ");
        list.add("ケイ");
        list.add("アントニア");
        list.add("ブリジェマ");
        list.add("キャエレン");
        list.add("ローレイン");
        list.add("ジョセアラ");
        list.add("ディアリー");
        list.add("コール");
        list.add("エルヴィラ");
        list.add("アトリエット");
        list.add("アイヴィス");
        list.add("ヴィヴェラ");
        list.add("クラリーナ");
        list.add("ダーリジット");
        list.add("ヴェランコ");
        list.add("ヴェネデット");
        list.add("エラルタコ");
        list.add("ターヴィオ");
        list.add("アンニコラ");
        list.add("ベネディオ");
        list.add("ティアーノ");
        list.add("サラディオ");
        list.add("ルフレート");
        list.add("ルメネーア");
        list.add("アンセスト");
        list.add("アポリスタ");
        list.add("ミントーレ");
        list.add("アンセルモ");
        list.add("バルダンテ");
        list.add("アナスパレ");
        list.add("ルクレンゾ");
        list.add("ジルベルト");
        list.add("ヴァレーモ");
        list.add("ファエーレ");
    }

    protected Party GetEnemy(){

        Random r = new Random();
        Party p = new Party();
        int taihi;
        ArrayList<String> kari = new ArrayList<>(list);


        for(int i=0; i<3; i++){
            taihi = r.nextInt(kari.size()-1);

            switch (r.nextInt(4)){

                case 0:
                    p.AppendPlayer(new Fighter(kari.get(taihi)));
                    break;
                case 1:
                    p.AppendPlayer(new Wizard(kari.get(taihi)));
                    break;
                case 2:
                    p.AppendPlayer(new Priest(kari.get(taihi)));
                    break;
                case 3:
                    p.AppendPlayer(new Fighter(kari.get(taihi)));
                    break;
            }

            kari.remove(taihi);
        }

        return p;

    }

}
