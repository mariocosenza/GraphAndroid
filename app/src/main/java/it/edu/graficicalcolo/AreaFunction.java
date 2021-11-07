package it.edu.graficicalcolo;

import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.Random;

public class AreaFunction {
    public float yMax;
    public float internalPoint = 0;
    public int TOTAL_POINT = 10000;
    private final ArrayList<Entry> scatterEntries = new ArrayList<>();
    private final ArrayList<Entry> lineEntries = new ArrayList<>();

    public ArrayList<Entry> getScatterEntries() {
        return scatterEntries;
    }
    public ArrayList<Entry> getLineEntries() {
        return lineEntries;
    }

    public float myFunction(float x) {
        return ((-x*x*x)+5*x);
    }

    public void drawFunction(){
        for (float i = -2; i < 3; i += 0.1)
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
        for (float i = 0; i < TOTAL_POINT; i++) {

            float x = randomPointX(a, b);
            float y = randomPointY();

            if (y <= Math.abs(myFunction(x))) {
                internalPoint++;
                scatterEntries.add(new Entry(x, y));
            }
        }
    }

    public float areaCalc (float a, float b) {
        findMax(a, b);
        drawFunction();
        float areaSquare = (b-a) * Math.abs(findMax(a,b));
        placePoint(a,b);
        return (internalPoint/TOTAL_POINT)*areaSquare;
    }




}
