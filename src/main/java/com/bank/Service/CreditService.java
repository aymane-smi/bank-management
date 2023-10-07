package com.bank.Service;

import com.bank.DAO.CreditDAOImpl;
import com.bank.Entity.Credit;
import com.bank.Enum.CreditStatus;

public class CreditService {
    private CreditDAOImpl creditDao;
    public CreditService(CreditDAOImpl creditDao){
        this.creditDao = creditDao;
    }
    public double makeSimulation(int value, int n) throws Exception{
        if(value <= 1000)
            throw new Exception("*****   LE MONTANT DOIT ETRE SUPERIEUR DE 1000   *****");
        if(n <= 2)
            throw new Exception("***** LE NOMBRE DE MENSUALITE DOIT ETRE SUPERIEUR DE 2   *****");
        double a = value * (Credit.TAUX /12);
        double b = Math.pow(1 -(1+(Credit.TAUX/n)), -n);
        return a/b;
    }
    public Credit addCredit(Credit credit) throws Exception{
        if(credit == null)
            throw new Exception("***** LE CREDIT NE PEUT PAS ETRE VIDE   *****");
        return creditDao.create(credit).get();
    }

    public int deletecredit(int id) throws Exception{
        if(id <= 0)
            throw new Exception("*****   ID INVALIDE   *****");
        return creditDao.delete(id);
    }

    public Credit updateStatus(int id, CreditStatus status)throws Exception{
        if(id <= 0 || status == null)
            throw new Exception("*****   STATUS|ID EST INVALIDE    *****");
        return creditDao.updateStatus(id, status).get();
    }

    public Credit findById(int id)throws Exception{
        if(id <= 0)
            throw new Exception("*****   ID EST INVALIDE    *****");
        return creditDao.findById(id).get();
    }
}
