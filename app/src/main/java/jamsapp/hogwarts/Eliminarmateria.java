package jamsapp.hogwarts;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static android.widget.AdapterView.*;

public class Eliminarmateria extends AppCompatActivity{

    Button Volver,Eliminar;
    EditText etnombrem,etprofesor,etdescripcion;
    Spinner spinner1;
    ArrayAdapter<String> semestre;
    String [] semestree={"--"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminarmateria);

        Volver = (Button)findViewById(R.id.volver);
        etnombrem = (EditText)findViewById(R.id.user);
        etprofesor = (EditText)findViewById(R.id.password);
        etdescripcion=(EditText)findViewById(R.id.password);
        spinner1= (Spinner) findViewById(R.id.spinner);


        Bundle bundle = getIntent().getExtras();
        String dato=bundle.getString("idestudiantee");
        new Eliminarmateria.ConsultarDatos().execute("http://labcowork.com/mostraralumno.php?id_alumno="+dato);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,semestree);
        spinner1.setAdapter(adapter);



        Volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
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

                String Semestre =ja.getString(3);
                Toast.makeText(getApplicationContext(), Semestre, Toast.LENGTH_LONG).show();
                new Eliminarmateria.ConsultarDatos2().execute("http://labcowork.com/mostrarmateriapors.php?semestre="+Semestre);



            } catch (JSONException e) {
                 e.printStackTrace();
            }

        }
    }
    private class ConsultarDatos2 extends AsyncTask<String, Void, String> {
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
                JSONObject jsonArray = null;



                for (int i=0;i<ja.length();i++){
                 semestree[i+1]=(ja.getJSONObject(i).getString(String.valueOf(1)));
                }
                Toast.makeText(getApplicationContext(), ja.getJSONObject(2).getString(String.valueOf(1)), Toast.LENGTH_LONG).show();



            } catch (JSONException e) {
                Toast.makeText(getApplicationContext(), "no entro a la consulta", Toast.LENGTH_LONG).show();
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

