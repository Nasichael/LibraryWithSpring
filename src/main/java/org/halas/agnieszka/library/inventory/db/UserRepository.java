package org.halas.agnieszka.library.inventory.db;

import org.halas.agnieszka.library.data.Book;
import org.halas.agnieszka.library.data.User;
import org.halas.agnieszka.library.inventory.UserInventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>, UserInventory {
}
