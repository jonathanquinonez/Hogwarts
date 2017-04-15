package jamsapp.hogwarts;

import android.content.Intent;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
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


public class ModificarUSU extends AppCompatActivity  {

    EditText semestre;
    EditText codigo;
    EditText nombre;
    EditText apellido;
    EditText direccion;
    EditText telefono;
    EditText correo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_modificar_usu);



        semestre = (EditText)findViewById(R.id.etsemestre);
        codigo = (EditText)findViewById(R.id.etcodigo);
        nombre = (EditText)findViewById(R.id.etnombre);
        apellido = (EditText)findViewById(R.id.etapellidos);
        direccion = (EditText)findViewById(R.id.etdireccion);
        telefono = (EditText)findViewById(R.id.ettelefono);
        correo = (EditText)findViewById(R.id.etcorreo);



        new ModificarUSU.ConsultarDatos().execute("http://Labcowork.com/mostraralumno.php?id_alumno=20132329122");




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
                nombre.setText(ja.getString(1));
                codigo.setText(ja.getString(0));
                semestre.setText(ja.getString(3));
                apellido.setText(ja.getString(4));
                telefono.setText(ja.getString(5));
                direccion.setText(ja.getString(8));
                correo.setText(ja.getString(6));



            } catch (JSONException e) {
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
