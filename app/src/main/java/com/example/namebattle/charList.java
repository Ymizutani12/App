package com.example.namebattle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class charList extends AppCompatActivity {

    private CustomOpenHelper helper;
    private SQLiteDatabase db;
    ArrayList<String> name = new ArrayList<String>(){};
    ArrayList<String> job = new ArrayList<String>(){};
    ArrayList<String> status = new ArrayList<String>(){};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_char_list);

        Button returnButton = findViewById(R.id.modoru);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

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
        List<Map<String, String>> data = new ArrayList<Map<String, String>>();
        for (int i=0; i<name.size(); i++){
            Map<String, String> item = new HashMap<String, String>();
            item.put("Name", name.get(i));
            item.put("Job", job.get(i));
            item.put("Status", status.get(i));
            data.add(item);
        }

        // リスト項目とListViewを対応付けるArrayAdapterを用意する
        SimpleAdapter adapter = new SimpleAdapter(this, data,
                R.layout.status,
                new String[] { "Name", "Job" ,"Status"},
                new int[] { R.id.namebox, R.id.jobbox,R.id.statusbox});

        // ListViewにArrayAdapterを設定する
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getApplication(), syousai.class);
                intent.putExtra("POSITION",position);

                startActivity(intent);
                finish();
            }
        });



        Button CharButton = findViewById(R.id.createbotton);
        CharButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplication(), creat.class);
                startActivity(intent);
                

            }

        });



    }
}
