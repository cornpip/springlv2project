package com.sparta.springlv2project.entity;

import com.sparta.springlv2project.dto.boardDto.PostRequestDto;
import io.jsonwebtoken.Claims;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name="post")
public class Post extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String subject;
    @Column(nullable = false)
    private String contents;

    public Post(PostRequestDto postRequestDto, Claims username) {
        this.username = username.getSubject();
        this.subject = postRequestDto.getSubject();
        this.contents = postRequestDto.getContents();
    }

}
