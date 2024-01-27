package com.community_board.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity {

    @Column(name = "created_date" , nullable = false)
    @CreatedDate // 생성시 날짜 자동 생성
    private String createdDate;

    @Column(name = "modified_date", nullable = false)
    @LastModifiedDate // 수정시 날짜 자동 갱신
    private String modifiedDate;

    // 해당 엔터티를 저장 하기 이전에 실행
    @PrePersist
    public void onPrePersist() {
        this.createdDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyy.mm.dd"));
        this.modifiedDate = this.createdDate;
    }

    // 해당 엔티티를 업데이트 하기 이전에 실행
    @PreUpdate
    public void onPreUDate() {
        this.modifiedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.mm.dd"));
    }


}
