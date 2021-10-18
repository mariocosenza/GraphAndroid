package it.edu.graficicalcolo;

import static it.edu.graficicalcolo.CalcoloApprossimato.PI_AVG;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.ScatterChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.data.ScatterDataSet;

import java.text.NumberFormat;
import java.util.Locale;


public class ChartActivity extends AppCompatActivity {
    private EditText editNumber;
    private TextView textOutPI;
    private ScatterChart scatterChart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        editNumber = findViewById(R.id.editNumber);
        textOutPI = findViewById(R.id.textOutPI);
        scatterChart = findViewById(R.id.scatterChart);
    }


    public void sendValue(View view) {
        CalcoloApprossimato ca = new CalcoloApprossimato();
        ca.findPI(getSpnner());
        textOutPI.setText(NumberFormat.getNumberInstance(Locale.getDefault()).format(PI_AVG));
        scatterChart.invalidate();
        ScatterDataSet scatterDataSet = new ScatterDataSet(ca.getScatterEntries(), "Point");
        ScatterData scatterData = new ScatterData(scatterDataSet);
        scatterDataSet.setScatterShapeSize((float) 10);
        scatterChart.setScaleMinima(1f, 1f);
        YAxis yAxis = scatterChart.getAxisLeft();
        yAxis.setAxisMinimum(0f);
        YAxis YAxisRight = scatterChart.getAxisRight();
        YAxisRight.setAxisMinimum(0f);
        scatterChart.getXAxis().setAxisMinimum(0f);
        scatterChart.getXAxis().setAxisMaximum(1f);
        scatterChart.setData(scatterData);
        scatterChart.getDescription().setText(getResources().getString(R.string.text_randomPoint));

    }

    int getSpnner() throws NullPointerException {
        return Integer.parseInt(editNumber.getText().toString());
    }

}