package mx.edu.utng.css;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import mx.edu.utng.css.dao.DatabaseHelper;


/**
 * Created by MD on 07/02/2016.C
 */
public class InicioActivity
    extends Activity {

    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio_layout);

        //Vamos a declarar un nuevo thread
        Thread timer = new Thread() {
            //El nuevo Thread exige el metodo run
            public void run() {
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    //Si no puedo ejecutar el sleep muestro el error
                    e.printStackTrace();
                } finally {
                    String name = helper.traerNombre();
             /*       if (name == "") {
                        Intent actividaPrincipal = new Intent(InicioActivity.this, SeleccionModuloActivity.class);
                        startActivity(actividaPrincipal);
                        System.exit(0);
                    } else {*/
                        Intent actividaPrincipal = new Intent(InicioActivity.this, FormLoginActivity.class);
                        startActivity(actividaPrincipal);
                        System.exit(0);
                  //  }
                }
            }
        };
        timer.start();
    }
}
