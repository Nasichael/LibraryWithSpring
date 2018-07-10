package org.halas.agnieszka.library.inventory;


import org.halas.agnieszka.library.data.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface UserInventory {

    Collection<User> findAll();

    Optional<User> findById(int userId);



}