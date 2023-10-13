package com.example.joinair.controller;

import com.example.joinair.dto.QNA;
import com.example.joinair.service.qnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/qna")
public class qnaController {
    @Autowired
    private qnaService qnaService;

    @GetMapping("/qnaList")
    public ModelAndView qnaList() {
        List<QNA> qnaList = qnaService.qnaList();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("qna");
        mv.setStatus(HttpStatus.OK);
        mv.addObject("qna", qnaList);
        return mv;
    }

    @GetMapping("/qnaview")
    public ModelAndView qnaView(@RequestParam int qna_No) {
        QNA result = qnaService.qna(qna_No);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("qnadetail");
        mv.setStatus(HttpStatus.OK);
        mv.addObject("qna", result);
        return mv;
    }

    @GetMapping("/qnainsert-view")
    public ModelAndView qnaInsertView() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("qnainsert");
        mv.setStatus(HttpStatus.OK);
        return mv;
    }

    @ResponseBody
    @PostMapping("/qnainsert")
    public boolean qnainsert(@RequestBody QNA qna) {
        return qnaService.qnainsert(qna);
    }

    @GetMapping("/qnaupdate-view")
    public ModelAndView qnaUpdateView(@RequestParam int qna_No) {
        QNA result = qnaService.qna(qna_No);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("qnaupdate");
        mv.setStatus(HttpStatus.OK);
        mv.addObject("qna", result);
        return mv;
    }

    @ResponseBody
    @PutMapping("/qnaupdate")
    public boolean qnaupdate(@RequestBody QNA qna) {
        return qnaService.qnaupdate(qna);
    }

    @ResponseBody
    @DeleteMapping("/qnadelete")
    public boolean qnadelete(@RequestParam int qna_No) {
        return qnaService.qnadelete(qna_No);
    }
}
