package com.example.dell.atlasi;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

public class PasLogInit extends AppCompatActivity {
    TextView textViewTekstiHyres;
    String email = Login.email;
    ImageButton btnEmjekut,btnFakteKlinike,btnAnalizat, btnSimptomat;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnEmjekut =(ImageButton) findViewById(R.id.imageButton3);
        btnAnalizat = (ImageButton) findViewById(R.id.btnAnalizat);
        btnFakteKlinike =(ImageButton)findViewById(R.id.imageButton5);
        btnSimptomat = (ImageButton)findViewById(R.id.btnSimptomat);
        btnFakteKlinike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PasLogInit.this,fakteKlinike.class));
            }
        });

        btnEmjekut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PasLogInit.this,simptoms.class));
            }
        });
        btnSimptomat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PasLogInit.this,regjistrimiSimptomave.class));
            }
        });

        btnAnalizat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PasLogInit.this,analizat.class));
            }
        });

    }
}
