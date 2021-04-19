package clases;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import com.example.ezequiel.camisetas.R;

/**
 * Created by ezequiel on 30/06/2016.
 */
public class popIncorrecto extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popincorrecto);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int ancho = dm.widthPixels;
        int alto = dm.heightPixels;

        getWindow().setLayout((int) (ancho * .2), (int) (alto * .2));

    }
}