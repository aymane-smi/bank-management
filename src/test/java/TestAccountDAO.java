import com.bank.DAO.AccountDAOImpl;
import com.bank.DAO.ClientDAOImpl;
import com.bank.Entity.Account;
import com.bank.Entity.Client;
import com.bank.Entity.CurrentAccount;
import com.bank.Entity.SavingAccount;
import com.bank.Enum.AccountStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

public class TestAccountDAO {
    @Test
    public void testCreate(){
        Client client = new Client("code1", "test", "test", LocalDate.of(2023, 10, 11), "123456789", "address");
        Optional<Client> tmp1 = new ClientDAOImpl().create(client);
        tmp1.ifPresent((clt)->{
            Account tmp = new Account(0, 12345.23, LocalDate.of(2023, 10, 11), AccountStatus.ACTIVE, clt);
            Optional<Account> optionalAcc = new AccountDAOImpl().createAccount(tmp);
            optionalAcc.ifPresent((acc)->{
                Assertions.assertNotNull(acc);
                Assertions.assertTrue(acc.getNumber() != 0);
            });
        });
    }
    @Test
    public void testCreateSaving(){
        Client client = new Client("code2", "test", "test", LocalDate.of(2023, 10, 11), "123456789", "address");
        Optional<Client> tmp1 = new ClientDAOImpl().create(client);
        tmp1.ifPresent((clt)->{
            Account tmp = new Account(0, 12345.23, LocalDate.of(2023, 10, 11), AccountStatus.ACTIVE, clt);
            Optional<Account> optionalAcc = new AccountDAOImpl().createAccount(tmp);
            optionalAcc.ifPresent((acc)->{
                Assertions.assertNotNull(acc);
                Assertions.assertTrue(acc.getNumber() != 0);
                SavingAccount savingAcc = new SavingAccount(acc, 10.12, "SAVING1");
                Optional<SavingAccount> optionalSaving = new AccountDAOImpl().createSavingAccount(savingAcc);
                optionalSaving.ifPresent((saving)->{
                    Assertions.assertNotNull(saving);
                    Assertions.assertTrue(saving.getCode().equals("SAVING1"));
                });
            });
        });
    }
    @Test
    public void testCreateCurrent(){
        Client client = new Client("code3", "test", "test", LocalDate.of(2023, 10, 11), "123456789", "address");
        Optional<Client> tmp1 = new ClientDAOImpl().create(client);
        tmp1.ifPresent((clt)->{
            Account tmp = new Account(0, 12345.23, LocalDate.of(2023, 10, 11), AccountStatus.ACTIVE, clt);
            Optional<Account> optionalAcc = new AccountDAOImpl().createAccount(tmp);
            optionalAcc.ifPresent((acc)->{
                Assertions.assertNotNull(acc);
                Assertions.assertTrue(acc.getNumber() != 0);
                CurrentAccount CurrentAcc = new CurrentAccount(acc, 10.12, "CURRENT1");
                Optional<CurrentAccount> optionalCurrent = new AccountDAOImpl().createCurrentAccount(CurrentAcc);
                optionalCurrent.ifPresent((saving)->{
                    Assertions.assertNotNull(saving);
                    Assertions.assertTrue(saving.getCode().equals("CURRENT1"));
                });
            });
        });
    }
}
