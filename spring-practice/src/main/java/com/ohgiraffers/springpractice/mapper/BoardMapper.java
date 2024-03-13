package com.ohgiraffers.springpractice.mapper;

import com.ohgiraffers.springpractice.DTO.BoardDTO;

import java.util.List;

public interface BoardMapper {
    List<BoardDTO> findAllBoardList();
}
