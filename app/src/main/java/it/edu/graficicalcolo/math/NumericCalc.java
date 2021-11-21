package it.edu.graficicalcolo.math;

public class NumericCalc {

    public double getFunction(double z) {
       return Math.pow(z,3)-2*Math.pow(z,2)-z+2;

    }

    private final double JMAX = 10000;

    public double bisection (double a, double b, double xacc) {
        boolean end = false;

        double Xa = 0, Xb = 0, Fa, Fb, Xm = 0, Fm, tmp;
        if (a>b) { //extreme not ordered
            tmp = a;
            a = b;
            b = tmp;
        }
        Fa = getFunction(a);
        Fb = getFunction(b);
        if (Fa*Fb>=0){
            System.out.println("Invalid range");
        }
        else
        {
            Xa = a;
            Xb = b;
        }
        for (int i = 0; i<JMAX && !end; i++){
            Fa = getFunction(Xa);
            Fb = getFunction(Xb);
            Xm = (Xa+Xb) * 0.5;
            Fm = getFunction(Xm);
            if (Fm*Fa >= 0) Xa=Xm;
            if (Xa!=Xm) {
                Xb = Xm;
            }
            if ((Math.abs(Xb-Xa)< xacc)||(Fm ==0))
            {
                end = true;
            }
            if (end) {
                break;
            }
            else
            {
                System.out.println("too");
            }

        }
        System.out.println(Xm);
        return Xm;


    }

    public double Kangular (double xi, double xa, double xb)
    {double lim, cond;
        cond=getFunction(xi)*getFunction(xa);
        if (cond<0) lim=xa;
        else lim=xb;
        return (getFunction(xi)-getFunction(lim))/(xi-lim);
    }
    public double rope(double a, double b, double xacc)
    { int i;
    boolean end = false;
        double Fa, Fb, Xi = 0, Fi, Xold, tmp;
        if (a>b) //extreme not ordered
        {
            tmp=a;
            a=b;
            b=tmp;
        }
        Fa=getFunction(a);
        Fb=getFunction(b);
        if (Fa*Fb>=0)
        {System.out.println("Invalid range");

        }
        Xold=(a+b)*0.5; //initialization
        for (i=0; i<JMAX && !end; i++) {
            Xi=-getFunction(Xold)/Kangular(Xold,a,b) + Xold;
            Fi=getFunction(Xi);
            if ((Math.abs(Xi-Xold)< xacc) || (Fi==0)) end=true;
            else Xold=Xi; }
        if (end) {
            System.out.println("performed %d iteration:" + i);
        return Xi;
        }
        else {
            System.out.println("Performed too much iteration: ");
        }
        return Xi;
    }

    double Ksangular(double Xnew, double Xold) {
        return (getFunction(Xnew)-getFunction(Xold))/(Xnew-Xold);
    }
    public double secant(double a, double b, double xacc) {
        int i;
        boolean end=false;
        double Fa, Fb, Xi = 0, Fi, Xnew,Xold, tmp;
        if (a>b) // extreme not ordered
        {
            tmp=a;
            a=b;
            b=tmp;}
        Fa=getFunction(a);
        Fb=getFunction(b);
        if (Fa*Fb>=0) {
            System.out.println("Invalid range");
           }
        Xold=a;
        Xnew=b;

        for (i=0; i<JMAX && !end; i++) {
            Xi=-getFunction(Xold)/ Ksangular(Xnew,Xold) + Xold;
            Fi=getFunction(Xi);
            if ((Math.abs(Xi-Xold)< xacc) || (Fi==0)) end=true;
            else {
                Xold=Xnew;
                Xnew=Xi;
            }
        }
        if (end) {
            System.out.println("performed %d iteration: "+ i);
            return Xi;
        }
        else {
            System.out.println("Performed too much iteration");
            }
        return Xi;
    }

    double Ktangular(double xi){
        double DELTA = 0.001;
        return (getFunction(xi+ DELTA)-getFunction(xi))/ DELTA;}

    public double tangent(double a, double b, double xacc) {
        int i;
        boolean end=false;
        double Fa, Fb, Xi = 0, Fi, Xold, tmp;
        if (a>b) //extreme not ordered
        { tmp=a;
          a=b;
          b=tmp;
        }
        Fa=getFunction(a);
        Fb=getFunction(b);
        if (Fa*Fb>=0) {
            System.out.println("Invalid range");
             }
        Xold=(a+b)*0.5; //initialization
        for (i=0; i<JMAX && !end; i++) {
            Xi=-getFunction(Xold)/ Ktangular(Xold) + Xold;
            Fi=getFunction(Xi);
            if ((Math.abs(Xi-Xold)< xacc) || (Fi==0)) end=true;
            else Xold=Xi;
        }
        if (end) {
            System.out.println("performed %d iteration:"+ i);
            return Xi;}
        else {
            System.out.println("Performed too much iteration");
          }
        return Xi;
    }
}
