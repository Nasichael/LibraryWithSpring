package org.halas.agnieszka.library.inventory.db;

import org.halas.agnieszka.library.data.Booking;
import org.halas.agnieszka.library.data.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
}

