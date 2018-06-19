package com.example.dell.atlasi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class simptoms extends AppCompatActivity implements View.OnClickListener{
    private ArrayList<String> illnesesFound;
    Connection conn;
    Button findIllneses1;
    private  Spinner simptom4;
    private Spinner simptom3;
    private Spinner simptom2;
    private Spinner simptom1;
    private String simptom1Value;
    private String simptom2Value;
    private String simptom3Value;
    private String simptom4Value;
    private ResultSet resultSet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simptoms);
        try {
            simptom1 = (Spinner) findViewById(R.id.simptom1);
            simptom2 = (Spinner) findViewById(R.id.simptom2);
            simptom3 = (Spinner) findViewById(R.id.simptom3);
            simptom4 = (Spinner) findViewById(R.id.simptom4);
            findIllneses1 = (Button) findViewById(R.id.findIllneses);
            findIllneses1.setOnClickListener(this);

            ArrayAdapter<String> simptom1Adapter = new ArrayAdapter<String>(getApplicationContext(),
                    android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.list_items));
            simptom1Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            simptom1.setAdapter(simptom1Adapter);
            simptom2.setAdapter(simptom1Adapter);
            simptom3.setAdapter(simptom1Adapter);
            simptom4.setAdapter(simptom1Adapter);
            simptom1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    simptom1Value = simptom1.getItemAtPosition(i).toString();
                   // Toast.makeText(getApplicationContext(),"simptom1Value is: "+simptom1Value, Toast.LENGTH_LONG).show();

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            simptom2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    simptom2Value = simptom2.getItemAtPosition(i).toString();

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            simptom3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    simptom3Value = simptom3.getItemAtPosition(i).toString();

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            simptom4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    simptom4Value = simptom4.getItemAtPosition(i).toString();

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),e.getMessage(), Toast.LENGTH_LONG).show();
        }


    }
    public void findIllneses(ResultSet result,String simptom1Value,String simptom2Value,String simptom3Value,String simptom4Value)
    {
        int numberOfRows=0;
        illnesesFound=new ArrayList<String>();
        try {
            while(result.next()) {
               // Toast.makeText(getApplicationContext(),result.getString(2), Toast.LENGTH_LONG).show();
                String simptoms;
                simptoms=result.getString(2);
                //illnesesFound.add(result.getString(1));
                if((simptoms.indexOf(simptom1Value)!=-1 && simptoms.indexOf(simptom2Value)!=-1)||
                        (simptoms.indexOf(simptom1Value)!=-1 && simptoms.indexOf(simptom3Value)!=-1)||
                        (simptoms.indexOf(simptom1Value)!=-1 && simptoms.indexOf(simptom4Value)!=-1)||
                        (simptoms.indexOf(simptom2Value)!=-1 && simptoms.indexOf(simptom3Value)!=-1)||
                        (simptoms.indexOf(simptom2Value)!=-1 && simptoms.indexOf(simptom4Value)!=-1)||
                        (simptoms.indexOf(simptom3Value)!=-1 && simptoms.indexOf(simptom4Value)!=-1) ){
                    illnesesFound.add(result.getString(1));
                }
                numberOfRows++;
            }
        } catch (SQLException e) {
            Toast.makeText(getApplicationContext(),e.getMessage(), Toast.LENGTH_LONG).show();
        }



    }

    public void showIllneses()
    {

        ArrayAdapter<String> illenesesAdapter=new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1,illnesesFound);
        ListView illneses=(ListView)findViewById(R.id.illneses);
        illneses.setAdapter(illenesesAdapter);

    }

    public Connection connectDb(String DBname,String username,String password)
    {
        Connection conn;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection)DriverManager.getConnection("jdbc:mysql://10.1.2.158:3307/"+DBname+"?useSSL=false", username, password);
            return conn;
        }
        catch(Exception e)
        {
            Toast.makeText(getApplicationContext(),e.getMessage()+e.getStackTrace()+e.getCause(), Toast.LENGTH_LONG).show();
            return null;
        }
    }

    @Override
    public void onClick(View view) {
        if(view==findIllneses1)
        {
            Runnable r=new Runnable() {
                @Override
                public void run() {
                    conn=connectDb("mjeksia","root1","toor");
                    String sqlQuery="Select emri_simptomes,simptoma from simptomat;";
                    //Toast.makeText(getApplicationContext(),conn.toString(), Toast.LENGTH_LONG).show();

                    try {
                        Statement stm=conn.createStatement();
                        resultSet=stm.executeQuery(sqlQuery);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            };
            Thread p=new Thread(r);
            p.start();
            try{
               // Toast.makeText(getApplicationContext(),conn.toString(), Toast.LENGTH_LONG).show();
                if(conn!=null) {
                    findIllneses(resultSet, simptom1Value, simptom2Value, simptom3Value, simptom4Value);
                    showIllneses();
                }
            //conn=connectDb("mjeksia","root1","toor");
            } catch (Exception e) {
              Toast.makeText(getApplicationContext(),e.getMessage()+e.getCause(), Toast.LENGTH_LONG).show();
            }

        }

    }
}


