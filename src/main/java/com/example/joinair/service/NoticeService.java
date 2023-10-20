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

@Service
public class NoticeService {

    private static NoticeRepository noticeRepository;

    @Autowired
    public NoticeService(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }


//    글 작성 처리
    public static void write(Notice notice, MultipartFile file) throws Exception {

        String projectpath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

        UUID uuid = UUID.randomUUID();

        String fileName = uuid + "_" + file.getOriginalFilename();

        File saveFile = new File(projectpath, fileName);
        file.transferTo(saveFile);

        notice.setNot_Filename(fileName);
        notice.setNot_Filepath("/files/" + fileName);
        noticeRepository.save(notice);

    }

    //게시글 리스트 처리
    public List<Notice> noticeList(){

        return noticeRepository.findAll();
    }

    public Page<Notice> NoticeSearchList(String searchKeyword, Pageable pageable) {
        return noticeRepository.findNoticesByTitleContaining(searchKeyword, pageable);
    }

    //특정 게시글 불러오기

    public static Optional<Notice> noticeview(Integer Not_No) {
        Optional<Notice> optionalNotice = noticeRepository.findById(Not_No);
        return optionalNotice;
    }



    //특정 게시글 삭제
    public void noticeDelete(Integer Not_No){

        noticeRepository.deleteById(Not_No);
    }

    public Page<Notice> noticeListWithPagination(Pageable pageable) {
        return noticeRepository.findAllOrderedByNotnoWithPagination(pageable);
    }

}
