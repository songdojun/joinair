package com.example.joinair.service;

import com.example.joinair.entity.Notice;
import com.example.joinair.entity.Review;
import com.example.joinair.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.io.File;
import java.util.UUID;
import java.util.List;
import java.util.Optional;
/*
 서비스 클래스는 공지사항 관련 작업을 처리
 컨트롤러와 데이터베이스 간의 중계 역할을 한다.
 */
@Service        //@Service 어노테이션을 사용하여 서비스 클래스로 선언
public class NoticeService {

    private static NoticeRepository noticeRepository; // noticeRepository 필드 선언:

    @Autowired
    public NoticeService(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }
    /*
    @Autowired 어노테이션을 사용한 생성자 메소드 public NoticeService(NoticeRepository noticeRepository)
    는 Spring에 의해 주입되며, 리포지토리를 초기화.
    이를 통해 NoticeService는 NoticeRepository와 상호작용할 수 있게 된다.
     */

//   write 메소드는 새로운 공지사항을 작성하고 파일을 업로드하는 메소드
    public static void write(Notice notice, MultipartFile file) throws Exception {

        String projectpath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
//이 경로는 업로드된 파일을 저장할 서버의 특정 위치를 나타냄.

        //고유 파일이름 생성 / 중복없이 고유 파일이름 생성
        UUID uuid = UUID.randomUUID();
        String fileName = uuid + "_" + file.getOriginalFilename();

//서버 저장경로. 파일 저장 위치를 설정
        File saveFile = new File(projectpath, fileName);
        file.transferTo(saveFile);
//공지사항 객체 파일이름 새로운 파일 이름 설정,
        notice.setNot_Filename(fileName);
        //업로드파일 경로 설정
        notice.setNot_Filepath("/files/" + fileName);

        noticeRepository.save(notice);
        /*
        업데이트 완료된 공지사항 객체 데이터베이스 저장함
         */
    }


    public List<Notice> noticeList(){ //모든 공지사항 목록 가져오는 역할

        return noticeRepository.findAll();//호출하여 DB모든 공지사항 가져옴
    }   //List<Notice>로 형태 변환한다


    public Page<Notice> NoticeSearchList(String searchKeyword, Pageable pageable) {
        //NoticeSearchList 메소드는 검색어를 사용하여 공지사항에서 검색하는 역할, searchKeyword, pageable 매개변수
        return noticeRepository.findNoticesByTitleContaining(searchKeyword, pageable);
        //noticeRepository.findNoticesByTitleContaining(searchKeyword, pageable);를 호출하여 검색결과를 반환한다,
    }

    //특정 게시글 불러오기

    public static Optional<Notice> noticeview(Integer Not_No) { //noticeview 메소드는 특정 공지사항을 조회하는 역할
        Optional<Notice> optionalNotice = noticeRepository.findById(Not_No);
        //Not_No 매개변수 받고 해당 공지사항 noticeRepository.findById(Not_No) 통해 조회함
       //조회된 공지사항은 Optional<Notice> 형태로 반환 해당정보가 있다면 반환한다
        return optionalNotice;
    }



    //특정 게시글 삭제
    public void noticeDelete(Integer Not_No){ //특정 공지사항 삭제 역할  (Not_No)

        noticeRepository.deleteById(Not_No);//를 호출하여 해당 공지사항을 DB에서 삭제함 (Not_No)
    }

    public Page<Notice> noticeListWithPagination(Pageable pageable) { //메소드는 공지사항 목록을 페이징 처리하여 반환하는 역할
                                                                        //pageable 매개변수로 받는다
        return noticeRepository.findAllOrderedByNotnoWithPagination(pageable);
                //위를 호출하여 페이징처리가 적용된 공지사항 목록을 반환
    }

}
