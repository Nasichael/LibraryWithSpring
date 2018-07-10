package org.halas.agnieszka.library.inventory.db;

import org.halas.agnieszka.library.data.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
