package com.example.a16022653.p06taskmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Tasks> al;
    ListView lv;
    Button add;
    ArrayAdapter aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.lv);
        add = (Button)findViewById(R.id.btnAdd);
        al = new ArrayList<Tasks>();
        aa = new TaskAdapter(this, R.layout.row, al);
        lv.setAdapter(aa);

        DBHelper db = new DBHelper(MainActivity.this);
        al.clear();
        al.addAll(db.getAllTasks());
        db.close();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveToAddActivity = new Intent(MainActivity.this, AddActivity.class);
                startActivityForResult(moveToAddActivity, 9);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 9) {
            lv = (ListView) findViewById(R.id.lv);
            al = new ArrayList<Tasks>();
            DBHelper dbh = new DBHelper(MainActivity.this);
            al.clear();
            al.addAll(dbh.getAllTasks());
            dbh.close();
            aa = new TaskAdapter(this, R.layout.row, al);

            lv.setAdapter(aa);

        }
    }
}
