package jamsapp.hogwarts;

        import android.content.Intent;
        import android.media.Image;
        import android.os.AsyncTask;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.webkit.WebView;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.Toast;

        import org.json.JSONArray;
        import org.json.JSONException;

        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.io.Reader;
        import java.io.UnsupportedEncodingException;
        import java.net.HttpURLConnection;
        import java.net.URL;



public class Menu extends AppCompatActivity {


    ImageView imagen1, imagen2, imagen3, imagen4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        imagen1= (ImageView) findViewById(R.id.imageView4);
        imagen2= (ImageView) findViewById(R.id.imageView5);
        imagen3= (ImageView) findViewById(R.id.imageView7);
        imagen4= (ImageView) findViewById(R.id.imageView6);




        imagen1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = getIntent().getExtras();
                        String dato=bundle.getString("idestudiante");
                        Intent i = new Intent(Menu.this,ModificarUSU.class);
                        i.putExtra("idestudiantem", dato);
                        startActivity(i);


                    }});
        imagen2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = getIntent().getExtras();
                        String dato=bundle.getString("idestudiante");
                        Intent i = new Intent(Menu.this,Matriculamateria.class);
                        i.putExtra("idestudiantem", dato);
                        startActivity(i);

                    }});
        imagen3.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = getIntent().getExtras();
                        String dato=bundle.getString("idestudiante");
                        Intent i = new Intent(Menu.this,Eliminarmateria.class);
                        i.putExtra("idestudiantee", dato);

                        String cadena = "Bienvenido";
                        Toast notificacion = Toast.makeText(getApplicationContext(), cadena, Toast.LENGTH_LONG);
                        notificacion.show();


                        startActivity(i);
                    }});
        imagen4.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                        System.exit(0);

                    }});

    }





}
