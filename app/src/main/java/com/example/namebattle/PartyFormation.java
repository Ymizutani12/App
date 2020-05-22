package com.example.namebattle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PartyFormation extends AppCompatActivity {

    private CustomOpenHelper helper;
    private SQLiteDatabase db;
    ArrayList<String> name = new ArrayList<String>(){};
    ArrayList<String> job = new ArrayList<String>(){};
    ArrayList<String> status = new ArrayList<String>(){};


    // Mapのキー
    private final String[] FROM = {"Name","Job","Status","Check"};
    // リソースのコントロールID
    private final int[] TO = {R.id.namebox, R.id.jobbox,R.id.statusbox,R.id.checkBox};

    // カスタムアダプター
    private class MyAdapter extends SimpleAdapter{

        // 外部から呼び出し可能なマップ
        public Map<Integer,Boolean> checkList = new HashMap<>();

        public MyAdapter(Context context, List<? extends Map<String, ?>> data,
                         int resource, String[] from, int[] to) {
            super(context, data, resource, from, to);

            // 初期値を設定する
            for(int i=0; i<data.size();i++){
                Map map = data.get(i);
                checkList.put(i,(Boolean)map.get("Check"));
            }
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final View view = super.getView(position, convertView, parent);
            CheckBox ch = view.findViewById(R.id.checkBox);

            // チェックの状態が変化した場合はマップに記憶する
            ch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    int count=0 ;

                    for(int i=0 ; i < checkList.size(); i++) {

                        if (checkList.get(i)) {
                            count++;
                            }
                        }

                        CheckBox check = view.findViewById(R.id.checkBox);

                        if(count >= 3 && isChecked){
                            check.setChecked(false);
                            Toast.makeText(getApplicationContext() , "パーティーメンバーは3人までです", Toast.LENGTH_LONG).show();
                        }else{

                            checkList.put(position,isChecked);

                        }

                        count=0;
                    for(int i=0 ; i < checkList.size(); i++) {

                        if (checkList.get(i)) {
                            count++;
                        }
                    }
                    Button ButtleButton = findViewById(R.id.battleButton);
                    ButtleButton.setText("このパーティで開始(" + count + "/3)");


                    }

            });
            return view;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_formation);

        Button returnButton = findViewById(R.id.modoru);

        if(helper == null){
            helper = new CustomOpenHelper(getApplicationContext());
        }

        if(db == null){
            db = helper.getWritableDatabase();
        }


        Cursor cursor = db.query(
                "CHARACTERS",
                new String[] { "name","job","hp","mp","str","def","agi" },
                null,
                null,
                null,
                null,
                null
        );

        cursor.moveToFirst();

        for (int i = 0; i < cursor.getCount(); i++) {
            name.add(cursor.getString(0));
            job.add(cursor.getString(1));
            status.add("HP:" + cursor.getInt(2)
                    + " MP:" + cursor.getInt(3)
                    + " STR:" + cursor.getInt(4)
                    + " DEF:" + cursor.getInt(5)
                    + " LUCK:" + cursor.getInt(6));

            cursor.moveToNext();
        }

        cursor.close();

        // ListViewに表示するリスト項目をArrayListで準備する
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();

        for (int i=0; i<name.size(); i++){
            Map<String,Object> item = new HashMap<String, Object>();
            item.put("Name", name.get(i));
            item.put("Job", job.get(i));
            item.put("Status", status.get(i));
            item.put("Check",false);
            data.add(item);
        }

        // リスト項目とListViewを対応付けるArrayAdapterを用意する
        final MyAdapter adapter = new MyAdapter(getApplicationContext(),data,R.layout.status_plus_radio,FROM,TO);

        // ListViewにArrayAdapterを設定する
        final ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);



        Button ButtleButton = findViewById(R.id.battleButton);
        ButtleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplication(), creat.class);
                startActivity(intent);


            }

        });

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }

}
