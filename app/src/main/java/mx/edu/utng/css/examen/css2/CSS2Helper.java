package mx.edu.utng.css.examen.css2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import mx.edu.utng.css.examen.Question;


public class CSS2Helper extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION = 1;
	// Database Name
	private static final String DATABASE_NAME = "css2";
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

	public CSS2Helper(Context context) {
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

	private void addQuestion() {
		Question q1=new Question("Completa la etiqueta para cambiar el fondo a blanco de todos los elementos <p> de del documento" +
				"p { background-color: _______; }", "*", "white", "p", "white");
		this.addQuestion(q1);
		Question q2=new Question("\"Completa la etiqueta " +
				"p_______aviso { \n" +
				"  color: red;\n" +
				"}\n" +
				"\"\n", ".", "=", "*", ".");
		this.addQuestion(q2);
		Question q3=new Question("Los selectores pueden ser agrupados separándolos con", ":", ";", ",", ",");
		this.addQuestion(q3);

		//*************************** SELECTOR POR CLASE *****************************************
		Question q4=new Question("¿Las hojas de estilo nos permiten colocar un borde a los elementos de las páginas web Los selectores de clase son imprescindibles para diseñar páginas web?", "Si", "Tal vez", "NO","Si");
		this.addQuestion(q4);
		Question q5=new Question("¿El selector de clases consta de un (___) seguido por el nombre de la clase que hayamos creado?", ".", "*", "+",".");
		this.addQuestion(q5);
		Question q6=new Question("Completa la siguiente etiqueta en el espacio faltante: P_____pregunta {font-weight: bold; font-style: italic}", "(", ".", "{",".");
		this.addQuestion(q6);
		//*************************** SELECTOR POR ID ****************************************

		Question q7=new Question("Los selectores por ID se le pueden aplicar a ______ elemento(s) de la página", "dos", "un", "cinco","un");
		this.addQuestion(q7);
		Question q8=new Question("¿Los selectores de ID no tienen el grado de flexibilidad de los selectores de clases?", "Cierto", "Falso", "Aveces","Cierto");
		this.addQuestion(q8);
		Question q9=new Question("¿Que símbolo se utiliza para los selectores ID?", ".", "#", "*","#"	);
		this.addQuestion(q9);
		Question q10=new Question("Completa la estiqueta en el espacio faltante: ____ destacado { color: red; }","#", ".", "*","#");
		this.addQuestion(q10);

		//=================== TEMAS LAYOUT  =====================================
		//*************************** LAYOUT****************************************
		Question q11=new Question("¿El diseño de las páginas web habituales se divide en bloques cuales son?", "Logotipo e imagen de la cabecera, Contenido, Pie de página", "Cabecera, Menú principal, contenido", "Cabecera, Menú, Contenidos y Pie de página","Cabecera, Menú, Contenidos y Pie de página");
		this.addQuestion(q11);
		Question q12=new Question("¿Qué tipos de documentos se relacionan mas con CSS?", "ASP y PHP", "HTML y XHTML","HTML y XHTML","HTML y XHTML");
		this.addQuestion(q12);
		Question q13=new Question("¿El desarrollo de CSS ha permitido que se puedan realizar los mismos diseños sin utilizar tablas HTML?", "Cierto", "Falso","Tal vez","Cierto");
		this.addQuestion(q13);
		Question q14=new Question("¿Diseñar una página web exclusivamente con CSS no garantiza que la página sea accesible?", "Falso", "Tal vez","Cierto","Cierto");
		this.addQuestion(q14);

		//*************************** CENTRAR UNA PAGINA VERTICALMENTE****************************************
		Question q15=new Question("Cuando se centra un página verticalmente sus ", "Falso", "Tal vez","Cierto","Cierto");
		this.addQuestion(q15);
		Question q16=new Question("¿Cuál es el objetivo de centrar una página verticalmente?", "Su contenido aparezca en el centro del navegador", " Su contenido aparezca en el lado izquierdo del navegador"," Su contenido aparezca en el lado derecho del navegador","Su contenido aparezca en el centro del navegador");
		this.addQuestion(q16);
		Question q17=new Question("Es cierto que los márgenes verticales se adaptan de forma dinámica en función del tamaño de la ventana del navegador", "Falso", "Tal vez","Cierto","Cierto");
		this.addQuestion(q17);
		Question q18=new Question ("Es más difícil centrar una página verticalmente que horizontalmente", "Tal vez", " Cierto","Falso","Cierto");
		this.addQuestion(q18);
		//*************************** ALTURAS ANCHURAS MAXIMAS Y MINIMAS****************************************
		Question q19=new Question("Permite definir la anchura máxima de un elemento", "min-width", " max-height","max-width","max-width");
		this.addQuestion(q19);
		Question q20=new Question("\"Completa la siguiente etiqueta:" +
				"<P _________ =“contraste”>Un párrafo con contraste</P>?\"", "aling", "border", "Class"," Class ");
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
