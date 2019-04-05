package com.example.andree.minisega;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import android.app.Activity;

import java.util.ArrayList;

public class AlumnosActivity extends AppCompatActivity {

    private ListView alumnosListView;

    /**
     * Declaración spinner y adapter
     */
    private Spinner spinnerAlumnos;
    private ArrayAdapter spinnerAdapter;

    /**
     * Declaración de lista y elemento actual
     */
    private ArrayList<Alumno> listaAlumnos;
    private Alumno alumno;

    /**
     * Controlador de bases de datos
     */
    private MyOpenHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumnos);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        alumnosListView = (ListView) findViewById(R.id.alumnos);

        alumnosListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object item = spinnerAdapter.getItem(position);
                Intent alumnoActivity = new Intent(view.getContext(), AlumnoActivity.class);
                alumnoActivity.putExtra("alumnoId", Integer.toString(((Alumno) item).getId()));
                alumnoActivity.putExtra("alumnoNombre", ((Alumno) item).getNombre());
                alumnoActivity.putExtra("alumnoAPaterno", ((Alumno) item).getaPaterno());
                startActivityForResult(alumnoActivity,0);
            }
        });

        db = new MyOpenHelper(this);
        init();
    }

    private void init() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(v -> {startNewAlumno(v);});
        listaAlumnos = db.getAlumnos();
        spinnerAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,listaAlumnos.toArray());
        alumnosListView.setAdapter(spinnerAdapter);
    }

    private void startNewAlumno(View v){
        Intent newAlumnoActivity = new Intent(v.getContext(), NewAlumnActivity.class);
        startActivityForResult(newAlumnoActivity,0);
    }
}