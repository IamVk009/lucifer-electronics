package com.lucifer.electronic.store.repositories;

import com.lucifer.electronic.store.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
