package com.sparta.springlv2project.controller;

import com.sparta.springlv2project.dto.boardDto.PostRequestDto;
import com.sparta.springlv2project.dto.boardDto.PostResponseDto;
import com.sparta.springlv2project.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("/posting")
    public ResponseEntity<String> posting(@RequestBody PostRequestDto postRequestDto, HttpServletRequest res){
        boardService.posting(postRequestDto, res);
        return ResponseEntity.status(HttpStatus.OK).body("포스팅 완료 !");
    }
    @GetMapping("/all")
    public List<PostResponseDto> getAllPost() {

        return boardService.getAllPost();
    }
    @GetMapping("/user")
    public List<PostResponseDto> getUserPost(HttpServletRequest res){
        return boardService.getUserPost(res);
    }

    //수정 & 삭제기능 구현 필요22
}
