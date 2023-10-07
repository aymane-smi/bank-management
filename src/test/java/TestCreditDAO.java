import com.bank.DAO.CreditDAOImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestCreditDAO {
    @Test
    public void delete(){
        int result = new CreditDAOImpl().delete(1);
        Assertions.assertEquals(1, result);
    }
}
