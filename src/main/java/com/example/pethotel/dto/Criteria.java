package com.example.pethotel.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {

    private int page;	//페이지 번호

    private int size;	//한 페이지당 출력 DATA 개수

    public Criteria(){
        this(1,4);
    }

    public Criteria(int page,int size) {
        this.page=page;
        this.size=size;
    }

    public int getSkip() {
        return Math.max(0, (this.page - 1) * this.size);
    }

}
