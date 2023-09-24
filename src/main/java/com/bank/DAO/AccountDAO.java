package com.bank.DAO;
import com.bank.Entity.Account;
import com.bank.Entity.CurrentAccount;
import com.bank.Entity.SavingAccount;
import com.bank.Enum.AccountStatus;

import java.util.Optional;

public interface AccountDAO {
    public Optional<Account> createAccount(Account account);
    public Optional<SavingAccount> createSavingAccount(SavingAccount account);
    public Optional<CurrentAccount> createCurrentAccount(CurrentAccount account);
    public Optional<Account> findAccountByNbr(int number);

    public int deleteSaving(String code);
    public int deleteCurrent(String code);
    public int delete(int number);

    public Optional<Account> updateStatus(Account account, AccountStatus status);
}
