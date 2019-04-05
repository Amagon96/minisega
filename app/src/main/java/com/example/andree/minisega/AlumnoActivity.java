package com.example.andree.minisega;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class AlumnoActivity extends AppCompatActivity {

    private String alumnoNombre;
    private String alumnoAPaterno;
    private int alumnId;
    private TextView alumnoTitle;

    private ListView materiassListView;

    private Button save;
    private ImageButton delete;

    /**
     * Declaración spinner y adapter
     */
    private Spinner spinnerMaterias;
    private ArrayAdapter spinnerAdapter;

    /**
     * Declaración de lista y elemento actual
     */
    private ArrayList<Materia> listaMaterias;
    private Materia Materia;

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
        alumnId = Integer.parseInt(intent.getStringExtra("alumnoId"));

        materiassListView = (ListView) findViewById(R.id.listMaterias);

        materiassListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object item = spinnerAdapter.getItem(position);
                Intent alumnoMateriasActivity = new Intent(view.getContext(), MateriaAlumnos.class);
      //          alumnoMateriasActivity.putExtra("materiaId", getMateriaById(position).getId());
                //alumnoMateriasActivity.putExtra("alumnoId", alumnId);
                alumnoMateriasActivity.putExtra("alumnoNombre", alumnoNombre);
                alumnoMateriasActivity.putExtra("alumnoAPaterno", alumnoAPaterno);
                System.out.println("position = " + position);
                startActivityForResult(alumnoMateriasActivity, 0);
            }
        });

        init();
    }

    private void init() {
        alumnoTitle = (TextView) findViewById(R.id.alumnoTitle);
        alumnoTitle.setText(String.format("%s - %s %s", alumnId, alumnoAPaterno,alumnoNombre));
        save = (Button) findViewById(R.id.saveButton);
        save.setOnClickListener(v -> {guardarMaterias();});
        delete = (ImageButton) findViewById(R.id.deleteButton);
        delete.setOnClickListener(v -> {borrarAlumno();});

        listaMaterias = db.getMaterias();
        spinnerAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item, listaMaterias.toArray());
        materiassListView.setAdapter(spinnerAdapter);
    }

    private void borrarAlumno() {

    }

    private void guardarMaterias() {

    }
}