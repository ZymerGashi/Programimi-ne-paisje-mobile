package com.example.dell.atlasi;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;

public class dbConnect
{
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
            //Toast.makeText(getApplicationContext(),e.getMessage()+e.getStackTrace()+e.getCause(), Toast.LENGTH_LONG).show();
            return null;
        }
    }
}
