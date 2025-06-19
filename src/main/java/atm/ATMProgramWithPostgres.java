package atm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class ATMProgramWithPostgres {

    @Autowired
    private ATM atm;

    public static void main(String[] args) {
        SpringApplication.run(ATMProgramWithPostgres.class, args);
    }

    @GetMapping("/")
    public String home() {
        return "Добро пожаловать!";
    }

    @GetMapping("/login")
    public String login(@RequestParam String pin) {
        if (atm.login(pin)) {
            StringBuilder response = new StringBuilder();
            response.append("Баланс для ").append(atm.getAccountHolder().getName()).append(": ")
                    .append(String.format("%.2f%n", atm.getAccountHolder().getBalance()));
            atm.displayTransactions();
            return response.toString();
        } else {
            return "Неверный PIN. Доступ запрещён.";
        }
    }
}