package be.dog.d.steven.app9;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@SalaryScope
public class Salary implements Sal {
    private static final Logger LOGGER = LoggerFactory.getLogger(Salary.class);
    private static final Random RANDOM = new Random();

    private final Integer amount;

    public Salary() {
        LOGGER.info("Creating new salary bean...");
        this.amount = RANDOM.nextInt(25000) + 25000;
    }

    public Integer getAmount() {
        return amount;
    }
}