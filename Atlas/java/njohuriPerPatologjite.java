package com.example.dell.atlasi;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class njohuriPerPatologjite extends AppCompatActivity {
    TextView textViewNjohuriPershkrimi,textViewNjohuriSimptomat;
    String patologjiaKerkuar = regjistrimiSimptomaveNew.patologjia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_njohuri_per_patologjite);
        textViewNjohuriPershkrimi = findViewById(R.id.txtNjohuriPershkrimi);
        textViewNjohuriSimptomat = findViewById(R.id.txtNjohuriSimptomat);
        SQLiteDatabase db = (new SQLLiteDatabase(njohuriPerPatologjite.this)).getReadableDatabase();
        Cursor c = db.rawQuery("Select *from Simptomat where PjesaTrupit='"+regjistrimiSimptomave.pjesaTrupit1+"' and EmriSimptomes='"+patologjiaKerkuar+"'", null);
        c.moveToFirst();
        textViewNjohuriPershkrimi.setText(patologjiaKerkuar);
        textViewNjohuriSimptomat.setText("Përshkrimi :"+c.getString(3)+"\n\n"+"Simptomat :"+c.getString(4));
    }
}
