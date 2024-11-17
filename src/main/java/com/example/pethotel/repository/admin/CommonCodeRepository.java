package com.example.pethotel.repository.admin;

import com.example.pethotel.entity.CommonCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommonCodeRepository extends JpaRepository<CommonCode, Long> {

    // 코드헤더 중복체크용
    Optional<CommonCode> findByCodeHead(String codeHead);
    Optional<CommonCode> findByCode(String code);

    List<CommonCode> findAll();
}
