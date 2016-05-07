package mx.edu.utng.css.dao;


import android.content.ContentValues;
import android.content.Context;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import mx.edu.utng.css.Contact;


/**
 * Created by MD on 29/02/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "contacts.db";

    private static final String TABLE_NAME_USUARIOS = "Usuario";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_UNAME = "uname";
    private static final String COLUMN_PASS = "pass";

    private static final String TABLE_NAME_LOCK = "Bloqueo";
    private static final String COLUMN_LOCK_ID = "id";
    private static final String COLUMN_LOCK = "lock";

    private static final String TABLE_NAME_POINTS = "Puntaje";
    private static final String COLUMN_POINTS_ID = "id";
    private static final String COLUMN_POINTS = "points";

    private static final String TABLE_NAME_CAPITULO = "Capitulo";
    private static final String COLUMN_CAPITULO_ID = "id";
    private static final String COLUMN_CAPITULO = "cap";

    private static final String TABLE_NAME_TEME = "Temas";
    private static final String COLUMN_TEME_ID = "id";
    private static final String COLUMN_TEME = "teme";



    private SQLiteDatabase dbSql;


    private static final String TABLE_CREATE_TEME = "CREATE TABLE "+ TABLE_NAME_TEME +"(" +
            COLUMN_TEME_ID +" INTEGER PRIMARY KEY NOT NULL, "+
            COLUMN_TEME +" INTEGER NOT NULL"+");";

    private static final String TABLE_CREATE_PUNTAJE = "CREATE TABLE "+ TABLE_NAME_POINTS +"(" +
            COLUMN_POINTS_ID +" INTEGER PRIMARY KEY NOT NULL, "+
            COLUMN_POINTS +" INTEGER NOT NULL"+");";

    ///Sintaxis para crear la tabla de usuario
    private static final String TABLE_CREATE_USUARIOS = "create table "+TABLE_NAME_USUARIOS+" (id integer primary key not null , " +
            "name text not null , email text not null , uname text not null , pass text not null);";

    private static final String TABLE_CREATE_BLOQUEO = "CREATE TABLE "+ TABLE_NAME_LOCK +"(" +
            COLUMN_LOCK_ID +" INTEGER PRIMARY KEY NOT NULL, "+
            COLUMN_LOCK +" INTEGER NOT NULL"+");";


    private static final String TABLE_CREATE_CAPITULOS = "CREATE TABLE "+ TABLE_NAME_CAPITULO +"(" +
            COLUMN_CAPITULO_ID +" INTEGER PRIMARY KEY NOT NULL, "+
            COLUMN_CAPITULO +" INTEGER NOT NULL"+");";

    public DatabaseHelper(Context context){
        super(context , DATABASE_NAME , null , DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        this.dbSql = db;
        db.execSQL(TABLE_CREATE_USUARIOS);
        db.execSQL(TABLE_CREATE_BLOQUEO);
        db.execSQL(TABLE_CREATE_CAPITULOS);
        db.execSQL(TABLE_CREATE_TEME);
        db.execSQL(TABLE_CREATE_PUNTAJE);


        db.execSQL("INSERT INTO " + TABLE_NAME_POINTS + " VALUES (1,0)");
        db.execSQL("INSERT INTO " + TABLE_NAME_POINTS + " VALUES (2,0)");
        db.execSQL("INSERT INTO " + TABLE_NAME_POINTS + " VALUES (3,0)");

        db.execSQL("INSERT INTO " + TABLE_NAME_LOCK + " VALUES (1,1)");
        db.execSQL("INSERT INTO " + TABLE_NAME_TEME + " VALUES (1,1)");

    }
   public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
       super(context, name, factory, version);
   }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME_USUARIOS);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_CAPITULO);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_LOCK);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_POINTS);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_TEME);

        this.onCreate(db);

    }

    public void insertContact(Contact c){
        dbSql = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from "+TABLE_NAME_USUARIOS;
        Cursor cursor = dbSql.rawQuery(query, null);
        int count = cursor.getCount();

        values.put(COLUMN_ID, count);
        values.put(COLUMN_NAME, c.getName());
        values.put(COLUMN_EMAIL, c.getEmail());
        values.put(COLUMN_UNAME, c.getUname());
        values.put(COLUMN_PASS, c.getPass());

        dbSql.insert(TABLE_NAME_USUARIOS, null, values);
        dbSql.close();
    }

    public String searchPass(String uname){
        dbSql = this.getReadableDatabase();

        String query= "select uname, pass from "+ TABLE_NAME_USUARIOS;
        Cursor cursor = dbSql.rawQuery(query, null);
        String a, b;
        b = "not found";
        if(cursor.moveToFirst()){
            do{
                a = cursor.getString(0);

                if(a.equals(uname)){
                    b = cursor.getString(1);
                    break;
                }
            }while (cursor.moveToNext());
        }
        return b;

    }

    public  int  tema (){
        dbSql = this.getReadableDatabase();
        String selectQuery =  "SELECT "+ COLUMN_TEME +" FROM "+ TABLE_NAME_TEME ;
        Cursor cursor = dbSql.rawQuery(selectQuery, null);
        int score=0;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    score = cursor.getInt(0);
                } while (cursor.moveToNext());
            }
        }
        return score;
    }
    public void actualizarTema(int date){

        dbSql = this.getWritableDatabase();
        //Actualizamos el registro en la base de datos
        dbSql.execSQL("UPDATE "+ TABLE_NAME_TEME +" SET "+ COLUMN_TEME +
                " = "+date+" WHERE id = 1");
        dbSql.close();
    }


    public  int  bloqueo (){
        //Consulta que perite verificar cuales temas estan bloqueados

        dbSql = this.getReadableDatabase();
        String selectQuery =  "SELECT "+ COLUMN_LOCK +" FROM "+ TABLE_NAME_LOCK ;
        Cursor cursor = dbSql.rawQuery(selectQuery, null);
        int score=0;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    score = cursor.getInt(0);
                } while (cursor.moveToNext());
            }
        }
        return score;
    }

    public void actualizarBloqueo(int date) {
//Consulta que permite actualizar los temas bloqueados
        dbSql = this.getWritableDatabase();

        //Actualizamos el registro en la base de datos
        dbSql.execSQL("UPDATE " + TABLE_NAME_LOCK + " SET " + COLUMN_LOCK +
                " = " + date + " WHERE id = 1");
        dbSql.close();

    }
    //METODO PARA VALIDAR QUE EL USUARIO ESTE REGISTRADO
    public  String  traerNombre(){
        dbSql = this.getReadableDatabase();
        String name="";
        String selectQuery = "SELECT "+COLUMN_NAME+" FROM " + TABLE_NAME_USUARIOS;
        Cursor cursor = dbSql.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                name = cursor.getString(0);
                return name;
            } while (cursor.moveToNext());
        }
        return name;
    }

    //BLOQUEO DE CAPITULOS
    public  int  bloqueoCapitulos (){
        //Consulta que perite verificar cuales temas estan bloqueados

        dbSql = this.getReadableDatabase();
        String selectQuery =  "SELECT "+ COLUMN_LOCK +" FROM "+ TABLE_NAME_LOCK ;
        Cursor cursor = dbSql.rawQuery(selectQuery, null);
        int score=0;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    score = cursor.getInt(0);
                } while (cursor.moveToNext());
            }
        }
        return score;
    }

    public void actualizarCapitulo(int date) {
//Consulta que permite actualizar los temas bloqueados
        dbSql = this.getWritableDatabase();
        String selectQuery = "SELECT " + COLUMN_LOCK + " FROM " + TABLE_NAME_LOCK;
        Cursor cursor = dbSql.rawQuery(selectQuery, null);
        int score = 0;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    score = cursor.getInt(0);
                    if (score < date) {
                        //Actualizamos el registro en la base de datos
                        dbSql.execSQL("UPDATE " + TABLE_NAME_LOCK + " SET " + COLUMN_LOCK +
                                " = " + date + " WHERE id = 1");
                        dbSql.close();
                    }
                } while (cursor.moveToNext());
            }
        }
    }

    public void actualizarQuiz(double re, int numQuiz){
        dbSql = this.getWritableDatabase();
        String selectQuery =  "SELECT "+ COLUMN_POINTS +" FROM "+ TABLE_NAME_POINTS +" where id="+numQuiz;
        Cursor cursor = dbSql.rawQuery(selectQuery, null);
        int score=0;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    score = cursor.getInt(0);
                    if(score<re){
                        //Actualizamos el registro en la base de datos
                        dbSql.execSQL("UPDATE "+ TABLE_NAME_POINTS +" SET "+ COLUMN_POINTS +
                                " = "+re+" WHERE id = "+numQuiz);
                        dbSql.close();
                    }
                } while (cursor.moveToNext());
            }
        }
    }

    public  int  traerResult(int id){
        dbSql = this.getReadableDatabase();
        String selectQuery =  "SELECT "+ COLUMN_POINTS +" FROM "+ TABLE_NAME_POINTS +" where id="+id;
        Cursor cursor = dbSql.rawQuery(selectQuery, null);
        int score=0;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    score = cursor.getInt(0);
                } while (cursor.moveToNext());
            }
        }
        return score;
    }
    public  String  traerEmail(){
        dbSql = this.getReadableDatabase();
        String email="";
        String selectQuery = "SELECT  email FROM " + TABLE_NAME_USUARIOS;
        Cursor cursor = dbSql.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                email = cursor.getString(0);
                return email;
            } while (cursor.moveToNext());
        }
        return email;
    }
    public int porsentajeCurso(){
        int q1 =traerResult(1);
        int q2 =traerResult(2);
        int q3 =traerResult(3);
        int suma;
        int porsentaje;
        suma= q1+q2+q3;
        porsentaje= suma/3;
        return porsentaje;
    }
}
