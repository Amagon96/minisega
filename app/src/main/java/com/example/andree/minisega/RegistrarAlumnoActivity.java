package com.example.andree.minisega;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class RegistrarAlumnoActivity extends AppCompatActivity {

    private TextView header;
    private MyOpenHelper db;
    private String idAlumno;
    private Alumno alumno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_alumno);
        Intent intent = getIntent();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        header = (TextView) findViewById(R.id.alumnoNombre);
        idAlumno = intent.getStringExtra("alumnoPosition");
        db = new MyOpenHelper(this);

        init();
    }

    private void init() {
        alumno = db.getAlumnoById(idAlumno);
        header.setText(alumno.getNombreCompleto());
    }
}
