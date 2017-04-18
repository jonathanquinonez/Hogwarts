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
    Button guardar;

    Spinner spmateria;
    RadioButton profesor1;
    RadioButton profesor2;
    RadioButton profesor3;


    //info alumno de consulta
    String codigo_alum;
    String nombre_alum;
    String apellido_alum;
    String direccion_alum;
    String telefono_alum;
    String semestre_alum;
    String correo_alum;

    //ifno profesor de consulta

    String id_profesor;
    String nombre_pro;
    String direccion_pro;
    String telefono_pro;

    //info materia de consulta
    String id_materia;
    String nombre_mate;
    String descripcion_mate;
    String semestre_mate;

    //inf curso de consulta
    String id_profesor_curso;
    String id_materia_curso;


    // validar spinner adapte
    int n;


    // valor del indice de  la matrix de profesor octavo o septimo

    int indice;


    // indice de id de profesor

    int indice_profe;

    ArrayAdapter<String> materiasdatoseptimo,materiasdatooctavo;
    String [] id_profesorspinnerseptimo ={"seleccione materia","Transformaciones","Estudios Muggles","Defensa Contra las Artes Oscuras","Pociones","Artes Oscuras","Cuidado de Criaturas Mágicas"};
    String [] id_profesorspinneroctavo ={"seleccione materia","Herbología","Encantamientos","Astronomia","Instructora de Vuelo","Runas Antiguas","Historia de la Magia","Aritmancia"};
    String [] profesoreseptimooctavo ={"Severus Snape","Dolores Umbridge ","Rubeus Hagrid","Albus Dumbledore","Remus Lupin"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matriculamateria);
        Volver = (Button)findViewById(R.id.volver);
        guardar = (Button)findViewById(R.id.guardar);
        spmateria = (Spinner) findViewById(R.id.spinnermateria);
        profesor1 = (RadioButton)findViewById(R.id.radprofesor1);
        profesor2 = (RadioButton)findViewById(R.id.radprofesor2);
        profesor3 = (RadioButton)findViewById(R.id.radprofesor3);

        spmateria.setOnItemSelectedListener(this);
        materiasdatoseptimo = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, id_profesorspinnerseptimo);
        materiasdatooctavo = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, id_profesorspinneroctavo);


        Bundle bundle = getIntent().getExtras();
        String dato=bundle.getString("idestudiantem");


        Volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        new Matriculamateria.ConsultarDatosalumno().execute("http://labcowork.com/mostraralumno.php?id_alumno="+dato);


        if(dato.equals("20132329122"))
        {
            n=1;
            spmateria.setAdapter(materiasdatoseptimo);
        } else if(dato.equals("20132328860"))
        {
            n=2;
            spmateria.setAdapter(materiasdatooctavo);
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
    {

        if(n==1){

                switch (adapterView.getId()) {
                 case R.id.spinnermateria:
                    int opcion = spmateria.getSelectedItemPosition();
                    switch (opcion) {
                        case 0:

                            profesor1.setEnabled(false);
                            profesor2.setEnabled(false);
                            profesor3.setEnabled(false);
                            profesor1.setText(null);
                            profesor2.setText(null);
                            profesor3.setText(null);

                            break;
                        case 1:  // transformaciones

                            profesor1.setEnabled(true);
                            profesor2.setEnabled(true);
                            profesor3.setEnabled(true);
                            profesor1.setText(profesoreseptimooctavo[0]);
                            profesor2.setText(profesoreseptimooctavo[1]);
                            profesor3.setText(profesoreseptimooctavo[3]);
                            indice = 1;
                            guardar.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    if(profesor1.isChecked()==true)
                                    {
                                        indice_profe=9123;
                                        new ConsultarDatosprofe().execute("http://Labcowork.com/mostrarprofesor.php?id_profesor="+indice_profe);
                                        new CargarDatos().execute("http://labcowork.com/agregaralumnocurso.php?id_alumno=20132329122&id_profesor="+indice_profe+"&id_materia="+indice);
                                        //http://labcowork.com/agregaralumnocurso.php?id_alumno=20132328860&id_profesor=9123&id_materia=1

                                    }else if(profesor2.isChecked()==true)
                                    {
                                        indice_profe=9124;
                                        new ConsultarDatosprofe().execute("http://Labcowork.com/mostrarprofesor.php?id_profesor="+indice_profe);
                                        new CargarDatos().execute("http://labcowork.com/agregaralumnocurso.php?id_alumno=20132329122&id_profesor="+indice_profe+"&id_materia="+indice);


                                    }else if(profesor3.isChecked()==true)
                                    {
                                        indice_profe=9126;
                                        new ConsultarDatosprofe().execute("http://Labcowork.com/mostrarprofesor.php?id_profesor="+indice_profe);
                                        new CargarDatos().execute("http://labcowork.com/agregaralumnocurso.php?id_alumno=20132329122&id_profesor="+indice_profe+"&id_materia="+indice);


                                    }
                                }
                            });

                            break;
                        case 2:  // Estudios Muggles
                            profesor1.setEnabled(true);
                            profesor1.setText(profesoreseptimooctavo[1]);
                            profesor2.setText(null);
                            profesor3.setText(null);
                            profesor2.setEnabled(false);
                            profesor3.setEnabled(false);
                            indice = 3;
                            guardar.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    if(profesor1.isChecked()==true)
                                    {
                                        indice_profe=9124;
                                        new ConsultarDatosprofe().execute("http://Labcowork.com/mostrarprofesor.php?id_profesor="+indice_profe);
                                        new CargarDatos().execute("http://labcowork.com/agregaralumnocurso.php?id_alumno=20132329122&id_profesor="+indice_profe+"&id_materia="+indice);
                                        //http://labcowork.com/agregaralumnocurso.php?id_alumno=20132328860&id_profesor=9123&id_materia=1
                                    }
                                }
                            });

                            break;
                        case 3:  //  Defensa contra las artes oscuras
                            profesor1.setEnabled(true);
                            profesor1.setText(profesoreseptimooctavo[1]);
                            profesor2.setText(null);
                            profesor3.setText(null);
                            profesor2.setEnabled(false);
                            profesor3.setEnabled(false);
                            indice = 2;
                            guardar.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    if(profesor1.isChecked()==true)
                                    {
                                        indice_profe=9124;
                                        new ConsultarDatosprofe().execute("http://Labcowork.com/mostrarprofesor.php?id_profesor="+indice_profe);
                                        new CargarDatos().execute("http://labcowork.com/agregaralumnocurso.php?id_alumno=20132329122&id_profesor="+indice_profe+"&id_materia="+indice);
                                        //http://labcowork.com/agregaralumnocurso.php?id_alumno=20132328860&id_profesor=9123&id_materia=1
                                    }
                                }
                            });

                            break;
                        case 4:  // Pociones
                            profesor1.setEnabled(true);
                            profesor2.setEnabled(true);
                            profesor1.setText(profesoreseptimooctavo[1]);
                            profesor2.setText(profesoreseptimooctavo[4]);
                            profesor3.setText(null);
                            profesor3.setEnabled(false);
                            indice = 4;
                            guardar.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    if(profesor1.isChecked()==true)
                                    {
                                        indice_profe=9124;
                                        new ConsultarDatosprofe().execute("http://Labcowork.com/mostrarprofesor.php?id_profesor="+indice_profe);
                                        new CargarDatos().execute("http://labcowork.com/agregaralumnocurso.php?id_alumno=20132329122&id_profesor="+indice_profe+"&id_materia="+indice);
                                        //http://labcowork.com/agregaralumnocurso.php?id_alumno=20132328860&id_profesor=9123&id_materia=1

                                    }else if(profesor2.isChecked()==true)
                                    {
                                        indice_profe=9127;
                                        new ConsultarDatosprofe().execute("http://Labcowork.com/mostrarprofesor.php?id_profesor="+indice_profe);
                                        new CargarDatos().execute("http://labcowork.com/agregaralumnocurso.php?id_alumno=20132329122&id_profesor="+indice_profe+"&id_materia="+indice);
                                    }
                                }
                            });

                            break;
                        case 5:  // Artes oscuras
                            profesor1.setEnabled(true);
                            profesor2.setEnabled(true);
                            profesor1.setText(profesoreseptimooctavo[2]);
                            profesor2.setText(profesoreseptimooctavo[4]);
                            profesor3.setText(null);
                            profesor3.setEnabled(false);

                            indice = 5;
                            guardar.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    if(profesor1.isChecked()==true)
                                    {
                                        indice_profe=9125;
                                        new ConsultarDatosprofe().execute("http://Labcowork.com/mostrarprofesor.php?id_profesor="+indice_profe);
                                        new CargarDatos().execute("http://labcowork.com/agregaralumnocurso.php?id_alumno=20132329122&id_profesor="+indice_profe+"&id_materia="+indice);
                                        //http://labcowork.com/agregaralumnocurso.php?id_alumno=20132328860&id_profesor=9123&id_materia=1

                                    }else if(profesor2.isChecked()==true)
                                    {
                                        indice_profe=9127;
                                        Toast.makeText(getApplicationContext(), "se preciono el radio buton", Toast.LENGTH_LONG).show();
                                        new ConsultarDatosprofe().execute("http://Labcowork.com/mostrarprofesor.php?id_profesor="+indice_profe);
                                        new CargarDatos().execute("http://labcowork.com/agregaralumnocurso.php?id_alumno=20132329122&id_profesor="+indice_profe+"&id_materia="+indice);
                                    }
                                }
                            });

                            break;
                        case 6:  // Cuidado de criaturas magicas
                            profesor1.setEnabled(true);
                            profesor1.setText(profesoreseptimooctavo[0]);
                            profesor2.setText(null);
                            profesor3.setText(null);
                            profesor2.setEnabled(false);
                            profesor3.setEnabled(false);
                            indice = 6;
                            guardar.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    if(profesor1.isChecked()==true)
                                    {
                                        indice_profe=9123;
                                        new ConsultarDatosprofe().execute("http://Labcowork.com/mostrarprofesor.php?id_profesor="+indice_profe);
                                        new CargarDatos().execute("http://labcowork.com/agregaralumnocurso.php?id_alumno=20132329122&id_profesor="+indice_profe+"&id_materia="+indice);
                                        //http://labcowork.com/agregaralumnocurso.php?id_alumno=20132328860&id_profesor=9123&id_materia=1
                                    }
                                }
                            });

                            break;
                    }
            }

        }


        if(n==2) {


            switch (adapterView.getId()) {
                case R.id.spinnermateria:
                    int opcion = spmateria.getSelectedItemPosition();
                    switch (opcion) {

                        case 0: // seleccione materia

                            profesor1.setEnabled(false);
                            profesor2.setEnabled(false);
                            profesor3.setEnabled(false);
                            profesor1.setText(null);
                            profesor2.setText(null);
                            profesor3.setText(null);
                            break;

                        case 1:  //Herbologia
                            profesor1.setEnabled(true);
                            profesor1.setText(profesoreseptimooctavo[2]);
                            profesor2.setText(null);
                            profesor3.setText(null);
                            profesor2.setEnabled(false);
                            profesor3.setEnabled(false);

                            indice = 7;
                            guardar.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    if(profesor1.isChecked()==true)
                                    {
                                        indice_profe=9125;
                                        new ConsultarDatosprofe().execute("http://Labcowork.com/mostrarprofesor.php?id_profesor="+indice_profe);
                                        new CargarDatos().execute("http://labcowork.com/agregaralumnocurso.php?id_alumno=20132328860&id_profesor="+indice_profe+"&id_materia="+indice);
                                        //http://labcowork.com/agregaralumnocurso.php?id_alumno=20132328860&id_profesor=9123&id_materia=1
                                    }
                                }
                            });

                            break;
                        case 2:  //Encantamientos
                            profesor1.setEnabled(true);
                            profesor1.setText(profesoreseptimooctavo[0]);
                            profesor2.setText(null);
                            profesor3.setText(null);
                            profesor2.setEnabled(false);
                            profesor3.setEnabled(false);
                            indice = 8;
                            guardar.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    if(profesor1.isChecked()==true)
                                    {
                                        indice_profe=9123;
                                        new ConsultarDatosprofe().execute("http://Labcowork.com/mostrarprofesor.php?id_profesor="+indice_profe);
                                        new CargarDatos().execute("http://labcowork.com/agregaralumnocurso.php?id_alumno=20132328860&id_profesor="+indice_profe+"&id_materia="+indice);
                                        //http://labcowork.com/agregaralumnocurso.php?id_alumno=20132328860&id_profesor=9123&id_materia=1
                                    }
                                }
                            });

                            break;
                        case 3:  //Astronomia
                            profesor1.setEnabled(true);
                            profesor2.setEnabled(true);
                            profesor1.setText(profesoreseptimooctavo[4]);
                            profesor2.setText(profesoreseptimooctavo[1]);
                            profesor3.setText(null);
                            profesor3.setEnabled(false);
                            indice = 9;
                            guardar.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    if(profesor1.isChecked()==true)
                                    {
                                        indice_profe=9127;
                                        new ConsultarDatosprofe().execute("http://Labcowork.com/mostrarprofesor.php?id_profesor="+indice_profe);
                                        new CargarDatos().execute("http://labcowork.com/agregaralumnocurso.php?id_alumno=20132328860&id_profesor="+indice_profe+"&id_materia="+indice);
                                        //http://labcowork.com/agregaralumnocurso.php?id_alumno=20132328860&id_profesor=9123&id_materia=1

                                    }else if(profesor2.isChecked()==true)
                                    {
                                        indice_profe=9124;
                                        new ConsultarDatosprofe().execute("http://Labcowork.com/mostrarprofesor.php?id_profesor="+indice_profe);
                                        new CargarDatos().execute("http://labcowork.com/agregaralumnocurso.php?id_alumno=20132328860&id_profesor="+indice_profe+"&id_materia="+indice);
                                    }
                                }
                            });



                            break;
                        case 4:  //Instruccion de vuelo
                            profesor1.setEnabled(true);
                            profesor2.setEnabled(true);
                            profesor1.setText(profesoreseptimooctavo[1]);
                            profesor2.setText(profesoreseptimooctavo[3]);
                            profesor3.setText(null);
                            profesor3.setEnabled(false);

                            indice = 10;
                            guardar.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    if(profesor1.isChecked()==true)
                                    {
                                        indice_profe=9124;
                                        new ConsultarDatosprofe().execute("http://Labcowork.com/mostrarprofesor.php?id_profesor="+indice_profe);
                                        new CargarDatos().execute("http://labcowork.com/agregaralumnocurso.php?id_alumno=20132328860&id_profesor="+indice_profe+"&id_materia="+indice);
                                        //http://labcowork.com/agregaralumnocurso.php?id_alumno=20132328860&id_profesor=9123&id_materia=1

                                    }else if(profesor2.isChecked()==true)
                                    {
                                        indice_profe=9126;
                                        new ConsultarDatosprofe().execute("http://Labcowork.com/mostrarprofesor.php?id_profesor="+indice_profe);
                                        new CargarDatos().execute("http://labcowork.com/agregaralumnocurso.php?id_alumno=20132328860&id_profesor="+indice_profe+"&id_materia="+indice);
                                    }
                                }
                            });


                            break;
                        case 5:  //Runas antiguas
                            profesor1.setEnabled(true);
                            profesor1.setText(profesoreseptimooctavo[4]);
                            profesor2.setText(null);
                            profesor3.setText(null);
                            profesor2.setEnabled(false);
                            profesor3.setEnabled(false);

                            indice = 11;
                            guardar.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    if(profesor1.isChecked()==true)
                                    {
                                        indice_profe=9127;
                                        new ConsultarDatosprofe().execute("http://Labcowork.com/mostrarprofesor.php?id_profesor="+indice_profe);
                                        new CargarDatos().execute("http://labcowork.com/agregaralumnocurso.php?id_alumno=20132328860&id_profesor="+indice_profe+"&id_materia="+indice);
                                        //http://labcowork.com/agregaralumnocurso.php?id_alumno=20132328860&id_profesor=9123&id_materia=1
                                    }
                                }
                            });

                            break;

                        case 6:  //Historia de la magia
                            profesor1.setEnabled(true);
                            profesor2.setEnabled(true);
                            profesor3.setEnabled(true);

                            profesor1.setText(profesoreseptimooctavo[0]);
                            profesor2.setText(profesoreseptimooctavo[1]);
                            profesor3.setText(profesoreseptimooctavo[2]);

                            indice = 12;
                            guardar.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    if(profesor1.isChecked()==true)
                                    {
                                        indice_profe=9123;
                                        new ConsultarDatosprofe().execute("http://Labcowork.com/mostrarprofesor.php?id_profesor="+indice_profe);
                                        new CargarDatos().execute("http://labcowork.com/agregaralumnocurso.php?id_alumno=20132329122&id_profesor="+indice_profe+"&id_materia="+indice);
                                        //http://labcowork.com/agregaralumnocurso.php?id_alumno=20132328860&id_profesor=9123&id_materia=1

                                    }else if(profesor2.isChecked()==true)
                                    {
                                        indice_profe=9124;
                                        new ConsultarDatosprofe().execute("http://Labcowork.com/mostrarprofesor.php?id_profesor="+indice_profe);
                                        new CargarDatos().execute("http://labcowork.com/agregaralumnocurso.php?id_alumno=20132329122&id_profesor="+indice_profe+"&id_materia="+indice);


                                    }else if(profesor3.isChecked()==true)
                                    {
                                        indice_profe=9125;
                                        new ConsultarDatosprofe().execute("http://Labcowork.com/mostrarprofesor.php?id_profesor="+indice_profe);
                                        new CargarDatos().execute("http://labcowork.com/agregaralumnocurso.php?id_alumno=20132329122&id_profesor="+indice_profe+"&id_materia="+indice);


                                    }
                                }
                            });

                            break;
                        case 7:  //Aritmancia
                            profesor1.setEnabled(true);
                            profesor1.setText(profesoreseptimooctavo[4]);
                            profesor2.setText(null);
                            profesor3.setText(null);
                            profesor2.setEnabled(false);
                            profesor3.setEnabled(false);
                            indice = 13;
                            guardar.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    if(profesor1.isChecked()==true)
                                    {
                                        indice_profe=9127;
                                        new ConsultarDatosprofe().execute("http://Labcowork.com/mostrarprofesor.php?id_profesor="+indice_profe);
                                        new CargarDatos().execute("http://labcowork.com/agregaralumnocurso.php?id_alumno=20132328860&id_profesor="+indice_profe+"&id_materia="+indice);
                                        //http://labcowork.com/agregaralumnocurso.php?id_alumno=20132328860&id_profesor=9123&id_materia=1
                                    }
                                }
                            });
                            break;


                    }
                    break;


            }
        }



    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }



    private class CargarDatos extends AsyncTask<String, Void, String> {
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

            Toast.makeText(getApplicationContext(), "Se almacenaron los datos correctamente", Toast.LENGTH_LONG).show();

        }
    }


    private class ConsultarDatosalumno extends AsyncTask<String, Void, String> {
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
        protected void onPostExecute(String result2) {

            JSONArray ja = null;


            try {
                ja = new JSONArray(result2);
                codigo_alum=(ja.getString(0));
                nombre_alum=(ja.getString(1));
                apellido_alum=(ja.getString(4));
                direccion_alum=(ja.getString(8));
                semestre_alum=(ja.getString(3));
                telefono_alum=(ja.getString(5));
                correo_alum=(ja.getString(6));
                new Matriculamateria.ConsultarDatoscurso().execute("http://labcowork.com/mostrarcursopors.php?semestre="+semestre_alum);

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



    private class ConsultarDatoscurso extends AsyncTask<String, Void, String> {
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
        protected void onPostExecute(String result4) {

            JSONArray ja = null;


            try {
                ja = new JSONArray(result4);

                id_profesor_curso=(ja.getString(0));
                id_materia_curso=(ja.getString(1));

               new Matriculamateria.ConsultarDatosprofe().execute("http://Labcowork.com/mostrarprofesor.php?id_profesor="+id_profesor_curso);




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
        protected void onPostExecute(String result5) {

            JSONArray ja = null;


            try {
                ja = new JSONArray(result5);
                nombre_pro=(ja.getString(1));
                id_profesor=(ja.getString(0));
                telefono_pro=(ja.getString(3));
                direccion_pro=(ja.getString(2));

                new Matriculamateria.ConsultarDatosmateria().execute("http://Labcowork.com/mostrarmateria.php?id_materia="+id_materia_curso);


            } catch (JSONException e) {
                Toast.makeText(getApplicationContext(), "Se almacenaron los datos correctamente", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }

        }
    }

    /////////////////////////////////////////////////////

    private class ConsultarDatosmateria extends AsyncTask<String, Void, String> {
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
        protected void onPostExecute(String result3) {

            JSONArray ja = null;


            try {
                ja = new JSONArray(result3);
                id_materia=(ja.getString(0));
                nombre_mate=(ja.getString(1));
                descripcion_mate=(ja.getString(2));
                semestre_mate=(ja.getString(3));


            } catch (JSONException e) {
                Toast.makeText(getApplicationContext(), "Se almacenaron los datos correctamente", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }

        }
    }







}
