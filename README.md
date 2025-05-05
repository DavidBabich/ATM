# Проект ATM на Java

Это приложение банкомата, написанное на Java с использованием PostgreSQL для хранения данных. 

## Возможности
- Аутентификация пользователя по PIN-коду.
- Отображение текущего баланса счёта.
- Просмотр истории транзакций.

## Требования
- Java 17
- Maven
- PostgreSQL


**Настройка базы данных**:
   - Следующие SQL-команды были использованы для создания таблиц и добавления тестовых данных в базу:
     ```sql
     CREATE TABLE account_holders (
         id SERIAL PRIMARY KEY,
         name VARCHAR(100) NOT NULL,
         pin VARCHAR(4) NOT NULL,
         balance DECIMAL(10, 2) NOT NULL
     );

     CREATE TABLE transactions (
         id SERIAL PRIMARY KEY,
         account_holder_id INT REFERENCES account_holders(id),
         type VARCHAR(50) NOT NULL,
         amount DECIMAL(10, 2) NOT NULL,
         date DATE NOT NULL
     );

     INSERT INTO account_holders (name, pin, balance) VALUES ('Иван Иванов, '1234', 12345.67);
     INSERT INTO transactions (account_holder_id, type, amount, date) VALUES
     (1, 'Снятие', 1234.00, '2025-05-03'),
     (1, 'Пополнение', 1234.00, '2025-05-03');
     ```

## Структура проекта
- `src/main/java/atm/`: Содержит все Java-файлы.
  - `AccountHolder.java`: Класс, представляющий владельца счёта с именем, PIN и балансом.
  - `Transaction.java`: Класс, представляющий транзакцию с типом, её суммой и датой.
  - `DatabaseManager.java`: Управляет подключением к PostgreSQL и выполнением запросов.
  - `ATM.java`: Реализует логику банкомата для аутентификации и отображения данных.
  - `ATMProgramWithPostgres.java`: Главный класс с точкой входа, обрабатывающий ввод пользователя.

## Примечания
- Это начальная версия проекта, в дальнейшем добавлю контейнеризации в докере.


