package com.example.joinair.controller;

import com.example.joinair.dto.QNA;
import com.example.joinair.dto.QNAPAGE;
import com.example.joinair.dto.USERS;
import com.example.joinair.service.UserService;
import com.example.joinair.service.qnaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    @Autowired
    private UserService userService;

    private void addUserInfoToModel(Model model) { //윗부분 이름 00님 환영합니다 부분임
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Add necessary user information to the model
        model.addAttribute("userId", username);
        model.addAttribute("userAuthorities", authentication.getAuthorities());

        if (authentication.getPrincipal() instanceof USERS) {
            USERS user = (USERS) authentication.getPrincipal();
            model.addAttribute("userMileage", user.getUser_Mileage());
        }
    }
    @GetMapping("/qnaList")
    public ModelAndView qnaList(Model model,@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int pageSize, QNA qnadto, HttpSession session) {
        ModelAndView mv = new ModelAndView();

        String userId = (String) session.getAttribute("User_Id");
        if (userId != null) {
            mv.addObject("userLoggedIn", true); // 사용자 로그인 상태를 true로 설정
        } else {
            mv.addObject("userLoggedIn", false);
        }
        addUserInfoToModel(model);
        QNAPAGE pagingInfo = new QNAPAGE();
        pagingInfo.setPage(page);
        pagingInfo.setPageSize(pageSize);

        //기억안남
        List<QNA> qnaList = qnaService.qnaList(qnadto);

        //검색어 + 페이징
//        List<QNA> qnaRueslt = qnaService.qnaListWithPaging(pagingInfo, qnadto);

        //총게물 갯수
        int totalItemCount = qnaService.getTotalItemCount();
        int totalPageCount = (int) Math.ceil((double) totalItemCount / pageSize);

        List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPageCount)
                .boxed()
                .collect(Collectors.toList());


        List<String> pagingList = qnaService.pagingList(totalPageCount, page);


        mv.addObject("pagelist", pagingList);
        mv.setViewName("qnaList");
        mv.setStatus(HttpStatus.OK);
        mv.addObject("qnaList", qnaList);
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
    public ModelAndView qnaInsertView(Model model, HttpSession session) {
        ModelAndView mv = new ModelAndView();

        addUserInfoToModel(model);

        String userId = (String) session.getAttribute("User_Id");
        mv.addObject("userLoggedIn", userId != null);
        mv.addObject("user_Id", userId);

        mv.setViewName("qnaInsert");
        mv.setStatus(HttpStatus.OK);
        mv.addObject("qna", new QNA());

        return mv;
    }

    @PostMapping("/qnainsert")
    public String qnainsert(@ModelAttribute QNA qna, HttpSession session) {
        // 세션에서 사용자 ID 가져오기
        String userId = (String) session.getAttribute("User_Id");

        // 가져온 사용자 ID를 QNA 객체에 설정
        qna.setUSER_ID(userId);

        boolean result = qnaService.qnainsert(qna);
        if (result) {
            // 추가 성공 시 qnaList 페이지로
            return "redirect:/qna/qnaList";
        } else {
            // 실패 시 다시 추가 페이지로 유지
            return "qnaInsert";
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

