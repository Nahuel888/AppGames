package clases;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import ar.edu.unnoba.ppc2016.tema3.R;

import ar.edu.unnoba.ppc2016.tema3.R;

public class popUpIncorrecto extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popupincorrecto);



        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int ancho = dm.widthPixels;
        int alto = dm.heightPixels;

        getWindow().setLayout((int) (ancho * .2), (int) (alto * .2));

    }
}


