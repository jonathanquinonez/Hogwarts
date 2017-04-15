package jamsapp.hogwarts;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Menu extends AppCompatActivity {
ImageView imagen1, imagen2, imagen3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        imagen1= (ImageView) findViewById(R.id.imageView4);
        imagen2= (ImageView) findViewById(R.id.imageView5);
        imagen3= (ImageView) findViewById(R.id.imageView7);


        Bundle bundle = getIntent().getExtras();
        String dato=bundle.getString("idestudiante");

        imagen1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Intent i = new Intent(Menu.this,ModificarUSU.class);
                        String cadena = "Bienvenido [NOMBRE]";
                        Toast notificacion = Toast.makeText(getApplicationContext(), cadena, Toast.LENGTH_LONG);
                        notificacion.show();
                        startActivity(i);


                    }});
        imagen2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Intent i = new Intent(Menu.this,Matriculamateria.class);
                        String cadena = "Bienvenido [NOMBRE]";
                        Toast notificacion = Toast.makeText(getApplicationContext(), cadena, Toast.LENGTH_LONG);
                        notificacion.show();
                        startActivity(i);


                    }});
        imagen3.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Intent i = new Intent(Menu.this,Eliminarmateria.class);
                        String cadena = "Bienvenido [NOMBRE]";
                        Toast notificacion = Toast.makeText(getApplicationContext(), cadena, Toast.LENGTH_LONG);
                        notificacion.show();
                        startActivity(i);


                    }});













    }
}
