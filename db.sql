DROP TABLE IF EXISTS client;
DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS mission;
DROP TABLE IF EXISTS current_account;
DROP TABLE IF EXISTS saving_account;
DROP TABLE IF EXISTS operation;
DROP TABLE IF EXISTS mission_employee;

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
      dateOfRecrutment DATE NOT NULL,
);

CREATE TABLE mission(
      code SERIAL PRIMARY KEY,
      name TEXT NOT NULL,
      description TEXT NOT NULL
);

CREATE TABLE account(
      number int PRIMARY KEY,
      balance NUMERIC(10, 4) NOT NULL,
      creationDate Date NOT NULL,
      status Text NOT NULL,
      client_code TEXT NOT NULL,
      FOREIGN KEY (client_code) REFERENCES client(code)
);

CREATE TABLE current_account(
      account_number INT NOT NULL,
      overDraft NUMERIC(6, 4) NOT NULL,
      FOREIGN KEY (account_number) REFERENCES account(number)
);

CREATE TABLE saving_account(
      account_number INT NOT NULL,
      tax NUMERIC(3, 2) NOT NULL,
      FOREIGN KEY (account_number) REFERENCES account(number)
);

CREATE TABLE operation(
      number SERIAL PRIMARY KEY,
      creationDate Date NOT NULL,
      amount NUMERIC(7, 4) NOT NULL,
      type TEXT NOT NULL,
      account_number INT NOT NULL,
      FOREIGN KEY (account_number) REFERENCES account(number)
);

create mission_employee(
    mission_number INT NOT NULL,
    employee_registrationNbr INT NOT NULL,
    startDate Date NOT NULL,
    endDate Date DEFAULT NULL,
    PRIMARY KEY (mission_number, registrationNbr),
    FOREIGN KEY (mission_number) REFERENCES mission(number),
    FOREIGN KEY (employee_registrationNbr) REFERENCES employee(registrationNbr)
);