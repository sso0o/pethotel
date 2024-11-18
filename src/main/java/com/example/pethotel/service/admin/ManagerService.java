package com.example.pethotel.service.admin;

import com.example.pethotel.entity.User;
import com.example.pethotel.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManagerService {
    private final ManagerRepository managerRepository;

    public List<User> findByUserroleAndUserstatus(String userrole, String userstatus) {
        return managerRepository.findByUserroleAndUserstatus(userrole, userstatus);
    }
}
