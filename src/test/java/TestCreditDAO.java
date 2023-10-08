import com.bank.DAO.CreditDAOImpl;
import com.bank.Entity.Credit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestCreditDAO {
    @Test
    public void delete(){
        int result = new CreditDAOImpl().delete(1);
        Assertions.assertEquals(1, result);
    }
    @Test
    public void testfind(){
        Credit credit = new CreditDAOImpl().findById(1).get();
        Assertions.assertNull(credit);
    }
}
