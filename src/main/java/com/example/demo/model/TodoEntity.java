package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity // Entity 클래스임을 명시, 데이터베이스에 저장될 것을 지정
@Table(name = "Todo") // Todo 테이블에 저장될 것
// 퍼시스턴트 계층, 비즈니스 계층에서 사용되는 데이터 
public class TodoEntity {
    @Id // 기본 키 지정
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id; 
    private String userId; // 이메일 
    private String title; // 할 일 이름
    private boolean done; // 완료 여부 
}
