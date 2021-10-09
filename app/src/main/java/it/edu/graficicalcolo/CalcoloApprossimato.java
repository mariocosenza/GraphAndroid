package it.edu.graficicalcolo;

import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.Random;

public class CalcoloApprossimato {

    public static double PI_AVG = 0;
    public static ArrayList scatterEntries = new ArrayList<>();

      // TODO add control for memory usage and max point - add ArrayList type and block it from override values after first initialization
    public double findPI (int totalPoint) {
        double x, y;
        int inside = 0;
        Random random = new Random();
        if (totalPoint > 0)
        for (int i = 0;  i <  totalPoint; i++) {
            x = random.nextDouble();
            y = random.nextDouble();
            getEntries(x,y);
            if ((x*x + y*y) < 1) {
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

    public double calculatePI (int inside, int totalPoint) {
        double pi = (4 * inside)/ (double) totalPoint;
        System.out.println(pi);
        return PI_AVG = pi;
    }
    private void getEntries(double a, double b) {
        scatterEntries.add(new BarEntry((float) a,(float) b));

    }




}
