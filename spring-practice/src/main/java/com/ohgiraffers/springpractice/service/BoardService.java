package com.ohgiraffers.springpractice.service;

import com.ohgiraffers.springpractice.DTO.BoardDTO;
import com.ohgiraffers.springpractice.mapper.BoardMapper;
import com.ohgiraffers.springpractice.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    BoardRepository boardRepository;

    public List<BoardDTO> findAllBoardList(){
        List<BoardDTO> boardList = boardRepository.findAllBoardList();
        return boardList;
    }
}
