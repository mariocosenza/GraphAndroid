package it.edu.graficicalcolo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
Spinner app_spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        app_spinner = findViewById(R.id.app_spinner);
    }
    public void selectActivity(View view) {
        if (app_spinner.getSelectedItem().toString().equals("Root") || app_spinner.getSelectedItem().toString().equals("Radice") ) {
            startActivity(new Intent(getApplicationContext(), RootChart.class));
        }
        else
        {
            startActivity(new Intent(getApplicationContext(), ChartActivity_.class));
        }
    }



}
