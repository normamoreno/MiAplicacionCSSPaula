package mx.edu.utng.css.examen.css3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import mx.edu.utng.css.examen.Question;


public class CSS3Helper extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION = 1;
	// Database Name
	private static final String DATABASE_NAME = "css3";
	// tasks table name
	private static final String TABLE_QUEST = "quest";
	// tasks Table Columns names
	private static final String KEY_ID = "qid";
	private static final String KEY_QUES = "question";
	private static final String KEY_ANSWER = "answer"; // respuesta correcta
	private static final String KEY_OPTA = "opta"; // opcion a
	private static final String KEY_OPTB = "optb"; // opcion b
	private static final String KEY_OPTC = "optc"; // opcion c

	private SQLiteDatabase dbase;

	public CSS3Helper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		dbase = db;
		String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUEST + " ( "
				+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
				+ " TEXT, " + KEY_ANSWER + " TEXT, " + KEY_OPTA + " TEXT, "
				+ KEY_OPTB + " TEXT, " + KEY_OPTC + " TEXT)";
		db.execSQL(sql);
		addQuestion();
		// db.close();
	}

	//preguntas de los examenes
	private void addQuestion() {
		Question q1=new Question("Permite definir la altura máxima de un elemento", "max-height", " max-width"," min-width","max-height");
		this.addQuestion(q1);
		Question q2=new Question("Permite definir la altura mínima de un elemento", " min-width", " min-height ","max-width"," min-height ");
		this.addQuestion(q2);
		Question q3=new Question("Para conseguir un diseño de anchura variable pero controlada completa el codigo:" +
				" #contenedor _____" +
				"min-width: 500px; " +
				"max-width: 900px; } ",
				"(", " *","{","{");
		this.addQuestion(q3);
		//*************************** CENTRAR UNA PAGINA HORIZONTAL****************************************
		Question q4=new Question("Los navegadores alinean por defecto las páginas web a", " la izquierda de la ventana", " la derecha de la ventana"," al centro de la ventana"," la izquierda de la ventana");
		this.addQuestion(q4);
		Question q5=new Question("Utilizar la propiedad margin, es", " muy dificil centrar una página web horizontalmente", " no se puede centrar una página web horizontalmente"," muy sencillo centrar una página web horizontalmente"," muy sencillo centrar una página web horizontalmente");
		this.addQuestion(q5);
		Question q6=new Question("A que se le suele llamar contenedor ", " <div>", " <body>"," <h> "," <div>");
		this.addQuestion(q6);
		Question q7=new Question("A que se le suele llamar contenedor ", " <div>", " <body>"," <h1> "," <div>");
		this.addQuestion(q7);
//=============================== FUNCIONES BASICAS DE CSS  ==================================================
		//*************************** CAMBIAR COLOR DE LETRA Y BORDE****************************************
		Question q8=new Question("En CSS3, ¿qué propiedad se emplea para definir un borde con imagen?", "border-attachment", "border-background","border-image","border-image");
		this.addQuestion(q8);
		Question q9=new Question("¿Qué propiedad no existe en CSS?", "border-line", "border-style","border-color", "border-line");
		this.addQuestion(q9);
		Question q10=new Question("¿Cómo se cambia el color del texto de un elemento en CSS?","color", "textcolor","text-color", "text-color");
		this.addQuestion(q10);
		Question q11=new Question("En CSS, para definir que el color del texto es rojo se emplea", "color: #F00;", "color: #red","color: rgb(red);","color: #F00;");
		this.addQuestion(q11);
		//*************************** TIPO DE LETRA Y TAMAÑO****************************************
		Question q12=new Question("¿Qué propiedad de CSS se emplea para indicar que un texto se debe mostrar en cursiva (itálica)?", "font-italic", "font-style","font-variant","font-style");
		this.addQuestion(q12);
		Question q13=new Question("¿Qué propiedad no existe en CSS?", "font-face", "font-size","font-variant","font-face");
		this.addQuestion(q13);
		Question q14=new Question("Indica cuál es el código CSS que define un texto en rojo, con el tipo de letra Arial y el tamaño 14 pixels", "font-family: Arial; font-size: 14px; color: #FF0000;", "font-family: Arial; size: 14px; color: #FF0000;","text-family: Arial; font-size: 14px; color: #FF0000;","font-family: Arial; font-size: 14px; color: #FF0000;");
		this.addQuestion(q14);
		Question q15=new Question("¿Qué propiedad de CSS se emplea para cambiar el tipo de letra de un elemento?", "text-family", "font-type","font-family","font-family");
		this.addQuestion(q15);
		//*************************** TIPO DE LETRA Y TAMAÑO****************************************
		Question q16=new Question("¿Cómo se puede cambiar el margen izquierdo de un elemento con CSS?", "marginleft", "margin-left","text-indent","margin-left");
		this.addQuestion(q16);
		Question q17=new Question("En CSS3, ¿qué propiedad se emplea para definir un borde con imagen?", "Las anteriores respuestas no son correctas", "border-background","border-image","border-image");
		this.addQuestion(q17);
		Question q18=new Question("En CSS, ¿qué se suele emplear para centrar un contenido horizontalmente?", "margin: auto 0;Falso", "margin: 0 auto;","margin: center;","margin: 0 auto;");
		this.addQuestion(q18);
		Question q19=new Question("En CSS, ¿cómo se interpreta la siguiente regla? {margin: 10px 5px 15px;}", " margen superior 10px, margen inferior 5px, margen izquierdo y derecho 15px ", "margen superior 10px, margen derecho 5px, margen inferior 15pxal"," margen superior 10 px, margen izquierdo y derecho 5px, margen inferior 15px","margen superior 10px, margen inferior 5px, margen izquierdo y derecho 15px");
		this.addQuestion(q19);
		//*************************** ESPACIADO****************************************
		Question q20=new Question("¿Qué propiedad de CSS se emplea para definir el espacio entre caracteres en el texto?", "letter-spacing", "white-space","word-spacing","letter-spacing");
		this.addQuestion(q20);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST);
		// Create tables again
		onCreate(db);
	}

	// Adding new question
	public void addQuestion(Question quest) {
		// SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_QUES, quest.getQUESTION());
		values.put(KEY_ANSWER, quest.getANSWER());
		values.put(KEY_OPTA, quest.getOPTA());
		values.put(KEY_OPTB, quest.getOPTB());
		values.put(KEY_OPTC, quest.getOPTC());

		// Inserting Row
		dbase.insert(TABLE_QUEST, null, values);
	}

	public List<Question> getAllQuestions() {
		List<Question> quesList = new ArrayList<Question>();
		// Select All Query
		String selectQuery = "SELECT * FROM " + TABLE_QUEST;
		dbase = this.getReadableDatabase();
		Cursor cursor = dbase.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Question quest = new Question();
				quest.setID(cursor.getInt(0));
				quest.setQUESTION(cursor.getString(1));
				quest.setANSWER(cursor.getString(2));
				quest.setOPTA(cursor.getString(3));
				quest.setOPTB(cursor.getString(4));
				quest.setOPTC(cursor.getString(5));

				quesList.add(quest);
			} while (cursor.moveToNext());
		}
		// return quest list
		return quesList;
	}


}
