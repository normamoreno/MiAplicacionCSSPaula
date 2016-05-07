package mx.edu.utng.css;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by user on 05/05/2016.
 */
public class CamaraActivity extends ActionBarActivity implements View.OnClickListener {
    ImageView iv;
    Button btnCapture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.camara_layout);
        initComponents();
    }

    private void initComponents() {
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        iv=(ImageView)findViewById(R.id.imageView3);
        btnCapture=(Button)findViewById(R.id.button_camera);
        btnCapture.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        Intent c=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(c,0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap m=(Bitmap)data.getExtras().get("data");
        iv.setImageBitmap(m);
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
    }
}
