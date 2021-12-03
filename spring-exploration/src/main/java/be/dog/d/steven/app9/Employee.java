package be.dog.d.steven.app9;

import org.springframework.stereotype.Component;

@Component
public class Employee {
    private final Sal salary;

    public Employee(Sal salary) {
        this.salary = salary;
    }

    public Sal getSalary() {
        return salary;
    }
}