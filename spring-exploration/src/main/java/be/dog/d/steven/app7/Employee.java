package be.dog.d.steven.app7;

import org.springframework.stereotype.Component;

@Component
public class Employee {
    private final Salary salary;

    public Employee(Salary salary) {
        this.salary = salary;
    }

    public Salary getSalary() {
        return salary;
    }
}