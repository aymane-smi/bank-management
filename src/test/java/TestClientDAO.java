import com.bank.DAO.ClientDAOImpl;
import com.bank.DAO.EmployeeDAOImpl;
import com.bank.Entity.Client;
import com.bank.Entity.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class TestClientDAO {
    @Test
    public void testCreate(){
        Client tmp = new Client("code1", "test", "test", LocalDate.of(2023, 10, 11), "123456789", "address");
        Optional<Client> tmp1 = new ClientDAOImpl().create(tmp);
        tmp1.ifPresent((emp)->{
            Assertions.assertNotNull(emp);
            Assertions.assertTrue(!emp.getCode().isEmpty());
        });
    }

    @Test
    public void testDelete(){
        int tmp = new ClientDAOImpl().delete("code2");
        Assertions.assertEquals(1, tmp);
    }
    @Test
    public void testFindAll(){
        Optional<List<Client>> tmp = new ClientDAOImpl().findAll();
        Assertions.assertTrue(tmp.get().size() == 3);
    }

    @Test
    public void testUpdate(){
        Optional<Client> tmp = new ClientDAOImpl().findByCode("code1");
        tmp.ifPresent((clt)->{
            clt.setFirstName("test****");
            clt.setLastName("test****");
            Optional<Client> tmp1 = new ClientDAOImpl().update(clt);
            tmp1.ifPresent((c)->{
                Assertions.assertTrue(c.getFirstName().equals("test****"));
            });
        });
    }

    @Test
    public void testFind(){
        Client clt = new ClientDAOImpl().findByCode("code1").get();
        Optional<List<Client>> tmp = new ClientDAOImpl().find(clt);
        tmp.ifPresent((client)->{
            System.out.println("code::"+client.get(0).getCode());
            Assertions.assertTrue(client.size() == 1);
        });
    }
    @Test
    public void getAccounts(){
        Client clt = new ClientDAOImpl().findByCode("code1").get();
        new ClientDAOImpl().getAccounts(clt).ifPresent((client)->{
            Assertions.assertTrue(client.getAccounts().get("current").size() == 0);
        });
    }
}
