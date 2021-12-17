package it.edu.graficicalcolo.math;

import com.itis.libs.parserng.android.expressParser.MathExpression;

public class StringToFunction {

    private MathExpression expression, expressionDifferential;
    private char iLetter = 'x';

    public void setExpression(String function) {
        for (int i = 0; i < function.length(); i++) {
            if (Character.isUpperCase(function.charAt(i))) iLetter = function.charAt(i);
        }
        expression = new MathExpression(iLetter + "=0;" + function);
    }

    public char getIletter() {
        return iLetter;
    }

    public float expressionSolver(float i) {
        expression.setValue(String.valueOf(iLetter), String.valueOf(i));
        return Float.parseFloat(expression.solve());
    }
    public float getDerivativeExpression(String function, float a) {
        expressionDifferential = new MathExpression("f(x)=" + function + ";" + " diff(f,"+ a +",1)");
        return Float.parseFloat(expressionDifferential.solve());
    }


}

