package com.example.pethotel.service.admin;

import com.example.pethotel.dto.admin.AddCodeRequest;
import com.example.pethotel.dto.admin.UpdateCodeRequest;
import com.example.pethotel.entity.CommonCode;
import com.example.pethotel.repository.CommonCodeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommonCodeService {
    private final CommonCodeRepository commonCodeRepository;

    // 코드 저장 요청
    public CommonCode save(AddCodeRequest req) {
        return commonCodeRepository.save(req.toEntity());
    }

    // 코드헤더 수정 요청
    @Transactional
    public CommonCode updateHead(Long id, UpdateCodeRequest req) {
        CommonCode commonCode = commonCodeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
        commonCode.updateHead(req.getCodeName(), req.getCodeUse());
        return commonCode;
    }

    // 코드디테일 수정 요청
    @Transactional
    public CommonCode updateDetail(Long id, UpdateCodeRequest req) {
        CommonCode commonCode = commonCodeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
        commonCode.updateDetail(req.getCodeHead(), req.getCodeDetail(), req.getCodeName(), req.getCodeUse());
        return commonCode;
    }

    // 코드헤더 중복체크
    public Optional<CommonCode> findByCodeHead(String codeHead) {
        return commonCodeRepository.findByCodeHead(codeHead);
    }
    public Optional<CommonCode> findByCode(String code) {
        return commonCodeRepository.findByCode(code);
    }
    public int countByCodeAndIdNot(String code, Long id) {
        return commonCodeRepository.countByCodeAndIdNot(code, id);
    }

    // 코드 헤더 조회
    public List<CommonCode> findByCodeDetailAndCodeUseLike(String codeDetail, String codeUse) {
        return commonCodeRepository.findByCodeDetailAndCodeUseLike(codeDetail, codeUse);
    }

    // 코드 디테일 조회 + 셀렉트 박스
    public List<CommonCode> findByCodeHeadAndCodeDetailNotAndCodeUseLike(String codeHead, String codeDetail, String codeUse) {
        return commonCodeRepository.findByCodeHeadAndCodeDetailNotAndCodeUseLike(codeHead, codeDetail, codeUse);
    }

    // id로 코드 조회
    public Optional<CommonCode> findById(Long codeId) {
        return commonCodeRepository.findById(codeId);
    }

    public Optional<CommonCode> findByCodeHeadAndCodeDetail(String codeHead, String codeDetail, String codeUse) {
        return commonCodeRepository.findByCodeHeadAndCodeDetailAndCodeUse(codeHead, codeDetail, codeUse);
    }

    // 셀렉트박스용

}
