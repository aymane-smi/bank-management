package com.bank.Service;

import com.bank.Entity.Credit;

public class CreditService {
    public double makeSimulation(double value, int n) throws Exception{
        if(value <= 1000)
            throw new Exception("*****   LE MONTANT DOIT ETRE SUPERIEUR DE 1000");
        if(n <= 2)
            throw new Exception("***** LE NOMBRE DE MENSUALITE DOIT ETRE SUPERIEUR DE 2");
        double a = value * (Credit.TAUX /12);
        double b = Math.pow(1 -(1+(Credit.TAUX/n)), -n);
        return a/b;
    }
}
