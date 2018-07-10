package org.halas.agnieszka.library.inventory.db;

import org.halas.agnieszka.library.data.Booking;
import org.halas.agnieszka.library.data.User;
import org.halas.agnieszka.library.inventory.BookingInventory;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Profile("db")
@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer>, BookingInventory {
}

