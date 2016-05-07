package mx.edu.utng.css;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import mx.edu.utng.css.dao.DatabaseHelper;

public class TemaActivity extends AppCompatActivity implements View.OnClickListener{

    DatabaseHelper helper = new DatabaseHelper(this);
    Button tema1;
    Button tema2;
    Button tema3;
    Button tema4;

    private Bundle valoresEviadosDBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tema);

        valoresEviadosDBundle=new Bundle();

        tema1=(Button)findViewById(R.id.btn_tema1);
        tema1.setOnClickListener(this);

        tema2=(Button)findViewById(R.id.btn_tema2);
        tema2.setOnClickListener(this);

        tema3=(Button)findViewById(R.id.btn_tema3);
        tema3.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_tema1:
                helper.actualizarTema(1);
                startActivity(new Intent(this, SeleccionModuloActivity.class).putExtras(valoresEviadosDBundle));
                System.exit(0);
                break;
            case R.id.btn_tema2:
                helper.actualizarTema(2);
                startActivity(new Intent(this, SeleccionModuloActivity.class).putExtras(valoresEviadosDBundle));
                System.exit(0);
                break;
            case R.id.btn_tema3:
                helper.actualizarTema(3);
                startActivity(new Intent(this, SeleccionModuloActivity.class).putExtras(valoresEviadosDBundle));
                System.exit(0);
                break;

        }
    }
}
