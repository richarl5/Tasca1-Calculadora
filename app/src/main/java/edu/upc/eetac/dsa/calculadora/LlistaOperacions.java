package edu.upc.eetac.dsa.calculadora;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class LlistaOperacions extends AppCompatActivity {

    private Integer pos;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //Recull la llista i mostra les diferents opcions al clicar sobre un element de la llista.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_llista_operacions);
        ArrayList<String> lista = (ArrayList<String>) getIntent().getStringArrayListExtra("llista");

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista);
        ListView lisv = (ListView) findViewById(R.id.llista);
        lisv.setAdapter(adapter);

        lisv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pos = position;
                Intent intent = new Intent(LlistaOperacions.this, TractamentOperacio.class);
                intent.putExtra("OP",((TextView)view).getText());
                startActivityForResult(intent,101);
            }
        });

        Button tancar = (Button) findViewById(R.id.buttonX);
        tancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Button esborrar = (Button) findViewById(R.id.buttonEsb);
        esborrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intEsb = new Intent(LlistaOperacions.this, ConfirmarEsborrar.class);
                startActivityForResult(intEsb,100);
            }
        });

    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        Intent intent;
        if((requestCode == 100) && (resultCode == ConfirmarEsborrar.RESULT_OK)) {
            intent = getIntent();
            setResult(RESULT_OK,intent);
            finish();
        }

        if((requestCode == 101) && (resultCode == TractamentOperacio.RESULT_OK)) {
            Object toRemove = adapter.getItem(pos);
            adapter.remove(toRemove);
            intent = getIntent();
            intent.putExtra("POS",pos);
            setResult(101,intent);
        }

        if((requestCode == 101) && (resultCode == 101)) {
            intent = getIntent();
            intent.putExtra("POS",pos);
            setResult(102,intent);
            finish();
        }
    }

}
