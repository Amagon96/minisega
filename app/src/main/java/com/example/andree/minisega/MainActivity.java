package com.example.andree.minisega;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button opcionAlumnos;
    private Button opcionMaterias;
    private Button thirdCard;
    private Button forthCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        opcionAlumnos = (Button) findViewById(R.id.alumnos);
        opcionAlumnos.setOnClickListener(v -> {showAlumnosActivity(v);});
        opcionMaterias = (Button) findViewById(R.id.materias);
        opcionMaterias.setOnClickListener(v ->{showMateriasActivity(v);});
    }

    private void showMateriasActivity(View v) {
        Intent materiasActivity = new Intent(v.getContext(), MateriasActivity.class);
        startActivityForResult(materiasActivity, 0);
    }

    private void showAlumnosActivity(View v) {
        Intent alumnosActivity = new Intent(v.getContext(), AlumnosActivity.class);
        startActivityForResult(alumnosActivity, 0);
    }
}
