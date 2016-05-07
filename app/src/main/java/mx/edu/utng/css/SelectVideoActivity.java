package mx.edu.utng.css;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SelectVideoActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnPhp;
    private Button btnMysql;
    private Button btnPhpMysql;
    private TextView txvPhp;
    private TextView txvMysql;
    private TextView txvPhpMysql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_video);
        btnPhp=(Button)findViewById(R.id.btn_video_php);
        btnPhp.setOnClickListener(this);
        btnMysql=(Button)findViewById(R.id.btn_video_mysql);
        btnMysql.setOnClickListener(this);
        btnPhpMysql=(Button)findViewById(R.id.btn_video_phpmysql);
        btnPhpMysql.setOnClickListener(this);
        txvPhp=(TextView)findViewById(R.id.txv_video_php);
        txvMysql=(TextView)findViewById(R.id.txv_video_mysql);
        txvPhpMysql=(TextView)findViewById(R.id.txv_video_phpmysql);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_video_php:
                Intent i = new Intent(this,VideoActivity.class);
                i.putExtra("url",txvPhp.getText().toString());
                startActivity(i);
                break;
            case R.id.btn_video_mysql:
                i = new Intent(this,VideoActivity.class);
                i.putExtra("url",txvMysql.getText().toString());
                startActivity(i);
                break;
            case R.id.btn_video_phpmysql:
                i = new Intent(this,VideoActivity.class);
                i.putExtra("url",txvPhpMysql.getText().toString());
                startActivity(i);
                break;

        }
    }
}
