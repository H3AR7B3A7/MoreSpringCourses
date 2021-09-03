package be.dog.d.steven.trackzilla.repository;

import be.dog.d.steven.trackzilla.entity.Ticket;
import org.springframework.data.repository.CrudRepository;

public interface TicketRepository extends CrudRepository<Ticket, Long> {
}
