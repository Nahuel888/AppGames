package ar.edu.unnoba.ppc2016.tema3;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by nahuel on 15/06/16.
 */
public class NumerosRandom {

    private int min;
    private int max;
    private ArrayList lista;


    public NumerosRandom(int min, int max) {

        this.min = min;
        this.max = max;
        lista = new ArrayList();

    }

    private int numeroAleatorio(){
        return (int)(Math.random()*(max-min+1)+min);
    }

    public int generarRandomAbajo(){ //Genera numeros Random entre el 1(Incluye) y el 19 (Excluye)

        if(lista.size() < (max - min) +1){
            //Aun no se han generado todos los numeros
            int numero = numeroAleatorio();//genero un numero
            if(lista.isEmpty()){//si la lista esta vacia
                lista.add(numero);
                return numero;
            }else{//si no esta vacia
                if(lista.contains(numero)){//Si el numero que generé esta contenido en la lista
                    return generarRandomAbajo();//recursivamente lo mando a generar otra vez
                }else{//Si no esta contenido en la lista
                    lista.add(numero);
                    return numero;
                }
            }
        }else{// ya se generaron todos los numeros
            //return -1;
            lista.removeAll(lista);
            return generarRandomAbajo();
        }

    }

    public int generarRandomArriba(){

        int min = 1;
        int max = 20;

        Random r = new Random();
        int numRandom = r.nextInt(max-min+1)+min;

        return numRandom;

    }

    public int modoAvanzado(){

        int min = 1;
        int max = 100;

        Random r = new Random();
        int numRandom = r.nextInt(max-min+1)+min;

        return numRandom;

    }

}
