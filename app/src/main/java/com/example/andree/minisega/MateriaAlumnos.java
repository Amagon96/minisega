package com.example.andree.minisega;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MateriaAlumnos extends AppCompatActivity {

    private TextView text;
    private int materiaId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumno_materias);
        Intent intent = getIntent();

        materiaId = Integer.parseInt(intent.getStringExtra("materiaId"));

        text = (TextView) findViewById(R.id.textView3);
        //materiaId = Integer.parseInt(intent.getStringExtra("materiaId"));
        init();
    }

    private void init() {
        text.setText(Integer.toString(materiaId));
    }
}
