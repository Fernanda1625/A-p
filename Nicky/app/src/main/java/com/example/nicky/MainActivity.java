package com.example.nicky;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import OpenHelper.SQLite_OpenHelper;

public class MainActivity extends AppCompatActivity {

    TextView tvRegistrate;
    Button btnIngresar;

    SQLite_OpenHelper helper = new SQLite_OpenHelper(this, usuarios, context,1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvRegistrate = (TextView)findViewById(R.id.tvRegistrate);
        tvRegistrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Registro.class);
                startActivity(i);

            }
        });

        btnIngresar= (Button)findViewById(R.id.btnIngresar);
        btnIngresar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                EditText txtUsu=(EditText)findViewById(R.id.txtUsuario);
                EditText txtContr=(EditText)findViewById(R.id.txtPassword);

                try {
                    Cursor cursor = helper.ConsultarUsuContr(txtUsu.getText().toString(), txtContr.getText().toString());
                    if (cursor.getCount() > 0) {
                        Intent intent = new Intent(getApplicationContext(), Principal.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Usuario y/o Contraseña erróneos", Toast.LENGTH_LONG).show();
                    }
                    txtUsu.setText("");
                    txtContr.setText("");
                    txtUsu.findFocus();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}