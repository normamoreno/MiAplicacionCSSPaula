package mx.edu.utng.css;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import mx.edu.utng.css.dao.DatabaseHelper;


/**
 * Created by luisito on 17/04/2016.
 */
public class MailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mail_activity);
    }

    public void onClick(View v) {

        DatabaseHelper login= new DatabaseHelper(this);
        String email=login.traerEmail();

        // Reemplazamos el email por algun otro real
        String[] to = { email, email };
        String[] cc = { email };

        DatabaseHelper helper= new DatabaseHelper(this);

        int po=helper.porsentajeCurso();
        int res= helper.traerResult(1);
        int resII= helper.traerResult(2);
        int resIII= helper.traerResult(3);


        enviar(to, cc, "Resultados de JPA (Java Persisten API)",
                "Esto es un email enviado desde una app de JPA.\n\n" +
                        "Calif Quiz I: " + res +
                        "\nCalif Quiz II: " + resII +
                        "\nCalif Quiz III: " + resIII +
                        "\nTu promedio final es de " + po + "."
        );
    }

    private void enviar(String[] to, String[] cc,
                        String asunto, String mensaje) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        // String[] to = direccionesEmail;
        //String[] cc = copias;
        emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
        emailIntent.putExtra(Intent.EXTRA_CC, cc);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, asunto);
        emailIntent.putExtra(Intent.EXTRA_TEXT, mensaje);
        emailIntent.setType("message/rfc822");
        startActivity(Intent.createChooser(emailIntent, "Email "));
        System.exit(0);
    }
}