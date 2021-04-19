package com.example.david.piedrapapeltijera;

        import android.content.Intent;
        import android.content.IntentSender;
        import android.media.MediaPlayer;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageButton;
        import android.widget.ImageView;
        import android.widget.TextView;

        import org.w3c.dom.Text;

        import java.util.Random;


public class MainActivity extends AppCompatActivity {

    private ImageView piedra,papel,tijera;
    private Button jugar, reset;
    private ImageView maquina;
    private int opcionPC = 0;
    private String opUsuario, opPC ="";
    private TextView textpc, resultado;
    MediaPlayer win;//variable para el sonido cuando es correcto.
    MediaPlayer lose;//variable para el sonido cuando es incorrecto.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ImageButton piedra = (ImageButton) findViewById(R.id.piedra);
        final ImageButton papel = (ImageButton) findViewById(R.id.papel);
        final ImageButton tijera = (ImageButton) findViewById(R.id.tijera);
        final Button jugar = (Button) findViewById(R.id.jugar);
        final Button reset = (Button) findViewById(R.id.reset);
        final TextView resultado = (TextView) findViewById(R.id.resultado);
        final TextView textpc = (TextView) findViewById(R.id.textpc);
        final ImageView maquina = (ImageView) findViewById(R.id.maquina);

        win = MediaPlayer.create(this, R.raw.youwin); //Le asigno a la variable el audio de correcto.. que está en la carpeta res/raw
        lose = MediaPlayer.create(this, R.raw.lose); //Le asigno a la variable el audiode incorrecto.. que está en la carpeta res/raw

        piedra.setVisibility(View.INVISIBLE);
        papel.setVisibility(View.INVISIBLE);
        tijera.setVisibility(View.INVISIBLE);

        reset.setEnabled(false);

        jugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                piedra.setVisibility(View.VISIBLE);
                papel.setVisibility(View.VISIBLE);
                tijera.setVisibility(View.VISIBLE);
                resultado.setText("Esperando elección...");
                jugar.setVisibility(View.INVISIBLE);

            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                piedra.setVisibility(View.VISIBLE);
                papel.setVisibility(View.VISIBLE);
                tijera.setVisibility(View.VISIBLE);
                piedra.setEnabled(true);
                papel.setEnabled(true);
                tijera.setEnabled(true);
                resultado.setText("Esperando elección...");
                textpc.setText("...");
                //maquina.setVisibility(View.GONE);
                maquina.setImageResource(0);
                /*finish();
                startActivity(getIntent());*/
            }
        });

        piedra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                papel.setVisibility(View.INVISIBLE);
                tijera.setVisibility(View.INVISIBLE);
                piedra.setEnabled(false);
                reset.setEnabled(true);
                jugar.setEnabled(false);
                resultado.setText("Elegiste Piedra...");
                opUsuario = "Piedra";
                Random rand = new Random();
                opcionPC = rand.nextInt(3);
                if (opcionPC == 0){
                    opPC = "Piedra";
                    textpc.setText("Eligio Piedra...");
                    maquina.setImageResource(R.drawable.piedra);
                    maquina.setVisibility(View.VISIBLE);


                }else{
                    if (opcionPC == 1){
                        opPC = "Papel";
                        textpc.setText("Eligio Papel...");
                        maquina.setImageResource(R.drawable.papel);
                        maquina.setVisibility(View.VISIBLE);

                    }else{
                        opPC = "Tijera";
                        textpc.setText("Eligio Tijera...");
                        maquina.setImageResource(R.drawable.tijera);
                        maquina.setVisibility(View.VISIBLE);

                    }
                }

                if (opPC == "Piedra"){
                    if (opUsuario == "Piedra")
                        resultado.setText("Empate...");
                    else if (opUsuario == "Papel") {
                        resultado.setText("¡¡¡Ganaste!!!");
                        win.start();
                    }else if (opUsuario == "Tijera"){
                        resultado.setText("Perdiste...");
                        lose.start();
                    }
                }
                else if (opPC == "Papel"){
                    if (opUsuario == "Piedra") {
                        resultado.setText("Perdiste...");
                        lose.start();
                    }else if (opUsuario == "Papel") {
                        resultado.setText("Empate...");
                    }else if (opUsuario == "Tijera") {
                        resultado.setText("¡¡¡Ganaste!!!");
                        win.start();
                    }
                }
                else if (opPC == "Tijera"){
                    if (opUsuario == "Piedra") {
                        resultado.setText("¡¡¡Ganaste!!!");
                        win.start();
                    }else if (opUsuario == "Papel") {
                        resultado.setText("Perdiste...");
                        lose.start();
                    }else if (opUsuario == "Tijera") {
                        resultado.setText("Empate...");
                    }
                }
            }
        });

        papel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                piedra.setVisibility(View.INVISIBLE);
                tijera.setVisibility(View.INVISIBLE);
                papel.setEnabled(false);
                reset.setEnabled(true);
                jugar.setEnabled(false);
                resultado.setText("Elegiste Papel...");
                opUsuario = "Papel";
                Random rand = new Random();
                opcionPC = rand.nextInt(3);
                if (opcionPC == 0){
                    opPC = "Piedra";
                    textpc.setText("Eligio Piedra...");
                    maquina.setImageResource(R.drawable.piedra);

                }else{
                    if (opcionPC == 1){
                        opPC = "Papel";
                        textpc.setText("Eligio Papel...");
                        maquina.setImageResource(R.drawable.papel);
                    }else{
                        opPC = "Tijera";
                        textpc.setText("Eligio Tijera...");
                        maquina.setImageResource(R.drawable.tijera);
                    }
                }

                if (opPC == "Piedra"){
                    if (opUsuario == "Piedra") {
                        resultado.setText("Empate...");
                    }else if (opUsuario == "Papel") {
                        resultado.setText("¡¡¡Ganaste!!!");
                        win.start();
                    }else if (opUsuario == "Tijera") {
                        resultado.setText("Perdiste...");
                        lose.start();
                    }
                }
                else if (opPC == "Papel"){
                    if (opUsuario == "Piedra") {
                        resultado.setText("Perdiste...");
                        lose.start();
                    }else if (opUsuario == "Papel") {
                        resultado.setText("Empate...");
                    }else if (opUsuario == "Tijera") {
                        resultado.setText("¡¡¡Ganaste!!!");
                        win.start();
                    }
                }
                else if (opPC == "Tijera"){
                    if (opUsuario == "Piedra") {
                        resultado.setText("¡¡¡Ganaste!!!");
                        win.start();
                    }else if (opUsuario == "Papel") {
                        resultado.setText("Perdiste...");
                        lose.start();
                    }else if (opUsuario == "Tijera") {
                        resultado.setText("Empate...");
                    }
                }
            }
        });

        tijera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tijera.setEnabled(false);
                papel.setVisibility(View.INVISIBLE);
                piedra.setVisibility(View.INVISIBLE);
                reset.setEnabled(true);
                jugar.setEnabled(false);
                resultado.setText("Elegiste Tijera...");
                opUsuario = "Tijera";
                Random rand = new Random();
                opcionPC = rand.nextInt(3);
                if (opcionPC == 0){
                    opPC = "Piedra";
                    textpc.setText("Eligio Piedra...");
                    maquina.setImageResource(R.drawable.piedra);

                }else{
                    if (opcionPC == 1){
                        opPC = "Papel";
                        textpc.setText("Eligio Papel...");
                        maquina.setImageResource(R.drawable.papel);
                    }else{
                        opPC = "Tijera";
                        textpc.setText("Eligio Tijera...");
                        maquina.setImageResource(R.drawable.tijera);
                    }
                }

                if (opPC == "Piedra"){
                    if (opUsuario == "Piedra") {
                        resultado.setText("Empate...");
                    }else if (opUsuario == "Papel") {
                        resultado.setText("¡¡¡Ganaste!!!");
                        win.start();
                    }else if (opUsuario == "Tijera") {
                        resultado.setText("Perdiste...");
                        lose.start();
                    }
                }
                else if (opPC == "Papel"){
                    if (opUsuario == "Piedra") {
                        resultado.setText("Perdiste...");
                        lose.start();
                    }else if (opUsuario == "Papel") {
                        resultado.setText("Empate...");
                    }else if (opUsuario == "Tijera") {
                        resultado.setText("¡¡¡Ganaste!!!");
                        win.start();
                    }
                }
                else if (opPC == "Tijera"){
                    if (opUsuario == "Piedra") {
                        resultado.setText("¡¡¡Ganaste!!!");
                        win.start();
                    }else if (opUsuario == "Papel") {
                        resultado.setText("Perdiste...");
                        lose.start();
                    }else if (opUsuario == "Tijera") {
                        resultado.setText("Empate...");
                    }
                }
            }
        });
    }

   /* public void jugadapc(){
        Random rand = new Random();
        opcionPC = rand.nextInt(3);
        if (opcionPC == 0){
            opPC = "Piedra";
            textpc.setText("Eligio Piedra...");
            maquina.setImageResource(R.drawable.piedra);


        }else{
            if (opcionPC == 1){
                opPC = "Papel";
                textpc.setText("Eligio Papel...");
                maquina.setImageResource(R.drawable.papel);

            }else{
                opPC = "Tijera";
                textpc.setText("Eligio Tijera...");
                maquina.setImageResource(R.drawable.tijera);

            }
        }
        chequear();
    }

    public void chequear(){
        if (opPC == "Piedra"){
            if (opUsuario == "Piedra")
                resultado.setText("Empate...");
            else if (opUsuario == "Papel")
                resultado.setText("Ganaste...");
            else if (opUsuario == "Tijera")
                resultado.setText("Perdiste...");
        }
        else if (opPC == "Papel"){
            if (opUsuario == "Piedra")
                resultado.setText("Perdiste...");
            else if (opUsuario == "Papel")
                resultado.setText("Empate...");
            else if (opUsuario == "Tijera")
                resultado.setText("Ganaste...");
        }
        else if (opPC == "Tijera"){
            if (opUsuario == "Piedra")
                resultado.setText("Ganaste...");
            else if (opUsuario == "Papel")
                resultado.setText("Perdiste...");
            else if (opUsuario == "Tijera")
                resultado.setText("Empate...");
        }

    }*/



}
