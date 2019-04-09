package com.example.andree.minisega;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class FaltasActivity extends AppCompatActivity {

    private TextView estatus;

    private String materiaId;
    private String alumnoId;
    private int materiaCreditos;
    private String alumno;

    private int faltas;

    private MyOpenHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faltas);
        Intent intent = getIntent();
        db = new MyOpenHelper(this);

        materiaId = intent.getStringExtra("materiaId");
        alumnoId = intent.getStringExtra("alumnoId");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent previousActivity = new Intent(v.getContext(), AlumnoActivity.class);
                previousActivity.putExtra("alumnoId", alumnoId);
                previousActivity.putExtra("alumnoNombre", intent.getStringExtra("alumnoNombre"));
                previousActivity.putExtra("alumnoAPaterno",intent.getStringExtra("alumnoAPaterno"));

                startActivity(previousActivity);
            }
        });

        estatus = (TextView) findViewById(R.id.faltasStatus);

        alumno = String.format("%s %s", intent.getStringExtra(
                "alumnoAPaterno"), intent.getStringExtra("alumnoNombre"));
        getMateriaCreditosByMateriaId(materiaId, alumnoId);
    }

    private void getMateriaCreditosByMateriaId(String materiaId, String alumnoId) {
        materiaCreditos = db.getMateriaCreditosById(materiaId);
        setEstatus(materiaCreditos, materiaId, alumnoId);
    }

    private void setEstatus(int materiaCreditosEstatus, String idMateria, String idAlumno) {
        faltas = db.getFaltas(idMateria, idAlumno);
        if(faltas >= Double.valueOf(materiaCreditosEstatus*.10).intValue()){
            estatus.setText(String.format("Reprobado por faltas."));
        }else if(faltas == 0){
            estatus.setText(String.format("El alumno no tiene faltas"));
        }else if (faltas == 1){
            estatus.setText(String.format("El alumno tiene 1 falta."));
        }else{
            estatus.setText(String.format("El alumno tiene %s faltas", faltas));
        }
    }
}
