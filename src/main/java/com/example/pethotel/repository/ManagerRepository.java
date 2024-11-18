package com.example.pethotel.repository;

import com.example.pethotel.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ManagerRepository extends JpaRepository<User, Long> {

    List<User> findByUserroleAndUserstatus(String userrole, String userstatus);
}