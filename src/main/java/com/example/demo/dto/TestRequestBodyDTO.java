package com.example.demo.dto;

import lombok.Data;

// 객체 전달 테스트용 데이터 
@Data // getter, setter 자동생성 
public class TestRequestBodyDTO {
    private int id; 
    private String message; 
}
