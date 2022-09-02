package ru.job4j.cinema.store;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import ru.job4j.cinema.model.Ticket;

@Repository
public class TicketStore {

    private static final Logger LOG = Logger.getLogger(TicketStore.class.getName());

    private final BasicDataSource pool;

    public TicketStore(BasicDataSource pool) {
        this.pool = pool;
    }

    public Ticket createTicket() {
        return null;
    }


}
