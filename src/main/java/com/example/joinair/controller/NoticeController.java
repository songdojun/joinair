package com.example.joinair.controller;

import com.example.joinair.entity.Notice;
import com.example.joinair.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import static com.example.joinair.service.NoticeService.write;
@Controller
public class NoticeController {
    @Autowired   //Autowired 이용하여  noticeService 주입 비즈니스로직 처리시킴
    private NoticeService noticeService;

    @GetMapping("/notice/create")    // "/notice/create" 경로로 GET 요청이 오면 이 메소드가 실행된다는 것을 의미.
    public String noticeWriteForm() {
        return "noticecreate";
    }

    @PostMapping("notice/createpro")
    // 이 메소드는 공지사항을 작성하고 업로드하는 역할을 한다
    public String noticeWritePro(Notice notice, Model model, MultipartFile file) throws Exception {
        write(notice,file); //write 메소드를 호출하여 공지사항을 작성하고 파일을 업로드하고


        model.addAttribute("message", "글 작성이 완료되었습니다.");
        model.addAttribute("searchUrl", "/notice/list");
        return "message";  // 업로드가 끝나면 message 뷰 페이지를 반환한다

    }

    @GetMapping("/notice/list") //"/notice/list" 경로로 GET 요청이 오면 이 메소드가 실행
    public String NoticeList(Model model,  //매개변수로 Model 객체를 받아오고. 이 객체를 사용하여 뷰에 데이터를 전달
                             @PageableDefault(page = 0, size = 10, sort = "Not_No", direction = Sort.Direction.DESC) Pageable pageable,
                             ////@PageableDefault 어노테이션을 사용하여 기본적인 페이징 설정을 정의
                             // 페이지 번호(page), 페이지 크기(size), 정렬 방식(sort), 정렬 방향(direction)을 설정한다.

                             String searchKeyword) { //매개변수 검색어 받아옴

            Page<Notice> list = null; //객체 초기화 이후 공지사항 목록을 담을 용도로 사용함

        if (searchKeyword == null) { //검색어가 없는 경우 그냥 null
            list = noticeService.noticeListWithPagination(pageable); //noticeService를 사용 공지사항 목록을 가져오고 페이징 처리가 적용된 공지사항 목록 반환
        } else {
            list = noticeService.NoticeSearchList(searchKeyword, pageable); //를 호출하여 공지사항 목록 가져옴
        }

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());
    //nowPage, startPage, endPage 변수를 사용하여 현재 페이지와 페이징에 대한 정보를 계산
        //예를 들어, 현재 페이지가 3이면 startPage는 1이 되고 endPage는 8이 된다


        model.addAttribute("list", list);           //공지사항 목록 모델 추가
        model.addAttribute("nowPage", nowPage);     //현제 페이지 번호 모델 추가
        //  시작과 마지막에 번호 모델 추기
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "/noticelist2";  //페이징 정보와 목록 정보를 모델에 추가하고 "noticelist2" 뷰 페이지를 반환
    }


    @GetMapping("notice/view")  // localhost:8080/notice/view 요청이 오면 메소드 실행
    public String noticeview(Model model, Integer Not_No) {  // 특정 공지사항 불러와 보여주는 역할
        model.addAttribute("Notice", noticeService.noticeview(Not_No).orElse(null));//noticeService 사용하여 특정 공지사항 가져옴
        return "noticeview";  // 그 반환한것을 noticeview 페이지에 반환 공지사항 표시됨
    }


    @GetMapping("/notice/delete") //localhost:notice/delete 요청이 오면 메소드 실행 똑같음 이 부분은
    public String boardDelete(Integer Not_No){   //이 메소드는 특정 공지사항을 삭제하는 역할
        noticeService.noticeDelete(Not_No);     // noticeService를 사용하여 공지사항을 가져온 이후 삭제한다.

        return "redirect:/notice/list";         //삭제가 완료되면 notice/list로 돌아감
    }

    @GetMapping("/notice/modify/{Not_No}")  //localhost:8080/notice/modify/{Not_No} 요청이 오면 매소드 실행
    public String noticeupdate(@PathVariable("Not_No") Integer Not_No, Model model){ // {Not_No} 경로 변수로 이 이 변수에 공지사항 번호가 들어옴
        Notice notice = NoticeService.noticeview(Not_No).orElse(null);  //NoticeService.noticeview(Not_No)를 호출하여 해당 식별 번호에 해당하는 공지사항 정보를 가져옴

        if (notice == null) {

            return "redirect:/error"; // 만약 해당 공지사항이 존재하지 않으면 리다이랙트 에러페이지 이동
        }

        model.addAttribute("notice", notice);
        return "noticeupdate";   // 그렇지 않다면 noticeupdate 뷰 페이지로 이동하여 해당 공지사항을 수정할 수 있는 폼을 표시
    }

    @PostMapping("/notice/update/{Not_No}") //notice/update/{Not_No}" 경로로 POST 요청이 오면 이 메소드가 실행
    public String noticeUpdate(@PathVariable("Not_No") Integer Not_No, Notice updatedNotice, Model model, MultipartFile file)throws Exception {
        //공지사항을 실제로 수정하고 업데이트하는 역할 (noticeUpdate 메소드)

        Notice noticeTemp = noticeService.noticeview(Not_No).orElse(null); //NoticeService.noticeview(Not_No)를 호출하여 현재 공지사항 정보를 가져옴

        if (noticeTemp != null) {

            noticeTemp.setNot_Title(updatedNotice.getNot_Title()); //updatedNotice 객체에는 수정된 정보가 포함되어있음 (제목)
            noticeTemp.setNot_Content(updatedNotice.getNot_Content()); //내용
            //noticeTemp에는 현재 공지사항 정보를 저장하고, 수정된 정보(updatedNotice)로 공지사항을 업데이트함.

            noticeService.write(noticeTemp, file);  // 호출하여 공지사항 업데이트 파일 업로드함
        }

        model.addAttribute("message", "수정이 완료되었습니다.");
        model.addAttribute("searchUrl", "/notice/list");
        return "message";   // 수정이 완료돠면 표시할 메시지를 뷰 페이지 이동 사용자에게 수정되었음을 알림.
    }



}
