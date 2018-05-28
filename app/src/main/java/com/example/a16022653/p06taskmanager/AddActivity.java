package com.example.a16022653.p06taskmanager;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity {

    EditText etName, etDesc;
    Button btnAdd, btnCancel;
    int requestCode = 123;
    int notificationID = 888;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        etName = (EditText) findViewById(R.id.etName);
        etDesc = (EditText) findViewById(R.id.etDesc);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String desc = etDesc.getText().toString();

                if (!name.equals("") || !desc.equals("")) {
                    DBHelper db = new DBHelper(AddActivity.this);
                    db.getWritableDatabase();
                    long result = db.addTask(name, desc);


                    if (result == -1) {
                        Toast.makeText(AddActivity.this, "Failed to add", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(AddActivity.this, "Task: " + name + ", has been added", Toast.LENGTH_LONG).show();
                        etName.setText("");
                        etDesc.setText("");
                        setResult(RESULT_OK);

                        //launch notification here
                        Calendar cal = Calendar.getInstance();
                        cal.add(Calendar.SECOND, 5);

                        Intent i = new Intent(AddActivity.this,
                                TaskNotification.class);
                        i.putExtra("name", name);
                        i.putExtra("desc",desc);

                        PendingIntent pIntent = PendingIntent.getBroadcast(
                                AddActivity.this, requestCode,
                                i, PendingIntent.FLAG_CANCEL_CURRENT);

                        AlarmManager am = (AlarmManager)
                                getSystemService(Activity.ALARM_SERVICE);
                        am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
                                pIntent);

                        finish();
                    }

                } else {
                    Toast.makeText(AddActivity.this, "Fill up the blanks", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveToMain = new Intent(AddActivity.this, MainActivity.class);
                startActivity(moveToMain);
                finish();
            }
        });
    }
}
