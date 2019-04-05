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

import java.util.ArrayList;

public class MateriasActivity extends AppCompatActivity {

    private ListView materiasListView;

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
        setContentView(R.layout.activity_materias);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        materiasListView = (ListView) findViewById(R.id.materias);

        materiasListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object item = spinnerAdapter.getItem(position);
                Intent materiaActivity = new Intent(view.getContext(), MateriaActivity.class);
                materiaActivity.putExtra("materiaId", Integer.toString(((Materia) item ).getId()));
                System.out.println("->" + Integer.toString(((Materia) item).getId()));
                materiaActivity.putExtra("nombreMateria", ((Materia) item).getNombre());
                materiaActivity.putExtra("materiaClave", ((Materia)item).getClave());
                materiaActivity.putExtra("creditos", Integer.toString(((Materia)item).getCreditos()));
                startActivityForResult(materiaActivity, 0);
            }
        });

        db = new MyOpenHelper(this);
        init();
    }

    private void init() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            startNewMateria(v);});
        listaMaterias = db.getMaterias();
        spinnerAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,listaMaterias.toArray());
        materiasListView.setAdapter(spinnerAdapter);
    }

    private void startNewMateria(View v){
        Intent newMateriaIntent = new Intent(v.getContext(), NewMateriaActivity.class);
        startActivityForResult(newMateriaIntent,0);
    }
}
