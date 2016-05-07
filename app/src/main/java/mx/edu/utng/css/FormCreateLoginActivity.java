package mx.edu.utng.css;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import mx.edu.utng.css.dao.DatabaseHelper;

public class FormCreateLoginActivity extends Activity {

    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_create_login_layout);
    }



    public void onSignUpClick(View v){
        if(v.getId() == R.id.Bsignupbutton){
            EditText name =(EditText)findViewById(R.id.TFname);
            EditText email =(EditText)findViewById(R.id.TFemail);
            EditText uname =(EditText)findViewById(R.id.TFuname);
            EditText pass1 =(EditText)findViewById(R.id.TFpass1);
            EditText pass2 =(EditText)findViewById(R.id.TFpass2);

            String namestr = name.getText().toString();
            String emailstr = email.getText().toString();
            String unamestr = uname.getText().toString();
            String pass1str = pass1.getText().toString();
            String pass2str = pass2.getText().toString();

            if(!pass1str.equals(pass2str)){
                //mensage
                Toast pass = Toast.makeText(FormCreateLoginActivity.this,"Las Contrasenias No Coinciden", Toast.LENGTH_SHORT);
                pass.show();

            }
            else{
                //insertar datos en la base de datos
                Contact c = new Contact();
                c.setName(namestr);
                c.setEmail(emailstr);
                c.setUname(unamestr);
                c.setPass(pass1str);
                helper.insertContact(c);
                Toast pass = Toast.makeText(FormCreateLoginActivity.this,"Datos Guardados", Toast.LENGTH_SHORT);
                pass.show();
                startActivity(new Intent(this, FormLoginActivity.class));
                System.exit(0);
            }
        }
    }
}
