package com.example.namebattle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    CustomOpenHelper co ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        co = new CustomOpenHelper(getApplicationContext());

        co.getWritableDatabase();

        Button CharButton = findViewById(R.id.charbutton);
        CharButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getApplication(), charList.class);
                startActivity(intent);

            }

        });

    }
}
