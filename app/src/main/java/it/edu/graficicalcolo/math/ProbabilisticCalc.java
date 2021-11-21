package it.edu.graficicalcolo.math;

public class ProbabilisticCalc {

    public double sqrtAproximated(double rooting) {
        int MAX = 100;
        double x = 2;
        for (int i = 0; i <= MAX; i++) {
            x = (x / 2) + (rooting / (2 * x));

        }
        return x;
    }


}
