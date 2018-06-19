package com.example.dell.atlasi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main extends AppCompatActivity implements View.OnClickListener {
private Button startButton;
private Button stopButton;
private int i;
private TextView drugName;
private String DrugName;

    public Main() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        startButton=(Button)findViewById(R.id.alarmButton);
        drugName=(TextView)findViewById(R.id.drugName);
    startButton.setOnClickListener(this);
    stopButton=(Button)findViewById(R.id.stopButton);
        stopButton.setOnClickListener(this);

        Intent thisIntent=getIntent();
        String I=thisIntent.getStringExtra(set_alarm.I);
        i=Integer.parseInt(I);
        DrugName=thisIntent.getStringExtra(set_alarm.DrugName);
        drugName.setText(DrugName);
    }

    @Override
    public void onClick(View view) {
       if(view == startButton)
       {
           set_alarm.p.set(i-1,1);
           stopService(new Intent(this,backgroundAlarm.class));
           finish();
       }
       else if(view ==stopButton)
       {
           try {
              // set_alarm.alarms.get(i-1).sleep(1000);
               //set_alarm.alarms.get(i-1).interrupt();
                set_alarm.p.set(i-1,2);
           }
           catch (Exception e)
           {
               startButton.setText("Gabim:  "+e.getMessage()+i+set_alarm.alarms.get(i));
           }
           stopService(new Intent(this,backgroundAlarm.class));
           finish();
       }
    }
}
