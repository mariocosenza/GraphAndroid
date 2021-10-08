package it.edu.graficicalcolo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity

public class ChartActivity_ extends AppCompatActivity {
    @ViewById
    EditText editNumber;
    @ViewById
    Button buttonCalc;
    CalcoloApprossimato ca = new CalcoloApprossimato();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
    }
    public void sendValue () {


    }

    public int getSpnner() {
        return Integer.parseInt(editNumber.getText().toString());
    }

}