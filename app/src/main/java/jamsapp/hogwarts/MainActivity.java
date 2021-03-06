package jamsapp.hogwarts;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class MainActivity extends AppCompatActivity {
     Button boton;
    EditText etuser, etpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boton = (Button) findViewById(R.id.button);
        etuser = (EditText)findViewById(R.id.user);
        etpassword = (EditText)findViewById(R.id.password);


        boton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        new ConsultarDatos().execute("http://labcowork.com/mostraralumno.php?id_alumno="+etuser.getText().toString());


                    }});




    }

    private class ConsultarDatos extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            // params comes from the execute() call: params[0] is the url.
            try {
                return downloadUrl(urls[0]);
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {

            JSONArray ja = null;


            try {
                ja = new JSONArray(result);

                //etNombres.setText(ja.getString(1));
                //etTelefono.setText(ja.getString(2));

                String usuario,contrasena, nombre,semestre;
                usuario=ja.getString(0);
                contrasena=ja.getString(7);
                nombre=ja.getString(1);
                semestre=ja.getString(3);
                if(usuario.equals(etuser.getText().toString()) && contrasena.equals(etpassword.getText().toString())) {
                    Intent i = new Intent(MainActivity.this, Menu.class);
                    i.putExtra("idestudiante",usuario);
                    String cadena = "Bienvenido "+nombre;
                    Toast notificacion = Toast.makeText(getApplicationContext(), cadena, Toast.LENGTH_LONG);
                    notificacion.show();
                    startActivity(i);
                }else{
                    String cadena = "Contraseña y Usuario no Coinciden";
                    Toast notificacion = Toast.makeText(getApplicationContext(), cadena, Toast.LENGTH_LONG);
                    notificacion.show();
                }




            } catch (JSONException e) {
                Toast.makeText(getApplicationContext(), "Usuario o Contraseña Incorrectos", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }

        }
    }

    private String downloadUrl(String myurl) throws IOException {
        Log.i("URL",""+myurl);
        myurl = myurl.replace(" ","%20");
        InputStream is = null;
        // Only display the first 500 characters of the retrieved
        // web page content.
        int len = 500;

        try {
            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            Log.d("respuesta", "The response is: " + response);
            is = conn.getInputStream();

            // Convert the InputStream into a string
            String contentAsString = readIt(is, len);
            return contentAsString;

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
    }
}
