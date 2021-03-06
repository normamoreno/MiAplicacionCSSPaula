package mx.edu.utng.css.examen.css3;

/**
 * Created by H on 7/12/2015.
 */


import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import mx.edu.utng.css.R;
import mx.edu.utng.css.SeleccionModuloActivity;
import mx.edu.utng.css.dao.DatabaseHelper;
import mx.edu.utng.css.examen.Question;


public class CSS3Question extends Activity {
    private Bundle bundleResividos;

    DatabaseHelper helper = new DatabaseHelper(this);
    List<Question> quesList;
    double score = 0;
    int qid = 0;


    Question currentQ;
    TextView txtQuestion, times, scored;
    Button button1, button2, button3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.answers);
        bundleResividos=getIntent().getExtras();

        CSS3Helper db = new CSS3Helper(this);  // Mi clase de banco de preguntas
        quesList = db.getAllQuestions(); // Esto obtendrá todas las preguntas
        currentQ = quesList.get(qid); // La pregunta actual

        txtQuestion = (TextView) findViewById(R.id.txtQuestion);
        // El TextView en la que se mostrará la pregunta

        // Los tres botones,
        // La idea es establecer el texto de tres botones con las opciones de banco de preguntas
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);

        // Se mostrará la Vista de Texto en el que la puntuación
        scored = (TextView) findViewById(R.id.score);

        // El temporizador
        times = (TextView) findViewById(R.id.timers);


        // Método que fijará las cosas a nuestro juego
        setQuestionView();
        times.setText("00:02:00");

        // Un temporizador de 60 segundos para jugar, con un intervalo de 1 segundo
        CounterClass timer = new CounterClass(120000, 1000);
        timer.start();

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pasa el texto del botón a otro método
                // Comprobar si el anser es correcta o no
                // Misma para los tres botones
                getAnswer(button1.getText().toString());
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAnswer(button2.getText().toString());
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAnswer(button3.getText().toString());
            }
        });
    }

    public void getAnswer(String AnswerString) {
        if (currentQ.getANSWER().equals(AnswerString)) {
            // Si las condiciones partidos aumentan la int (puntuación) por 1
            // Y establecer el texto de la visualización de partitura
            score = score+0.5;
            helper.actualizarQuiz(score,3);
            //helper.actualizarBloqueo(4);
        } else {
            helper.actualizarQuiz(score, 3);
        }
        if (qid < 20) {
            // Si las preguntas no han terminado luego hacer esto
            currentQ = quesList.get(qid);
            scored.setText("Score : "+score);
            setQuestionView();
        } else {
            // Si es más de hacer esto
            helper.actualizarBloqueo(4);
            Intent intent = new Intent(CSS3Question.this,
                    SeleccionModuloActivity.class).putExtra("logeo",bundleResividos.getBoolean("logeo"));
            helper.actualizarQuiz(score,3);
            helper.actualizarBloqueo(4);
            startActivity(intent);
            System.exit(0);
        }
    }


    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @SuppressLint("NewApi")
    public class CounterClass extends CountDownTimer {

        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            // TODO Auto-generated constructor stub
        }


        @Override
        public void onFinish() {
            times.setText("Tu tiempo termino!!!!");
            Intent intent = new Intent(CSS3Question.this,
                    SeleccionModuloActivity.class).putExtra("logeo",bundleResividos.getBoolean("logeo"));
            helper.actualizarQuiz(score,3);
            helper.actualizarBloqueo(4);
            startActivity(intent);
            finish();
        }

        @Override
        public void onTick(long millisUntilFinished) {
            // TODO Auto-generated method stub

            long millis = millisUntilFinished;
            String hms = String.format(
                    "%02d:%02d:%02d",
                    TimeUnit.MILLISECONDS.toHours(millis),
                    TimeUnit.MILLISECONDS.toMinutes(millis)
                            - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS
                            .toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis)
                            - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS
                            .toMinutes(millis)));
            System.out.println(hms);
            times.setText(hms);
        }


    }

    private void setQuestionView() {
        // El método que pondrá todas las cosas juntas
        txtQuestion.setText(currentQ.getQUESTION());
        button1.setText(currentQ.getOPTA());
        button2.setText(currentQ.getOPTB());
        button3.setText(currentQ.getOPTC());
        qid++;
    }
}
