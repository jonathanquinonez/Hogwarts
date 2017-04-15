package jamsapp.hogwarts;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class cambiarcontrasena extends AppCompatActivity {


    EditText nuevaC;
    EditText repetirC;
    Button cambiarcontrasena;
    Button Volver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiarcontrasena);


        cambiarcontrasena=(Button)findViewById(R.id.guardarcontrasena);
        nuevaC =(EditText)findViewById(R.id.contrasena) ;
        repetirC =(EditText)findViewById(R.id.rcontrasena) ;
        Volver = (Button)findViewById(R.id.volver);


        cambiarcontrasena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(nuevaC.getText().toString().equals(repetirC.getText().toString())&&nuevaC.getText().toString()!=null&&repetirC.getText().toString()!=null){
                    Bundle bundle = getIntent().getExtras();
                    String dato=bundle.getString("idestudiante");

                    new CargarDatos().execute("http://Labcowork.com/updatecontrasenaalumno.php?id_alumno="+dato+"&contrasena="+nuevaC.getText().toString());

                }else {
                    Toast notificacion = Toast.makeText(getApplicationContext(), "error al cambiar la contraseña", Toast.LENGTH_LONG);
                    notificacion.show();
                }


            }
        });

        Volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

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
