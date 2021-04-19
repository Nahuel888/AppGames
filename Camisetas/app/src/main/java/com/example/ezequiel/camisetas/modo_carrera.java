package com.example.ezequiel.camisetas;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import clases.FinDeTiempo;
import clases.NumerosRandom;
import clases.popIncorrecto;
import clases.rompeRecord;

public class modo_carrera extends AppCompatActivity implements View.OnTouchListener {

    private TextView tiempocrono, textrecord, recordt;
    public long tiempoacumulado;
    public long record= 60;
    public long tot=0;
    long time= 0;
    private Chronometer crono;
    int pantalla = 1;

    int score;
    int highScore;

    private TextView camisetasMessi;
    private TextView camisetasTotales;
    private TextView camisetasRonaldo;
    private TextView textView7;
    private TextView textView9;
    private TextView textView11;
    private TextView textView12;
    private LinearLayout llcantidades;

    private List<Integer> lista2;

    //Minimo y Maximo de camisetas
    private static final int min = 1;
    private static int max = 6;

    private int totalCamisetas, totalCamMessi, totatCamRonaldo;


    //Opciones
    private TextView num1,num2,num3,num4,num5,num6;



    private NumerosRandom num = new NumerosRandom(min, max);

    private ViewGroup layout, layout2;
    private EditText etMessi;
    private EditText etRonaldo;
    private EditText etCantidad;
    private CheckBox cbBasico;
    private Button ok;
    private Button inicio;
    private Button corregir;
    int m;
    int cr;
    MediaPlayer win;//variable para el sonido cuando es correcto.
    MediaPlayer lose;//variable para el sonido cuando es incorrecto.

    private boolean modoAvanzado = false;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //if (item.getItemId() == R.id.modoAvanzado) {
        //case R.id.modoAvanzado:

