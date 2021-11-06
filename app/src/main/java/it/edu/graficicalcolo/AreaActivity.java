package it.edu.graficicalcolo;

import static it.edu.graficicalcolo.SettingsActivity.ANIMATE;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.data.ScatterDataSet;

public class AreaActivity extends AppCompatActivity {

    private CombinedChart combinedChart;
    private EditText editA;
    private EditText editB;
    private EditText editTextNumberTotalPoint;
    private TextView textViewAreaOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area);
        combinedChart = findViewById(R.id.combined_chart);
        editA = findViewById(R.id.editA);
        editB = findViewById(R.id.editB);
        textViewAreaOut = findViewById(R.id.textViewAreaOut);
        editTextNumberTotalPoint = findViewById(R.id.editTextNumberTotalPoint);
    }

    public void sendValueCalc(View view) {
        drawFunction();
    }

    public void drawFunction() {
        try {
            AreaFunction areaFunction = new AreaFunction();
            areaFunction.TOTAL_POINT = Integer.parseInt(String.valueOf(editTextNumberTotalPoint.getText()));
            float area = areaFunction.areaCalc(Float.parseFloat(String.valueOf(editA.getText())), Float.parseFloat(String.valueOf(editB.getText())));
            LineDataSet lineDataSet = new LineDataSet(areaFunction.getLineEntries(), "Function");
            LineData lineData = new LineData(lineDataSet);
            ScatterDataSet scatterDataSet = new ScatterDataSet(areaFunction.getScatterEntries(), "Point");
            ScatterData scatterData = new ScatterData(scatterDataSet);
            lineDataSet.setDrawCircles(false);
            scatterDataSet.setColor(Color.RED);
            scatterDataSet.setScatterShapeSize(0.5f);
            lineDataSet.setLineWidth(2f);
            CombinedData data = new CombinedData();
            data.setData(lineData);
            data.setData(scatterData);
            combinedChart.getDescription().setText("Subtended area");
            combinedChart.setData(data);
            combinedChart.invalidate();
            textViewAreaOut.setText(String.valueOf(area));
            if (ANIMATE){
                combinedChart.animateX(10000);
                combinedChart.animateY(10000);
            }

        } catch (Exception e) {
            textViewAreaOut.setText("Error Try Again");
            e.printStackTrace();
        }
    }
}