package it.edu.graficicalcolo;

import static it.edu.graficicalcolo.SettingsActivity.ANIMATE;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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

import it.edu.graficicalcolo.math.AreaFunction;

public class AreaActivity extends AppCompatActivity {

    private CombinedChart combinedChart;
    private EditText editA;
    private EditText editB;
    private EditText editTextNumberTotalPoint;
    private EditText editFunction;
    private TextView textViewAreaOut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area);
        editFunction = findViewById(R.id.editTextTextExspression);
        combinedChart = findViewById(R.id.combined_chart);
        editA = findViewById(R.id.editA);
        editB = findViewById(R.id.editB);
        textViewAreaOut = findViewById(R.id.textViewAreaOut);
        editTextNumberTotalPoint = findViewById(R.id.editTextNumberTotalPoint);
    }

    public void sendValueCalc(View view) {
        drawFunction();
    }

    private final AreaFunction areaFunction = new AreaFunction();
    public void drawFunction() {
        try {

            areaFunction.TOTAL_POINT = Integer.parseInt(String.valueOf(editTextNumberTotalPoint.getText()));
            float a = Float.parseFloat(String.valueOf(editA.getText()));
            float b = Float.parseFloat(String.valueOf(editB.getText()));
            float area = a>b?  areaFunction.areaCalc(b, a, String.valueOf(editFunction.getText())) : areaFunction.areaCalc(a, b,String.valueOf(editFunction.getText()));
            LineDataSet lineDataSet = new LineDataSet(areaFunction.getLineEntries(), "f(" + areaFunction.letter + ")" );
            LineData lineData = new LineData(lineDataSet);
            ScatterDataSet scatterDataSet = new ScatterDataSet(areaFunction.getScatterEntries(), getResources().getString(R.string.text_point));
            ScatterData scatterData = new ScatterData(scatterDataSet);
            lineDataSet.setDrawCircles(false);
            lineDataSet.setDrawValues(false);
            scatterDataSet.setColor(Color.RED);
            scatterDataSet.setDrawValues(false);
            scatterDataSet.setScatterShapeSize(0.8f);
            lineDataSet.setLineWidth(2f);
            CombinedData data = new CombinedData();
            data.setData(lineData);
            data.setData(scatterData);
            combinedChart.getDescription().setText(getResources().getString(R.string.text_subtendedArea));
            combinedChart.setData(data);
            combinedChart.invalidate();
            textViewAreaOut.setText(String.valueOf(area));
            if (ANIMATE){
                combinedChart.animateX(10000);
                combinedChart.animateY(10000);
            }

        } catch (Exception e) {
            Log.e("AreaActivity","Exception trying to set up chart");
            e.printStackTrace();
        }
    }
}