package it.edu.graficicalcolo;

import static android.graphics.Color.RED;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static it.edu.graficicalcolo.SettingsActivity.ANIMATE;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.data.ScatterDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

import it.edu.graficicalcolo.math.AreaFunction;

public class AreaActivity extends AppCompatActivity {

    private final AreaFunction areaFunction = new AreaFunction();
    private CombinedChart combinedChart;
    private EditText editA;
    private EditText editB;
    private EditText editTextNumberTotalPoint;
    private EditText editFunction;
    private TextView textViewAreaOut;
    private Spinner spinner;
    private String[] spinnerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area);
        spinner = findViewById(R.id.app_spinnerArea);
        editFunction = findViewById(R.id.editTextTextExpression);
        combinedChart = findViewById(R.id.combined_chart);
        editA = findViewById(R.id.editA);
        editB = findViewById(R.id.editB);
        textViewAreaOut = findViewById(R.id.textViewAreaOut);
        editTextNumberTotalPoint = findViewById(R.id.editTextNumberTotalPoint);
        spinnerText = getResources().getStringArray(R.array.area_array);
    }

    public void sendValueCalc(View view) {
        if (spinner.getSelectedItem().toString().equals(spinnerText[2])) {
            drawFunction();
        } else if (spinner.getSelectedItem().toString().equals(spinnerText[0])) {
            drawFunctionRectangle();
        } else if (spinner.getSelectedItem().toString().equals(spinnerText[1])) {
            drawFunctionTrapezoid();
        } else {
            drawFunctionSimpson();
        }
    }

    public void drawFunction() {
        try {
            areaFunction.TOTAL_POINT = Integer.parseInt(String.valueOf(editTextNumberTotalPoint.getText()));
            float a = Float.parseFloat(String.valueOf(editA.getText()));
            float b = Float.parseFloat(String.valueOf(editB.getText()));
            float area = a > b ? areaFunction.areaCalc(b, a, String.valueOf(editFunction.getText()), TRUE) : areaFunction.areaCalc(a, b, String.valueOf(editFunction.getText()), TRUE);
            LineDataSet lineDataSet = new LineDataSet(areaFunction.getLineEntries(), "f(" + areaFunction.letter + ")");
            LineData lineData = new LineData(lineDataSet);
            ScatterDataSet scatterDataSet = new ScatterDataSet(areaFunction.getScatterEntries(), getResources().getString(R.string.text_point));
            ScatterData scatterData = new ScatterData(scatterDataSet);
            lineDataSet.setDrawCircles(false);
            lineDataSet.setDrawValues(false);
            scatterDataSet.setColor(RED);
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
            if (ANIMATE) {
                combinedChart.animateX(10000);
                combinedChart.animateY(10000);
            }

        } catch (Exception e) {
            Log.e("AreaActivity", "Exception trying to set up chart");
            e.printStackTrace();
        }
    }

    public void drawFunctionRectangle() {
        try {
            areaFunction.TOTAL_RECT = Integer.parseInt(String.valueOf(editTextNumberTotalPoint.getText()));
            float a = Float.parseFloat(String.valueOf(editA.getText()));
            float b = Float.parseFloat(String.valueOf(editB.getText()));
            areaFunction.areaCalc(b, a, String.valueOf(editFunction.getText()), FALSE);
            LineDataSet lineDataSet = new LineDataSet(areaFunction.getLineEntries(), "f(" + areaFunction.letter + ")");
            lineDataSet.setDrawCircles(false);
            lineDataSet.setDrawValues(false);
            lineDataSet.setLineWidth(2f);
            LineDataSet lineDataSetRect = new LineDataSet(areaFunction.rectangleDraw(a, b), "Rectangle");
            lineDataSetRect.setDrawCircles(false);
            lineDataSetRect.setDrawValues(false);
            lineDataSetRect.setLineWidth(1f);
            lineDataSetRect.setColor(RED);
            CombinedData data = new CombinedData();
            ArrayList<ILineDataSet> lines = new ArrayList<>();
            lines.add(lineDataSet);
            lines.add(lineDataSetRect);
            LineData line = new LineData(lines);
            data.setData(line);
            combinedChart.getDescription().setText(getResources().getString(R.string.text_subtendedArea));
            combinedChart.setData(data);
            combinedChart.invalidate();
            textViewAreaOut.setText(String.valueOf(areaFunction.rectangle(a, b)));
            if (ANIMATE) {
                combinedChart.animateX(10000);
                combinedChart.animateY(10000);
            }

        } catch (Exception e) {
            Log.e("AreaActivity", "Exception trying to set up chart");
            e.printStackTrace();
        }
    }

    public void drawFunctionTrapezoid() {
        try {
            areaFunction.TOTAL_RECT = Integer.parseInt(String.valueOf(editTextNumberTotalPoint.getText()));
            float a = Float.parseFloat(String.valueOf(editA.getText()));
            float b = Float.parseFloat(String.valueOf(editB.getText()));
            areaFunction.areaCalc(b, a, String.valueOf(editFunction.getText()), FALSE);
            LineDataSet lineDataSet = new LineDataSet(areaFunction.getLineEntries(), "f(" + areaFunction.letter + ")");
            lineDataSet.setDrawCircles(false);
            lineDataSet.setDrawValues(false);
            lineDataSet.setLineWidth(2f);
            LineDataSet lineDataSetTrap = new LineDataSet(areaFunction.trapezoidDraw(a, b), "Trapezoid");
            lineDataSetTrap.setDrawCircles(false);
            lineDataSetTrap.setDrawValues(false);
            lineDataSetTrap.setLineWidth(1f);
            lineDataSetTrap.setColor(RED);
            CombinedData data = new CombinedData();
            ArrayList<ILineDataSet> lines = new ArrayList<>();
            lines.add(lineDataSet);
            lines.add(lineDataSetTrap);
            LineData line = new LineData(lines);
            data.setData(line);
            combinedChart.getDescription().setText(getResources().getString(R.string.text_subtendedArea));
            combinedChart.setData(data);
            combinedChart.invalidate();
            textViewAreaOut.setText(String.valueOf(areaFunction.trapezoid(a, b)));
            if (ANIMATE) {
                combinedChart.animateX(10000);
                combinedChart.animateY(10000);
            }

        } catch (Exception e) {
            Log.e("AreaActivity", "Exception trying to set up chart");
            e.printStackTrace();
        }
    }

    public void drawFunctionSimpson() {
        try {
            areaFunction.TOTAL_RECT = Integer.parseInt(String.valueOf(editTextNumberTotalPoint.getText()));
            float a = Float.parseFloat(String.valueOf(editA.getText()));
            float b = Float.parseFloat(String.valueOf(editB.getText()));
            areaFunction.areaCalc(b, a, String.valueOf(editFunction.getText()), FALSE);
            LineDataSet lineDataSet = new LineDataSet(areaFunction.getLineEntries(), "f(" + areaFunction.letter + ")");
            lineDataSet.setDrawCircles(false);
            lineDataSet.setDrawValues(false);
            lineDataSet.setLineWidth(2f);
            LineDataSet lineDataSetSimp = new LineDataSet(areaFunction.simpsonDraw(a, b), "Simpson");
            lineDataSetSimp.setDrawCircles(false);
            lineDataSetSimp.setDrawValues(false);
            lineDataSetSimp.setLineWidth(1f);
            lineDataSetSimp.setColor(RED);
            CombinedData data = new CombinedData();
            ArrayList<ILineDataSet> lines = new ArrayList<>();
            lines.add(lineDataSet);
            lines.add(lineDataSetSimp);
            LineData line = new LineData(lines);
            data.setData(line);
            combinedChart.getDescription().setText(getResources().getString(R.string.text_subtendedArea));
            combinedChart.setData(data);
            combinedChart.invalidate();
            textViewAreaOut.setText(String.valueOf(areaFunction.simpson(a, b)));
            if (ANIMATE) {
                combinedChart.animateX(10000);
                combinedChart.animateY(10000);
            }

        } catch (Exception e) {
            Log.e("AreaActivity", "Exception trying to set up chart");
            e.printStackTrace();
        }
    }
}