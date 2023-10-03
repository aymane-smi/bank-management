import com.bank.DAO.AccountDAOImpl;
import com.bank.DAO.AgencyDAOImpl;
import com.bank.DAO.ClientDAOImpl;
import com.bank.DAO.EmployeeDAOImpl;
import com.bank.Entity.*;
import com.bank.Enum.AccountStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class TestAccountDAO {
    @Test
    public void testCreate(){
        Client client = new Client("code2", "test", "test", LocalDate.of(2023, 10, 11), "123456789", "address");
        client.setEmployee(new EmployeeDAOImpl().findByRegistrationNbr(3).get());
        client.setAgency(new AgencyDAOImpl().findByCode("AGENCY1").get());
        Optional<Client> tmp1 = new ClientDAOImpl().create(client);
        tmp1.ifPresent((clt)->{
            Account tmp = new Account(0, 12345.23, LocalDate.of(2023, 10, 11), AccountStatus.ACTIVE, clt);
            tmp.setAgency(client.getAgency());
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

    @Test
    public void testCurrentDelete(){
        int result = new AccountDAOImpl().deleteCurrent("CURRENT1");
        Assertions.assertTrue(result == 1);
    }

    @Test
    public void testSavingDelete(){
        int result = new AccountDAOImpl().deleteSaving("saving1111");
        Assertions.assertTrue(result == 1);
    }

    @Test
    public void testDelete(){
        int result = new AccountDAOImpl().delete(7);
        Assertions.assertTrue(result == 1);
    }

    @Test
    public void testUpdateStatus(){
        Optional<Account> optionalAccount = new AccountDAOImpl().updateStatus(new AccountDAOImpl().findAccountByNbr(1).get(), AccountStatus.SUSPEND);
        optionalAccount.ifPresent((account)->{
            Assertions.assertTrue(account.getStatus() == AccountStatus.SUSPEND);
        });
    }

    @Test
    public void testgetCurrentAccounts(){
        Optional<List<CurrentAccount>> listCurrent = new AccountDAOImpl().findAllCurrent();
        listCurrent.ifPresent((list)->{
            Assertions.assertTrue(list.size() == 0);
        });
    }

    @Test
    public void testgetSavingAccounts(){
        Optional<List<SavingAccount>> listCurrent = new AccountDAOImpl().findAllSaving();
        listCurrent.ifPresent((list)->{
            Assertions.assertTrue(list.size() == 2);
        });
    }

    @Test
    public void testUpdateAccount(){
        Account test = new AccountDAOImpl().findAccountByNbr(5).get();
        test.setCreationDate(LocalDate.of(1999,11,11));
        Optional<Account> optionalAccount = new AccountDAOImpl().update(test);
        optionalAccount.ifPresent((val)->{
            Assertions.assertTrue(val.getCreationDate().isEqual(LocalDate.of(1999,11,11)));
        });
    }

    @Test
    public void testFindOneSaving(){
        Optional<SavingAccount> savingAccount = new AccountDAOImpl().findSaving("SAVING1");
        savingAccount.ifPresent((saving)->{
            Assertions.assertTrue(saving.getTax() == 10.12);
        });
    }

    @Test
    public void testStatusSaving(){
        Optional<List<SavingAccount>> savingAccount = new AccountDAOImpl().findSavingStatus(AccountStatus.ACTIVE);
        savingAccount.ifPresent((list)->{
            Assertions.assertTrue(list.size() == 1);
        });
    }
    @Test
    public void testStatusCurrent(){
        Optional<List<CurrentAccount>> currentAccount = new AccountDAOImpl().findCurrentStatus(AccountStatus.ACTIVE);
        currentAccount.ifPresent((list)->{
            Assertions.assertTrue(list.size() == 0);
        });
    }

    @Test
    public void testfindSavingByDate(){
        Optional<List<SavingAccount>> savingAccount = new AccountDAOImpl().findSavingByDate(LocalDate.of(1999, 11, 11));
        savingAccount.ifPresent((saving)->{
            Assertions.assertTrue(saving.size() == 1);
        });
    }

    @Test
    public void testfindCurrentgByDate(){
        Optional<List<CurrentAccount>> currentAccount = new AccountDAOImpl().findCurrentByDate(LocalDate.of(1999, 11, 11));
        currentAccount.ifPresent((current)->{
            Assertions.assertTrue(current.size() == 0);
        });
    }
    @Test
    public void testFindByOp(){
        Operation op = new Operation();
        op.setNumber(0);
        Optional<Account> optionalAcccount = new AccountDAOImpl().findByOperation(op);
        optionalAcccount.ifPresent((account -> {
            Assertions.assertNull(account);
        }));
    }
}
