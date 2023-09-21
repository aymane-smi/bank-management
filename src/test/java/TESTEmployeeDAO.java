import com.bank.DAO.EmployeeDAOImpl;
import com.bank.Entity.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Optional;

public class TESTEmployeeDAO {

    @Test
    public void testCreate(){
        Employee tmp = new Employee("test", "test", new Date(), "123456789", "address", 0, new Date());
        Optional<Employee> tmp1 = new EmployeeDAOImpl().create(tmp);
        tmp1.ifPresent((emp)->{
            Assertions.assertNotNull(emp);
            Assertions.assertTrue(emp.getRegistrationNbr() != 0);
        });
    }

    @Test
    public void testDelete(){
        int tmp = new EmployeeDAOImpl().delete(9);
        Assertions.assertEquals(0, tmp);
    }
}
