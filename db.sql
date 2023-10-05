DROP TABLE IF EXISTS client CASCADE;
DROP TABLE IF EXISTS employee CASCADE;
DROP TABLE IF EXISTS mission CASCADE;
DROP TABLE IF EXISTS current_account CASCADE;
DROP TABLE IF EXISTS saving_account CASCADE;
DROP TABLE IF EXISTS operation CASCADE;
DROP TABLE IF EXISTS mission_employee CASCADE;
DROP TABLE IF EXISTS account CASCADE;
DROP TABLE IF EXISTS agency CASCADE;
DROP TABLE IF EXISTS payment CASCADE;
DROP TABLE IF EXISTS employee_history CASCADE;

-- SEQUENCES
CREATE SEQUENCE agency_seq START 1;
CREATE SEQUENCE employee_history_seq START 1;

CREATE TABLE agency(
    code VARCHAR(9) DEFAULT ('AGENCY' || nextval('agency_seq')::text) PRIMARY KEY,
    name TEXT NOT NULL,
    address TEXT NOT NULL,
    phone TEXT NOT NULL
);

CREATE TABLE employee(
      registrationNbr SERIAL PRIMARY KEY,
      firstName TEXT NOT NULL,
      lastName TEXT NOT NULL,
      birthDay Date NOT NULL,
      phone TEXT NOT NULL,
      address TEXT NOT NULL,
      dateOfRecrutment DATE NOT NULL,
      agency_code VARCHAR(9) NOT NULL,
      FOREIGN KEY(agency_code) REFERENCES agency(code) ON DELETE CASCADE
);

CREATE TABLE client(
      code TEXT PRIMARY KEY,
      firstName TEXT NOT NULL,
      lastName TEXT NOT NULL,
      birthDay Date NOT NULL,
      phone TEXT NOT NULL,
      address TEXT NOT NULL,
      employee_registrationNbr INT,
      agency_code VARCHAR(9) NOT NULL,
      FOREIGN KEY(employee_registrationNbr) REFERENCES employee(registrationNbr) ON DELETE CASCADE,
      FOREIGN KEY(agency_code) REFERENCES agency(code) ON DELETE CASCADE
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
      status Text NOT NULL CHECK (status IN ('ACTIVE', 'SUSPEND', 'BANNED')),
      client_code TEXT NOT NULL,
      agency_code TEXT NOT NULL,
      FOREIGN KEY (client_code) REFERENCES client(code) ON DELETE CASCADE,
      FOREIGN KEY (agency_code) REFERENCES agency(code) ON DELETE CASCADE
);

CREATE TABLE current_account(
      code Text PRIMARY KEY,
      account_number INT NOT NULL,
      overDraft NUMERIC(10, 4) NOT NULL,
      FOREIGN KEY (account_number) REFERENCES account(number) ON DELETE CASCADE
);

CREATE TABLE saving_account(
      code Text PRIMARY KEY,
      account_number INT NOT NULL,
      tax NUMERIC(4, 2) NOT NULL,
      FOREIGN KEY (account_number) REFERENCES account(number) ON DELETE CASCADE
);

CREATE TABLE operation(
      number SERIAL PRIMARY KEY,
      creationDate Date NOT NULL,
      amount NUMERIC(7, 4) NOT NULL,
      type TEXT NOT NULL,
      account_number INT NOT NULL,
      employee_registrationNbr INT NOT NULL,
      FOREIGN KEY (account_number) REFERENCES account(number) ON DELETE CASCADE,
      FOREIGN KEY (employee_registrationNbr) REFERENCES employee(registrationNbr) ON DELETE CASCADE
);

CREATE TABLE mission_employee(
    id SERIAL PRIMARY KEY,
    mission_code INT NOT NULL,
    employee_registrationNbr INT NOT NULL,
    startDate Date NOT NULL,
    endDate Date DEFAULT NULL,
    FOREIGN KEY (mission_code) REFERENCES mission(code) ON DELETE CASCADE,
    FOREIGN KEY (employee_registrationNbr) REFERENCES employee(registrationNbr) ON DELETE CASCADE
);

CREATE TABLE payment(
    id SERIAL PRIMARY KEY,
    transaction_time TIMESTAMP NOT NULL,
    balance NUMERIC(10,4) NOT NULL,
    from_account INT NOT NULL,
    to_account INT NOT NULL,
    employee_registrationNbr INT NOT NULL,
    FOREIGN KEY (from_account) REFERENCES account(number) ON DELETE CASCADE,
    FOREIGN KEY (to_account) REFERENCES account(number) ON DELETE CASCADE,
    FOREIGN KEY (employee_registrationNbr) REFERENCES employee(registrationNbr) ON DELETE CASCADE
);

CREATE TABLE employee_history(
    id VARCHAR(5) DEFAULT ('ID' || nextval('employee_history_seq')::text) PRIMARY KEY,
    employee_registrationNbr INT NOT NULL,
    agency_code TEXT NOT NULL,
    transfer_date DATE NOT NULL,
    FOREIGN KEY (employee_registrationNbr) REFERENCES employee(registrationNbr) ON DELETE CASCADE,
    FOREIGN KEY (agency_code) REFERENCES agency(code) ON DELETE CASCADE
);

CREATE TABLE CREDIT(
    id SERIAL PRIMARY KEY,
    client_code TEXT NOT NULL,
    agency_code VARCHAR(9) NOT NULL,
    employee_registrationNbr INT NOT NULL,
    credit_value INT NOT NULL,
    status TEXT CHECK (status IN ('PENDING', 'ACCEPTED', 'REFUSED')),
    duration INT NOT NULL,
    remark TEXT,
    FOREIGN KEY(client_code) REFERENCES client(code),
    FOREIGN KEY(agency_code) REFERENCES agency(code),
    FOREIGN KEY(employee_registrationNbr) REFERENCES employee(registrationNbr)
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

CREATE OR REPLACE FUNCTION getClientCurrentStatus(current_status TEXT) RETURNS TABLE (
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
        account ON current_account.account_number = account.number WHERE account.status = getClientCurrentStatus.current_status;
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

CREATE OR REPLACE FUNCTION getClientCurrentByDate(account_date DATE) RETURNS TABLE (
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
        account ON current_account.account_number = account.number WHERE account.creationDate = getClientCurrentByDate.account_date;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION getClientSavingByDate(account_date DATE) RETURNS TABLE (
    balance NUMERIC(10, 4),
    creationDate DATE,
    Status TEXT,
    code TEXT,
    tax NUMERIC(10, 4) -- Corrected data type to match saving_account's tax column
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
        account ON saving_account.account_number = account.number WHERE account.creationDate = getClientSavingByDate.account_date;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION getAccountSavingByOp(operation_nbr INT) RETURNS TABLE (
    number INT,
    balance NUMERIC(10, 4),
    creationDate DATE,
    Status TEXT
) AS $$
BEGIN
    RETURN QUERY SELECT
        account.number,
        account.balance,
        account.creationDate,
        account.Status
    FROM
        operation
    JOIN
        account ON operation.account_number = account.number WHERE operation.number = getAccountSavingByOp.operation_nbr;
END;
$$ LANGUAGE plpgsql;