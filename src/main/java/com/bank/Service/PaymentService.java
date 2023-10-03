package com.bank.Service;

import com.bank.DAO.PaymentDAOImpl;
import com.bank.Entity.Payment;

import java.time.LocalDate;
import java.util.List;

public class PaymentService {
    private PaymentDAOImpl paymentDao;
    public PaymentService(PaymentDAOImpl paymentDao){
        this.paymentDao = paymentDao;
    }

    public Payment create(Payment payment) throws Exception{
        if(payment == null)
            throw new Exception("*****   OBJET DE PAYMENT NE PEUT PAS ETRE NULL   *****");
        if(payment.getFrom().getBalance() < payment.getBalance())
            throw new Exception("*****   MONTANT INSUFFISANT DE LA PART DU DONNEUR   *****");
        else
            return paymentDao.create(payment).get();
    }

    public int delete(int id) throws Exception{
        if(id == 0)
            throw new Exception("*****   L'ID DU VIRMENT NE DOIT PAS ETRE 0   *****");
        return paymentDao.delete(id);
    }

    public List<Payment> findByDate(LocalDate date) throws Exception{
        if(date == null)
            throw new Exception("*****   LA DATE DE VIRMENT DOIT ETRE NOT NULLE   *****");
        return paymentDao.findByDate(date);
    }
}
