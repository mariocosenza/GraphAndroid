package it.edu.graficicalcolo;

import java.util.Random;

public class CalcoloApprossimato {
    public static double PI_AVG = 0;

    public double findPI (int totalPoint) {
        double x, y;
        int inside = 0;
        Random random = new Random();
        if (totalPoint > 0)
        for (int i = 0;  i <  totalPoint; i++) {
            x = random.nextDouble();
            y = random.nextDouble();
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



}
