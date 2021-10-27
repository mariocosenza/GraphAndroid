package it.edu.graficicalcolo;

import static it.edu.graficicalcolo.SettingsActivity.ANIMATE;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class NumericActivity extends AppCompatActivity {

    private EditText editTextNumberDecimalA;
    private EditText editTextNumberDecimalB;
    private TextView textViewOutRoot;
    private String[] spinnerText;
    private Spinner appSpinnerNumeric;
    private LineChart lineChartNumeric;
    private final NumericCalc numericCalc = new NumericCalc();
    private final ArrayList<Entry> functionEntry = new ArrayList<>();
    private final double EPS = 0.00001;
    private LineDataSet lineDataSet;
    private LineDataSet lineDataSetX;
    private LineDataSet lineDataSetY;
    private LineData lineData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numeric);
        editTextNumberDecimalA = findViewById(R.id.editTextNumberDecimalA);
        editTextNumberDecimalB = findViewById(R.id.editTextNumberDecimalB);
        appSpinnerNumeric = findViewById(R.id.app_spinnerNumeric);
        textViewOutRoot = findViewById(R.id.textViewOutRoot);
        spinnerText = getResources().getStringArray(R.array.numeric_array);
        lineChartNumeric = findViewById(R.id.lineChartNumeric);
        setChart();
    }
    public float getNumeberA(){
        return Float.parseFloat(editTextNumberDecimalA.getText().toString());
    }
    public float getNumeberB(){
        return Float.parseFloat(editTextNumberDecimalB.getText().toString());
    }

    public void selectMethod(View view) {
        if (appSpinnerNumeric.getSelectedItem().toString().equals(spinnerText[0])){
            selectedBisection();
        }
        else if (appSpinnerNumeric.getSelectedItem().toString().equals(spinnerText[1])){
                 selectedTangent();
        }
        else if (appSpinnerNumeric.getSelectedItem().toString().equals(spinnerText[2])){
                 selectedRope();
        }

        else {
              selectedSecant();
        }
    }
    public void createXline(){
        ArrayList<Entry> lineX = new ArrayList<>();
        lineX.add(new Entry(-4,0));
        lineX.add(new Entry(5,0));
        lineDataSetX= new LineDataSet(lineX, "X");
        lineDataSetX.setColor(Color.GREEN);
        lineDataSetX.setLineWidth(1);
        lineDataSetX.setDrawCircles(false);
    }
    public void createYline(){
        ArrayList<Entry> lineY = new ArrayList<>();
        lineY.add(new Entry(0,-60));
        lineY.add(new Entry(0,60));
        lineDataSetY = new LineDataSet(lineY, "Y");
        lineDataSetY.setColor(Color.GREEN);
        lineDataSetY.setLineWidth(1);
        lineDataSetY.setDrawCircles(false);
    }
    public void setChart(){
        for (float i = -4; i<=5; i += 0.001)
        {
            functionEntry.add(new Entry(i, (float) numericCalc.getFunction(i)));
        }
        lineDataSet = new LineDataSet(functionEntry, "Point");
        lineData = new LineData(lineDataSet);
        lineChartNumeric.invalidate();
        lineChartNumeric.setData(lineData);
        lineDataSet.setDrawCircles(false);
        lineDataSet.setLineWidth(5);
        lineChartNumeric.getDescription().setText(getResources().getString(R.string.text_functionLabel));
        lineChartNumeric.getLegend().setEnabled(false);
        if (ANIMATE) {
        lineChartNumeric.animateX(10000);
        lineChartNumeric.animateY(10000);
        }
    }
    public void setSquare(){
        createXline();
        createYline();
        ArrayList<Entry> squareEntry = new ArrayList<>();
        squareEntry.add(new Entry(getNumeberA(), -5));
        squareEntry.add(new Entry(getNumeberA(), 5));
        squareEntry.add(new Entry(getNumeberB(), 5));
        squareEntry.add(new Entry(getNumeberB(), -5));
        squareEntry.add(new Entry(getNumeberA(), -5));
        ArrayList<ILineDataSet> lines = new ArrayList<> ();
        LineDataSet lineDataSetAB = new LineDataSet(squareEntry, "AB");
        lines.add(lineDataSetAB);
        lines.add(lineDataSet);
        lines.add(lineDataSetX);
        lines.add(lineDataSetY);
        LineData line= new LineData(lines);
        lineChartNumeric.setData(line);
        lineDataSetAB.setDrawCircles(false);
        lineDataSetAB.setLineWidth(1);
        lineDataSetAB.setColor(Color.RED);
        lineChartNumeric.invalidate();

    }

    public void selectedBisection()
    {

        NumericCalc calcBisection = new NumericCalc();
        textViewOutRoot.setText(String.valueOf(calcBisection.bisection(getNumeberA(),getNumeberB(), EPS)));
        setSquare();

    }
    public void selectedSecant()
    {
        NumericCalc calcBisection = new NumericCalc();
        textViewOutRoot.setText(String.valueOf(calcBisection.secant(getNumeberA(),getNumeberB(), EPS)));
        setSquare();
    }
    public void selectedTangent()
    {
        NumericCalc calcBisection = new NumericCalc();
        textViewOutRoot.setText(String.valueOf(calcBisection.tangent(getNumeberA(),getNumeberB(), EPS)));
        setSquare();
    }
    public void selectedRope()
    {
        NumericCalc calcBisection = new NumericCalc();
        textViewOutRoot.setText(String.valueOf(calcBisection.rope(getNumeberA(),getNumeberB(), EPS)));
        setSquare();
    }

}