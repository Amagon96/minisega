package com.example.andree.minisega;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AlumnoActivity extends AppCompatActivity {

    private String alumnoNombre;
    private String alumnoAPaterno;
    private String alumnoId;
    private TextView alumnoTitle;

    private ListView materiassListView;

    private Button goBackButton;
    private Button delete;

    /**
     * Declaración spinner y adapter
     */
    private Spinner spinnerMaterias;
    private ArrayAdapter spinnerAdapter;

    /**
     * Declaración de lista y elemento actual
     */
    private ArrayList<Materia> listaMaterias;
    private Materia materia;

    /**
     * Controlador de bases de datos
     */
    private MyOpenHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumno);
        Intent intent = getIntent();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = new MyOpenHelper(this);

        alumnoNombre = intent.getStringExtra("alumnoNombre");
        alumnoAPaterno = intent.getStringExtra("alumnoAPaterno");
        alumnoId = intent.getStringExtra("alumnoId");

        materiassListView = (ListView) findViewById(R.id.listMaterias);


        materiassListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent faltasActivity = new Intent(view.getContext(), FaltasActivity.class);
                faltasActivity.putExtra("materiaId", Integer.toString(position+1));
                faltasActivity.putExtra("alumnoId", alumnoId);
                faltasActivity.putExtra("alumnoNombre", alumnoNombre);
                faltasActivity.putExtra("alumnoAPaterno", alumnoAPaterno);
                startActivityForResult(faltasActivity, 0);
            }
        });


        init();
    }


    private void init() {
        alumnoTitle = (TextView) findViewById(R.id.alumnoTitle);
        alumnoTitle.setText(String.format("%s %s", alumnoAPaterno,alumnoNombre));
        goBackButton = (Button) findViewById(R.id.saveButton);
        goBackButton.setOnClickListener(v -> {
            goBack(v);});
        delete = (Button) findViewById(R.id.cancelar);
        delete.setOnClickListener(v -> {borrarAlumno(v, alumnoId);});

        listaMaterias = db.getMateriasOfAlumno(alumnoId);
        spinnerAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item, listaMaterias.toArray());
        materiassListView.setAdapter(spinnerAdapter);
    }

    private void borrarAlumno(View view, String idAlumno) {
        db.deleteAlumno(idAlumno);
        Toast.makeText(this, "Alumno eliminado con extito",Toast.LENGTH_LONG).show();
        Intent faltasActivity = new Intent(view.getContext(), AlumnosActivity.class);
        startActivityForResult(faltasActivity, 0);
    }

    private void goBack(View view) {
        Intent faltasActivity = new Intent(view.getContext(), AlumnosActivity.class);
        startActivityForResult(faltasActivity, 0);
    }
}
