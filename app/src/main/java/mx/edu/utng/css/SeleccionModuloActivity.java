package mx.edu.utng.css;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import mx.edu.utng.css.dao.DatabaseHelper;
import mx.edu.utng.css.estrategia.Manager;

/**
 * Created by MD on 06/02/2016.
 */
public class SeleccionModuloActivity extends AppCompatActivity implements View.OnClickListener{
    private Bundle bundleResividos,bundle;
    private Button modulo1;
    private Button modulo2;
    private Button modulo3;

    private ProgressDialog _progressDialog;
    private int _progress = 0;
    private Handler _progressHandler;


    DatabaseHelper helper = new DatabaseHelper(this);
    private String[] itemMenuSecundario;
    private ArrayAdapter<String> arrayAdapter;
    private Bundle bundleSep;
    private  Bundle valoresRecibidos;
    private Intent intent;
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_modulo);
        bundle=new Bundle();
        bundleResividos=getIntent().getExtras();

        modulo1 =(Button)findViewById(R.id.txv_selec1);
        modulo1.setOnClickListener(this);
        modulo2=(Button)findViewById(R.id.txv_selec2);
        modulo2.setOnClickListener(this);
        modulo3=(Button)findViewById(R.id.txv_selec3);
        modulo3.setOnClickListener(this);

        RelativeLayout fondo = (RelativeLayout)findViewById(R.id.relativeLayout);
        int tema = helper.tema();
        if(tema==1){
            fondo.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }else{
            if(tema==2){
                fondo.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
            }else{
                if(tema==3){
                    fondo.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                }else{
                    if(tema==4){
                        fondo.setBackgroundColor(getResources().getColor(R.color.colorGrey));
                    }
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        if (bundleResividos.getBoolean("logeo")){
            inflater.inflate(R.menu.menu_layout, menu);
        }else {
            inflater.inflate(R.menu.menu_no_privilegios_layout, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Bundle bundle=new Bundle();
        String mensaje="";
        if (bundleResividos.getBoolean("logeo")){
        ////////////////////////////////////////////////////////////////////////////////////////////
            switch (item.getItemId()){
                case R.id.itm_menu_bar_configuracion:
                    Intent i = new Intent(this,ConfiguracionActivity.class);
                    startActivity(i);
                    mensaje="Configuración";
                    break;
                case R.id.itm_menu_bar_camara:
                    i = new Intent(this,CamaraActivity.class);
                    startActivity(i);
                    mensaje="Cámara";
                    break;
                case R.id.itm_menu_bar_video_pro:
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=59Q-tLqcxV8&feature=youtu.be")));
                    break;
                case R.id.itm_menu_bar_progreso:
                    mensaje="Progreso";
                    ///////////////////
                    int res= helper.traerResult(1);
                    int resII= helper.traerResult(2);
                    int resIII= helper.traerResult(3);
                    int ava=0;
                    if (res>0){
                        ava=30;
                    }
                    if (resII>0){
                        ava=60;
                    }
                    if (resIII>0){
                        ava=100;
                    }
                    showDialog(1);
                    _progress = 0;
                    _progressDialog.setProgress(ava);
                    //_progressHandler.sendEmptyMessage(0);

                    _progressHandler = new Handler() {
                        public void handleMessage(Message msg)
                        {
                            super.handleMessage(msg);
                            if (_progress >= 100) {
                                _progressDialog.dismiss();
                            } else {
                                _progress++;
                                //_progressDialog.incrementProgressBy(1);
                                _progressHandler.sendEmptyMessageDelayed(50, 100);
                            }
                        }
                    };
                    ///////////////////
                    break;
                case R.id.itm_menu_bar_buscaminas:
                    startActivity(new Intent(this,BuscaminasActivity.class));
                    mensaje="Buscaminas";
                    break;
                case R.id.itm_menu_bar_desarrollador:
                    bundle.putInt("posicion",R.id.itm_menu_bar_desarrollador);
                    intent=new Intent(SeleccionModuloActivity.this,DesarrolladorActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    mensaje="Desarrollador";
                    break;
                case R.id.itm_menu_bar_video:
                    i = new Intent(this, SelectVideoActivity.class);
                    startActivity(i);
                    mensaje="Videos";
                    break;
                case R.id.itm_menu_bar_progreso_salir:
                    finish();
                    Intent intent1=new Intent(Intent.ACTION_MAIN);
                    intent1.addCategory(Intent.CATEGORY_HOME);
                    intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent1);
                    mensaje="Adios";
                    break;
                case R.id.itm_menu_bar_musica:
                    mensaje="Musica";
                    startActivity(new Intent(this,AudioActivity.class));
                    break;
                case R.id.itm_menu_bar_Tema:
                    mensaje="Temas";
                    startActivity(new Intent(SeleccionModuloActivity.this, TemaActivity.class));
                    break;
                case R.id.itm_menu_bar_correo:
                    mensaje="Correo";
                    startActivity(new Intent(SeleccionModuloActivity.this, MailActivity.class));
                    break;
                case R.id.itm_menu_bar_grafica:
                    mensaje="Grafica";
                    startActivity(new Intent(SeleccionModuloActivity.this, GraficaMenuActiviry.class));
                    break;
                case R.id.itm_menu_bar_juegos:
                    mensaje="Juegos";
                    startActivity(new Intent(SeleccionModuloActivity.this, Manager.class));
                    break;
                case R.id.itm_menu_bar_galery:
                    mensaje="Galerias";
                    startActivity(new Intent(SeleccionModuloActivity.this, GaleriasActivity.class));
                    break;
                case R.id.itm_menu_bar_tools:
                    mensaje="Tools";
                    startActivity(new Intent(SeleccionModuloActivity.this, UtileriasActivity.class));
                    break;
                case R.id.itm_menu_bar_streaming:
                    mensaje="Streaming";
                    startActivity(new Intent(SeleccionModuloActivity.this, StreamingMp3Player.class));
                    break;
                case R.id.itm_menu_bar_maps:
                    mensaje="Mapa";
                    startActivity(new Intent(SeleccionModuloActivity.this, MapsActivity.class));
                    break;
                default:
                    break;
            }
            ////////////////////////////////////////////////////////////////////////////////////////////
        }else {
            switch (item.getItemId()){
                case R.id.itm_menu_dos_bar_progreso_sugerencias:
                    mensaje="Sugerencias";
                    Toast.makeText(SeleccionModuloActivity.this, "Su", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.itm_menu_dos_bar_progreso_salir:
                    mensaje="Adios";
                    Toast.makeText(SeleccionModuloActivity.this, "Salir", Toast.LENGTH_SHORT).show();

                    break;
                default:
                    break;
            }
        }

        Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        bundle.putBoolean("logeo",bundleResividos.getBoolean("logeo"));

        int bloqueo=helper.bloqueo();
        String nombre=helper.traerNombre();
        switch (v.getId()) {
            case R.id.txv_selec1:
                bundle.putString("mod", modulo1.getText().toString());
                Intent i = new Intent(this, CapituloCSSActivity.class).putExtras(bundle);
                startActivity(i);
                break;
            case R.id.txv_selec2:
                if (bloqueo >= 2 && nombre != "") {
                    bundle.putString("mod", modulo2.getText().toString());
                    i = new Intent(this, CapituloCSS1Activity.class).putExtras(bundle);
                    startActivity(i);
                } else {
                    if(bloqueo >= 2 && nombre=="") {
                        new AlertDialog.Builder(this)
                                .setTitle("No estas registrado")
                                .setMessage("Necesitas registrate para continuar")
                                .setNegativeButton("Cancelar", null)
                                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent i = new Intent(SeleccionModuloActivity.this, FormCreateLoginActivity.class);
                                        startActivity(i);
                                    }
                                }).show();
                    }else{
                        Toast temp = Toast.makeText(this, "Tema Bloqueado", Toast.LENGTH_LONG);
                        temp.show();
                    }
                }
                break;
            case R.id.txv_selec3:
                if (bloqueo >= 3 && nombre != "") {//-----------------------------------------------Probable
                    bundle.putString("mod", modulo2.getText().toString());
                    i = new Intent(this, CapituloCSS2Activity.class).putExtras(bundle);
                    startActivity(i);
                } else {
                    if(bloqueo >= 3 && nombre=="") {
                        new AlertDialog.Builder(this)
                                .setTitle("No estas registrado")
                                .setMessage("Necesitas registrate para continuar")
                                .setNegativeButton("Cancelar", null)
                                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent i = new Intent(SeleccionModuloActivity.this, FormCreateLoginActivity.class);
                                        startActivity(i);
                                    }
                                }).show();
                    }else{
                        Toast temp = Toast.makeText(this, "Tema Bloqueado", Toast.LENGTH_LONG);
                        temp.show();
                    }
                }
                break;
        }

    }

    @Override
    protected Dialog onCreateDialog(int i)
    {
        _progressDialog = new ProgressDialog(this);
        _progressDialog.setTitle("Progreso");
        _progressDialog.setIcon(R.mipmap.ic_launcher);
        _progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        _progressDialog.setButton(DialogInterface.BUTTON_POSITIVE,"Aceptar", new
                DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        Toast.makeText(getBaseContext(),
                                "Aceptar", Toast.LENGTH_SHORT).show();
                    }
                });
        return _progressDialog;
    }

}