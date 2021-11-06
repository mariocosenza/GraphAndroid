package it.edu.graficicalcolo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

/**
 * Grafici Java
 * Copyright (C) 2021  Mario Cosenza
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

public class MainActivity extends AppCompatActivity {
    Spinner app_spinner;
    AdView myadview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        app_spinner = findViewById(R.id.app_spinnerNumeric);

        MobileAds.initialize(this, initializationStatus -> {
        });
        myadview = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        myadview.loadAd(adRequest);
    }


    public void selectActivity(View view) {
        if (app_spinner.getSelectedItem().toString().equals("Root") || app_spinner.getSelectedItem().toString().equals("Radice")) {
            startActivity(new Intent(getApplicationContext(), RootChart.class));
        } else if (app_spinner.getSelectedItem().toString().equals("Find PI") || app_spinner.getSelectedItem().toString().equals("Trova PI")){
            startActivity(new Intent(getApplicationContext(), ChartActivity.class));
        } else if (app_spinner.getSelectedItem().toString().equals("Area Chart") || app_spinner.getSelectedItem().toString().equals("Area Grafico")){
            startActivity(new Intent(getApplicationContext(), AreaActivity.class));
        } else {
            startActivity(new Intent(getApplicationContext(), NumericActivity.class));
        }
    }

    public void onClickSettings(View view) {
        startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
    }


}
