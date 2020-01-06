package com.example.administrador.ipsapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;

public class Registrar extends AppCompatActivity {

    private final String TAG = this.getClass().getName();

    private int PICK_IMAGE_REQUEST = 1;
    EditText nombre,apellido,dni,email,usuario,password;
    ProgressDialog progressDialog ;
    Button registrar;
    String selectedSexo,SelectedCarrera;
    String Getnombre,GetApellido,Gedni,GetSexo,Getemail,GetCarrera,GetUsuario,GetPass;
    // String ImageName = "image_name" ;
    String USUARIO = "usuario" ;
    String EMAIL = "email" ;
    String  pass ="contraseña";
    String nombreAlumno = "nombre" ;
    String ape="apellido";
    String carre = "carrera";
    String documento="dni";
    String genero="sexo";
    String ServerUploadPath ="http://danbijann.freeiz.com/ips/upload.php";







    Spinner spinnerDropDownCarrera, sexo;
    String []sexoAlumno={"SEXO....","Masculino","Feminino"};
    String []Carrera ={"CARRERAS....",
            "Analista Universitario en Sistemas",
            "Técnico Universitario en Gestión y Producción",
            "Técnico Universitario en Mecatrónica",
            "Técnico Universitario en Plásticos y Elastómeros",
            "Técnico Universitario en Química",
            "Técnico Universitario en Sistemas Electrónicos",

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        spinnerDropDownCarrera =(Spinner)findViewById(R.id.spinner);
        sexo =(Spinner)findViewById(R.id.spinner2);

        nombre = (EditText)findViewById(R.id.etnombre);
        apellido = (EditText)findViewById(R.id.etApellido);
        dni = (EditText)findViewById(R.id.etDni);
        email = (EditText)findViewById(R.id.etEmail);
        usuario = (EditText)findViewById(R.id.etPassword);
        password = (EditText)findViewById(R.id.usuario);
        registrar=(Button) findViewById(R.id.button);

        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.
                R.layout.simple_spinner_dropdown_item ,Carrera);
        spinnerDropDownCarrera.setAdapter(adapter);

        spinnerDropDownCarrera.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                SelectedCarrera
                        = (String) adapterView.getItemAtPosition(i);
                if(i==0){
                    Toast.makeText(Registrar.this,"Por favor ingrese su sexo",Toast.LENGTH_LONG).show();
                }else if(i>0){
                    Toast.makeText(Registrar.this,GetSexo,Toast.LENGTH_LONG).show();


                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        ArrayAdapter<String> adapter2= new ArrayAdapter<String>(this,android.
                R.layout.simple_spinner_dropdown_item ,sexoAlumno);

        sexo.setAdapter(adapter2);
        sexo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                selectedSexo= (String) adapterView.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
       //
registrar.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Getnombre = nombre.getText().toString();
        GetApellido = apellido.getText().toString();
        Gedni = dni.getText().toString();
        //SelectedCarrera=GetCarrera.toString();
        Getemail = email.getText().toString();
        //selectedSexo=GetSexo.toString();
        GetUsuario = usuario.getText().toString();
        GetPass = password.getText().toString();
        new Signup().execute();

    }
});


}



    class  Signup extends AsyncTask<String,String,String>{
        @Override
        protected void onPreExecute() {

            super.onPreExecute();

            progressDialog = ProgressDialog.show(Registrar.this,"Cargando Datos ","Por Favor Espere..",false,false);
        }

        @Override
        protected String doInBackground(String... strings) {
            ProcessClass imageProcessClass = new ProcessClass();
            HashMap<String,String> HashMapParams = new HashMap<String,String>();

            HashMapParams.put(nombreAlumno, Getnombre);
            HashMapParams.put(ape, GetApellido);
            HashMapParams.put(EMAIL, Getemail);
            HashMapParams.put(documento, Gedni);
            HashMapParams.put(carre, SelectedCarrera);
            HashMapParams.put(genero, selectedSexo);
            HashMapParams.put(USUARIO, GetUsuario);
            HashMapParams.put(pass, GetPass);



            String FinalData = imageProcessClass.ImageHttpRequest(ServerUploadPath, HashMapParams);

            return FinalData;
        }

        @Override
        protected void onPostExecute(String string1) {

            super.onPostExecute(string1);

            // Dismiss the progress dialog after done uploading.
            progressDialog.dismiss();

            // Printing uploading success message coming from server on android app.
            Toast.makeText(Registrar.this,string1,Toast.LENGTH_LONG).show();

            // Setting image as transparent after done uploading.
           // circularImageView.setImageResource(android.R.color.transparent);
            nombre.getText().clear();
            email.getText().clear();
            dni.getText().clear();
            apellido.getText().clear();
            usuario.getText().clear();
            password.getText().clear();
            Intent intent = new Intent(Registrar.this, Login.class);
            startActivity(intent);


        }
        }
    }

