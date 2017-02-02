package com.example.administrador.formulariov1;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import static android.app.Activity.RESULT_OK;
import static com.example.administrador.formulariov1.R.id.imageButton;
import static com.example.administrador.formulariov1.R.id.txtColor;

public class MainActivity extends AppCompatActivity {

    //variable de objetos
    private Spinner sp1;
    private Spinner sp2;
    private ImageButton btnImg;
    private Button btnColor;
    private TextView txtColor;

    //variables
    private String[] spGenero = {"Selecciona...", "Masculino", "Femenino", "Otros"};
    private static int RESULT_LOAD_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //txtview
        txtColor=(TextView)findViewById(R.id.txtColor);

        //spinner
        sp1 = (Spinner) findViewById(R.id.spinner);
        sp2 = (Spinner) findViewById(R.id.spinner2);
        sp1.setPrompt("Género");
        //cargarSpinner(spGenero);

        //ImagenButton
        btnImg = (ImageButton) findViewById(R.id.imageButton);
        btnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galeria = new Intent(Intent.ACTION_GET_CONTENT);
                galeria.setType("image/*");
                startActivityForResult(galeria, RESULT_LOAD_IMAGE);
            }
        });

        //butoncolor
        btnColor=(Button)findViewById(R.id.button2);
        btnColor.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                sp2.performClick();
                txtColor.setText(sp2.getSelectedItem().toString());
            }
        });
    }

    public void cargarSpinner(String[] datos) {
        ArrayAdapter spadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spGenero);
        sp1.setAdapter(spadapter);
    }


    @Override
    protected void onActivityResult(int requesCode, int resultcode, Intent data) {
        super.onActivityResult(requesCode, resultcode, data);

        if (requesCode == RESULT_LOAD_IMAGE && resultcode == RESULT_OK) {
            Uri imgUri = data.getData();
            btnImg.setImageURI(imgUri);
        }
    }


}