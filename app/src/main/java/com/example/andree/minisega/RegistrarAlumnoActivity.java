package com.example.andree.minisega;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class RegistrarAlumnoActivity extends AppCompatActivity {

    private TextView header;
    private MyOpenHelper db;
    private String idAlumno;
    private Alumno alumno;

    private FloatingActionButton agregarFalta;
    private FloatingActionButton quitarFalta;
    private EditText editFechaFalta;
    private TextView numeroFaltas;
    private RelativeLayout layoutFaltas;
    private Button inscribirAlumno;
    private String materiaId;
    private String alumnoId;

    private int faltasToInsert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_alumno);
        Intent intent = getIntent();
        db = new MyOpenHelper(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        materiaId = intent.getStringExtra("materiaId");
        alumnoId = intent.getStringExtra("alumnoPosition");

        layoutFaltas = (RelativeLayout) findViewById(R.id.containerFaltas);
        layoutFaltas.setVisibility(View.GONE);

        inscribirAlumno = (Button) findViewById(R.id.inscribirAlumno);
        inscribirAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.registrarAlumnoMateria(Integer.valueOf(materiaId), Integer.valueOf(alumnoId));
                layoutFaltas.setVisibility(View.VISIBLE);
            }
        });

        header = (TextView) findViewById(R.id.alumnoNombre);
        idAlumno = intent.getStringExtra("alumnoPosition");
        agregarFalta = (FloatingActionButton) findViewById(R.id.agregarFalta);

        isAlumnoInscribed();

        if (isAlumnoInscribed()){
            layoutFaltas.setVisibility(View.VISIBLE);
        }

        agregarFalta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                faltasToInsert = Integer.parseInt(numeroFaltas.getText().toString());
                faltasToInsert +=1;
                numeroFaltas.setText(String.format("%s", faltasToInsert));
            }
        });
        quitarFalta = (FloatingActionButton) findViewById(R.id.quitarFalta);
        quitarFalta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                faltasToInsert = Integer.parseInt(numeroFaltas.getText().toString());
                faltasToInsert+=-1;
                numeroFaltas.setText(String.format("%s",faltasToInsert));
            }
        });
        editFechaFalta =(EditText) findViewById(R.id.fechaFalta);
        editFechaFalta.setVisibility(View.GONE);
        numeroFaltas = (TextView) findViewById(R.id.numeroFaltas);
        System.out.println("alumnoId = " + alumnoId);
        System.out.println("materiaId = " + materiaId);
        numeroFaltas.setText(String.format("%s",db.getFaltas(materiaId, alumnoId)));

        init();
    }

    private boolean isAlumnoInscribed() {
        return db.isAlumnoOfMateria(materiaId, alumnoId);
    }

    private void init() {
        alumno = db.getAlumnoById(idAlumno);
        header.setText(alumno.getNombreCompleto());
    }
}
