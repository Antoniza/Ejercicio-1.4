package com.example.ejercicio14;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {


    ImageView imagen;
    Button btnsalvar, btnver;
    EditText txtnombre, txtdescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cargarElementos();

    }

    private void cargarElementos() {
        txtnombre = (EditText) findViewById(R.id.txt_nombre);
        txtdescripcion = (EditText) findViewById(R.id.txt_description);

        btnsalvar = (Button) findViewById(R.id.btn_salvar);
        btnver = (Button) findViewById(R.id.btn_ver);

        imagen = (ImageView)findViewById(R.id.imagen);
    }

    private void agregarContacto() {
        // Creamos la conexion e insercion a la BDD
        SQLiteConexion conexion = new SQLiteConexion(this, Contactos.NameDatabase, null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();


        try {
            String resu = "";
            //tenemos que comprobar que se haya seleccionado alguna operacion a realizar.

            if (txtnombre.getText().toString().isEmpty()) {
                resu = "Upps!!, ingrese el nombre del registro";
                Toast.makeText(getApplicationContext(), resu, Toast.LENGTH_LONG).show();
            } else {
                if (txtdescripcion.getText().toString().isEmpty()) {
                    resu = "Upps!!, ingrese el apellido";
                    Toast.makeText(getApplicationContext(), resu, Toast.LENGTH_LONG).show();
                } else {
                    ContentValues datos = new ContentValues();
                    datos.put(Contactos.nombres, txtnombre.getText().toString());
                    datos.put(Contactos.descripcion, txtdescripcion.getText().toString());

                    BitmapDrawable drawable = (BitmapDrawable) imagen.getDrawable();
                    Bitmap bitmap = drawable.getBitmap();

                    ByteArrayOutputStream baos = new ByteArrayOutputStream(20480);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 0 , baos);
                    byte[] blob = baos.toByteArray();

                    datos.put(Contactos.imagen, blob);
                    Long resultado = db.insert(Contactos.tablaContactos, Contactos.id, datos);

                    // Creamos un Toas para que muestre en pantalla que se ha salvado correctamente el contacto
                    Toast.makeText(getApplicationContext(), " Registro (" + resultado.toString() + ") Ingresado Correctamente :)", Toast.LENGTH_LONG).show();

                    db.close();
                    limpiar();
                }
            }
        }catch (Exception e) {
            System.out.println("Error!! Exception: " + e);
        }

    }

    private void limpiar() {
        txtnombre.setText("");
        txtdescripcion.setText("");
        imagen.setImageResource(0);
    }

    public void onclick(View view) {
        dispatchTakePictureIntent();
    }
    public void saveData(View view) {agregarContacto();}
    static final int REQUEST_IMAGE_CAPTURE = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imagen.setImageBitmap(imageBitmap);
        }
    }


}