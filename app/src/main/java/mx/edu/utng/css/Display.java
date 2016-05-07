package mx.edu.utng.css;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Display extends AppCompatActivity implements View.OnClickListener{

    Button btnSiguiente;
    private Bundle valoresEviadosDBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_layout);
        String username= getIntent().getStringExtra("Username");
        valoresEviadosDBundle=new Bundle();

        TextView tv = (TextView)findViewById(R.id.TVusername);
        tv.setText(username);
        btnSiguiente =(Button)findViewById(R.id.btnSiguiente);
        btnSiguiente.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSiguiente:
                valoresEviadosDBundle.putBoolean("logeo",true);
                startActivity(new Intent(this, SeleccionModuloActivity.class).putExtras(valoresEviadosDBundle));
        }

    }
}
