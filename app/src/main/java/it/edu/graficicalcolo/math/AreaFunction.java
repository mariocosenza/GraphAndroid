package it.edu.graficicalcolo.math;

import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.Random;

public class AreaFunction {
    private final float DEFAULT_XMIN = -2;
    private final float DEFAULT_XMAX = 3;
    private final ArrayList<Entry> scatterEntries = new ArrayList<>();
    private final ArrayList<Entry> lineEntries = new ArrayList<>();
    private final ArrayList<Entry> rectEntries = new ArrayList<>();
    private final ArrayList<Entry> trapezoidEntries = new ArrayList<>();
    private final ArrayList<Entry> simpsonEntries = new ArrayList<>();
    private final StringToFunction parser = new StringToFunction();
    private final Random randomX = new Random();
    private final Random randomY = new Random();
    public float yMax;
    public float internalPoint = 0;
    public int TOTAL_POINT = 10000;
    public int TOTAL_RECT = 1000;
    public char letter;
    float s, h;
    private float xMin = DEFAULT_XMIN;
    private float xMax = DEFAULT_XMAX;

    public ArrayList<Entry> getScatterEntries() {
        return scatterEntries;
    }

    public ArrayList<Entry> getLineEntries() {
        return lineEntries;
    }

    public float myFunction(float x) {
        return parser.expressionSolver(x);
    }

    public void drawFunction(float xMin, float xMax) {
        lineEntries.clear();
        for (float i = xMin; i < xMax; i += 0.1f) {
            lineEntries.add(new Entry(i, myFunction(i)));
        }
    }

    public float findMax(float a, float b) {
        yMax = myFunction(a);
        for (float i = a; i < b; i += 0.1f) {

            if (myFunction(i) > yMax) {
                yMax = myFunction(i);
            }

        }
        return yMax;
    }

    public float randomPointX(float a, float b) {
        return a + randomX.nextFloat() * (b - a);
    }

    public float randomPointY() {
        return randomY.nextFloat() * (yMax);
    }

    public void placePoint(float a, float b) {
        scatterEntries.clear();
        internalPoint = 0;
        for (float i = 0; i < TOTAL_POINT; i++) {

            float x = randomPointX(a, b);
            float y = randomPointY();

            if (y <= Math.abs(myFunction(x))) {
                internalPoint++;
                scatterEntries.add(new Entry(x, y));
            }
        }
    }

    public float areaCalc(float a, float b, String function, boolean checkCompatible) {
        parser.setExpression(function);
        xMin = a < DEFAULT_XMIN ? a - 0.1f : DEFAULT_XMIN;
        xMax = b > DEFAULT_XMAX ? b + 0.1f : DEFAULT_XMAX;
        findMax(a, b);
        drawFunction(xMin, xMax);
        letter = parser.getIletter();
        if (checkCompatible) {
            float areaSquare = (b - a) * Math.abs(findMax(a, b));
            placePoint(a, b);
            return (internalPoint / TOTAL_POINT) * areaSquare;
        } else return 0;
    }

    public float rectangle(float a, float b) {
        float x, sum = 0, h;
        h = (b - a) / TOTAL_RECT;
        x = a + h / 2;
        for (int i = 0; i < TOTAL_RECT; i++) {
            x += h;
            sum += h * myFunction(x);
        }
        return sum;
    }

    public ArrayList<Entry> rectangleDraw(float a, float b) {
        rectEntries.clear();
        float h = (b - a) / TOTAL_RECT;
        for (float i = a; i <= b-h; i += h) {
            rectEntries.add(new Entry(i, 0));
            rectEntries.add(new Entry(i, myFunction(i)));
            rectEntries.add(new Entry(i + h, myFunction(i)));
        }
        return rectEntries;
    }

    public float trapezoid(float a, float b) {
        float x, sum, h;
        h = (b - a) / TOTAL_RECT;
        x = a + h;
        sum = myFunction(a) + myFunction(b);
        for (int i = 1; i < TOTAL_RECT; i++) {
            sum += 2 * myFunction(x);
            x += h;
        }
        return sum * (h / 2);
    }

    public ArrayList<Entry> trapezoidDraw(float a, float b) {
        trapezoidEntries.clear();
        float h = (b - a) / TOTAL_RECT;
        for (float i = a; i <= b-h; i += h) {
            trapezoidEntries.add(new Entry(i, 0));
            trapezoidEntries.add(new Entry(i, myFunction(i)));
            trapezoidEntries.add(new Entry(i + h, myFunction(i + h)));
        }
        return trapezoidEntries;
    }

    public float simpson(float a, float b) {
        float sum, x;
        h = (b - a) / TOTAL_RECT;
        s = h / 2;
        sum = myFunction(a) + myFunction(b) + 4 * myFunction(a + s);
        x = a + h;
        for (int i = 1; i < TOTAL_RECT; i++) {
            sum += myFunction(a) + myFunction(b) + 4 * myFunction(x + s);
            x += h;
        }
        return sum * (h / 6);
    }

    public ArrayList<Entry> simpsonDraw(float a, float b) {
        float x1, y1, x2, y2, x3, y3;
        float denom, A, B, C;
        simpsonEntries.clear();
        for (float i = a; i <= b-h; i += h) {
            x1 = i;
            y1 = myFunction(i);
            x2 = i + s;
            y2 = myFunction(i + s);
            x3 = i + h;
            y3 = myFunction(i + h);
            denom = (x1 - x2) * (x1 - x3) * (x2 - x3);
            A = (x3 * (y2 - y1) + x2 * (y1 - y3) + x1 * (y3 - y2)) / denom;
            B = (x3 * x3 * (y1 - y2) + x2 * x2 * (y3 - y1) + x1 * x1 * (y2 - y3)) / denom;
            C = (x2 * x3 * (x2 - x3) * y1 + x3 * x1 * (x3 - x1) * y2 + x1 * x2 * (x1 - x2) * y3) / denom;

            for (float j = i; j <= i + h; j += 0.1f) {
                simpsonEntries.add(new Entry(j, A * j * j + B * j + C));
            }
        }
        return simpsonEntries;
    }
}
