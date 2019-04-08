package com.example.andree.minisega;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MyOpenHelper extends SQLiteOpenHelper {

    public static final String GRUPO_TABLE_CREATE = "CREATE TABLE grupos(_id INTEGER PRIMARY " +
            "KEY AUTOINCREMENT, clave TEXT, materia INTEGER)";

    public static final String ALUMNO_TABLE_CREATE = "CREATE TABLE alumnos(_id INTEGER PRIMARY " +
            "KEY AUTOINCREMENT, nombre TEXT, aPaterno TEXT, carrera TEXT)";

    public static final String MATERIA_TABLE_CREATE = "CREATE TABLE materias(_id INTEGER PRIMARY " +
            "KEY AUTOINCREMENT, creditos INTEGER, clave TEXT, nombre TEXT)";

    public static final String ALUMNO_Y_MATERIA = "CREATE TABLE materias_alumnos(_id INTEGER PRIMARY " +
            "KEY AUTOINCREMENT, idAlumno INTEGER, idMateria INTEGER, faltas INTEGER)";

    private static final String DB_NAME = "minisega.sqlite";
    private static final int DB_VERSION = 1;
    private SQLiteDatabase db;

    public MyOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ALUMNO_TABLE_CREATE);
        db.execSQL(MATERIA_TABLE_CREATE);
        db.execSQL(ALUMNO_Y_MATERIA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertAlumno(String nombre, String aPaterno, String carrera){
        ContentValues cv = new ContentValues();
        cv.put("nombre", nombre);
        cv.put("aPaterno", aPaterno);
        cv.put("carrera", carrera);
        db.insert("alumnos", null, cv);
    }

    public ArrayList<Alumno> getAlumnos(){
        ArrayList<Alumno> lista=new ArrayList<>();
        Cursor c = db.rawQuery("select _id, nombre, aPaterno, carrera from alumnos", null);
        if (c != null && c.getCount()>0) {
            c.moveToFirst();
            do {
                String name = c.getString(c.getColumnIndex("nombre"));
                String aPaterno = c.getString(c.getColumnIndex("aPaterno"));
                String carrera = c.getString(c.getColumnIndex("carrera"));
                int id=c.getInt(c.getColumnIndex("_id"));
                Alumno al =new Alumno(name, aPaterno, carrera, id);
                lista.add(al);
            } while (c.moveToNext());
        }

        c.close();
        return lista;
    }

    public void insertMateria(int creditos, String clave, String nombre){
        ContentValues cv = new ContentValues();
        cv.put("nombre", nombre);
        cv.put("creditos", creditos);
        cv.put("clave", clave);
        db.insert("materias", null, cv);
    }

    public ArrayList<Materia> getMaterias(){
        ArrayList<Materia> lista=new ArrayList<>();
        Cursor c = db.rawQuery("select _id, creditos, clave, nombre from materias", null);
        if (c != null && c.getCount()>0) {
            c.moveToFirst();
            do {
                String nombre = c.getString(c.getColumnIndex("nombre"));
                String clave = c.getString(c.getColumnIndex("clave"));
                int creditos = c.getInt(c.getColumnIndex("creditos"));
                int id=c.getInt(c.getColumnIndex("_id"));
                Materia materia =new Materia(creditos, clave, nombre, id);
                lista.add(materia);
            } while (c.moveToNext());
        }
        c.close();
        return lista;
    }

    public void registrarAlumnoMateria(Integer idAlumno, Integer idMateria){
        ContentValues cv = new ContentValues();
        cv.put("idAlumno", idAlumno);
        cv.put("idMateria", idMateria);
        cv.put("faltas", 0);
        db.insert("materias_alumnos", null, cv);
    }


    public ArrayList<Materia> getMateriasOfAlumno(String idSelectedAlumno){
        ArrayList<MateriasAlumnos> lista = new ArrayList<>();
        Cursor c = db.rawQuery("select * from materias_alumnos WHERE idAlumno = ?", new String[]{idSelectedAlumno});
        if(c != null && c.getCount()>0){
            c.moveToFirst();
            do{
                int id = c.getInt(c.getColumnIndex("id"));
                int idAlumno = c.getInt(c.getColumnIndex("idAlumno"));
                int idMateria = c.getInt(c.getColumnIndex("idMateria"));
                Integer faltas = c.getInt(c.getColumnIndex("faltas"));
                MateriasAlumnos materiasAlumnos = new MateriasAlumnos(id, idAlumno, idMateria, faltas);

                lista.add(materiasAlumnos);
            }while (c.moveToNext());
        }
        c.close();

        ArrayList<Materia> listaMaterias = new ArrayList<>();
        for (int i = 0; i < lista.size(); i++) {
            Cursor d = db.rawQuery("select * from materias WHERE _id = ?", new String[]{Integer.toString(lista.get(i).getIdMateria())});
            if (d != null && d.getCount()>0){
                d.moveToFirst();
                do {
                    String nombre = d.getString(d.getColumnIndex("nombre"));
                    String clave = d.getString(d.getColumnIndex("clave"));
                    Integer creditos = d.getInt(d.getColumnIndex("creditos"));
                    int idM = d.getInt(d.getColumnIndex("_id"));
                    Materia materia = new Materia(creditos, clave, nombre, idM);
                    listaMaterias.add(materia);
                }while(d.moveToFirst());
            }
            d.close();
        }
        return listaMaterias;
    }

    public ArrayList<Alumno> getAlumnosOfMateria(String idSelectedMateria){
        ArrayList<MateriasAlumnos> lista = new ArrayList<>();
        Cursor c = db.rawQuery("select * from materias_alumnos WHERE idMateria = ?", new String[]{idSelectedMateria});
        if(c != null && c.getCount()>0){
            c.moveToFirst();
            do {
                int idAlumno = c.getInt(c.getColumnIndex("idAlumno"));
                int idMateria = c.getInt(c.getColumnIndex("idMateria"));
                int faltas = c.getInt(c.getColumnIndex("faltas"));
                int id = c.getInt(c.getColumnIndex("_id"));
                MateriasAlumnos materiasAlumnos = new MateriasAlumnos(id, idAlumno, idMateria, faltas);
                lista.add(materiasAlumnos);
            }while(c.moveToNext());
        }

        c.close();

        ArrayList<Alumno> listaAlumnos = new ArrayList<>();
        for (int i = 0; i < lista.size(); i++) {
            Cursor d = db.rawQuery("select * from alumnos WHERE _id = ?", new String[]{Integer.toString(lista.get(i).getIdAlumno())});
            if (d != null && d.getCount()>0){
                d.moveToFirst();
                do {
                    String nombre = d.getString(d.getColumnIndex("nombre"));
                    String aPaterno = d.getString(d.getColumnIndex("aPaterno"));
                    String carrera = d.getString(d.getColumnIndex("carrera"));
                    int id = d.getInt(d.getColumnIndex("_id"));
                    Alumno alumno = new Alumno(nombre,aPaterno, carrera, id);
                    listaAlumnos.add(alumno);
                }while(d.moveToNext());
            }
            d.close();
        }
        return listaAlumnos;
    }

    public Materia getMateriaById(String idSelectedMateria) {
        Cursor c = db.rawQuery("select * from materias WHERE _id = ?", new String[]{idSelectedMateria});
        c.moveToFirst();
        int materiaId = c.getInt(c.getColumnIndex("_id"));
        String claveMateria = c.getString(c.getColumnIndex("clave"));
        String nombreMateria = c.getString(c.getColumnIndex("nombre"));
        int materiaCreditos = c.getInt(c.getColumnIndex("creditos"));
        Materia materia = new Materia(materiaId, claveMateria, nombreMateria, materiaCreditos);
        c.close();
        return materia;
    }

    public Alumno getAlumnoById(String idSelectedAlumno) {
        Cursor c = db.rawQuery("select * from alumnos WHERE _id = ?", new String[]{idSelectedAlumno});
        c.moveToFirst();
        int alumnoId = c.getInt(c.getColumnIndex("_id"));
        String aPaternoAlumno = c.getString(c.getColumnIndex("aPaterno"));
        String nombreAlumno = c.getString(c.getColumnIndex("nombre"));
        String carreraAlumno = c.getString(c.getColumnIndex("carrera"));
        Alumno alumno = new Alumno(nombreAlumno, aPaternoAlumno, carreraAlumno, alumnoId);
        c.close();
        return alumno;
    }

    public Integer getFaltas(String idSelectedMateria, String idSelectedAlumno){
        Cursor c = db.rawQuery("select * from materias_alumnos WHERE idAlumno = ? AND idMateria = ?", new String[]{idSelectedAlumno, idSelectedMateria});
        c.moveToFirst();
        int faltas = 0;
        if(c.getCount()>0){
            faltas = c.getInt(c.getColumnIndex("faltas"));
        }
        c.close();
        return faltas;
    }

    public boolean isAlumnoOfMateria(String materiaId, String alumnoId) {
        Cursor c = db.rawQuery("select * from materias_alumnos WHERE idAlumno = ? AND idMateria = ?", new String[]{alumnoId, materiaId});
        c.moveToFirst();
        if(c.getCount()>0){
            c.close();
            return true;
        }
        else {
            c.close();
            return false;
        }
    }
}
