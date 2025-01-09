package com.example.pethotel.service;

import com.example.pethotel.dto.AddUserRequest;
import com.example.pethotel.dto.UpdateUserRequest;
import com.example.pethotel.entity.User;
import com.example.pethotel.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public User save(AddUserRequest req) {
        return userRepository.save(User.builder()
                .username(req.getUsername())
                .userid(req.getUserid())
                .password(passwordEncoder.encode(req.getPassword()))
                .nickname(req.getNickname())
                .userphone(req.getUserphone())
                .userrole(req.getUserrole())
                .build());
    }

    public boolean isUserIdTaken(String userid) {
        return userRepository.existsByUserid(userid);
    }

    @Transactional
    public User update(Long id, UpdateUserRequest req) {
        User user = userRepository.findById(id)
                .orElseThrow(() ->  new IllegalArgumentException("not found : "+id));
        user.update(req.getUsername(), passwordEncoder.encode(req.getPassword()), req.getNickname(), req.getUserphone());
        return user;
    }

    @Transactional
    public void updateUserstatus(Long id, String userstatus) {
        User user = userRepository.findById(id)
                .orElseThrow(() ->  new IllegalArgumentException("not found : "+id));
        user.updateUserstatus(userstatus);

    }
}
