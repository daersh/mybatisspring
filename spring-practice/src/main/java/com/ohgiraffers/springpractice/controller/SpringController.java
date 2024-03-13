package com.ohgiraffers.springpractice.controller;

import com.ohgiraffers.springpractice.DTO.BoardDTO;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/dml", method = RequestMethod.GET)
public class SpringController {
    @GetMapping("/dml-form")
    public String dmlForm(){return "dml-form";}

    @GetMapping("/board-write")
    public String boardWrite(){return "board-write";}
    @GetMapping("/board")
    public String  board(ModelAndView modelAndView){
        return "board";
    }
    @PostMapping("board-write")
    public String boardWrite2(){
        return "board";
    }

}
