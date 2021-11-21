package it.edu.graficicalcolo.math;

import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.Random;

public class AreaFunction {
    public float yMax;
    public float internalPoint = 0;
    public int TOTAL_POINT = 10000;
    private final float DEFAULT_XMIN = -2;
    private final float DEFAULT_XMAX= 3;
    private float xMin = DEFAULT_XMIN;
    private float xMax = DEFAULT_XMAX;
    private final ArrayList<Entry> scatterEntries = new ArrayList<>();
    private final ArrayList<Entry> lineEntries = new ArrayList<>();
    private final StringToFunction parser = new StringToFunction();


    public ArrayList<Entry> getScatterEntries() {
        return scatterEntries;
    }
    public ArrayList<Entry> getLineEntries() {
        return lineEntries;
    }

    public float myFunction(float x) {
        return parser.expressionSolver(x);
    }


    public void drawFunction(float xMin, float xMax){
        lineEntries.clear();
        for (float i = xMin; i < xMax; i += 0.1)
        {
            lineEntries.add(new Entry(i, myFunction(i)));
        }
    }

    public float findMax(float a, float b){
        yMax = myFunction(a);
        for (float i=a; i<b; i += 0.1){

            if (myFunction(i)>yMax){
                yMax = myFunction(i);
            }

        }
        return yMax;
    }

    private final Random randomX = new Random();
    public float randomPointX (float a, float b){
        return a+ randomX.nextFloat()*(b-a);
    }

    private final Random randomY = new Random();
    public float randomPointY (){
        return randomY.nextFloat()*(yMax);
    }

    public void placePoint (float a, float b) {
        scatterEntries.clear();
        for (float i = 0; i < TOTAL_POINT; i++) {

            float x = randomPointX(a, b);
            float y = randomPointY();

            if (y <= Math.abs(myFunction(x))) {
                internalPoint++;
                scatterEntries.add(new Entry(x, y));
            }
        }
    }

    public float areaCalc (float a, float b, String function) {

        parser.setExpression(function);
        xMin = xMin < DEFAULT_XMIN? a - 0.5f : DEFAULT_XMIN;
        xMax = xMax > DEFAULT_XMAX? b + 0.5f : DEFAULT_XMAX ;

        findMax(a, b);
        drawFunction(xMin, xMax);
        float areaSquare = (b-a) * Math.abs(findMax(a,b));
        placePoint(a,b);
        return (internalPoint/TOTAL_POINT)*areaSquare;
    }




}
