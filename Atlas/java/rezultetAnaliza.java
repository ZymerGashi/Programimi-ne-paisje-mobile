package com.example.dell.atlasi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class rezultetAnaliza extends AppCompatActivity {
    TextView sasia,peshaSpecifike,reaksioniElektrokimik,perberesitENgurte,natriumi,kaliumi,kalciumi,magnezi,amoniaku,fosfatet,urea,acidiUrik,acidiLaktik,kreatinina,kreatina,aminoAcide,indikoni,urobilinogjena,amilaza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rezultet_analiza);
        sasia = findViewById(R.id.txtSasiaR);
        peshaSpecifike = findViewById(R.id.txtPeshaSpecifikeR);
        reaksioniElektrokimik = findViewById(R.id.txtReaksioniElektrokimikR);
        perberesitENgurte = findViewById(R.id.txtPerberesitENgurteR);
        natriumi = findViewById(R.id.txtNatriumR);
        kaliumi = findViewById(R.id.txtKaliumR);
        kalciumi = findViewById(R.id.txtKalciumR);
        magnezi = findViewById(R.id.txtMagneziR);
        amoniaku = findViewById(R.id.txtAmoniakR);
        fosfatet = findViewById(R.id.txtFosfateR);
        urea = findViewById(R.id.txtUreR);
        acidiUrik = findViewById(R.id.txtAcidiUrikR);
        acidiLaktik = findViewById(R.id.txtAcidiLaktikR);
        kreatinina = findViewById(R.id.txtKreatininaR);
        kreatina = findViewById(R.id.txtKreatinaR);
        aminoAcide = findViewById(R.id.txtAminoAcideR);
        indikoni = findViewById(R.id.txtIndikoniR);
        urobilinogjena = findViewById(R.id.txtUrobilinogjenaR);
        amilaza = findViewById(R.id.txtAmilazaR);
        sasia.setText(sasia.getText().toString()+" "+analizat.rezultati[0]);
        peshaSpecifike.setText(peshaSpecifike.getText().toString()+" "+analizat.rezultati[1]);
        reaksioniElektrokimik.setText(reaksioniElektrokimik.getText().toString()+" "+analizat.rezultati[2]);
        perberesitENgurte.setText(perberesitENgurte.getText().toString()+" "+analizat.rezultati[3]);
        natriumi.setText(natriumi.getText().toString()+" "+analizat.rezultati[4]);
        kaliumi.setText(kaliumi.getText().toString()+" "+analizat.rezultati[5]);
        kalciumi.setText(kalciumi.getText().toString()+" "+analizat.rezultati[6]);
        magnezi.setText(magnezi.getText().toString()+" "+analizat.rezultati[7]);
        amoniaku.setText(amoniaku.getText().toString()+" "+analizat.rezultati[8]);
        fosfatet.setText(fosfatet.getText().toString()+" "+analizat.rezultati[9]);
        urea.setText(urea.getText().toString()+" "+analizat.rezultati[10]);
        acidiUrik.setText(acidiUrik.getText().toString()+" "+analizat.rezultati[11]);
        acidiLaktik.setText(acidiLaktik.getText().toString()+" "+analizat.rezultati[12]);
        kreatinina.setText(kreatinina.getText().toString()+" "+analizat.rezultati[13]);
        kreatina.setText(kreatina.getText().toString()+" "+analizat.rezultati[14]);
        aminoAcide.setText(aminoAcide.getText().toString()+" "+analizat.rezultati[15]);
        indikoni.setText(indikoni.getText().toString()+" "+analizat.rezultati[16]);
        urobilinogjena.setText(urobilinogjena.getText().toString()+" "+analizat.rezultati[17]);
        amilaza.setText(amilaza.getText().toString()+" "+analizat.rezultati[18]);

    }
}
