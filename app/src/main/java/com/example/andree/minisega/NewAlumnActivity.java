package com.example.andree.minisega;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class NewAlumnActivity extends AppCompatActivity {

    private Button btnAdd;
    private EditText editNombre;
    private EditText editAPaterno;
    private EditText editCarrera;

    private MyOpenHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_alumn);

        init();
    }

    public void init(){
        editNombre = (EditText) findViewById(R.id.editNombre);
        editAPaterno = (EditText) findViewById(R.id.editAPaterno);
        editCarrera = (EditText) findViewById(R.id.editCarrera);

        btnAdd = (Button) findViewById(R.id.addButton);

        db = new MyOpenHelper(this);

        btnAdd.setOnClickListener(v -> {
            addAlumno(editNombre.getText().toString(), editAPaterno.getText().toString(), editCarrera.getText().toString());
        });
    }

    private void addAlumno(String nombre, String aPaterno, String carrera) {
        if(nombre.isEmpty() || aPaterno.isEmpty() || carrera.isEmpty()){
            Toast.makeText(this, "Por favor llene todos los campos.", Toast.LENGTH_LONG);
        }else {
            db.insertAlumno(nombre, aPaterno, carrera);
            finish();
            startActivity(getIntent());
        }
    }
}
