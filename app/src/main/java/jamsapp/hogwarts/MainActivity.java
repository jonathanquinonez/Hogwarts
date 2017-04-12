package jamsapp.hogwarts;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
     Button boton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boton = (Button) findViewById(R.id.button);


        boton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Intent i = new Intent(MainActivity.this, Menu.class);
                        String cadena = "Bienvenido [NOMBRE]";
                        Toast notificacion = Toast.makeText(getApplicationContext(), cadena, Toast.LENGTH_LONG);
                        notificacion.show();
                        startActivity(i);


                    }});


    }
}
