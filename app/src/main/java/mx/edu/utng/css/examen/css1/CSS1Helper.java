package mx.edu.utng.css.examen.css1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import mx.edu.utng.css.examen.Question;


public class CSS1Helper extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION = 1;
	// Database Name
	private static final String DATABASE_NAME = "css1";
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

	public CSS1Helper(Context context) {
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
		Question q1=new Question("¿Qué entiendes por CSS? "," Class Style Sheets ", "Cascading Style Sheets", "Cascading Style System", "Cascading Style Sheets");
		this.addQuestion(q1);
		Question q2=new Question("¿Para qué es indispensable CSS?", "Crear páginas WEB", "Crear Aplicaciones", "Desarrollar juegos", "Crear páginas WEB");
		this.addQuestion(q2);
		Question q3=new Question("¿Qué tipos de documentos se relacionan mas con CSS?", "PHP y HTML", "ASP y PHP", "HTML y XHTML","HTML y XHTML");
		this.addQuestion(q3);
		Question q4=new Question("¿Por quién es más utilizado CSS?", "Diseñadores y Programadores", "Diseñadores y Documentadores", "Programadores y documentadores", "Diseñadores y Programadores");
		this.addQuestion(q4);
		Question q5=new Question("¿En qué año aproximadamente aparecieron las hojas de estilo?", "1970", "1980", "1975","1970");
		this.addQuestion(q5);
		Question q6=new Question("¿Después de que lenguaje aparecieron las CSS?", "SGML", "W3C", "CHSS","SGML");
		this.addQuestion(q6);
		Question q7=new Question("¿Con que se produjo el gran impulso de los lenguajes de hojas de estilos?", "PHP", " Boom de Internet y HTML", "Internet y ASP", " Boom de Internet y HTML");
		this.addQuestion(q7);
		Question q8=new Question("¿La guerra de navegadores y la falta de un estándar dificultaban la creación de Documentos?", "Si", "No", "Ninguna de las anteriores", "Si");
		this.addQuestion(q8);
		Question q9=new Question("¿Una etiqueta en concreto?", "Define varios estilos diferentes para una sola etiqueta", "Define solo un estilo", "Define solo 5 estilos", "Define varios estilos diferentes para una sola etiqueta");
		this.addQuestion(q9);
		Question q10=new Question("¿Es cierto que CSS tiene ventajas como?",  "Soporta todos los navegadores", "px,%,in,pt,cm", "Utiliza menos espacio", " px,%,in,pt,cm");
		this.addQuestion(q10);
		Question q11=new Question("¿Existen diferentes versiones de CSS?",  "Si hay más de 2", "No", "Solo hay 2", "Si hay más de 2");
		this.addQuestion(q11);
		Question q12=new Question("¿Cuáles son los navegadores que soporta CSS?", "Google Chrome, Internet Explorer, Firefox", "Soporta todos los que existen", "ASK, UC Browser, Safari, ", " Google Chrome, Internet Explorer, Firefox ");
		this.addQuestion(q12);
		Question q13=new Question("¿Es cierto que todos los navegadores implementan las mismas funciones de las hojas?",  "No", "Si", "Ninguna de las anteriores", "No");
		this.addQuestion(q13);
		Question q14=new Question("¿El trabajo del diseñador web siempre está limitado por?",  "Las posibilidades de los navegadores que utilizan los usuarios para acceder a sus páginas", "Falta de conocimientos", "Falta de proyectos", "Las posibilidades de los navegadores que utilizan los usuarios para acceder a sus páginas ");
		this.addQuestion(q14);
		Question q15=new Question("¿Desde el punto de vista del diseñador CSS?",  " La versión de un motor es mucho más importante que la versión del propio navegador", "Es mas importante la versión del navegador", "Es más fácil usar el código CSS", " La versión de un motor es mucho más importante que la versión del propio navegador");
		this.addQuestion(q15);
		Question q16=new Question("¿Qué falta para eliminar el margen y el relleno de todos los elementos HTML \n" +
				"_____{\n" +
				"  margin: 0;\n" +
				"  padding: 0;\n" +
				"}\n" +
				"?\"","red", "*", "p", "*");
		this.addQuestion(q16);
		Question q17=new Question("Cual es el código faltante para la etiqueta\"*{color:_________}?\"", "red", "0", "italic", "red");
		this.addQuestion(q17);

		Question q18=new Question("¿Con que signo se marca el sector universal?",  "*", "-", "+", "*");
		this.addQuestion(q18);
		Question q19=new Question("\"¿Cual es el código faltante para añadir el borde?\n" +
				"*________\n" +
				"  border: 1px solid #000000;\n" +
				"}\n" +
				"q1=new Question();\n" +
				"this.addQuestion(q1);\n" +
				"", "/", "{", "+", "{");
		this.addQuestion(q19);

		Question q20=new Question("Los selectores por tipo afectan a", "Alguna apariciones de un documento HTML", "Medio documento HTML", "Todas las apariciones de un elemento en el documento XHTML","Todas las apariciones de un elemento en el documento XHTML");
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
