import com.bank.DAO.EmployeeDAOImpl;
import com.bank.Entity.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class TESTEmployeeDAO {

    @Test
    public void testCreate(){
        Employee tmp = new Employee("test", "test", LocalDate.of(2023, 10, 11), "123456789", "address", 0, LocalDate.of(2023, 10, 11));
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

    @Test
    public void testFindAll(){
        Optional<List<Employee>> tmp = new EmployeeDAOImpl().findAll();
        tmp.ifPresent(l->{
            Assertions.assertTrue(l.size() > 0);
            Stream<Employee> stream = l.stream();
            stream.forEach(emp->System.out.println(emp.getRegistrationNbr()+"/::/"+emp.getPhone()));
        });
    }

    @Test
    public void testFindByRegistrationNbr(){
        Optional<Employee> tmp = new EmployeeDAOImpl().findByRegistrationNbr(11);
        Assertions.assertTrue(tmp.isPresent());
    }
}
