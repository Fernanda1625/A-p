package com.example.nicky;

import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import OpenHelper.SQLite_OpenHelper;

public class Registro extends AppCompatActivity {

    Button btnGrabarUsu;
    EditText txtNomUsu, txtEmailUsu, numCelUsu, txtDirUsu, txtContrUsu;

    SQLite_OpenHelper helper = new SQLite_OpenHelper(this,"usuarios",1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        btnGrabarUsu = (Button) findViewById(R.id.btnRegistro);
        txtNomUsu = (EditText) findViewById(R.id.txtNomUsu);
        txtEmailUsu = (EditText) findViewById(R.id.txtEmailUsu);
        numCelUsu = (EditText) findViewById(R.id.numCelUsu);
        txtDirUsu = (EditText) findViewById(R.id.txtDirUsu);
        txtContrUsu = (EditText) findViewById(R.id.txtContrUsu);

        btnGrabarUsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bCamposCorrectos()) {
                    helper.abrir();
                    helper.insertarRegistro(String.valueOf(txtNomUsu.getText()),
                            String.valueOf(txtEmailUsu.getText()),
                            Integer.valueOf(String.valueOf(numCelUsu.getText())),
                            String.valueOf(txtDirUsu.getText()),
                            String.valueOf(txtContrUsu.getText()));
                    helper.close();

                    Toast.makeText(getApplicationContext(), "Registro almacenado con éxito.",
                            Toast.LENGTH_LONG).show();

                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Campos vacíos",
                            Toast.LENGTH_LONG).show();
                }
            }

        });
    }

    private boolean bCamposCorrectos (){
        boolean res = true;
        if(txtNomUsu.getText().toString().trim().isEmpty()){
            txtNomUsu.requestFocus();
            res = false;
        }
        if(txtEmailUsu.getText().toString().trim().isEmpty()){
            txtEmailUsu.requestFocus();
            res = false;
        }
        if(txtDirUsu.getText().toString().trim().isEmpty()){
            txtDirUsu.requestFocus();
            res = false;
        }
        if(txtContrUsu.getText().toString().trim().isEmpty()){
            txtContrUsu.requestFocus();
            res = false;
        }
        if(numCelUsu.getText().toString().trim().isEmpty()){
            numCelUsu.requestFocus();
            res = false;
        }
        return res;
    }
}