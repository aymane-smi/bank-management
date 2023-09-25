package com.bank.DAO;
import com.bank.Entity.Account;
import com.bank.Entity.CurrentAccount;
import com.bank.Entity.SavingAccount;
import com.bank.Enum.AccountStatus;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface AccountDAO {
    public Optional<Account> createAccount(Account account);
    public Optional<SavingAccount> createSavingAccount(SavingAccount account);
    public Optional<CurrentAccount> createCurrentAccount(CurrentAccount account);
    public Optional<Account> findAccountByNbr(int number);
    public Optional<List<SavingAccount>> findAllSaving();
    public Optional<List<CurrentAccount>> findAllCurrent();

    public int deleteSaving(String code);
    public int deleteCurrent(String code);
    public int delete(int number);

    public Optional<Account> updateStatus(Account account, AccountStatus status);

    public Optional<Account> update(Account account);
    public Optional<SavingAccount> findSaving(String code);
    public Optional<CurrentAccount> findCurrent(String code);
}
