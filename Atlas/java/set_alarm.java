package com.example.dell.atlasi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class set_alarm extends AppCompatActivity implements View.OnClickListener {
    private EditText drugName;
    private TimePicker time;
    private EditText periodHour;
    private EditText periodMinutes;
    private String name;
    private String timePick;
    private int periodH;
    private int periodM;
    private Button createAlarm;
    public static List<Thread> alarms = new ArrayList<Thread>();
    public static Thread newAlarm;
    int threadToStop = 0;
    public static final String I = "com.example.dell.atlasi.I";
    public static final String DrugName = "com.example.dell.atlasi.DrugName";
    public static ArrayList<Integer> p = new ArrayList<Integer>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm);
        createAlarm = (Button) findViewById(R.id.createAlarm);
        createAlarm.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        p.add(threadToStop, 0);
        if (view == createAlarm) {

            Runnable theAlarm = new Runnable() {
                @Override
                public void run() {
                    try {
                        createAlarm(threadToStop, DrugName);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                }
            };

            periodHour = (EditText) findViewById(R.id.periodHour);
            periodMinutes = (EditText) findViewById(R.id.periodMinutes);
            try {
                periodH = Integer.parseInt(periodHour.getText().toString());
                periodM = Integer.parseInt(periodMinutes.getText().toString());
                if (periodH != 0 || periodM != 0) {
                    Intent clockPic = new Intent(getApplicationContext(), alarmIsSet.class);
                    startActivity(clockPic);
                    newAlarm = new Thread(theAlarm);
                    newAlarm.start();
                    set_alarm.alarms.add(newAlarm);
                    threadToStop++;
                } else {
                    Toast.makeText(getApplicationContext(), "Perioda nuk mund te jete zero!!", Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Ju lutem caktoni perioden e perdorimit!!", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void createAlarm(int i, String DrugName) throws InterruptedException {
        drugName = (EditText) findViewById(R.id.drugButton);
        time = (TimePicker) findViewById(R.id.timePicker);
        periodHour = (EditText) findViewById(R.id.periodHour);
        periodMinutes = (EditText) findViewById(R.id.periodMinutes);
        name = drugName.getText().toString();
        timePick = String.valueOf(String.valueOf(time.getMinute()));
        TextView txtView = (TextView) findViewById(R.id.textView);
        periodH = Integer.parseInt(periodHour.getText().toString());
        periodM = Integer.parseInt(periodMinutes.getText().toString());
        long currentTime;
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.set(2018, date.getMonth(), date.getDate(), time.getHour(), time.getMinute(), 0);
        currentTime = date.getTime();
        Date exactTime = cal.getTime();
        //txtView.setText(String.valueOf(currentTime)+" "+String.valueOf(exactTime.getTime()+" "+date.toString()));
        //txtView.setText(exactTime.toString()+" "+date.toString());
        //wait(1000);
        int k = 0;
        while (p.get(i - 1) == 0) {
            long nextTimeUsage = System.currentTimeMillis() + (periodH * 3600 * 1000 + periodM * 60 * 1000);
            int l = 0;
            while (true) {
                long futureTime = cal.getTimeInMillis() - System.currentTimeMillis();
                if ((futureTime < 0 && k == 0) || (nextTimeUsage < System.currentTimeMillis())) {

                    if (k == 0 || l == 0) {
                        startService(new Intent(getApplicationContext(), backgroundAlarm.class));
                        Intent stopContinueAlarm = new Intent(getApplicationContext(), Main.class);
                        stopContinueAlarm.putExtra(I, String.valueOf(i));
                        stopContinueAlarm.putExtra(DrugName, drugName.getText().toString());
                        startActivity(stopContinueAlarm);
                        k += 1;
                        if (k > 1) {
                            l = 1;
                        }
                    }
                }
                // Toast.makeText(this,"Unaza e pare"+p.get(i-1),Toast.LENGTH_SHORT);
                if (p.get(i - 1) == 1) {
                    p.set(i - 1, 0);
                    break;

                } else if (p.get(i - 1) == 2) {
                    break;
                }
            }
            // Toast.makeText(this,"Unaza e dyte"+p.get(i-1),Toast.LENGTH_SHORT);
        }
    }
}
