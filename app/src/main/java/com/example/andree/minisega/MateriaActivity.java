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

public class MateriaActivity extends AppCompatActivity {

    private String materiaNombre;
    private int materiaId;
    private String materiaCreditos;
    private String materiaClave;
    private TextView materiaTitle;

    private ListView alumnosListView;

    private Button save;
    private ImageButton delete;

    /**
     * Declaración spinner y adapter
     */
    private Spinner spinnerAlumnos;
    private ArrayAdapter spinnerAdapter;

    /**
     * Declaración de lista y elemento actual
     */
    private ArrayList<Alumno> listaAlumnos;
    private Materia Materia;

    /**
     * Controlador de bases de datos
     */
    private MyOpenHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materia);
        Intent intent = getIntent();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = new MyOpenHelper(this);
        //materiaId = Integer.parseInt(intent.getStringExtra("materiaId"));
        materiaNombre = intent.getStringExtra("nombreMateria");
        materiaClave = intent.getStringExtra("materiaClave");
        materiaCreditos = intent.getStringExtra("creditos");

        alumnosListView = (ListView) findViewById(R.id.listAlumnos);

        alumnosListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object item = spinnerAdapter.getItem(position);
                Intent alumnoMateriasActivity = new Intent(view.getContext(), AlumnoMaterias.class);
                //materiaActivity.putExtra("materiaId", materiaId);
                alumnoMateriasActivity.putExtra("nombreMateria", materiaNombre);
                alumnoMateriasActivity.putExtra("materiaClave", materiaClave);
                alumnoMateriasActivity.putExtra("creditos", materiaCreditos);
                startActivityForResult(alumnoMateriasActivity, 0);
            }
        });

        db = new MyOpenHelper(this);
        init();
    }

    private void init() {
        materiaTitle = (TextView) findViewById(R.id.alumnoTitle);
        materiaTitle.setText(String.format("%s - %s", materiaClave, materiaNombre));
        listaAlumnos = db.getAlumnos();
        spinnerAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,listaAlumnos.toArray());
        alumnosListView.setAdapter(spinnerAdapter);
    }
}
