package com.pluralsight.conferencedemo.models;

import com.pluralsight.conferencedemo.repositories.TicketTypeJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class TicketTypeTest {
    @Autowired
    private TicketTypeJpaRepository repository;

    @Test
    void testJpaTrue() {
        List<TicketType> types = repository.findByIncludesWorkshopTrue();
        assertTrue(types.size() > 0);
    }
}