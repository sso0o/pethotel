package com.example.pethotel.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpdateCodeRequest {
    private String codeHead;
    private String codeDetail;
    private String codeName;
    private String codeUse;
}
