package com.example.pethotel.repository;

import com.example.pethotel.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserid(String userid);

    boolean existsByUserid(String userid);
}
