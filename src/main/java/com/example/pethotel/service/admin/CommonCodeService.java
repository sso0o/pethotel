package com.example.pethotel.service.admin;

import com.example.pethotel.dto.admin.AddCodeRequest;
import com.example.pethotel.entity.CommonCode;
import com.example.pethotel.repository.admin.CommonCodeRepository;
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

    // 코드헤더 중복체크
    public Optional<CommonCode> findByCodeHead(String codeHead) {
        return commonCodeRepository.findByCodeHead(codeHead);
    }
    public Optional<CommonCode> findByCode(String code) {
        return commonCodeRepository.findByCode(code);
    }

    public List<CommonCode> findAll(){
        return commonCodeRepository.findAll();
    }
}
