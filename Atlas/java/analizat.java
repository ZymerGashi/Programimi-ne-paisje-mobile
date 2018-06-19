package com.example.dell.atlasi;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class analizat extends AppCompatActivity {
    EditText sasia,peshaSpecifike,reaksioniElektrokimik,perberesitENgurte,natriumi,kaliumi,kalciumi,magnezi,amoniaku,fosfatet,urea,acidiUrik,acidiLaktik,kreatinina,kreatina,aminoAcide,indikoni,urobilinogjena,amilaza;
    Button btnKrahaso;
    public static String[] rezultati;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analizat);
        sasia = findViewById(R.id.txtSasia);
        peshaSpecifike = findViewById(R.id.txtPeshaSpecifike);
        reaksioniElektrokimik = findViewById(R.id.txtReaksioniElektrokimik);
        perberesitENgurte = findViewById(R.id.txtPerberesitENgurte);
        natriumi = findViewById(R.id.txtNatrium);
        kaliumi = findViewById(R.id.txtKalium);
        kalciumi = findViewById(R.id.txtKalcium);
        magnezi = findViewById(R.id.txtMagnezi);
        amoniaku = findViewById(R.id.txtAmoniak);
        fosfatet = findViewById(R.id.txtFosfate);
        urea = findViewById(R.id.txtUre);
        acidiUrik = findViewById(R.id.txtAcidiUrik);
        acidiLaktik = findViewById(R.id.txtAcidiLaktik);
        kreatinina = findViewById(R.id.txtKreatinina);
        kreatina = findViewById(R.id.txtKreatina);
        aminoAcide = findViewById(R.id.txtAminoAcide);
        indikoni = findViewById(R.id.txtIndikoni);
        urobilinogjena = findViewById(R.id.txtUrobilinogjena);
        amilaza = findViewById(R.id.txtAmilaza);
        btnKrahaso = findViewById(R.id.btnKrahasoAnalizat);

        btnKrahaso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             try{
               rezultati = new String[19];
                SQLiteDatabase db = (new SQLLiteDatabase(analizat.this)).getReadableDatabase();
                Cursor c = db.rawQuery("Select * from vlerat_normale_te_urines", null);
                if(sasia.getText().toString().equals("")){
                    rezultati[0]="Nuk eshte dhënë ky parametër!";
                }
                else{
                    float sasiaMi,sasiaMa;
                    float sasiaA = Float.valueOf(sasia.getText().toString());
                    c.moveToFirst();
                    sasiaMi = Float.valueOf(c.getString(3));
                    sasiaMa = Float.valueOf(c.getString(4));
                    if(sasiaA<=sasiaMi)
                        rezultati[0] ="Sasia eshte jo e mjaftueshme!";
                    else if(sasiaA>=sasiaMa)
                        rezultati[0]="Sasia eshte e tepert";
                    else
                        rezultati[0]="Vlera normale";
                }

                 if(peshaSpecifike.getText().toString().equals("")){
                     rezultati[1]="Nuk eshte dhënë ky parametër!";
                 }
                 else{
                     float peshaSpecifikeMi,peshaSpecifikeMa;
                     float peshaSpecifikeA = Float.valueOf(peshaSpecifike.getText().toString());
                     c.moveToNext();
                     peshaSpecifikeMi = Float.valueOf(c.getString(3));
                     peshaSpecifikeMa = Float.valueOf(c.getString(4));
                     if(peshaSpecifikeA<=peshaSpecifikeMi)
                         rezultati[1]="Pesha specifike është më e ulët se sa vlera normale";
                     else if(peshaSpecifikeA>=peshaSpecifikeMa)
                         rezultati[1]="Pesha specifike është më e lartë se sa vlera normale";
                     else
                         rezultati[1]="Vlera normale";
                 }
                 if(reaksioniElektrokimik.getText().toString().equals("")){
                     rezultati[2]="Nuk eshte dhënë ky parametër!";
                 }
                 else{
                     float reaksioniElektrokimikMi,reaksioniElektrokimikMa;
                     float reaksioniElektrokimikA = Float.valueOf(reaksioniElektrokimik.getText().toString());
                     c.moveToNext();
                     reaksioniElektrokimikMi = Float.valueOf(c.getString(3));
                     reaksioniElektrokimikMa = Float.valueOf(c.getString(4));
                     if(reaksioniElektrokimikA<=reaksioniElektrokimikMi)
                         rezultati[2]="Niveli i pH-së është më i ulët se sa vlera normale";
                     else if(reaksioniElektrokimikA>=reaksioniElektrokimikMa)
                         rezultati[2]="Niveli i pH-së është më i lartë se sa vlera normale";
                     else
                         rezultati[2]="Vlera normale";
                 }
                 if(perberesitENgurte.getText().toString().equals("")){
                     rezultati[3]="Nuk eshte dhënë ky parametër!";
                 }
                 else{
                     float perberesitENgurteMi,perberesitENgurteMa;
                     float perberesitENgurteA = Float.valueOf(perberesitENgurte.getText().toString());
                     c.moveToNext();
                     perberesitENgurteMi = Float.valueOf(c.getString(3));
                     perberesitENgurteMa = Float.valueOf(c.getString(4));
                     if(perberesitENgurteA<=perberesitENgurteMi)
                         rezultati[3]="Përbërësit e ngurtë janë më pak se sa vlera normale";
                     else if(perberesitENgurteA>=perberesitENgurteMa)
                         rezultati[3]="Përbërësit e ngurtë janë më shumë se sa vlera normale";
                     else
                         rezultati[3]="Vlera normale";
                 }
                 if(natriumi.getText().toString().equals("")){
                     rezultati[4]="Nuk eshte dhënë ky parametër!";
                 }
                 else{
                     float natriumiMi,natriumiMa;
                     float natriumiA = Float.valueOf(natriumi.getText().toString());
                     c.moveToNext();
                     natriumiMi = Float.valueOf(c.getString(3));
                     natriumiMa = Float.valueOf(c.getString(4));
                     if(natriumiA<=natriumiMi)
                         rezultati[4]="Niveli i Natriumit është më i ulët se sa vlera normale";
                     else if(natriumiA>=natriumiMa)
                         rezultati[4]="Niveli i Natrimuit është më i lartë se sa vlera normale";
                     else
                         rezultati[4]="Vlera normale";
                 }
                 if(kaliumi.getText().toString().equals("")){
                     rezultati[5]="Nuk eshte dhënë ky parametër!";
                 }
                 else{
                     float kaliumiMi,kaliumiMa;
                     float kaliumiA = Float.valueOf(kaliumi.getText().toString());
                     c.moveToNext();
                     kaliumiMi = Float.valueOf(c.getString(3));
                     kaliumiMa = Float.valueOf(c.getString(4));
                     if(kaliumiA<=kaliumiMi)
                         rezultati[5]="Niveli i Kaliumit është më i ulët se sa vlera normale";
                     else if(kaliumiA>=kaliumiMa)
                         rezultati[5]="Niveli i Kaliumit është më i lartë se sa vlera normale";
                     else
                         rezultati[5]="Vlera normale";
                 }
                 if(kalciumi.getText().toString().equals("")){
                     rezultati[6]="Nuk eshte dhënë ky parametër!";
                 }
                 else{
                     float kalciumiMi,kalciumiMa;
                     float kalciumiA = Float.valueOf(kalciumi.getText().toString());
                     c.moveToNext();
                     kalciumiMi = Float.valueOf(c.getString(3));
                     kalciumiMa = Float.valueOf(c.getString(4));
                     if(kalciumiA<=kalciumiMi)
                         rezultati[6]="Niveli i Kalciumit është më i ulët se sa vlera normale";
                     else if(kalciumiA>=kalciumiMa)
                         rezultati[6]="Niveli i Kalciumit është më i lartë se sa vlera normale";
                     else
                         rezultati[6]="Vlera normale";
                 }
                 if(magnezi.getText().toString().equals("")){
                     rezultati[7]="Nuk eshte dhënë ky parametër!";
                 }
                 else{
                     float magneziMi,magneziMa;
                     float magneziA = Float.valueOf(magnezi.getText().toString());
                     c.moveToNext();
                     magneziMi = Float.valueOf(c.getString(3));
                     magneziMa = Float.valueOf(c.getString(4));
                     if(magneziA<=magneziMi)
                         rezultati[7]="Niveli i Magnezit është më i ulët se sa vlera normale";
                     else if(magneziA>=magneziMa)
                         rezultati[7]="Niveli i Magnezit është më i lartë se sa vlera normale";
                     else
                         rezultati[7]="Vlera normale";
                 }
                 if(amoniaku.getText().toString().equals("")){
                     rezultati[8]="Nuk eshte dhënë ky parametër!";
                 }
                 else{
                     float amoniakuMi,amoniakuMa;
                     float amoniakuA = Float.valueOf(amoniaku.getText().toString());
                     c.moveToNext();
                     amoniakuMi = Float.valueOf(c.getString(3));
                     amoniakuMa = Float.valueOf(c.getString(4));
                     if(amoniakuA<=amoniakuMi)
                         rezultati[8]="Niveli i Amoniakut është më i ulët se sa vlera normale";
                     else if(amoniakuA>=amoniakuMa)
                         rezultati[8]="Niveli i Amoniakut është më i lartë se sa vlera normale";
                     else
                         rezultati[8]="Vlera normale";
                 }
                 if(fosfatet.getText().toString().equals("")){
                     rezultati[9]="Nuk eshte dhënë ky parametër!";
                 }
                 else{
                     float fosfatetMi,fosfatetMa;
                     float fosfatetA = Float.valueOf(fosfatet.getText().toString());
                     c.moveToNext();
                     fosfatetMi = Float.valueOf(c.getString(3));
                     fosfatetMa = Float.valueOf(c.getString(4));
                     if(fosfatetA<=fosfatetMi)
                         rezultati[9]="Niveli i Fosfateve është më i ulët se sa vlera normale";
                     else if(fosfatetA>=fosfatetMa)
                         rezultati[9]="Niveli i Fosfateve është më i lartë se sa vlera normale";
                     else
                         rezultati[9]="Vlera normale";
                 }
                 if(urea.getText().toString().equals("")){
                     rezultati[10]="Nuk eshte dhënë ky parametër!";
                 }
                 else{
                     float ureaMi,ureaMa;
                     float ureaA = Float.valueOf(urea.getText().toString());
                     c.moveToNext();
                     ureaMi = Float.valueOf(c.getString(3));
                     ureaMa = Float.valueOf(c.getString(4));
                     if(ureaA<=ureaMi)
                         rezultati[10]="Niveli i Uresë është më i ulët se sa vlera normale";
                     else if(ureaA>=ureaMa)
                         rezultati[10]="Niveli i Uresë është më i lartë se sa vlera normale";
                     else
                         rezultati[10]="Vlera normale";
                 }
                 if(acidiUrik.getText().toString().equals("")){
                     rezultati[11]="Nuk eshte dhënë ky parametër!";
                 }
                 else{
                     float acidiUrikMi,acidiUrikMa;
                     float acidiUrikA = Float.valueOf(acidiUrik.getText().toString());
                     c.moveToNext();
                     acidiUrikMi = Float.valueOf(c.getString(3));
                     acidiUrikMa = Float.valueOf(c.getString(4));
                     if(acidiUrikA<=acidiUrikMi)
                         rezultati[11]="Niveli i Acidit Urik është më i ulët se sa vlera normale";
                     else if(acidiUrikA>=acidiUrikMa)
                         rezultati[11]="Niveli i Acidit Urik është më i lartë se sa vlera normale";
                     else
                         rezultati[11]="Vlera normale";
                 }
                 if(acidiLaktik.getText().toString().equals("")){
                     rezultati[12]="Nuk eshte dhënë ky parametër!";
                 }
                 else{
                     float acidiLaktikMi,acidiLaktikMa;
                     float acidiLaktikA = Float.valueOf(acidiLaktik.getText().toString());
                     c.moveToNext();
                     acidiLaktikMi = Float.valueOf(c.getString(3));
                     acidiLaktikMa = Float.valueOf(c.getString(4));
                     if(acidiLaktikA<=acidiLaktikMi)
                         rezultati[12]="Niveli i Acidit Laktik është më i ulët se sa vlera normale";
                     else if(acidiLaktikA>=acidiLaktikMa)
                         rezultati[12]="Niveli i Acidit Laktik është më i lartë se sa vlera normale";
                     else
                         rezultati[12]="Vlera normale";
                 }
                 if(kreatinina.getText().toString().equals("")){
                     rezultati[13]="Nuk eshte dhënë ky parametër!";
                 }
                 else{
                     float kreatininaMi,kreatininaMa;
                     float kreatininaA = Float.valueOf(kreatinina.getText().toString());
                     c.moveToNext();
                     kreatininaMi = Float.valueOf(c.getString(3));
                     kreatininaMa = Float.valueOf(c.getString(4));
                     if(kreatininaA<=kreatininaMi)
                         rezultati[13]="Niveli i Kreatininës është më i ulët se sa vlera normale";
                     else if(kreatininaA>=kreatininaMa)
                         rezultati[13]="Niveli i Kreatininës është më i lartë se sa vlera normale";
                     else
                         rezultati[13]="Vlera normale";
                 }
                 if(kreatina.getText().toString().equals("")){
                     rezultati[14]="Nuk eshte dhënë ky parametër!";
                 }
                 else{
                     float kreatinaMi, kreatinaMa;
                     float kreatinaA = Float.valueOf(kreatina.getText().toString());
                     c.moveToNext();
                     kreatinaMi = Float.valueOf(c.getString(3));
                     kreatinaMa = Float.valueOf(c.getString(4));
                     if(kreatinaA<=kreatinaMi)
                         rezultati[14]="Niveli i Kreatinës është më i ulët se sa vlera normale";
                     else if(kreatinaA>=kreatinaMa)
                         rezultati[14]="Niveli i Kreatinës është më i lartë se sa vlera normale";
                     else
                         rezultati[14]="Vlera normale";
                 }
                 if(aminoAcide.getText().toString().equals("")){
                     rezultati[15]="Nuk eshte dhënë ky parametër!";
                 }
                 else{
                     float aminoAcideMi,aminoAcideMa;
                     float aminoAcideA = Float.valueOf(aminoAcide.getText().toString());
                     c.moveToNext();
                     aminoAcideMi = Float.valueOf(c.getString(3));
                     aminoAcideMa = Float.valueOf(c.getString(4));
                     if(aminoAcideA<=aminoAcideMi)
                         rezultati[15]="Niveli i Amino Acideve është më i ulët se sa vlera normale";
                     else if(aminoAcideA>=aminoAcideMa)
                         rezultati[15]="Niveli i Amino Acideve është më i lartë se sa vlera normale";
                     else
                         rezultati[15]="Vlera normale";
                 }
                 if(indikoni.getText().toString().equals("")){
                     rezultati[16]="Nuk eshte dhënë ky parametër!";
                 }
                 else{
                     float indikoniMi,indikoniMa;
                     float indikoniA = Float.valueOf(indikoni.getText().toString());
                     c.moveToNext();
                     indikoniMi = Float.valueOf(c.getString(3));
                     indikoniMa = Float.valueOf(c.getString(4));
                     if(indikoniA<=indikoniMi)
                         rezultati[16]="Niveli i Indikonit është më i ulët se sa vlera normale";
                     else if(indikoniA>=indikoniMa)
                         rezultati[16]="Niveli i Indikonit është më i lartë se sa vlera normale";
                     else
                         rezultati[16]="Vlera normale";
                 }
                 if(urobilinogjena.getText().toString().equals("")){
                     rezultati[17]="Nuk eshte dhënë ky parametër!";
                 }
                 else{
                     float urobilinogjenaMi,urobilinogjenaMa;
                     float urobilinogjenaA = Float.valueOf(urobilinogjena.getText().toString());
                     c.moveToNext();
                     urobilinogjenaMi = Float.valueOf(c.getString(3));
                     urobilinogjenaMa = Float.valueOf(c.getString(4));
                     if(urobilinogjenaA<=urobilinogjenaMi)
                         rezultati[17]="Niveli i Urobilinogjenës është më i ulët se sa vlera normale";
                     else if(urobilinogjenaA>=urobilinogjenaMa)
                         rezultati[17]="Niveli i Urobilinogjës është më i lartë se sa vlera normale";
                     else
                         rezultati[17]="Vlera normale";
                 }
                 if(amilaza.getText().toString().equals("")){
                     rezultati[18]="Nuk eshte dhënë ky parametër!";
                 }
                 else{
                     float amilazaMi,amilazaMa;
                     float amilazaA = Float.valueOf(amilaza.getText().toString());
                     c.moveToNext();
                     amilazaMi = Float.valueOf(c.getString(3));
                     amilazaMa = Float.valueOf(c.getString(4));
                     if(amilazaA<=amilazaMi)
                         rezultati[18]="Niveli i Amilazës është më i ulët se sa vlera normale";
                     else if(amilazaA>=amilazaMa)
                         rezultati[18]="Niveli i Amilazës është më i lartë se sa vlera normale";
                     else
                         rezultati[18]="Vlera normale";
                 }


                 startActivity(new Intent(analizat.this,rezultetAnaliza.class));
            }catch (Exception e){
                 Toast.makeText(analizat.this, "Gabim "+e, Toast.LENGTH_SHORT).show();
             }
            }
        });

    }
}
