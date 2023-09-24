package com.bank.DAO;
import com.bank.Entity.Account;
import com.bank.Entity.CurrentAccount;
import com.bank.Entity.SavingAccount;

import java.util.Optional;

public interface AccountDAO {
    public Optional<Account> createAccount(Account account);
    public Optional<SavingAccount> createSavingAccount(SavingAccount account);
    public Optional<CurrentAccount> createCurrentAccount(CurrentAccount account);
    public Optional<Account> findAccountByNbr(int number);
}
