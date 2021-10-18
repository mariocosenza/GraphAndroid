package it.edu.graficicalcolo;

import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.Random;

public class CalcoloApprossimato {

    public static double PI_AVG = 0;
    public ArrayList<Entry> scatterEntries = new ArrayList<>();

    public ArrayList<Entry> getScatterEntries() {
        return scatterEntries;
    }

    public double findPI(int totalPoint) {
        double x, y;
        int inside = 0;
        Random random = new Random();
        if (totalPoint > 0)
            for (int i = 0; i < totalPoint; i++) {
                x = random.nextDouble();
                y = random.nextDouble();
                getEntries(x, y);
                if ((x * x + y * y) < 1) {
                    inside++;
                }
            }
        else {
            totalPoint = 10000;
            System.out.println("Invalid Number auto setted to 10000");
            findPI(totalPoint);
        }
        return calculatePI(inside, totalPoint);
    }

    public double calculatePI(int inside, int totalPoint) {
        double pi = (4 * inside) / (double) totalPoint;
        return PI_AVG = pi;
    }

    private void getEntries(double a, double b) {
        scatterEntries.add(new BarEntry((float) a, (float) b));

    }


}
