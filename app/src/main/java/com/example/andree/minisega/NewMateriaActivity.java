package com.example.andree.minisega;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewMateriaActivity extends AppCompatActivity {
    private EditText nombre;
    private EditText clave;
    private EditText creditos;

    private Button goBackButton;

    private Button addButton;
    private MyOpenHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_materia);

        init();
    }

    private void init() {
        nombre = (EditText) findViewById(R.id.editNombre);
        clave = (EditText) findViewById(R.id.editClave);
        creditos = (EditText) findViewById(R.id.editCreditos);
        creditos.setText("0");

        addButton = (Button) findViewById(R.id.addButton);

        goBackButton = (Button) findViewById(R.id.saveButton);
        goBackButton.setOnClickListener(v -> {
            goBack(v);});

        db = new MyOpenHelper(this);

        addButton.setOnClickListener(v -> {addMateria(nombre.getText().toString(), clave.getText().toString(), Integer.valueOf(creditos.getText().toString()));});
    }

    private void addMateria(String nombre, String clave, Integer creditos) {
        if(nombre.isEmpty() || clave.isEmpty() || creditos.equals(0)){
            Toast.makeText(this, "Por favor llene todos los campos.", Toast.LENGTH_LONG);
        }else{
            db.insertMateria(creditos, clave, nombre);
            finish();
            startActivity(getIntent());
        }
    }

    private void goBack(View view) {
        Intent materiasActivity = new Intent(view.getContext(), MateriasActivity.class);
        startActivityForResult(materiasActivity, 0);
    }
}
