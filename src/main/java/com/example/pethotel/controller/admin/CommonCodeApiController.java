package com.example.pethotel.controller.admin;

import com.example.pethotel.dto.admin.AddCodeRequest;
import com.example.pethotel.dto.admin.UpdateCodeRequest;
import com.example.pethotel.entity.CommonCode;
import com.example.pethotel.service.admin.CommonCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CommonCodeApiController {
    private final CommonCodeService commonCodeService;

    //=============================================================================================
    //================================              get               =============================
    //=============================================================================================

    // 공통코드 헤더 호출
    @GetMapping("/admin/codeHead")
    public ResponseEntity codeHead() {
        HashMap<Object, Object> resultMap = new HashMap<>();

        List<CommonCode> commonCodes = commonCodeService.findByCodeDetailAndCodeUseLike("","Y");
        resultMap.put("codeHeads", commonCodes);
        return ResponseEntity.ok().body(resultMap);
    }

    // 코드헤드로 디테일 가져오기
    @GetMapping("/admin/codeHead/{codehead}")
    public ResponseEntity getDetailsByHead(@PathVariable String codehead) {
        //HashMap<Object, Object> resultMap = new HashMap<>();
        List<CommonCode> codeDetails = commonCodeService.findByCodeHeadAndCodeDetailNotAndCodeUseLike(codehead, "","Y");
        //resultMap.put("codeDetails", codeDetails);
        return ResponseEntity.ok().body(codeDetails);
    }

    // id로 코드 조회
    @GetMapping("/admin/code/{codeId}")
    public ResponseEntity getDetailsByCode(@PathVariable Long codeId) {
        Optional<CommonCode> code = commonCodeService.findById(codeId);
        return ResponseEntity.ok().body(code);
    }


    //=============================================================================================
    //================================              post              =============================
    //=============================================================================================

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
            resultMap.put("msg", "요청 성공");
            resultMap.put("val", saveCode);
            return ResponseEntity.ok().body(resultMap);
        }
    }


    //=============================================================================================
    //================================              put               =============================
    //=============================================================================================

    // 공통코드 수정
    @PutMapping("/admin/code/{codeId}")
    public ResponseEntity updateCode(@PathVariable Long codeId, @RequestBody UpdateCodeRequest req) {
        HashMap<Object, Object> resultMap = new HashMap<>();

        // 헤더면 중복 체크 안함
        if(req.getCodeDetail().equals("")){
            CommonCode code = commonCodeService.updateHead(codeId, req);
            resultMap.put("msg", "요청 성공");
        }else{
            // 디테일만 중복확인
            int codeChk = commonCodeService.countByCodeAndIdNot(req.getCodeHead()+req.getCodeDetail(),codeId);
            if (codeChk > 0) { //중복값이 있을경우
                resultMap.put("msg", "중복된 코드가 존재합니다");
                return ResponseEntity.badRequest().body(resultMap);
            }else { // 중복값이 없으면 바로 저장
                CommonCode code = commonCodeService.updateDetail(codeId, req);
                resultMap.put("msg", "요청 성공");
            }
        }

        return ResponseEntity.ok().body(resultMap);
    }


}