        switch (item.getItemId()) {

            case android.R.id.home:
                Intent intent = new Intent(this, bienvenida.class);
                startActivity(intent);
        }


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
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
            v.startDrag(null, shadowBuilder, v, 0);
            return true;
        } else return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modo_carrera);
        crono = (Chronometer)findViewById(R.id.crono);
        crono.setVisibility(View.INVISIBLE);
        tiempocrono = (TextView)findViewById(R.id.tiempocrono);
        tiempocrono.setVisibility(View.INVISIBLE);
        textrecord = (TextView)findViewById(R.id.textrecord);
        textrecord.setVisibility(View.INVISIBLE); //oculta el tiempo acumulado.
        recordt = (TextView) findViewById(R.id.recordt);
        recordt.setVisibility(View.INVISIBLE);
        llcantidades=(LinearLayout)findViewById(R.id.linearLayout2);
        llcantidades.setVisibility(View.INVISIBLE); //OCULTAMOS EL LINEAR LAYOUT CON LAS CANTIDADES DE LAS CAMISETAS
        String font_path = "font/arial.ttf";
        Typeface TF = Typeface.createFromAsset(getAssets(),font_path);

        String font_path2 = "font/Cursive_standard.ttf";
        Typeface TF2 = Typeface.createFromAsset(getAssets(),font_path2);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        layout = (ViewGroup) findViewById(R.id.ll);
        layout2 = (ViewGroup) findViewById(R.id.ll2);
        //cbBasico = (CheckBox) findViewById(R.id.checkBox);
        //cbBasico.setTypeface(TF);
        corregir = (Button) findViewById(R.id.btncorregir);
        corregir.setTypeface(TF);
        corregir.setEnabled(false);
        inicio = (Button) findViewById(R.id.btnInicio);
        inicio.setTypeface(TF);

        win = MediaPlayer.create(this, R.raw.youwin); //Le asigno a la variable el audio de correcto.. que está en la carpeta res/raw
        lose = MediaPlayer.create(this, R.raw.youlose); //Le asigno a la variable el audiode incorrecto.. que está en la carpeta res/raw

        camisetasMessi = (TextView) findViewById(R.id.camMessi);
        camisetasMessi.setTypeface(TF2, Typeface.BOLD);
        camisetasMessi.setText("");
        camisetasRonaldo = (TextView) findViewById(R.id.camRonaldo);
        camisetasRonaldo.setTypeface(TF2, Typeface.BOLD);
        camisetasRonaldo.setText("");
        camisetasTotales = (TextView) findViewById(R.id.total);
        camisetasTotales.setTypeface(TF2, Typeface.BOLD);
        camisetasTotales.setText("");
        textView7 = (TextView) findViewById(R.id.textView7);
        textView7.setTypeface(TF, Typeface.BOLD);
        textView9 = (TextView) findViewById(R.id.textView9);
        textView9.setTypeface(TF, Typeface.BOLD);
        textView11 = (TextView) findViewById(R.id.textView11);
        textView11.setTypeface(TF, Typeface.BOLD);
        textView12 = (TextView) findViewById(R.id.textView12);
        textView12.setTypeface(TF,Typeface.BOLD);


        num1 = (TextView) findViewById(R.id.n1);
        num1.setTypeface(TF2, Typeface.BOLD);
        num2 = (TextView) findViewById(R.id.n2);
        num2.setTypeface(TF2, Typeface.BOLD);
        num3 = (TextView) findViewById(R.id.n3);
        num3.setTypeface(TF2, Typeface.BOLD);
        num4 = (TextView) findViewById(R.id.n4);
        num4.setTypeface(TF2, Typeface.BOLD);
        num5 = (TextView) findViewById(R.id.n5);
        num5.setTypeface(TF2, Typeface.BOLD);
        num6 = (TextView) findViewById(R.id.n6);
        num6.setTypeface(TF2, Typeface.BOLD);

        tiempocrono.setVisibility(View.INVISIBLE);

        //crono = (Chronometer) findViewById(R.id.crono);

        num1.setOnTouchListener(this);
        num2.setOnTouchListener(this);
        num3.setOnTouchListener(this);
        num4.setOnTouchListener(this);
        num5.setOnTouchListener(this);
        num6.setOnTouchListener(this);

        inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comenzar();
            }
        });

        corregir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //crono.stop();
                //time = crono.getBase()- SystemClock.elapsedRealtime();
                //tot = (time/1000)*-1;
                validarCampos();
                habilitarCantidadesCamisetas();
                //validarResultado();
            }
        });

        recordt.setText("El record es de: " + record + " segs.");

    }

    public void comenzar(){
        layout.removeAllViews();
        layout2.removeAllViews();
        llcantidades.setVisibility(View.VISIBLE);
        crono.setVisibility(View.VISIBLE);
        crono.setBase(SystemClock.elapsedRealtime());
        corregir.setEnabled(true);
        inicio.setEnabled(false);
        recordt.setText("El record es de: " + record + " segs.");
        camisetasRonaldo.setText("");
        camisetasRonaldo.setText("");
        camisetasTotales.setText("");
        //if (!cbBasico.isChecked()) {


            if (modoAvanzado) {
                // textrecord.setText(String.valueOf(record));
                cargarAvanzado();
                setearRandomATextField();
                crono.start();
            } else {
                cargarCamisetas();
                setearRandomATextField();
                crono.start();
            }

    }

    public void validaRecord(){
        if (tiempoacumulado < record){
            record = tiempoacumulado;
            Intent i = new Intent(modo_carrera.this, rompeRecord.class);
            startActivity(i);
            //pop up record nuevo
            //tiempocrono.setText("¡¡FELICITACIONES!! ¡¡ROMPISTE EL RECORD!!");
        }
    }

    public void validarCampos(){
        if((camisetasMessi.getText().toString() == "") ||  (camisetasRonaldo.getText().toString() == "") ||(camisetasTotales.getText().toString() == ""))  {
            Intent i = new Intent(modo_carrera.this, FinDeTiempo.class);
            startActivity(i);
            //tiempo.setText(" ");
            inicio.setEnabled(false);
        } else  {
            validarResultado();

        }
    }

    public void desabilitarCantidadesCamisetas(){

        num1.setEnabled(false);
        num2.setEnabled(false);
        num3.setEnabled(false);
        num4.setEnabled(false);
        num5.setEnabled(false);
        num6.setEnabled(false);
    }

    public void habilitarCantidadesCamisetas(){

        num1.setEnabled(true);
        num2.setEnabled(true);
        num3.setEnabled(true);
        num4.setEnabled(true);
        num5.setEnabled(true);
        num6.setEnabled(true);
    }



    public void validarResultado(){
        //int pantalla = 1;
        String messi = camisetasMessi.getText().toString();
        int rm= Integer.parseInt(messi);
        String ronaldo= camisetasRonaldo.getText().toString();
        int rr= Integer.parseInt(ronaldo);
        recordt.setText("El record es de: " + record + " segs.");
        if ((rm == m ) && (rr == cr)){
            win.start();//Activa el sonido si es correcto.
            crono.stop();
            time = crono.getBase()- SystemClock.elapsedRealtime();
            tot = (time/1000)*-1;
            tiempoacumulado += tot;
            System.out.print(pantalla);
            if ( pantalla == 3){
                tiempocrono.setVisibility(View.VISIBLE);
                corregir.setEnabled(false);
                inicio.setEnabled(true);
                validaRecord();

            }else {
                pantalla += 1;
                max+=2;
                //cbBasico.setChecked(true);
                comenzar();
                String msj3 = "Tiempo acumulado: ";
                textrecord.setText(msj3 + tiempoacumulado + " segundos");



            }
        }else{
            //tiempocrono.setText("¡Lo siento, no has roto el record!");
            crono.stop();
            startActivity(new Intent(modo_carrera.this, popIncorrecto.class));//Esto iria donde verificamos si es incorrecto.
            lose.start();//Activa el sonido si es incorrecto.
            desabilitarCantidadesCamisetas();

            inicio.setEnabled(true);
            corregir.setEnabled(false);
            tiempoacumulado = 0;
        }
    }



    public void PopUp(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Mensaje de prueba")
                .setTitle("TITULO DE PRUEBA")
                .setCancelable(false)
                .setNeutralButton("Aceptar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }



                        });
        AlertDialog alert = builder.create();
        alert.show();
    }



    public void cargarCamisetas(){
        Random r = new Random();
        int i = (r.nextInt(max) + 1);

        m = max - i;
        cr = max - m;

        System.out.println(m);
        System.out.println(cr);

        int j = 0;
        int k = 0;

    /*    for ( j=0; j < 5; j++){
            ImageView camiseta = new ImageView(this);
            camiseta.setImageResource(R.drawable.messi);
            camiseta.setLayoutParams(new android.view.ViewGroup.LayoutParams(200, 200));
            layout.addView(camiseta);
        }
        for ( k=0; k < 5; k++){
            ImageView camiseta = new ImageView(this);
            camiseta.setImageResource(R.drawable.ronaldo);
            camiseta.setLayoutParams(new android.view.ViewGroup.LayoutParams(200, 200));
            layout2.addView(camiseta);
        }*/

        while (j < m){
            ImageView camiseta = new ImageView(this);
            camiseta.setImageResource(R.drawable.messi);
            camiseta.setLayoutParams(new android.view.ViewGroup.LayoutParams(100, 100));
            layout.addView(camiseta);
            ++j;
        }
        while ( k < cr){
            ImageView camiseta = new ImageView(this);
            camiseta.setImageResource(R.drawable.ronaldo);
            camiseta.setLayoutParams(new android.view.ViewGroup.LayoutParams(100, 100));
            layout2.addView(camiseta);
            ++k;
        }

    }
    public void cargarAvanzado(){
        m = 0;
        cr = 0;
        System.out.println(m);
        System.out.println(cr);
        int op;
        for ( int i=0; i < max; i++){
            Random r = new Random();
            op = (r.nextInt(2) + 0);

            if (op == 1){
                ImageView camiseta = new ImageView(this);
                camiseta.setImageResource(R.drawable.messi);
                camiseta.setLayoutParams(new android.view.ViewGroup.LayoutParams(100, 100));
                //camiseta.setBackgroundResource(R.color.trans);
                int azar = (r.nextInt(2));
                if (azar == 1){
                    layout.addView(camiseta);
                    m++;
                }else{
                    layout2.addView(camiseta);
                    m++;
                }
                // m++;
            }else {
                ImageView camiseta = new ImageView(this);
                camiseta.setImageResource(R.drawable.ronaldo);
                camiseta.setLayoutParams(new android.view.ViewGroup.LayoutParams(130, 150));
                int azar = r.nextInt(2);
                if (azar == 1){
                    layout.addView(camiseta);
                    cr++;
                }else{
                    layout2.addView(camiseta);
                    cr++;
                }
                //cr++;
            }
        }



    }

    public void setearRandomATextField() {
        totalCamisetas = max;
        totalCamMessi = m;
        totatCamRonaldo = cr;

        //ant = actual - 1;
        //post = actual + 1;


        ArrayList<Integer> lista = new ArrayList<Integer>();
        for (int i = min; i < max; i++) {
            lista.add(i);

        }
        lista.remove(Integer.valueOf(totalCamisetas));
        lista.remove(Integer.valueOf(totalCamMessi));
        lista.remove(Integer.valueOf(totatCamRonaldo));
        Collections.shuffle(lista);
        //List<Integer> lista2 = lista.subList(0,4);
        lista2 = lista.subList(0, 3);
        lista2.add(Integer.valueOf(totalCamisetas));
        lista2.add(Integer.valueOf(totalCamMessi));
        lista2.add(Integer.valueOf(totatCamRonaldo));
        Collections.shuffle(lista2);


        //Seteamos con valor Random del numero que hay que sacar el anterior y posterior.
        //numeroRandom.setText(String.valueOf(actual));

        //Generamos el Random de las 6 opciones y la seteamos.
        num1.setText(String.valueOf(lista2.get(0)));
        num2.setText(String.valueOf(lista2.get(1)));
        num3.setText(String.valueOf(lista2.get(2)));
        num4.setText(String.valueOf(lista2.get(3)));
        num5.setText(String.valueOf(lista2.get(4)));
        num6.setText(String.valueOf(lista2.get(5)));

        //Seteamos los numeros a la posicion actual cuando se posicionan en el anterior y posterior.
        num1.setVisibility(TextView.VISIBLE);
        num2.setVisibility(TextView.VISIBLE);
        num3.setVisibility(TextView.VISIBLE);
        num4.setVisibility(TextView.VISIBLE);
        num5.setVisibility(TextView.VISIBLE);
        num6.setVisibility(TextView.VISIBLE);

        camisetasTotales.setText("");
        camisetasMessi.setText("");
        camisetasRonaldo.setText("");

        camisetasTotales();
        camisetasDeMessi();
        camisetasDeRonaldo();

    }

    public void camisetasTotales(){ //Se realiza el arrastrar y soltar del valor correspondiente al TexView correspondiente.
        camisetasTotales.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {
                if (dragEvent.getAction() == DragEvent.ACTION_DROP) {

                    TextView dropped = (TextView) dragEvent.getLocalState();
                    TextView dropTarget = (TextView) view;
                    String text = dropTarget.getText().toString();
                    dropped.setVisibility(View.INVISIBLE);
                    dropTarget.setText(dropped.getText());

                    /*if (dropped.getText().toString().equals(String.valueOf(totalCamisetas)) && (view.getId() == R.id.total)){

                        dropped.setVisibility(View.INVISIBLE);
                        dropTarget.setText(dropped.getText());

                    } else {
                        Toast.makeText(MainActivity.this, "No es el número CORRECTO", Toast.LENGTH_SHORT).show();
                    }*/

                }
                return true;
            }

        });

    }

    public void camisetasDeMessi(){ //Se realiza el arrastrar y soltar del valor correspondiente al TexView correspondiente.
        camisetasMessi.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {
                if (dragEvent.getAction() == DragEvent.ACTION_DROP) {

                    TextView dropped = (TextView) dragEvent.getLocalState();
                    TextView dropTarget = (TextView) view;
                    String text = dropTarget.getText().toString();
                    dropped.setVisibility(View.INVISIBLE);
                    dropTarget.setText(dropped.getText());

                    /*if (dropped.getText().toString().equals(String.valueOf(totalCamMessi)) && (view.getId() == R.id.camMessi)){

                        dropped.setVisibility(View.INVISIBLE);
                        dropTarget.setText(dropped.getText());

                    } else {
                        Toast.makeText(MainActivity.this, "No es el número CORRECTO", Toast.LENGTH_SHORT).show();
                    }*/

                }
                return true;
            }

        });

    }

    public void camisetasDeRonaldo(){ //Se realiza el arrastrar y soltar del valor correspondiente al TexView correspondiente.
        camisetasRonaldo.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {
                if (dragEvent.getAction() == DragEvent.ACTION_DROP) {

                    TextView dropped = (TextView) dragEvent.getLocalState();
                    TextView dropTarget = (TextView) view;
                    String text = dropTarget.getText().toString();
                    dropped.setVisibility(View.INVISIBLE);
                    dropTarget.setText(dropped.getText());

                    /*if (dropped.getText().toString().equals(String.valueOf(totatCamRonaldo)) && (view.getId() == R.id.camRonaldo)) {

                        dropped.setVisibility(View.INVISIBLE);
                        dropTarget.setText(dropped.getText());

                    } else {
                        Toast.makeText(MainActivity.this, "No es el número CORRECTO", Toast.LENGTH_SHORT).show();
                    }*/

                }
                return true;

            }


        });

    }
}
