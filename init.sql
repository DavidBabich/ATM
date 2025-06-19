CREATE TABLE IF NOT EXISTS account_holders (
                                               id SERIAL PRIMARY KEY,
                                               name VARCHAR(100) NOT NULL,
    pin VARCHAR(10) NOT NULL UNIQUE,
    balance NUMERIC(10, 2) DEFAULT 0
    );

CREATE TABLE IF NOT EXISTS transactions (
                                            id SERIAL PRIMARY KEY,
                                            account_holder_id INT REFERENCES account_holders(id),
    type VARCHAR(20) NOT NULL,
    amount NUMERIC(10, 2) NOT NULL,
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

INSERT INTO account_holders (name, pin, balance) VALUES
                                                     ('Иван Иванов', '1234', 1000.00),
                                                     ('Лера Иванова', '5678', 2500.50);

INSERT INTO transactions (account_holder_id, type, amount, date) VALUES
                                                                     (1, 'Снятие', 200.00, '2025-05-08 10:00:00'),
                                                                     (1, 'Пополнение', 500.00, '2025-05-08 12:30:00'),
                                                                     (2, 'Снятие', 100.50, '2025-05-08 14:00:00');