package it.edu.graficicalcolo;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.data.ScatterDataSet;

public class AreaActivity extends AppCompatActivity {

    private CombinedChart combinedChart;
    private final AreaFunction areaFunction = new AreaFunction();
    private EditText editA;
    private EditText editB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area);
        combinedChart = findViewById(R.id.combined_chart);
        editA = findViewById(R.id.editA);
        editB = findViewById(R.id.editB);

    }
    public void sendValueCalc(View view) {
        drawFunction();
    }

    public void drawFunction() {
        float area = areaFunction.areaCalc(Float.parseFloat(String.valueOf(editA.getText())),Float.parseFloat(String.valueOf(editB.getText())));
        LineDataSet lineDataSet = new LineDataSet (areaFunction.getLineEntries() , "Function");
        LineData lineData = new LineData(lineDataSet);
        ScatterDataSet scatterDataSet = new ScatterDataSet (areaFunction.getScatterEntries(), "Point");
        ScatterData scatterData = new ScatterData(scatterDataSet);
        lineDataSet.setDrawCircles(false);
        scatterDataSet.setColor(Color.RED);
        scatterDataSet.setScatterShapeSize(0.5f);
        lineDataSet.setLineWidth(2f);
        CombinedData data=new CombinedData();
        data.setData(lineData);
        data.setData(scatterData);
        combinedChart.setData(data);
        combinedChart.invalidate();
        System.out.println(area);
    }
}