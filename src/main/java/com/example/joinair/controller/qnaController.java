package com.example.joinair.controller;

import com.example.joinair.dto.QNA;
import com.example.joinair.dto.QNAPAGE;
import com.example.joinair.service.qnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/qna")

public class qnaController {

    @Autowired
    private qnaService qnaService;

    @GetMapping("/qnaList")
    public ModelAndView qnaList(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int pageSize, QNA qnadto) {

        QNAPAGE pagingInfo = new QNAPAGE();
        pagingInfo.setPage(page);
        pagingInfo.setPageSize(pageSize);

        //기억안남
        List<QNA> qnaList = qnaService.qnaList(qnadto);

        //검색어 + 페이징
        List<QNA> qnaRueslt = qnaService.qnaListWithPaging(pagingInfo, qnadto);

        //총게물 갯수
        int totalItemCount = qnaService.getTotalItemCount();
        int totalPageCount = (int) Math.ceil((double) totalItemCount / pageSize);

        List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPageCount)
                .boxed()
                .collect(Collectors.toList());


        List<String> pagingList = qnaService.pagingList(totalPageCount, page);



        ModelAndView mv = new ModelAndView();

        mv.addObject("pagelist", pagingList);
        mv.setViewName("qnaList");
        mv.setStatus(HttpStatus.OK);
        mv.addObject("qnaList", qnaRueslt);
        mv.addObject("currentPage", page);
        mv.addObject("totalPageCount", totalPageCount);
        mv.addObject("pageNumbers", pageNumbers);



        return mv;
    }

    @GetMapping("/qnadetail/{QNA_NO}")
    public ModelAndView qnaView(QNA QNA_NO) {
        System.out.println(QNA_NO.getQNA_NO());
        QNA result = qnaService.qna(QNA_NO);

        if(result.getQNA_COMMAND()==null){
            result.setQNA_COMMAND("flag");
        }
        //if 끝

        if(result.getQNA_COMMAND().equals("flag")){
            result.setFlag(false);
        } else {
            result.setFlag(true);
        }

        ModelAndView mv = new ModelAndView();
        mv.setViewName("qnadetail");
        mv.setStatus(HttpStatus.OK);
        mv.addObject("qna" , result);
        return mv;
    }
    @PostMapping("/qnaupdateCommand/{QNA_NO}")
    public String qnaupdateCommand(QNA qna, @RequestParam("text") String text) {
        // 여기서 text를 댓글로 처리하여 저장
        qna.setQNA_COMMAND(text);

        //댓글 내용을 qna테이블에 업데이트 실행 sql
        qnaService.qnaupdateCommand(qna);

        return "redirect:/qna/qnadetail/" + qna.getQNA_NO();
    }

    @GetMapping("/qnaInsert-view")
    public ModelAndView qnaInsertView() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("qnaInsert"); // "qnaInsert.mustache" 템플릿을 사용
        mv.setStatus(HttpStatus.OK);
        return mv;
    }

    @PostMapping("/qnainsert")
    public String qnainsert(@ModelAttribute QNA qna) {
        boolean result = qnaService.qnainsert(qna);
        if (result) {
            // 추가 성공 시 qnaList 페이지로
            return "redirect:/qna/qnaList";
        } else {
            return "qnaInsert"; // 실패 시 다시 추가 페이지로 유지
        }
    }

    @GetMapping("/qnaUpdate-view/{QNA_NO}")
    public ModelAndView qnaUpdateView(QNA QNA_NO) {
        QNA result = qnaService.qna(QNA_NO);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("qnaUpdate");
        mv.setStatus(HttpStatus.OK);
        mv.addObject("qna", result);
        System.out.println("test1");
        return mv;

    }

    @PostMapping("/qnaUpdate/{QNA_NO}")
    public String qnaupdate(QNA qna) {
        boolean result = qnaService.qnaupdate(qna);
        if (result) {
            // 업데이트 성공 시 qnaList 페이지로
            return "redirect:/qna/qnadetail/{QNA_NO}";
        } else {
            return "redirect:/qna/qnaUpdate-view/" + qna.getQNA_NO(); // 실패 시 다시 수정 페이지로 유지
        }
    }

    @GetMapping("/qnadelete/{QNA_NO}")
    public String qnadelete(QNA QNA_NO) {
        qnaService.qnadelete(QNA_NO);
        return "redirect:/qna/qnaList";
    }

}

