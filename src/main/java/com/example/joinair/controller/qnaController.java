package com.example.joinair.controller;

import com.example.joinair.service.qnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/qna")
public class qnaController {
    @Autowired
    private qnaService qnaService;

    @GetMapping("/noticeList")
    public ModelAndView qnaList() {
        System.out.println("test1");
        List<Map<String, Object>> qnaList = qnaService.qnaList();
        ModelAndView mv = new ModelAndView();
        System.out.println("test2");

        mv.setViewName("qna");
        mv.setStatus(HttpStatus.valueOf(200));
        mv.addObject("qna", qnaList);
        return mv;
    }
}
