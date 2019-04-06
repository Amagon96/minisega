package com.example.andree.minisega;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MateriaAlumnosActivity extends AppCompatActivity {

    private TextView header;
    private int materiaId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumno_materias);
        Intent intent = getIntent();

        materiaId = Integer.parseInt(intent.getStringExtra("materiaId"));

        header = (TextView) findViewById(R.id.alumnoTitle);
        init();
    }

    private void init() {
        header.setText(Integer.toString(materiaId));
    }
}
