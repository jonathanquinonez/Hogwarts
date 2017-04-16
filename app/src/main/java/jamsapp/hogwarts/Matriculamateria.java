package jamsapp.hogwarts;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
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

public class Matriculamateria extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button Volver;

    Spinner materia;
    RadioButton profesor1;
    RadioButton profesor2;
    RadioButton profesor3;
    int n=0;

    //info alumno
    EditText codigo;
    EditText nombre;
    EditText apellido;
    EditText direccion;
    EditText telefono;
    EditText correo;

    //ifno profesor

    EditText id_profesor;
    EditText nombre_pro;
    EditText direccion_pro;
    EditText telefono_pro;

    //info materia
    EditText id_materia;
    EditText nombre_mate;
    EditText descripcion_mate;





    ArrayAdapter<String> semestre,mate7,mate8;
    String [] id_profesorspinner ={"Transformaciones","Estudios Muggles","Defensa Contra las Artes Oscuras","Pociones","Artes Oscuras","Cuidado de Criaturas Mágicas","Herbología","Encantamientos","Astronomia","Instructora de Vuelo","Runas Antiguas","Historia de la Magia","Aritmancia"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matriculamateria);

        Volver = (Button)findViewById(R.id.volver);
        materia = (Spinner) findViewById(R.id.spinnermateria);
        profesor1 = (RadioButton)findViewById(R.id.radprofesor1);
        profesor2 = (RadioButton)findViewById(R.id.radprofesor2);
        profesor3 = (RadioButton)findViewById(R.id.radprofesor3);
        materia.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, id_profesorspinner);




        Volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Bundle bundle = getIntent().getExtras();
        String dato=bundle.getString("idestudiantem");


        new ConsultarDatos().execute("http://Labcowork.com/mostrarmateria.php?id_materia=1");
        new ConsultarDatosprofe().execute("http://Labcowork.com/mostrarprofesor.php?id_profesor="+dato);







}

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
    {
        switch (adapterView.getId()) {
            case R.id.spinnermateria:
                int opcion = materia.getSelectedItemPosition();


                new ConsultarDatosprofe().execute("http://Labcowork.com/mostrarprofesor.php?id_profesor="+opcion);

                profesor1.setText(nombre_pro.getText().toString());
                profesor1.setText(nombre_pro.getText().toString());
                profesor1.setText(nombre_pro.getText().toString());



                break;

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

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
                nombre_mate.setText(ja.getString(1));
                id_materia.setText(ja.getString(0));
                descripcion_mate.setText(ja.getString(3));




            } catch (JSONException e) {
                Toast.makeText(getApplicationContext(), "Se almacenaron los datos correctamente", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }

        }
    }

    private class ConsultarDatosprofe extends AsyncTask<String, Void, String> {
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
                nombre_pro.setText(ja.getString(1));
                id_profesor.setText(ja.getString(0));
                telefono_pro.setText(ja.getString(3));
                direccion_pro.setText(ja.getString(2));




            } catch (JSONException e) {
                Toast.makeText(getApplicationContext(), "Se almacenaron los datos correctamente", Toast.LENGTH_LONG).show();
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
