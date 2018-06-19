package com.example.dell.atlasi;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

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
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Register extends Activity implements View.OnClickListener {
       public static EditText Name,Surname,Email,Password, DataLindjes, MobileNumber,RepeatPassword;
        TextView Njoftime;
        public static String currentDatetime;
        Connection conn;
        public static String njoftime;
        Statement s;
        String sql;
        String salt;
        String hash;
        Button register;
        String error="";

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Name = findViewById(R.id.txtName);
        Surname = findViewById(R.id.txtSurname);
        Email = findViewById(R.id.txtEmail);
        Password = findViewById(R.id.txtPassword);
        RepeatPassword = findViewById(R.id.txtRepeatPassword);
        DataLindjes = findViewById(R.id.txtDataLindjes);
        MobileNumber = findViewById(R.id.txtNrTel);
        Njoftime = findViewById(R.id.txtNjoftime);
        Name.setText(njoftime);
        register=(Button)findViewById(R.id.btnRegjistro);
            register.setOnClickListener(this);

    }

    public void ruajInformatat() throws SQLException {

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        currentDatetime = df.format(c.getTime());
        dbConnect connectionToDb=new dbConnect();
        try {
            conn=connectionToDb.connectDb("mjeksia","root1","toor");
            s = conn.createStatement();
            salt=HashSHA256.gjeneroSalt();
            hash=HashSHA256.hash(Password.getText().toString(),salt);

            //sql="INSERT INTO pacientet VALUES(NULL,'Zymer','Gashi','z2.g@outlook.com','31wasdaewq1231sqs','31wsasdadaasd','1996-11-04','2018-05-14 21:28:48','045463675','1','0');";

            sql="INSERT INTO pacientet VALUES(NULL,'"+Name.getText().toString()+"','"+Surname.getText().toString()+"','"
                    +Email.getText().toString()+"','"+salt+"','"+hash+"','"+ DataLindjes.getText().toString()+"','"
                    + currentDatetime+"','"+MobileNumber.getText().toString()+"','F','0');";
            s.executeUpdate(sql);
            conn.close();
        }
        catch(SQLException e){
            error+=e.getMessage();
        }


    }

    @Override
    public void onClick(View view) {
            if(view==register) {
                Runnable runableCode = new Runnable() {
                    @Override
                    public void run() {
                        try {
                            ruajInformatat();
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(),"Useri  nuk eshte regjistruar!!",Toast.LENGTH_LONG).show();
                        }
                    }
                };
                Thread registerCurrentUser=new Thread(runableCode);
                registerCurrentUser.start();

            }
        Toast.makeText(Register.this,"Regjistrimi eshte bere me sukeses!!", Toast.LENGTH_LONG).show();
    }
}
