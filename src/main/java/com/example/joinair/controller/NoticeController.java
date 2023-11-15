package com.example.joinair.controller;

import com.example.joinair.dto.USERS;
import com.example.joinair.entity.Notice;
import com.example.joinair.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import static com.example.joinair.service.NoticeService.write;
@Controller
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @GetMapping("/notice/create")    // url :localhost:8080/notice/create
    public String noticeWriteForm() {
        return "noticecreate";
    }

    @PostMapping("notice/createpro")
    public String noticeWritePro(Notice notice, Model model, MultipartFile file) throws Exception {
        write(notice,file);


        model.addAttribute("message", "글 작성이 완료되었습니다.");
        model.addAttribute("searchUrl", "/notice/list");
        return "message";

    }
    @GetMapping("/notice/list")
    public String NoticeList(Model model,
                             @PageableDefault(page = 0, size = 10, sort = "Not_No", direction = Sort.Direction.DESC) Pageable pageable,
                             String searchKeyword) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // 여기에서 필요한 사용자 정보를 모델에 추가합니다.
        model.addAttribute("userId", username);
        model.addAttribute("userAuthorities", authentication.getAuthorities());

        // 마일리지 정보를 얻어와 모델에 추가
        if (authentication.getPrincipal() instanceof USERS) {
            USERS user = (USERS) authentication.getPrincipal();
            model.addAttribute("userMileage", user.getUser_Mileage());
        }

        Page<Notice> list = null;

        if (searchKeyword == null) {
            list = noticeService.noticeListWithPagination(pageable);
        } else {
            list = noticeService.NoticeSearchList(searchKeyword, pageable);
        }

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "/noticelist2";
    }


    @GetMapping("notice/view")  // localhost:8080/notice/view
    public String noticeview(Model model, Integer Not_No) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // 여기에서 필요한 사용자 정보를 모델에 추가합니다.
        model.addAttribute("userId", username);
        model.addAttribute("userAuthorities", authentication.getAuthorities());

        // 마일리지 정보를 얻어와 모델에 추가
        if (authentication.getPrincipal() instanceof USERS) {
            USERS user = (USERS) authentication.getPrincipal();
            model.addAttribute("userMileage", user.getUser_Mileage());
        }

        model.addAttribute("Notice", noticeService.noticeview(Not_No).orElse(null));
        return "noticeview";
    }


    @GetMapping("/notice/delete")
    public String boardDelete(Integer Not_No){
        noticeService.noticeDelete(Not_No);

        return "redirect:/notice/list";
    }

    @GetMapping("/notice/modify/{Not_No}")
    public String noticeupdate(@PathVariable("Not_No") Integer Not_No, Model model){
        Notice notice = NoticeService.noticeview(Not_No).orElse(null);

        if (notice == null) {

            return "redirect:/error";
        }

        model.addAttribute("notice", notice);
        return "noticeupdate";
    }

    @PostMapping("/notice/update/{Not_No}")
    public String noticeUpdate(@PathVariable("Not_No") Integer Not_No, Notice updatedNotice, Model model, MultipartFile file)throws Exception {
        // 현재 리뷰 정보를 가져옴
        Notice noticeTemp = noticeService.noticeview(Not_No).orElse(null);

        if (noticeTemp != null) {
            // 업데이트할 정보를 새로운 리뷰 정보에 설정
            noticeTemp.setNot_Title(updatedNotice.getNot_Title());
            noticeTemp.setNot_Content(updatedNotice.getNot_Content());

            // 리뷰 정보 저장
            noticeService.write(noticeTemp, file);
        }

        model.addAttribute("message", "수정이 완료되었습니다.");
        model.addAttribute("searchUrl", "/notice/list");
        return "message";
    }



}
