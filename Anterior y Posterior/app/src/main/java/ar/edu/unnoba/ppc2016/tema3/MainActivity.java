package ar.edu.unnoba.ppc2016.tema3;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;


import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import clases.*;



public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    private Button botonNuevo, botonCorregir;
    private int numRandom;

    private List<Integer> lista2;

    private boolean modoAvanzado = false;

    MediaPlayer win;//variable para el sonido cuando es correcto.
    MediaPlayer lose;//variable para el sonido cuando es incorrecto.

    //Numero Arriba
    private TextView numeroRandom;

    //Variables donde completar
    private TextView anterior, posterior;

    //Opciones
    private TextView n1;
    private TextView n2;
    private TextView n3;
    private TextView n4;
    private TextView n5;
    private TextView n6;

    private NumerosRandom num = new NumerosRandom(1, 19);

    private int actual, ant, post;

    private int contAnt, contPost;




    //When text1 or text2 or text3 gets clicked or touched then this method will be called
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
            v.startDrag(null, shadowBuilder, v, 0);
            return true;
        } else return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //METODO QUE DIBUJA EL MODO AVANZADO EN EL MENU BAR
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) { //MANEJAMOS EL MODO AVANZADO.



        if(item.isChecked()){
            // If item already checked then unchecked it
            item.setChecked(false);
            modoAvanzado = false;
            //cargarAvanzado();
            //setearRandomATextField();

        }else{
            // If item is unchecked then checked it
            item.setChecked(true);
            modoAvanzado = true;
            //cargarCamisetas();
            //setearRandomATextField();
        }
        return true;
        //}

        //return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        win = MediaPlayer.create(this, R.raw.youwin); //Le asigno a la variable el audio de correcto.. que está en la carpeta res/raw
        lose = MediaPlayer.create(this, R.raw.youlose); //Le asigno a la variable el audiode incorrecto.. que está en la carpeta res/raw

        botonNuevo = (Button) findViewById(R.id.nuevo);
        botonNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (modoAvanzado) {
                   modoAvanzado();
                    //setearRandomATextField();
                } else {
                    modoBasico();
                    //setearRandomATextField();
                }

            }
        });

        botonCorregir = (Button)findViewById(R.id.corregir);
        botonCorregir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarCampos();
            }
        });

        numeroRandom = (TextView) findViewById(R.id.numero);

        n1 = (TextView) findViewById(R.id.num1);
        n2 = (TextView) findViewById(R.id.num2);
        n3 = (TextView) findViewById(R.id.num3);
        n4 = (TextView) findViewById(R.id.num4);
        n5 = (TextView) findViewById(R.id.num5);
        n6 = (TextView) findViewById(R.id.num6);

        anterior = (TextView) findViewById(R.id.anterior);

        posterior = (TextView) findViewById(R.id.posterior);

        //setearRandomATextField();
        modoBasico();

        n1.setOnTouchListener(this);
        n2.setOnTouchListener(this);
        n3.setOnTouchListener(this);
        n4.setOnTouchListener(this);
        n5.setOnTouchListener(this);
        n6.setOnTouchListener(this);

        anterior();
        posterior();

    }

    public void validarCampos(){

        if((anterior.getText().toString() == "") ||  (posterior.getText().toString() == "")){
            Toast toast = new Toast(getApplicationContext());
            Toast.makeText(this, "Complete los campos", Toast.LENGTH_SHORT).show();

            //Intent i = new Intent(MainActivity.this, completarCampos.class);
            //startActivity(i);

        } else  {
            validarResultado();
        }


    }


    public void validarResultado(){

        String numant = anterior.getText().toString(); //Guardo el valor anterior en una variable.
        String numpost = posterior.getText().toString(); //Guardo el valor posterior en una variable.

        int a = Integer.parseInt(numant);
        int p = Integer.parseInt(numpost);


        if ((a==ant) && (p == post)) {

            Intent i =new Intent(MainActivity.this, popUpCorrecto.class);
            startActivity(i);//Esto iria donde verificamos si es correcto.
            win.start();
        } else{
            Intent i =new Intent(MainActivity.this, popUpIncorrecto.class);
            startActivity(i);//Esto iria donde verificamos si es incorrecto.
            lose.start();


        }



    }

    public void anterior(){

        anterior.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {
                if (dragEvent.getAction() == DragEvent.ACTION_DROP) {

                    TextView dropped = (TextView) dragEvent.getLocalState();
                    TextView dropTarget = (TextView) view;
                    String text = dropTarget.getText().toString();
                    dropped.setVisibility(View.INVISIBLE);
                    dropTarget.setText(dropped.getText());

                }
                return true;
            }

        });



    }

    public void posterior(){

        posterior.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {
                if (dragEvent.getAction() == DragEvent.ACTION_DROP) {

                    TextView dropped = (TextView) dragEvent.getLocalState();
                    TextView dropTarget = (TextView) view;
                    String text = dropTarget.getText().toString();
                    dropped.setVisibility(View.INVISIBLE);
                    dropTarget.setText(dropped.getText());

                }
                return true;
            }

        });
    }


    public void modoBasico() {
    //public setearRandomATextField()

        //int actual =  num.generarRandomArriba();
        //int ant = actual - 1;
        //int post = actual + 1;

        actual = num.generarRandomArriba();
        ant = actual - 1;
        post = actual + 1;


        ArrayList<Integer> lista = new ArrayList<Integer>();
        for (int i = 1; i < 19; i++) {
            lista.add(i);

        }
        lista.remove(Integer.valueOf(ant));
        lista.remove(Integer.valueOf(actual));
        lista.remove(Integer.valueOf(post));
        Collections.shuffle(lista);
        //List<Integer> lista2 = lista.subList(0,4);
        lista2 = lista.subList(0, 4);
        lista2.add(Integer.valueOf(ant));
        lista2.add(Integer.valueOf(post));
        Collections.shuffle(lista2);


        //Seteamos con valor Random del numero que hay que sacar el anterior y posterior.
        numeroRandom.setText(String.valueOf(actual));

        //Generamos el Random de las 6 opciones y la seteamos.
        n1.setText(String.valueOf(lista2.get(0)));
        n2.setText(String.valueOf(lista2.get(1)));
        n3.setText(String.valueOf(lista2.get(2)));
        n4.setText(String.valueOf(lista2.get(3)));
        n5.setText(String.valueOf(lista2.get(4)));
        n6.setText(String.valueOf(lista2.get(5)));

        //Seteamos los numeros a la posicion actual cuando se posicionan en el anterior y posterior.
        n1.setVisibility(TextView.VISIBLE);
        n2.setVisibility(TextView.VISIBLE);
        n3.setVisibility(TextView.VISIBLE);
        n4.setVisibility(TextView.VISIBLE);
        n5.setVisibility(TextView.VISIBLE);
        n6.setVisibility(TextView.VISIBLE);

        anterior.setText("");
        posterior.setText("");

        anterior();
        posterior();

    }

    public void modoAvanzado() {

        //int actual =  num.generarRandomArriba();
        //int ant = actual - 1;
        //int post = actual + 1;

        actual = num.modoAvanzado();
        ant = actual - 1;
        post = actual + 1;


        ArrayList<Integer> lista = new ArrayList<Integer>();
        for (int i = 1; i < 99; i++) {
            lista.add(i);

        }
        lista.remove(Integer.valueOf(ant));
        lista.remove(Integer.valueOf(actual));
        lista.remove(Integer.valueOf(post));
        Collections.shuffle(lista);
        //List<Integer> lista2 = lista.subList(0,4);
        lista2 = lista.subList(0, 4);
        lista2.add(Integer.valueOf(ant));
        lista2.add(Integer.valueOf(post));
        Collections.shuffle(lista2);


        //Seteamos con valor Random del numero que hay que sacar el anterior y posterior.
        numeroRandom.setText(String.valueOf(actual));

        //Generamos el Random de las 6 opciones y la seteamos.
        n1.setText(String.valueOf(lista2.get(0)));
        n2.setText(String.valueOf(lista2.get(1)));
        n3.setText(String.valueOf(lista2.get(2)));
        n4.setText(String.valueOf(lista2.get(3)));
        n5.setText(String.valueOf(lista2.get(4)));
        n6.setText(String.valueOf(lista2.get(5)));

        //Seteamos los numeros a la posicion actual cuando se posicionan en el anterior y posterior.
        n1.setVisibility(TextView.VISIBLE);
        n2.setVisibility(TextView.VISIBLE);
        n3.setVisibility(TextView.VISIBLE);
        n4.setVisibility(TextView.VISIBLE);
        n5.setVisibility(TextView.VISIBLE);
        n6.setVisibility(TextView.VISIBLE);

        anterior.setText("");
        posterior.setText("");


        anterior();
        posterior();
    }
/*
    public void btonNuevo() {

        setearRandomATextField();

    }
*/

}
