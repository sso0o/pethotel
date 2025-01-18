package com.example.pethotel.controller.main;

import com.example.pethotel.entity.CommonCode;
import com.example.pethotel.service.CommonService;
import com.example.pethotel.service.admin.CommonCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MainApiController {

    private final CommonCodeService commonCodeService;

    private final CommonService commonService;

    //=============================================================================================
    //================================              get               =============================
    //=============================================================================================

    // 메인에서 공통코드 헤더로 디테일 가져오기
    @GetMapping("/main/commoncode/{codeHead}")
    public ResponseEntity getCommonCode(@PathVariable String codeHead) {
        HashMap<Object, Object> resultMap = new HashMap<>();
        List<CommonCode> codes = commonCodeService.findByCodeHeadAndCodeDetailNotAndCodeUseLike(codeHead, "", "Y");
        resultMap.put("codes", codes);
        return ResponseEntity.ok().body(resultMap);
    }




    //=============================================================================================
    //================================              post              =============================
    //=============================================================================================


    //=============================================================================================
    //================================              put               =============================
    //=============================================================================================


    //=============================================================================================
    //================================              del               =============================
    //=============================================================================================



}
