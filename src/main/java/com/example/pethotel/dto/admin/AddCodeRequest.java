package com.example.pethotel.dto.admin;

import com.example.pethotel.entity.CommonCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddCodeRequest {

    private String codeHead;
    private String codeDetail;
    private String codeName;
    private String codeUse;


    public CommonCode toEntity() {
        return CommonCode.builder()
                .codeHead(codeHead)
                .codeDetail(codeDetail)
                .codeName(codeName)
                .codeUse(codeUse)
                .build();
    }

}
