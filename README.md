
# ATM Application

ATM — это мой Java-проект, реализующий простую систему банкомата. Поддерживаются:
- авторизация по PIN-коду
- просмотр текущего баланса
- отображение истории транзакций

Проект реализован в двух версиях:
Актуальная (REST API + Spring Boot)
Legacy(консольная Java-программа)

---

### Технологии:
- Java 17
- Spring Boot 
- PostgreSQL
- Docker + Docker Compose
- Maven

### Структура API:

| Метод | URL | Описание |
|-------|-----|----------|
| `GET` | `/api/login?pin=1234` | Авторизация по PIN |
| `GET` | `/api/balance`        | Баланс авторизованного пользователя |
| `GET` | `/api/transactions`   | История транзакций |

### Быстрый запуск через Docker:
Качаем докер и делаем:

```
docker-compose up --build
```

Приложение будет доступно по локальному адресу:
```
http://localhost:8080
```

```
GET http://localhost:8080/api/login?pin=1234
GET http://localhost:8080/api/balance
GET http://localhost:8080/api/transactions
```

---

### Legacy версия

Зархивированная версия проекта доступна в папке [`legacy/console-version`](legacy/console-version).  
Это первый вариант программы без Spring'а и использования Docker.

### Как запустить:

```
javac legacy/console-version/*.java
java atm.ATMProgramWithPostgres
```

---

### Настройка базы данных

По умолчанию используется PostgreSQL с такими параметрами (можно изменить в `application.properties` или `docker-compose.yml`):

- URL: `jdbc:postgresql://localhost:5432/atm_db`
- User: `postgres`
- Password: `111111`

SQL-инициализация происходит из файла `init.sql`.

---

## TODO

- [ ] Обработка ошибок
- [ ] JWT авторизация
- [ ] Тесты на JUnit + MockMvc
