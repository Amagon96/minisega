package com.example.andree.minisega;

import android.annotation.SuppressLint;
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
import android.widget.Toast;

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
    private Button guardarFaltas;
    private String materiaId;
    private String alumnoId;

    private int faltasToInsert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_alumno);
        Intent intent = getIntent();
        db = new MyOpenHelper(this);

        idAlumno = intent.getStringExtra("alumnoPosition");

        alumno = db.getAlumnoById(idAlumno);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        materiaId = intent.getStringExtra("materiaId");
        alumnoId = intent.getStringExtra("alumnoPosition");

        layoutFaltas = (RelativeLayout) findViewById(R.id.containerFaltas);
        layoutFaltas.setVisibility(View.GONE);


        inscribirAlumno = (Button) findViewById(R.id.inscribirAlumno);
        inscribirAlumno.setOnClickListener((v)-> {
            registrarAlumnoAMateria(materiaId, alumnoId, alumno);
        });

        header = (TextView) findViewById(R.id.alumnoNombre);
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
                if (faltasToInsert != 0){
                    faltasToInsert+=-1;
                }
                numeroFaltas.setText(String.format("%s",faltasToInsert));
            }
        });

        guardarFaltas = (Button) findViewById(R.id.guardar);

        guardarFaltas.setOnClickListener((v)->{
            insertarFaltasAAlumno(materiaId, alumnoId, faltasToInsert, alumno);
        });

        editFechaFalta =(EditText) findViewById(R.id.fechaFalta);
        editFechaFalta.setVisibility(View.GONE);
        numeroFaltas = (TextView) findViewById(R.id.numeroFaltas);

        numeroFaltas.setText(String.format("%s",db.getFaltas(materiaId, alumnoId)));

        getHeader(alumno);
    }

    @SuppressLint("SetTextI18n")
    private void registrarAlumnoAMateria(String materiaId, String alumnoId, Alumno alumnoRegistro) {
        if(!isAlumnoInscribed()) {
            db.registrarAlumnoMateria(Integer.valueOf(materiaId), Integer.valueOf(alumnoId));
            layoutFaltas.setVisibility(View.VISIBLE);
            Toast.makeText(this, String.format("%s ha sido registrado con exito", alumnoRegistro.getNombre(), faltasToInsert),
                    Toast.LENGTH_LONG).show();
            inscribirAlumno.setText("Correr Alumno");
        }else{
            db.eliminarAlumnoMateria(Integer.valueOf(materiaId), Integer.valueOf(alumnoId));
            layoutFaltas.setVisibility(View.GONE);
            Toast.makeText(this, String.format("%s ha sido removido con exito", alumnoRegistro.getNombre(), faltasToInsert),
                    Toast.LENGTH_LONG).show();
            inscribirAlumno.setText("Inscribir Alumno");
        }
    }

    private void insertarFaltasAAlumno(String materiaId, String alumnoId, int faltasToInsert, Alumno alumnoFaltas) {
        db.insertFaltas(materiaId, alumnoId, faltasToInsert);
        Toast.makeText(this, String.format("%s tiene %s faltas.", alumnoFaltas.getNombre(), faltasToInsert),
                Toast.LENGTH_LONG).show();
    }

    private boolean isAlumnoInscribed() {
        return db.isAlumnoOfMateria(materiaId, alumnoId);
    }

    private void getHeader(Alumno alumnoHeader) {
        header.setText(alumnoHeader.getNombreCompleto());
    }
}
