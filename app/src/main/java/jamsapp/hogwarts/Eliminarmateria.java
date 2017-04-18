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

public class Eliminarmateria extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Button Volver,Eliminar;
    EditText etcodigo,etnombre,etdescripcion;
    Spinner spinner1;
    ArrayAdapter<String> semestre;

    //info materia de consulta
    String id_materia;
    String nombre_mate;
    String descripcion_mate;
    String semestre_mate;


    //inf dato
    int n;
    int indice;
    ArrayAdapter<String> materiasdatoseptimo,materiasdatooctavo;
    String [] id_materiaspinnerseptimo ={"seleccione materia","Transformaciones","Defensa Contra las Artes Oscuras","Estudios Muggles","Pociones","Artes Oscuras","Cuidado de Criaturas Mágicas"};
    String [] id_materiaspinneroctavo ={"seleccione materia","Herbología","Encantamientos","Astronomia","Instructora de Vuelo","Runas Antiguas","Historia de la Magia","Aritmanc"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminarmateria);

        Volver = (Button)findViewById(R.id.volver);
        etcodigo = (EditText)findViewById(R.id.codigo);
        etnombre = (EditText)findViewById(R.id.nombre);
        etdescripcion=(EditText)findViewById(R.id.descripcion);
        spinner1= (Spinner) findViewById(R.id.spinner);








        Bundle bundle = getIntent().getExtras();
        String dato=bundle.getString("idestudiantee");

        spinner1.setOnItemSelectedListener(this);
        materiasdatoseptimo=  new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,id_materiaspinnerseptimo);
        materiasdatooctavo=  new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,id_materiaspinneroctavo);




        Volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        if(dato.equals("20132329122"))
        {
            n=1;
            spinner1.setAdapter(materiasdatoseptimo);

        } else if(dato.equals("20132328860"))
        {
            n=2;
            spinner1.setAdapter(materiasdatooctavo);

        }






    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
    {

        if(n==1){

            switch (adapterView.getId()) {
                case R.id.spinner:
                    int opcion = spinner1.getSelectedItemPosition();
                    switch (opcion) {
                        case 0:
                            etcodigo.setEnabled(false);
                            etnombre.setEnabled(false);
                            etdescripcion.setEnabled(false);
                            etcodigo.setText(null);
                            etnombre.setText(null);
                            etdescripcion.setText(null);

                            break;

                        case 1:  // transformaciones

                            indice = 1;
                            new ConsultarDatos().execute("http://Labcowork.com/mostrarmateria.php?id_materia="+indice);

                            etcodigo.setEnabled(true);
                            etnombre.setEnabled(true);
                            etdescripcion.setEnabled(true);
                            etcodigo.setText(""+indice);
                            etnombre.setText(id_materiaspinnerseptimo[1]);
                         //   etdescripcion.setText(descripcion_mate);


                            break;

                        case 2:  // Estudios Muggles

                            indice = 3;
                            new ConsultarDatos().execute("http://Labcowork.com/mostrarmateria.php?id_materia="+indice);

                            etcodigo.setEnabled(true);
                            etnombre.setEnabled(true);
                            etdescripcion.setEnabled(true);
                            etcodigo.setText(""+indice);
                            etnombre.setText(id_materiaspinnerseptimo[2]);
                           // etdescripcion.setText(descripcion_mate);


                            break;
                        case 3:  //  Defensa contra las artes oscuras

                            indice = 2;
                            new ConsultarDatos().execute("http://Labcowork.com/mostrarmateria.php?id_materia="+indice);

                            etcodigo.setEnabled(true);
                            etnombre.setEnabled(true);
                            etdescripcion.setEnabled(true);
                            etcodigo.setText(""+indice);
                            etnombre.setText(id_materiaspinnerseptimo[3]);
                            //etdescripcion.setText(descripcion_mate);

                            break;
                        case 4:  // Pociones

                            indice = 4;
                            new ConsultarDatos().execute("http://Labcowork.com/mostrarmateria.php?id_materia="+indice);

                            etcodigo.setEnabled(true);
                            etnombre.setEnabled(true);
                            etdescripcion.setEnabled(true);
                            etcodigo.setText(""+indice);
                            etnombre.setText(id_materiaspinnerseptimo[4]);
//                            etdescripcion.setText(descripcion_mate);


                            break;
                        case 5:  // Artes oscuras
                            indice = 5;
                            new ConsultarDatos().execute("http://Labcowork.com/mostrarprofesor.php?id_materia="+indice);

                            etcodigo.setEnabled(true);
                            etnombre.setEnabled(true);
                            etdescripcion.setEnabled(true);
                            etcodigo.setText(""+indice);
                            etnombre.setText(id_materiaspinnerseptimo[5]);
                         //   etdescripcion.setText(descripcion_mate);


                            break;
                        case 6:  // Cuidado de criaturas magicas
                            indice = 6;
                            new ConsultarDatos().execute("http://Labcowork.com/mostrarprofesor.php?id_materia="+indice);

                            etcodigo.setEnabled(true);
                            etnombre.setEnabled(true);
                            etdescripcion.setEnabled(true);
                            etcodigo.setText(""+indice);
                            etnombre.setText(id_materiaspinnerseptimo[6]);
                            //etdescripcion.setText(descripcion_mate);


                            break;
                    }
            }

        }


        if(n==2) {


            switch (adapterView.getId()) {
                case R.id.spinner:
                    int opcion = spinner1.getSelectedItemPosition();
                    switch (opcion) {

                        case 0: // seleccione materia

                            etcodigo.setEnabled(false);
                            etnombre.setEnabled(false);
                            etdescripcion.setEnabled(false);
                            etcodigo.setText(null);
                            etnombre.setText(null);
                            etdescripcion.setText(null);
                            break;

                        case 1:  //Herbologia

                            indice = 7;
                            new ConsultarDatos().execute("http://Labcowork.com/mostrarmateria.php?id_materia="+indice);
                            etcodigo.setEnabled(true);
                            etnombre.setEnabled(true);
                            etdescripcion.setEnabled(true);
                            etcodigo.setText(""+indice);
                            etnombre.setText(id_materiaspinneroctavo[1]);
                           // etdescripcion.setText(descripcion_mate);


                            break;
                        case 2:  //Encantamientos

                            indice = 8;
                            new ConsultarDatos().execute("http://Labcowork.com/mostrarmateria.php?id_materia="+indice);
                            etcodigo.setEnabled(true);
                            etnombre.setEnabled(true);
                            etdescripcion.setEnabled(true);
                            etcodigo.setText(""+indice);
                            etnombre.setText(id_materiaspinneroctavo[2]);
                         // etdescripcion.setText(descripcion_mate);

                            break;
                        case 3:  //Astronomia

                            indice = 9;
                            new ConsultarDatos().execute("http://Labcowork.com/mostrarmateria.php?id_materia="+indice);
                            etcodigo.setEnabled(true);
                            etnombre.setEnabled(true);
                            etdescripcion.setEnabled(true);
                            etcodigo.setText(""+indice);
                            etnombre.setText(id_materiaspinneroctavo[3]);
                          //  etdescripcion.setText(descripcion_mate);

                            break;
                        case 4:  //Instruccion de vuelo

                            indice = 10;
                            new ConsultarDatos().execute("http://Labcowork.com/mostrarmateria.php?id_materia="+indice);
                            etcodigo.setEnabled(true);
                            etnombre.setEnabled(true);
                            etdescripcion.setEnabled(true);
                            etcodigo.setText(""+indice);
                            etnombre.setText(id_materiaspinneroctavo[4]);
                        //    etdescripcion.setText(descripcion_mate);


                            break;
                        case 5:  //Runas antiguas

                            indice = 11;
                            new ConsultarDatos().execute("http://Labcowork.com/mostrarmateria.php?id_materia="+indice);
                            etcodigo.setEnabled(true);
                            etnombre.setEnabled(true);
                            etdescripcion.setEnabled(true);
                            etcodigo.setText(""+indice);
                            etnombre.setText(id_materiaspinneroctavo[5]);
                         //   etdescripcion.setText(descripcion_mate);


                            break;

                        case 6:  //Historia de la magia

                            indice = 12;
                            new ConsultarDatos().execute("http://Labcowork.com/mostrarmateria.php?id_materia="+indice);
                            etcodigo.setEnabled(true);
                            etnombre.setEnabled(true);
                            etdescripcion.setEnabled(true);
                            etcodigo.setText(""+indice);
                            etnombre.setText(id_materiaspinneroctavo[6]);
                          //  etdescripcion.setText(descripcion_mate);

                            break;
                        case 7:  //Aritmancia

                            indice = 13;
                            new ConsultarDatos().execute("http://Labcowork.com/mostrarmateria.php?id_materia="+indice);
                            etcodigo.setEnabled(true);
                            etnombre.setEnabled(true);
                            etdescripcion.setEnabled(true);
                            etcodigo.setText(""+indice);
                            etnombre.setText(id_materiaspinneroctavo[7]);
                          //  etdescripcion.setText(descripcion_mate);

                            break;


                    }
                    break;


            }
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

                id_materia=(ja.getString(0));
                nombre_mate=(ja.getString(1));
                descripcion_mate=(ja.getString(2));
                etdescripcion.setText(descripcion_mate);


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

