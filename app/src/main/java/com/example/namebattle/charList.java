package com.example.namebattle;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class charList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_char_list);

        Button returnButton = findViewById(R.id.button6);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // リスト項目のもととなる値を準備する
        ArrayList<String> subjects = new ArrayList<String>(){
            {
                add("國ちゃん");
                add("数ちゃん");
                add("かっちゃん");
                add("ちびちゃん");

            }
        };
        ArrayList<String> comments = new ArrayList<String>(){
            {
                add("言語午後ご");
                add("経緯いいさんんん");
                add("勝ちっっっっっま");
                add("ちょせええええええ");

            }
        };
        ArrayList<String> saramis = new ArrayList<String>(){
            {
                add("シャア");
                add("ジム");
                add("ドム");
                add("ガンダム");

            }
        };

        // ListViewに表示するリスト項目をArrayListで準備する
        List<Map<String, String>> data = new ArrayList<Map<String, String>>();
        for (int i=0; i<subjects.size(); i++){
            Map<String, String> item = new HashMap<String, String>();
            item.put("Subject", subjects.get(i));
            item.put("Comment", comments.get(i));
            item.put("Saramis", saramis.get(i));
            data.add(item);
        }

        // リスト項目とListViewを対応付けるArrayAdapterを用意する
        SimpleAdapter adapter = new SimpleAdapter(this, data,
                R.layout.status,
                new String[] { "Subject", "Comment" ,"Saramis"},
                new int[] { R.id.text2, R.id.text3,R.id.text1});

        // ListViewにArrayAdapterを設定する
        ListView listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String msg = position + "番目のアイテムがクリックされました";
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
            }
        });








    }
}
