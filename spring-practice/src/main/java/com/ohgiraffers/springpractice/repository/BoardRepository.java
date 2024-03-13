package com.ohgiraffers.springpractice.repository;

import com.ohgiraffers.springpractice.DTO.BoardDTO;
import com.ohgiraffers.springpractice.mapper.BoardMapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardRepository {
    private final SqlSessionTemplate sqlSession;

    @Autowired
    public BoardRepository(SqlSessionTemplate sqlSession){
        this.sqlSession = sqlSession;
    }
    public List<BoardDTO> findAllBoardList(){
        return sqlSession.getMapper(BoardMapper.class).findAllBoardList();
    }
}
