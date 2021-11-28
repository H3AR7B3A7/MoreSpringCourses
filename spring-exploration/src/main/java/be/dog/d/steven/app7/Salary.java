package be.dog.d.steven.app7;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Salary {
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