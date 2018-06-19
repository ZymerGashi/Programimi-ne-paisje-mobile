package com.example.dell.atlasi;

import android.content.Intent;
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

public class regjistrimiSimptomave extends AppCompatActivity{
    public static String pjesaTrupit1;
    int[] fotografite = {R.drawable.koka,R.drawable.qafa,R.drawable.gjoksi,R.drawable.shpatulla,R.drawable.krahu,R.drawable.dora,R.drawable.pelvisi,R.drawable.kemba,R.drawable.shputa};
String[] emriIPjesesSeTrupit={"Koka","Qafa","Gjoksi","Shpatulla","Krahu","Dora","Pelvisi","Këmba","Shputa"};
String[] problemetEZakonshme={"Konfuzion dhe kokëdhimbje, Depresion, Presion në vesh, Dhimbje e veshit....","Dhimbje të shpinës, Probleme me frymëmarrje, Pezmatim i fytit....","Dhimbje gjoksi, Ngushtim rëndim ose presion në gjoks, Kollë, Kollë me gjak .....","Dhimbje të duarve, Dhimbje të kyqeve, Dhimbje të qafës, Dhimbje të shpatullës....","Dhimbje krahu, Mpirje të krahëve, Dhimbje të bërryleve, Skuqje e lëkurës....","Dhimbje e duarve, Prerje e duarve, Dëmtim i thonjëve, Infeksion i lëkurës...","Gjak në urinë, Kapsllëk, Barkqitje, Urinim i shpeshtë....","Dhimbje e nyjës së këmbës, Dhimbje në gju, Kruarje e lëkurës,.....","Dhimbje në nyje, Prerje e shputës, Kruarje e shputës,....."};
    @Override
    protected void  onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regjistrimi_simptomave);
        ListView listView = findViewById(R.id.listaMePjesetETrupit);

        adapteri objAdapteri = new adapteri();
        listView.setAdapter(objAdapteri);

    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            try{
            pjesaTrupit1 = emriIPjesesSeTrupit[i];
            startActivity(new Intent(regjistrimiSimptomave.this,regjistrimiSimptomaveNew.class));
        }catch (Exception e){
                Toast.makeText(getApplicationContext(),"Gabim "+e,Toast.LENGTH_SHORT).show();
            }
        }
    });
    }
    class adapteri extends BaseAdapter{

        @Override
        public int getCount() {
            return fotografite.length;
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
            view = getLayoutInflater().inflate(R.layout.customlayout,null);
            ImageView imageView =(ImageView) view.findViewById(R.id.imageView);
            TextView textViewEmri = (TextView) view.findViewById(R.id.textViewEmri);
            TextView textViewPershkrimi =(TextView) view.findViewById(R.id.textViewPershkrimi);

            imageView.setImageResource(fotografite[i]);
            textViewEmri.setText(emriIPjesesSeTrupit[i]);
            textViewPershkrimi.setText(problemetEZakonshme[i]);

            return view;
        }
    }
}
