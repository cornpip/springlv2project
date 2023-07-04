package com.sparta.springlv2project.service;

import com.sparta.springlv2project.dto.boardDto.PostResponseDto;
import com.sparta.springlv2project.dto.boardDto.PostRequestDto;
import com.sparta.springlv2project.entity.Post;
import com.sparta.springlv2project.jwt.JwtUtil;
import com.sparta.springlv2project.repository.BoardRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {


    private final BoardRepository boardRepository;
    private final JwtUtil jwtUtil;

    public BoardService(BoardRepository boardRepository, JwtUtil jwtUtil) {
        this.boardRepository = boardRepository;
        this.jwtUtil = jwtUtil;
    }
    public void posting(PostRequestDto postRequestDto, HttpServletRequest res) {
        String Token = jwtUtil.getTokenFromRequest(res);
        Claims userToken=jwtUtil.getUserInfoFromToken(jwtUtil.substringToken(Token));
            Post post = new Post(postRequestDto, userToken);
            boardRepository.save(post);
    }


    public List<PostResponseDto> getAllPost() {
        return boardRepository.findAll().stream().map(PostResponseDto::new).toList();
    }

    public List<PostResponseDto> getUserPost(HttpServletRequest res) {
        String Token = jwtUtil.getTokenFromRequest(res);
        String userToken=jwtUtil.getUserInfoFromToken(jwtUtil.substringToken(Token)).getSubject();
        return boardRepository.findAllByUsername(userToken).stream().map(PostResponseDto::new).toList();
    }

    //수정 & 삭제기능 구현 필요
}
