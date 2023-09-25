DROP TABLE IF EXISTS client;
DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS mission;
DROP TABLE IF EXISTS current_account;
DROP TABLE IF EXISTS saving_account;
DROP TABLE IF EXISTS operation;
DROP TABLE IF EXISTS mission_employee;
DROP TABLE IF EXISTS account;

CREATE TABLE client(
      code TEXT PRIMARY KEY,
      firstName TEXT NOT NULL,
      lastName TEXT NOT NULL,
      birthDay Date NOT NULL,
      phone TEXT NOT NULL,
      address TEXT NOT NULL
);

CREATE TABLE employee(
      registrationNbr SERIAL PRIMARY KEY,
      firstName TEXT NOT NULL,
      lastName TEXT NOT NULL,
      birthDay Date NOT NULL,
      phone TEXT NOT NULL,
      address TEXT NOT NULL,
      dateOfRecrutment DATE NOT NULL
);

CREATE TABLE mission(
      code SERIAL PRIMARY KEY,
      name TEXT NOT NULL,
      description TEXT NOT NULL
);

CREATE TABLE account(
      number SERIAL PRIMARY KEY,
      balance NUMERIC(10, 4) NOT NULL,
      creationDate Date NOT NULL,
      status Text NOT NULL CHECK IN ("ACTIVE", "SUSPEND", "BANNED"),
      client_code TEXT NOT NULL,
      FOREIGN KEY (client_code) REFERENCES client(code)
);

CREATE TABLE current_account(
      code Text PRIMARY KEY,
      account_number INT NOT NULL,
      overDraft NUMERIC(10, 4) NOT NULL,
      FOREIGN KEY (account_number) REFERENCES account(number)
);

CREATE TABLE saving_account(
      code Text PRIMARY KEY,
      account_number INT NOT NULL,
      tax NUMERIC(4, 2) NOT NULL,
      FOREIGN KEY (account_number) REFERENCES account(number)
);

CREATE TABLE operation(
      number SERIAL PRIMARY KEY,
      creationDate Date NOT NULL,
      amount NUMERIC(7, 4) NOT NULL,
      type TEXT NOT NULL,
      account_number INT NOT NULL,
      employee_registrationNbr INT NOT NULL,
      FOREIGN KEY (account_number) REFERENCES account(number),
      FOREIGN KEY (employee_registrationNbr) REFERENCES employee(registrationNbr)
);

CREATE TABLE mission_employee(
    mission_code INT NOT NULL,
    employee_registrationNbr INT NOT NULL,
    startDate Date NOT NULL,
    endDate Date DEFAULT NULL,
    PRIMARY KEY (mission_code, employee_registrationNbr),
    FOREIGN KEY (mission_code) REFERENCES mission(code),
    FOREIGN KEY (employee_registrationNbr) REFERENCES employee(registrationNbr)
);

CREATE OR REPLACE FUNCTION getSavingAccounts() RETURNS TABLE (
    balance NUMERIC(10, 4),
    creationDate DATE,
    Status TEXT,
    code TEXT,
    tax NUMERIC(4, 2) -- Corrected data type to match saving_account's tax column
) AS $$
BEGIN
    RETURN QUERY SELECT
        account.balance,
        account.creationDate,
        account.Status,
        saving_account.code,
        saving_account.tax
    FROM
        saving_account
    JOIN
        account ON saving_account.account_number = account.number;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION getCurrentAccounts() RETURNS TABLE (
    balance NUMERIC(10, 4),
    creationDate DATE,
    Status TEXT,
    code TEXT,
    overDraft NUMERIC(10, 4) -- Corrected data type to match saving_account's tax column
) AS $$
BEGIN
    RETURN QUERY SELECT
        account.balance,
        account.creationDate,
        account.Status,
        current_account.code,
        current_account.overDraft
    FROM
        current_account
    JOIN
        account ON current_account.account_number = account.number;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION getClientSavingAccounts(client_code TEXT) RETURNS TABLE (
    balance NUMERIC(10, 4),
    creationDate DATE,
    Status TEXT,
    code TEXT,
    tax NUMERIC(4, 2) -- Corrected data type to match saving_account's tax column
) AS $$
BEGIN
    RETURN QUERY SELECT
        account.balance,
        account.creationDate,
        account.Status,
        saving_account.code,
        saving_account.tax
    FROM
        saving_account
    JOIN
        account ON saving_account.account_number = account.number WHERE account.client_code = getClientSavingAccounts.client_code;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION getClientCurrentStatus(status TEXT) RETURNS TABLE (
    balance NUMERIC(10, 4),
    creationDate DATE,
    Status TEXT,
    code TEXT,
    overDraft NUMERIC(10, 4) -- Corrected data type to match saving_account's tax column
) AS $$
BEGIN
    RETURN QUERY SELECT
        account.balance,
        account.creationDate,
        account.Status,
        current_account.code,
        current_account.overDraft
    FROM
        current_account
    JOIN
        account ON current_account.account_number = account.number WHERE account.status = getClientCurrentStatus.status;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION getClientSavingStatus(account_status TEXT) RETURNS TABLE (
    balance NUMERIC(10, 4),
    creationDate DATE,
    Status TEXT,
    code TEXT,
    tax NUMERIC(4, 2) -- Corrected data type to match saving_account's tax column
) AS $$
BEGIN
    RETURN QUERY SELECT
        account.balance,
        account.creationDate,
        account.Status,
        saving_account.code,
        saving_account.tax
    FROM
        saving_account
    JOIN
        account ON saving_account.account_number = account.number WHERE account.status = getClientSavingStatus.account_status;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION getClientCurrentStatus(account_status TEXT) RETURNS TABLE (
    balance NUMERIC(10, 4),
    creationDate DATE,
    Status TEXT,
    code TEXT,
    overDraft NUMERIC(10, 4) -- Corrected data type to match saving_account's tax column
) AS $$
BEGIN
    RETURN QUERY SELECT
        account.balance,
        account.creationDate,
        account.Status,
        current_account.code,
        current_account.overDraft
    FROM
        current_account
    JOIN
        account ON current_account.account_number = account.number WHERE account.status = getClientCurrentStatus.account_status;
END;
$$ LANGUAGE plpgsql;
