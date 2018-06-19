package com.example.dell.atlasi;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ms.square.android.expandabletextview.ExpandableTextView;


public class regjistrimiSimptomaveNew extends AppCompatActivity {
    int numriSimptomave; //Numri merret nga databaza SQLite
    ListView listView;
    public static String patologjia;
    TextView textViewListaSimptomave;
     String pjesaTrupit = regjistrimiSimptomave.pjesaTrupit1;
    String emriProblemit, pershkrimi, simptomat;
    Cursor c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regjistrimi_simptomave_new);
        textViewListaSimptomave = findViewById(R.id.ListaeSimptomaveMeTeZakonshme);
        textViewListaSimptomave.append(pjesaTrupit);
try {
    SQLiteDatabase db = (new SQLLiteDatabase(regjistrimiSimptomaveNew.this)).getReadableDatabase();
    c = db.rawQuery("Select *from Simptomat where PjesaTrupit='"+regjistrimiSimptomave.pjesaTrupit1+"'", null);
    c.moveToFirst();
    numriSimptomave = c.getCount()-1;
}catch (Exception e){
    Toast.makeText(getApplicationContext(), "Gabim " + e.toString(), Toast.LENGTH_SHORT).show();
}
   listView = findViewById(R.id.listaMeSimptoma);
        regjistrimiSimptomaveNew.adapteri objAdapteri = new regjistrimiSimptomaveNew.adapteri();
        listView.setAdapter(objAdapteri);


    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            c.moveToPosition(i-1);
           patologjia = c.getString(2);
           startActivity(new Intent(regjistrimiSimptomaveNew.this,njohuriPerPatologjite.class));
        }
    });
    }
    class adapteri extends BaseAdapter {

        @Override
        public int getCount() {
            return numriSimptomave;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
          try {
              view = getLayoutInflater().inflate(R.layout.simptomatextraactivity, null);
              TextView textViewEmriSimptomes = (TextView) view.findViewById(R.id.txtEmriSimptomes);
              textViewEmriSimptomes.setText(c.getString(2));
              c.moveToPosition(i);
          }catch (Exception e){
              Toast.makeText(getApplicationContext(), "Gabim "+e, Toast.LENGTH_LONG).show();
          }
            return view;
        }
    }




}
