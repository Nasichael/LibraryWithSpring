package org.halas.agnieszka.library.inventory.memory;

import org.halas.agnieszka.library.data.User;

import org.halas.agnieszka.library.inventory.UserInventory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.*;

@Component
@Profile("inMemory")
public class InMemoryUserInventory implements UserInventory {

    Map<Integer, User> users = new HashMap<>();

    {
        users.put(1, new User(1, "Alicja", "Kos"));
        users.put(2, new User(2, "Barbara", "Las"));
        users.put(3, new User(3, "James", "Fox"));
        users.put(4, new User(4, "Patryk", "Rid"));
        users.put(5, new User(5, "Paweł", "Kyrc"));
        users.put(6, new User(6, "Alicja", "Duda"));
        users.put(7, new User(7, "Mateusz", "Swarowski"));
        users.put(8, new User(8, "Martyna", "Tomaszewska"));
    }

    public List<User> findAll() {
        return (List<User>) users.values();
    }

    public Optional<User> getById(int userId) {
        return Optional.ofNullable(users.get(userId));
    }



}
