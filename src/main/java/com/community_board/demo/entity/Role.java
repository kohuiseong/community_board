package com.community_board.demo.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    User("ROLE_USER");
    ADMIN("ROLE_ADMIN");
    SOCIAL("ROLE_SOCIAL");

    private final String value;
}
