package org.halas.agnieszka.library.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = SEQUENCE)

    private int id;
    private String name;
    private String firstName;

    public User() {
    }

    public User(String name, String firstName) {
        this.name = name;
        this.firstName = firstName;
    }

    public User(int id, String name, String firstName) {
        this.id = id;
        this.name = name;
        this.firstName = firstName;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFirstName() {
        return firstName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (getId() != user.getId()) return false;
        if (!getName().equals(user.getName())) return false;
        return getFirstName().equals(user.getFirstName());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getFirstName().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", firstName='" + firstName + '\'' +
                '}';
    }
}
