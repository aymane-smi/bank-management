import com.bank.DAO.AccountDAOImpl;
import com.bank.DAO.EmployeeDAOImpl;
import com.bank.DAO.OperationDAOImpl;
import com.bank.Entity.Account;
import com.bank.Entity.Employee;
import com.bank.Entity.Operation;
import com.bank.Enum.OperationType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

public class TestOperationDAO {
    @Test
    public void testcreate(){
        Employee emp = new EmployeeDAOImpl().findByRegistrationNbr(11).get();
        Account acc = new AccountDAOImpl().findAccountByNbr(6).get();
        Operation op = new Operation(0, LocalDate.of(2023, 11, 11), 120, OperationType.PAYMENT);
        op.setAccount(acc);
        op.setEmployee(emp);
        Optional<Operation> optionalOperation = new OperationDAOImpl().create(op);
        optionalOperation.ifPresent(operation->{
            Assertions.assertEquals(2, operation.getNumber());
        });
    }
    @Test
    public void testDelete(){
        Operation operation = new Operation();
        operation.setNumber(4);
        int op = new OperationDAOImpl().delete(operation);
        Assertions.assertEquals(1, op);
    }
}
