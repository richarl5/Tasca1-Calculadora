package edu.upc.eetac.dsa.calculadora;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TractamentOperacio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tractament_operacio);
        TextView operacio = (TextView) findViewById(R.id.operacio);
        String op = getIntent().getExtras().getString("OP");
        operacio.setText(op);

        Button esborrar = (Button) findViewById(R.id.buttonEsb);
        esborrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intPrin = getIntent();
                setResult(RESULT_OK,intPrin);
                finish();
            }
        });

        Button modificar = (Button) findViewById(R.id.buttonMod);
        modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intPrin = getIntent();
                setResult(101,intPrin);
                finish();
            }
        });
    }
}
