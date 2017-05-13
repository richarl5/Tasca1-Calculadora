package edu.upc.eetac.dsa.calculadora;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PantallaPrincipal extends AppCompatActivity {

    private String op ="";
    private List<Operacio> lista = new ArrayList<>();
    private ArrayList<String> llistaStr = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_principal);

        Button button = (Button) findViewById(R.id.buttonhist);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intHist = new Intent(PantallaPrincipal.this, LlistaOperacions.class);
                intHist.putStringArrayListExtra("llista",llistaStr);
                startActivityForResult(intHist,100);
            }
        });
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if((requestCode == 100) && (resultCode == LlistaOperacions.RESULT_OK)) {
            lista = new ArrayList<>();
            llistaStr = new ArrayList<>();
        }

        if((requestCode == 100) && (resultCode == 101)) {
            int pos = data.getExtras().getInt("POS");
            lista.remove(pos);
            llistaStr.remove(pos);
        }

        if((requestCode == 100) && (resultCode == 102)) {
            int pos = data.getExtras().getInt("POS");
            Operacio operacio = lista.get(pos);
            EditText num1 = (EditText) findViewById(R.id.num1);
            EditText num2 = (EditText) findViewById(R.id.num2);
            num1.setText(operacio.getNum1().toString());
            num2.setText(operacio.getNum2().toString());
            op = operacio.getOp();
            TextView textbox = (TextView) findViewById(R.id.textOp);
            textbox.setText(operacio.getOp());
            textbox = (TextView) findViewById(R.id.res);
            textbox.setText("");
        }
    }

    public void selectOp(View view) {   //Mostra un dialog amb les diferents operacions
        AlertDialog levelDialog;
        final CharSequence[] items = {" + ", " - ", " / ", " X "};
        // Creating and Building the Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Escull operació");
        final TextView textop = (TextView) findViewById(R.id.textOp);
        builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                switch (item) {
                    case 0:
                        op="+";
                        textop.setText("+");
                        break;
                    case 1:
                        op="-";
                        textop.setText("-");
                        break;
                    case 2:
                        op="/";
                        textop.setText("/");
                        break;
                    case 3:
                        op="*";
                        textop.setText("*");
                        break;
                }
                dialog.dismiss();
            }
        });
        levelDialog = builder.create();
        levelDialog.show();
    }

    public void setC(View view) {   //Posa a 0 el num1, num2 i el resultat
        EditText editText = (EditText) findViewById(R.id.num1);
        editText.setText("", TextView.BufferType.EDITABLE);
        editText.setHint("0");
        editText = (EditText) findViewById(R.id.num2);
        editText.setHint("0");
        editText.setText("", TextView.BufferType.EDITABLE);
        TextView text = (TextView) findViewById(R.id.res);
        text.setText("0", TextView.BufferType.EDITABLE);
    }

    public void setRes (View view) { //Legeix els dos números i realitza l'operació
        try {
            EditText editText = (EditText) findViewById(R.id.num1);
            Integer num1 = Integer.parseInt(editText.getText().toString());
            editText = (EditText) findViewById(R.id.num2);
            Integer num2 = Integer.parseInt(editText.getText().toString());
            Float res = null;
            TextView text = (TextView) findViewById(R.id.res);
            switch (op) {
                case "+":
                    res = (float) num1 + num2;
                    text.setText(res.toString());
                    break;
                case "-":
                    res = (float) num1 - num2;
                    text.setText(res.toString());
                    break;
                case "/":
                    res = (float) num1 / num2;
                    text.setText(String.valueOf(res));
                    break;
                case "*":
                    res = (float) num1 * num2;
                    text.setText(res.toString());
                    break;
                default: Toast.makeText(this, "Escull una operació", Toast.LENGTH_SHORT).show();
            }
            Operacio operacio = new Operacio(num1,num2,op,res); //Afegeix a la llista l'operació realitzada.
            if(operacio.getRes()!=null) {
                lista.add(operacio);
                llistaStr.add(lista.size() + ": " + operacio.toString());
            }

        } catch (Exception e) {
            Toast.makeText(this, "Cal indicar els dos valors numèrics", Toast.LENGTH_SHORT).show();
        }
    }

}
