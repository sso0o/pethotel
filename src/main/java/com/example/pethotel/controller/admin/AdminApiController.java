package com.example.pethotel.controller.admin;

import com.example.pethotel.dto.admin.AddCodeRequest;
import com.example.pethotel.entity.CommonCode;
import com.example.pethotel.service.admin.CommonCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AdminApiController {

    private final CommonCodeService commonCodeService;

    // 공통코드 헤더 호출
    @GetMapping("/admin/codeHead")
    public ResponseEntity<List<CommonCode>> codeHead() {
        List<CommonCode> commonCodes = commonCodeService.findAll();
        return ResponseEntity.ok(commonCodes);
    }

    // 공통코드 저장 요청
    @PostMapping("/admin/code")
    public ResponseEntity saveCode(@RequestBody AddCodeRequest req) {
        HashMap<Object, Object> resultMap = new HashMap<>();

        //코드헤더 중복확인
        Optional<CommonCode> codeChk = commonCodeService.findByCode(req.getCodeHead()+req.getCodeDetail());
        if (codeChk.isPresent()) { //중복값이 있을경우
            resultMap.put("msg", "중복된 코드 헤더가 존재합니다");
            return ResponseEntity.badRequest().body(resultMap);
        }else { // 중복값이 없으면 바로 저장
            CommonCode saveCode = commonCodeService.save(req);
            resultMap.put("msg", "등록을 성공하였습니다");
            resultMap.put("val", saveCode);
            return ResponseEntity.ok().body(resultMap);
        }

    }

}
