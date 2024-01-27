package com.community_board.demo.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

public class Comment {

    @Id
    // 기본키를 자동으로 생성해주는 어노테이션
    // Identity 전략은 기본 키 생성을 데이터 베이스에 위임하는 전략
    // 주의점 : 엔티티가 영속 상태가 되기 위해선 식별자가 꼭 필요로 하다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 댓글 내용
    @Column(columnDefinition = "TEXT", nullable = false)
    private String comment;

    @Column(name = "created_date")
    @CreatedDate
    private String createdDate;

    @Column(name = "modified_date")
    @LastModifiedDate
    private String modifiedDate;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // 댓글 수정
    public void update(String commnet) {
        this.comment = comment;
    }
}
