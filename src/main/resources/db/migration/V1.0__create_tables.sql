CREATE TABLE employee
(
    code              TEXT NOT NULL,
    telegram_username TEXT NOT NULL,
    full_name         TEXT NOT NULL,
    phone             TEXT NOT NULL,
    PRIMARY KEY (code)
);

CREATE TABLE role
(
    code        TEXT NOT NULL,
    description TEXT,
    PRIMARY KEY (code)
);

CREATE TABLE shift
(
    id            BIGSERIAL,
    date          DATE NOT NULL,
    role_code     TEXT NOT NULL,
    employee_code TEXT NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (date, role_code, employee_code),
    FOREIGN KEY (role_code) REFERENCES role (code) ON UPDATE CASCADE,
    FOREIGN KEY (employee_code) REFERENCES employee (code) ON UPDATE CASCADE
);
