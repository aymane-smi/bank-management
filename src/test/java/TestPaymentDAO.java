import com.bank.DAO.AccountDAOImpl;
import com.bank.DAO.EmployeeDAOImpl;
import com.bank.DAO.PaymentDAOImpl;
import com.bank.Entity.Account;
import com.bank.Entity.Employee;
import com.bank.Entity.Payment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class TestPaymentDAO {
    @Test
    public void testCreate(){
        Employee emp = new EmployeeDAOImpl().findByRegistrationNbr(3).get();
        Account from = new AccountDAOImpl().findAccountByNbr(1).get();
        Account to = new AccountDAOImpl().findAccountByNbr(2).get();
        Payment payment = new Payment(0, 1234, from, to, emp, LocalDateTime.now());
        Optional<Payment> optionalPayment = new PaymentDAOImpl().create(payment);
        optionalPayment.ifPresent((p)->{
            Assertions.assertEquals(1, p.getId());
        });
    }

    @Test
    public void testDelete(){
        int result = new PaymentDAOImpl().delete(1);
        Assertions.assertEquals(1, result);
    }

    @Test
    public void testFindByDate(){
        List<Payment> paymentList = new PaymentDAOImpl().findByDate(LocalDate.now());
        Assertions.assertEquals(0, paymentList.size());
    }
}
