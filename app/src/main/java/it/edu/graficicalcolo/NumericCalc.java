package it.edu.graficicalcolo;

public class NumericCalc {

    public double getFunction(double z) {
        return Math.pow(3,z)-2*Math.pow(2,z)-z+2;
    }

    private final double EPS = 0.00001;
    private final double JMAX = 1000;

    public double bisection (double a, double b, double xacc) {
        boolean end = false;
        double Xa = 0, Xb = 0, Fa, Fb, Xm = 0, Fm, tmp;
        if (a>b) {
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
        for (int i = 0; i<JMAX; i++){
            Fa = getFunction(Xa);
            Fb = getFunction(Xb);
            Xm = (Xa+Xb)/2;
            Fm = getFunction(Xm);
            if (Fm*Fa<0);
            else Xa=Xm;
            if (Xa!=Xm) {Xb = Xm;}
            if ((Math.abs(Xb-Xa)< xacc)|| (Fm ==0))
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
        return Xm;


    }

    public double K (double xi, double xa, double xb)
    {double lim, cond;
        cond=getFunction(xi)*getFunction(xa);
        if (cond<0) lim=xa; else lim=xb;
        return (getFunction(xi)-getFunction(lim))/(xi-lim);
    }
    public double corde(double a, double b, double xacc)
    { int i;
    boolean fine = false;
        double Fa, Fb, Xi = 0, Fi, Xold, tmp;
        if (a>b) /* estremi non ordinati */
        {tmp=a; a=b; b=tmp; }
        Fa=getFunction(a);
        Fb=getFunction(b);
        if (Fa*Fb>=0)
        {System.out.println("intervallo non valido");

        }
        Xold=(a+b)*0.5; /*inizializzazione*/
        for (i=0; i<JMAX && !fine; i++) {
            Xi=-getFunction(Xold)/K(Xold,a,b) + Xold;
            Fi=getFunction(Xi);
            if ((Math.abs(Xi-Xold)< xacc) || (Fi==0)) fine=true;
            else Xold=Xi; }
        if (fine) {System.out.println("eseguite %d iterazioni:" + i); return Xi;}
        else { System.out.println("eseguitetroppe iterazioni: ");
        }
        return Xi;
    }

    double Ks(double Xnew, double Xold) {
        return (getFunction(Xnew)-getFunction(Xold))/(Xnew-Xold);
    }
    double secanti(double a, double b, double xacc) {
        int i;
        boolean fine=false;
        double Fa, Fb, Xi = 0, Fi, Xnew,Xold, tmp;
        if (a>b) /* estremi non ordinati */
        {tmp=a; a=b; b=tmp;}
        Fa=getFunction(a);
        Fb=getFunction(b);
        if (Fa*Fb>=0) {
            System.out.println("intervallo non valido\n");
           }

        Xold=a;
        Xnew=b;
        for (i=0; i<JMAX && !fine; i++) {
            Xi=-getFunction(Xold)/Ks(Xnew,Xold) + Xold;
            Fi=getFunction(Xi);
            if ((Math.abs(Xi-Xold)< xacc) || (Fi==0)) fine=true;
            else {Xold=Xnew;Xnew=Xi;}
        }
        if (fine) {System.out.println("eseguite %d iterazioni\n"+ i); return Xi;}
        else {
            System.out.println("eseguitetroppe iterazioni\n");
            }
        return Xi;
    }

    private final double DELTA = 0.001;

    double Kt(double xi){
        return (getFunction(xi+DELTA)-getFunction(xi))/DELTA;}

    double tangenti(double a, double b, double xacc) {
        int i;
        boolean fine=false;
        double Fa, Fb, Xi = 0, Fi, Xold, tmp;
        if (a>b) /* estremi non ordinati */
        {tmp=a; a=b; b=tmp;}
        Fa=getFunction(a);
        Fb=getFunction(b);
        if (Fa*Fb>=0) {
            System.out.println("intervallo non valido\n");
             }
        Xold=(a+b)*0.5; /*inizializz. */
        for (i=0; i<JMAX && !fine; i++) {
            Xi=-getFunction(Xold)/Kt(Xold) + Xold;
            Fi=getFunction(Xi);
            if ((Math.abs(Xi-Xold)< xacc) || (Fi==0)) fine=true;
            else Xold=Xi;
        }
        if (fine) {
            System.out.println("eseguite %d iterazioni\n"+ i);
            return Xi;}
        else {
            System.out.println("eseguitetroppe iterazioni\n");
          }
        return Xi;
    }
}
