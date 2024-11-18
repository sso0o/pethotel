package com.example.pethotel.repository.admin;

import com.example.pethotel.entity.CommonCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommonCodeRepository extends JpaRepository<CommonCode, Long> {

    // 코드헤더 중복체크용
    Optional<CommonCode> findByCodeHead(String codeHead);
    Optional<CommonCode> findByCode(String code);
    int countByCodeAndIdNot(String code, Long codeId);

    // 코드 헤더 조회
    List<CommonCode> findAll();
    List<CommonCode> findByCodeDetailAndCodeUseLike(String codeDetail, String codeUse);

    // 코드 디테일 조회
    List<CommonCode> findByCodeHeadAndCodeDetailNotAndCodeUseLike(String codeHead, String codeDetail, String codeUse);

    // id값으로 코드 조회
    Optional<CommonCode> findById(Long codeId);
}
