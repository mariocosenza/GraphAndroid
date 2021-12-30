package it.edu.graficicalcolo;

import static it.edu.graficicalcolo.SettingsActivity.ANIMATE;
import static it.edu.graficicalcolo.SettingsActivity.DERIVATE;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

import it.edu.graficicalcolo.math.NumericCalc;
import it.edu.graficicalcolo.math.StringToFunction;

public class NumericActivity extends AppCompatActivity {

    private final NumericCalc numericCalc = new NumericCalc();
    private final ArrayList<Entry> functionEntry = new ArrayList<>();
    private final double EPS = 0.00001;
    private EditText editTextNumberDecimalA;
    private EditText editTextNumberDecimalB;
    private EditText editTextExspression;
    private TextView textViewOutRoot;
    private String[] spinnerText;
    private Spinner appSpinnerNumeric;
    private LineChart lineChartNumeric;
    private LineDataSet lineDataSet;
    private LineDataSet lineDataSetX;
    private LineDataSet lineDataSetY;
    private LineDataSet lineDerivate;
    private float a, b;
    private float yMax;
    private float yMin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numeric);
        editTextNumberDecimalA = findViewById(R.id.editTextNumberDecimalA);
        editTextNumberDecimalB = findViewById(R.id.editTextNumberDecimalB);
        appSpinnerNumeric = findViewById(R.id.app_spinnerArea);
        textViewOutRoot = findViewById(R.id.textViewOutRoot);
        spinnerText = getResources().getStringArray(R.array.numeric_array);
        lineChartNumeric = findViewById(R.id.lineChartNumeric);
        editTextExspression = findViewById(R.id.editTextTextExspressionNumeric);
    }

    public void getNumeber() {
        try {
            a = Float.parseFloat(editTextNumberDecimalA.getText().toString());
            b = Float.parseFloat(editTextNumberDecimalB.getText().toString());
            float tmp;
            if (a > b) {
                tmp = a;
                a = b;
                b = tmp;
            }
        } catch (Exception e) {
            Log.e("NumericActivity", "getNumberA() error!");
        }
    }

    public void selectMethod(View view) {
        try {
            getNumeber();
            String function = String.valueOf(editTextExspression.getText());
            System.out.println(function);
            numericCalc.setMathExpression(function);
            findMax(a, b);
            findMin(a, b);
            setChart();
            if (appSpinnerNumeric.getSelectedItem().toString().equals(spinnerText[0])) {
                selectedBisection();
            } else if (appSpinnerNumeric.getSelectedItem().toString().equals(spinnerText[1])) {
                selectedTangent();
            } else if (appSpinnerNumeric.getSelectedItem().toString().equals(spinnerText[2])) {
                selectedRope();
            } else {
                selectedSecant();
            }
        } catch (Exception e) {
            Log.e("NumericActivity", "selectMethod() error!");
        }
    }

    public void createXline() {
        ArrayList<Entry> lineXdraw = new ArrayList<>();
        lineXdraw.add(new Entry(a - 1, 0));
        lineXdraw.add(new Entry(b + 1, 0));
        lineDataSetX = new LineDataSet(lineXdraw, "X");
        lineDataSetX.setColor(Color.GREEN);
        lineDataSetX.setLineWidth(1);
        lineDataSetX.setDrawCircles(false);
        lineDataSetX.setDrawValues(false);
    }
    public void createDerive() {
        ArrayList<Entry> functionDerivate = new ArrayList<>();
        StringToFunction derivated = new StringToFunction();
        for (float i = a-1; i<b+1; i += 0.1){
            functionDerivate.add(new Entry(i, derivated.getDerivativeExpression(String.valueOf(editTextExspression.getText()), i)));
        }
        lineDerivate = new LineDataSet(functionDerivate, "D");
        lineDerivate.setColor(Color.YELLOW);
        lineDerivate.setLineWidth(2f);
        lineDerivate.setDrawCircles(false);
        lineDerivate.setDrawValues(false);
    }


    public void createYline() {
        ArrayList<Entry> lineY = new ArrayList<>();
        lineY.add(new Entry(0, (float) numericCalc.getFunction(a - 1)));
        lineY.add(new Entry(0, (float) numericCalc.getFunction(b + 1)));
        lineDataSetY = new LineDataSet(lineY, "Y");
        lineDataSetY.setColor(Color.GREEN);
        lineDataSetY.setLineWidth(1);
        lineDataSetY.setDrawCircles(false);
        lineDataSetY.setDrawValues(false);
    }

    public void setChart() {
        functionEntry.clear();
        for (float i = a - 1; i <= b + 1; i += 0.1f) {
            functionEntry.add(new Entry(i, (float) numericCalc.getFunction(i)));
        }
        lineDataSet = new LineDataSet(functionEntry, "Point");
        lineChartNumeric.invalidate();
        lineDataSet.setDrawCircles(false);
        lineDataSet.setDrawValues(false);
        lineDataSet.setLineWidth(2f);
        lineChartNumeric.getDescription().setText(getResources().getString(R.string.text_functionLabel));
        lineChartNumeric.getLegend().setEnabled(false);
        if (ANIMATE) {
            lineChartNumeric.animateX(10000);
            lineChartNumeric.animateY(10000);
        }
    }

    public void findMax(float a, float b) {
        yMax = (float) numericCalc.getFunction(a);
        for (float i = a; i < b; i += 0.1f) {
            if (numericCalc.getFunction(i) > yMax) {
                yMax = (float) numericCalc.getFunction(i);
            }
        }
    }

    public void findMin(float a, float b) {
        yMin = (float) numericCalc.getFunction(a);
        for (float i = a; i < b; i += 0.1f) {

            if (numericCalc.getFunction(i) < yMin) {
                yMax = (float) numericCalc.getFunction(i);
            }

        }
    }

    public void setSquare() {
        createXline();
        createYline();
        ArrayList<Entry> squareEntry = new ArrayList<>();
        squareEntry.add(new Entry(a, yMin));
        squareEntry.add(new Entry(a, yMax));
        squareEntry.add(new Entry(b, yMax));
        squareEntry.add(new Entry(b, yMin));
        squareEntry.add(new Entry(a, yMin));
        ArrayList<ILineDataSet> lines = new ArrayList<>();
        LineDataSet lineDataSetAB = new LineDataSet(squareEntry, "AB");
        lineDataSetAB.setDrawCircles(false);
        lineDataSetAB.setDrawValues(false);
        lines.add(lineDataSetY);
        lines.add(lineDataSetAB);
        lines.add(lineDataSet);
        lines.add(lineDataSetX);
        if(DERIVATE) {
            createDerive();
            lines.add(lineDerivate);
        }
        LineData line = new LineData(lines);
        lineChartNumeric.setData(line);
        lineDataSetAB.setDrawCircles(false);
        lineDataSetAB.setLineWidth(1);
        lineDataSetAB.setColor(Color.RED);
        lineChartNumeric.invalidate();
    }

    public void selectedBisection() {
        textViewOutRoot.setText(String.valueOf(numericCalc.bisection(a, b, EPS)));
        setSquare();
    }

    public void selectedSecant() {
        textViewOutRoot.setText(String.valueOf(numericCalc.secant(a, b, EPS)));
        setSquare();
    }

    public void selectedTangent() {
        textViewOutRoot.setText(String.valueOf(numericCalc.tangent(a, b, EPS)));
        setSquare();
    }

    public void selectedRope() {
        textViewOutRoot.setText(String.valueOf(numericCalc.rope(a, b, EPS)));
        setSquare();
    }

}