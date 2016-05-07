package mx.edu.utng.css;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UtileriasActivity extends AppCompatActivity implements View.OnClickListener{

    private Button a;
    private Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utilerias);
        a=(Button)findViewById(R.id.calculadora);
        a.setOnClickListener(this);
        b=(Button)findViewById(R.id.cronometro);
        b.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.calculadora:
                Intent i = new Intent(this, CalculadoraActivity.class);
                startActivity(i);
                break;
            case R.id.cronometro:
                i = new Intent(this, ChronometerActivity.class);
                startActivity(i);
                break;
        }
    }
}
