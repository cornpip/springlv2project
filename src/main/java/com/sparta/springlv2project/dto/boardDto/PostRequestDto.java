package com.sparta.springlv2project.dto.boardDto;

import lombok.Getter;

@Getter
public class PostRequestDto {
    private String subject;
    private String username;
    private String contents;
}
