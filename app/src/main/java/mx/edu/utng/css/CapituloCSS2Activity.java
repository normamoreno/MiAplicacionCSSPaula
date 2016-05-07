package mx.edu.utng.css;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import mx.edu.utng.css.dao.DatabaseHelper;
import mx.edu.utng.css.estrategia.Manager;
import mx.edu.utng.css.examen.css3.CSS3Question;

/**
 * Created by MD on 06/02/2016.
 */
public class CapituloCSS2Activity extends AppCompatActivity implements View.OnClickListener{
    private Bundle bundleResividos;

    private TextView texto;
    private Button examen;

    private ImageView imagen;

    DatabaseHelper helper = new DatabaseHelper(this);
    private Intent intent;
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capitulo);
        bundleResividos=getIntent().getExtras();

        texto=(TextView)findViewById(R.id.texto);
        examen = (Button) findViewById(R.id.txv_selec4);
        examen.setOnClickListener(this);

        imagen = (ImageView) findViewById(R.id.img_mod);
        texto.setText(R.string.css3 );//toda la informacion del modulo
        imagen.setBackgroundResource(R.drawable.imag9);
        imagen=(ImageView)findViewById(R.id.img_mod);
        String username= getIntent().getStringExtra("mod");
        TextView tv = (TextView)findViewById(R.id.txv_selec_title);
        tv.setText(username);

        RelativeLayout fondo = (RelativeLayout)findViewById(R.id.relativeLayout);
        DatabaseHelper helper = new DatabaseHelper(this);
        int tema = helper.tema();
        if(tema==1){
            fondo.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }else{
            if(tema==2){
                fondo.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
            }else{
                if(tema==3){
                    fondo.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                }
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_layout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Bundle bundle=new Bundle();
        String mensaje="";
        switch (item.getItemId()){
            case R.id.itm_menu_bar_configuracion:
                Intent i = new Intent(this,ConfiguracionActivity.class);
                startActivity(i);
                mensaje="Configuracion";
                break;
            case R.id.itm_menu_bar_desarrollador:
                bundle.putInt("posicion",R.id.itm_menu_bar_desarrollador);
                intent=new Intent(CapituloCSS2Activity.this,DesarrolladorActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.itm_menu_bar_video:
                i = new Intent(this, SelectVideoActivity.class);
                startActivity(i);
                break;
            case R.id.itm_menu_bar_progreso_salir:
                finish();
                Intent intent1=new Intent(Intent.ACTION_MAIN);
                intent1.addCategory(Intent.CATEGORY_HOME);
                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent1);
                break;
            case R.id.itm_menu_bar_musica:
                mp =new MediaPlayer().create(this,R.raw.heart);
                if (mp.isPlaying()) {
                    mp.pause();
                }else {
                    mp.start();
                }
                break;
            case R.id.itm_menu_bar_Tema:
                startActivity(new Intent(CapituloCSS2Activity.this, TemaActivity.class));
                break;
            case R.id.itm_menu_bar_correo:
                startActivity(new Intent(CapituloCSS2Activity.this, MailActivity.class));
                break;
            case R.id.itm_menu_bar_grafica:
                startActivity(new Intent(CapituloCSS2Activity.this, GraficaMenuActiviry.class));
                break;
            case R.id.itm_menu_bar_juegos:
                startActivity(new Intent(CapituloCSS2Activity.this, Manager.class));
                break;
            case R.id.itm_menu_bar_galery:
                startActivity(new Intent(CapituloCSS2Activity.this, GaleriasActivity.class));
                break;
            case R.id.itm_menu_bar_tools:
                startActivity(new Intent(CapituloCSS2Activity.this, UtileriasActivity.class));
                break;
            case R.id.itm_menu_bar_streaming:
                startActivity(new Intent(CapituloCSS2Activity.this, StreamingMp3Player.class));
                break;
            case R.id.itm_menu_bar_maps:
                startActivity(new Intent(CapituloCSS2Activity.this, MapsActivity.class));
                break;
            default:
                break;
        }
        Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        int bloqueo=helper.bloqueoCapitulos();
        switch (v.getId()) {
            case R.id.txv_selec4:
                    new AlertDialog.Builder(this)
                            .setTitle("Realizar test")
                            .setMessage("Estas seguro de realizar el test")
                            .setNegativeButton("Cancelar", null)
                            .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent i = new Intent(CapituloCSS2Activity.this, CSS3Question.class).putExtra("logeo",bundleResividos.getBoolean("logeo"));
                                    startActivity(i);
                                }
                            }).show();

                break;
        }
    }
}
