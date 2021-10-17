package com.pluralsight.conferencedemo.repositories;

import com.pluralsight.conferencedemo.models.TicketPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface TicketPriceJpaRepository extends JpaRepository<TicketPrice, Long> {
    
    @Query(
            "SELECT tp FROM TicketPrice tp WHERE tp.basePrice < ?1 " +
                    "AND tp.ticketType.includesWorkshop = TRUE"
    )
    List<TicketPrice> getTicketsUnderPriceWithWorkshops(BigDecimal maxPrice);

    // Named Query: TicketPrice.java
    List<TicketPrice> namedFindTicketsByPricingCategoryName(@Param("name") String name);
}