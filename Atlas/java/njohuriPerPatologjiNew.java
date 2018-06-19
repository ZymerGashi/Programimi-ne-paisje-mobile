package com.example.dell.atlasi;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class njohuriPerPatologjiNew extends AppCompatActivity {
    TextView textViewNjohuriPershkrimi,textViewNjohuriSimptomat;
    String patologjiaKerkuar = fakteKlinike.faktiZgjedhur;
    Cursor c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_njohuri_per_patologji_new);
        textViewNjohuriPershkrimi = findViewById(R.id.txtNjohuriPershkrimiNew);
        textViewNjohuriSimptomat = findViewById(R.id.txtNjohuriSimptomatNew);
      try {
          SQLiteDatabase db = (new SQLLiteDatabase(njohuriPerPatologjiNew.this)).getReadableDatabase();
          c = db.rawQuery("Select * from fakte_klinike where emri_faktit='" + fakteKlinike.faktiZgjedhur + "'", null);

           c.moveToFirst();
      }catch (Exception e){
          Toast.makeText(getApplicationContext(), "Gabim "+e, Toast.LENGTH_SHORT).show();
      }
        textViewNjohuriPershkrimi.setText(patologjiaKerkuar);
        textViewNjohuriSimptomat.setText(c.getString(2));
    }
}
