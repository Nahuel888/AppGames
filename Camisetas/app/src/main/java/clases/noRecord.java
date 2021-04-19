package clases;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.example.ezequiel.camisetas.R;

public class noRecord extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.norompisterecord);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int ancho = dm.widthPixels;
        int alto = dm.heightPixels;

        getWindow().setLayout((int) (ancho*.5), (int) (alto*.5));

    }
}
