package com.example.dell.atlasi;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Login extends Activity {
    EditText Email,Password;
    public static String email,password;
    TextView TxtNjoftimeLogin;
    public static String mesazhiNgaServeri="";
    public static String EmailAddrGabuar = "";
    private static dbConnect connectionToDb = new dbConnect();
    ProgressBar progressBar;
    Statement s;
    Connection conn;
    ResultSet resultSet;
    String hash="0",salt="0",dbEmail;
    Boolean b=false;
    String error;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Email = findViewById(R.id.edtPerdoruesi);
        Password = findViewById(R.id.editFjalekalimi);
        TxtNjoftimeLogin = findViewById(R.id.txtNjoftime);
        if(mesazhiNgaServeri.length()==0) {

        }
        else {
            TxtNjoftimeLogin.setText(mesazhiNgaServeri);
        }
        Email.setText(EmailAddrGabuar);
        progressBar = findViewById(R.id.prgBar);
    }
    public void btnRegjistrohu(View view){
        try {
            startActivity(new Intent(Login.this, Register.class));
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "Error "+e, Toast.LENGTH_SHORT).show();
        }

        }
    public void KyquNeSistem(View view){
            email = Email.getText().toString();
            password = Password.getText().toString();

            Runnable runableCode = new Runnable() {
                @Override
                public void run() {
                    try {
                        b=compare(email,password);
                        if(b) {
                            startActivity(new Intent(Login.this, PasLogInit.class));
                        }
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(),"Gabim gjate komunikimit me server!!!",Toast.LENGTH_LONG).show();
                    }
                }
            };
        Toast.makeText(getApplicationContext(),"Duke kontrolluar kredencialet!",Toast.LENGTH_SHORT).show();
            if(b==false&&conn!=null)
            {
                Toast.makeText(getApplicationContext(),"Kredencialet gabim!",Toast.LENGTH_SHORT).show();

            }
            Thread registerCurrentUser=new Thread(runableCode);
            registerCurrentUser.start();

    }

    public boolean compare(String email,String password){
        try {
            conn = connectionToDb.connectDb("mjeksia", "root1", "toor");

            //String sqlQuery="Select email,salt,hash from pacientet;";
            String sqlQuery="Select salt,fjalekalimi from pacientet where email='"+email+"';";
            s=conn.createStatement();
            resultSet=s.executeQuery(sqlQuery);
           resultSet.next();
               salt = resultSet.getString(1);
               hash = resultSet.getString(2);
conn.close();
        }catch(Exception e){
error=e.getMessage();
        }
        if(HashSHA256.hash(password,salt).equals(hash))
        {
            return true;
        }
        else{
            return false;
        }

    }

    public void BtnShfaq(View view){
        startActivity(new Intent(Login.this,set_alarm.class));
    }


    class logInToMysql extends AsyncTask<String,Integer,String> {
        String add_info_url;

        @Override
        protected void onPreExecute() {
            add_info_url = "http://192.168.0.103:80/login.php";
            Toast.makeText(getApplicationContext(),"Duke u kyqur",Toast.LENGTH_SHORT).show();
        }

        @Override
        protected String doInBackground(String... args) {
            int i=0;
            String email, password;
            String mesazhiNgaPHP;
            email = args[0];
            password = args[1];
            URL url = null;
            i=10;
            publishProgress(i);
            try {
                i=20;
                url = new URL(add_info_url);
                HttpURLConnection httpURLConnection = null;
                httpURLConnection = (HttpURLConnection) url.openConnection();
                publishProgress(i);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = null;
                i=40;
                publishProgress(i);
                outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = null;
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data_string = null;

                data_string =  URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&"
                             + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
                i=60;
                publishProgress(i);
                bufferedWriter.write(data_string);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                i=70;
                publishProgress(i);
                while((mesazhiNgaPHP = bufferedReader.readLine()) != null){
                    stringBuilder.append(mesazhiNgaPHP+"\n");
                }
                i=80;
                publishProgress(i);
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                if(stringBuilder.toString().trim().equals("\"1\"")){
                    i=100;
                    publishProgress(i);
                    return "\"1\"";
                }
                else {
                    return stringBuilder.toString().trim();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);

        }

        @Override
        protected void onPostExecute(String result) {
            try {
                if (result.equals("\"1\"")) {
                    startActivity(new Intent(Login.this, PasLogInit.class));
                    mesazhiNgaServeri = "Kredencialet e sakta.";
                } else if (result.equals("\"2\"")) {
                    mesazhiNgaServeri = "Passwordi eshte gabuar!";
                    EmailAddrGabuar = email;
                    startActivity(new Intent(Login.this, Login.class));
                } else if (result.equals("\"3\"")) {
                    mesazhiNgaServeri = "Ky perdorues nuk egziston.";
                    startActivity(new Intent(Login.this, Login.class));
                }
            }catch (Exception e){
                Toast.makeText(getApplicationContext(), "Error "+e, Toast.LENGTH_SHORT).show();
            }
        }

    }
}
