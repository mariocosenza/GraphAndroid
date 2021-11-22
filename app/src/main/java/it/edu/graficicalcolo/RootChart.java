package it.edu.graficicalcolo;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

import it.edu.graficicalcolo.math.ProbabilisticCalc;

public class RootChart extends AppCompatActivity {
    private final ArrayList<Entry> lineEntry = new ArrayList<>();
    private EditText editRooting;
    private TextView textViewRoot;
    private LineChart lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root_chart);
        editRooting = findViewById(R.id.editTextRooting);
        textViewRoot = findViewById(R.id.textViewRootValue);
        lineChart = findViewById(R.id.lineChart);

    }

    public void onButtonClick(View v) {
        textViewRoot.setText(String.valueOf(new ProbabilisticCalc().sqrtAproximated(Integer.parseInt(editRooting.getText().toString()))));
        rootPoint();
    }

    private void rootPoint() {
        for (double x = 0; x < 100; x += 0.1) {
            lineEntry.add(new Entry((float) x, (float) Math.sqrt(x)));
        }
        lineChart.invalidate();
        LineDataSet lineDataSet = new LineDataSet(lineEntry, "Point");
        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
        lineDataSet.setDrawCircles(false);
        lineDataSet.setLineWidth(5);
        lineChart.getDescription().setText(getResources().getString(R.string.text_rootValue));


    }
}