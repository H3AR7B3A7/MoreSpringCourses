package be.dog.d.steven.app7;

import org.springframework.stereotype.Component;

@Component
public class Employee2 {
    private final Sal salary;

    public Employee2(Sal salary) {
        this.salary = salary;
    }

    public Sal getSalary() {
        return salary;
    }
}