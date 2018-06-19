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

public class fakteKlinike extends AppCompatActivity {
        ListView listView;
        int numriFakteveKlinike;
        Cursor c;
        public static String faktiZgjedhur;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fakte_klinike);

        SQLiteDatabase db = (new SQLLiteDatabase(fakteKlinike.this)).getReadableDatabase();
        c = db.rawQuery("Select *from fakte_klinike", null);
        c.moveToFirst();
        numriFakteveKlinike = c.getCount();
        listView = findViewById(R.id.listaMeFakteKlinike);
        adapteri objAdapteri = new adapteri();
        listView.setAdapter(objAdapteri);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                c.moveToPosition(i-1);
                faktiZgjedhur = c.getString(1);
                Toast.makeText(getApplicationContext(), c.getString(1), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(fakteKlinike.this,njohuriPerPatologjiNew.class));
            }
        });


    }

    class adapteri extends BaseAdapter {

        @Override
        public int getCount() {

            return numriFakteveKlinike;
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
            view = getLayoutInflater().inflate(R.layout.simptomatextraactivity,null);
            TextView textViewEmriSimptomes = (TextView) view.findViewById(R.id.txtEmriSimptomes);
            textViewEmriSimptomes.setText(c.getString(1));
            c.moveToPosition(i);
            return view;
        }
    }


}
