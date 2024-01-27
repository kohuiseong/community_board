package com.community_board.demo.entity;

import com.community_board.demo.domain.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class User extends BaseTimeEnitiy{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30, unique = true)
    private String userName;

    @Column(nullable = false, unique = true)
    private String nickName;

    @Column(length = 100)
    private String passWord;

    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    // 회원 정보 수정
    public void modify(String name, String passWord) {
        this.nickName = nickName;
        this.passWord = passWord;
    }

    // 소셜 로그인시 이미 등록된 회원 이라면 수정날짜만 업데이트 해줘서 기존 데이터를 보존 하도록 예외 처리 하기
    public User updateModifiedDate() {
        this.onPreUpdate();
        return this;
    }

    public String getRoleValue() {
        return this.role.getValue();
    }
}
