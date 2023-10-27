package com.example.joinair.service;


import com.example.joinair.entity.Cart;
import com.example.joinair.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CartService {


    private static CartRepository cartRepository;

    @Autowired
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;}



    public static void regist(Cart cart, MultipartFile file) throws Exception {
        String projectpath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

        UUID uuid = UUID.randomUUID();

        String fileName = uuid + "_" + file.getOriginalFilename();

        File saveFile = new File(projectpath, fileName);
        file.transferTo(saveFile);

        product.setPro_Filename(fileName);
        product.setPro_Filepath("/files/" + fileName);

        // 현재 날짜와 시간으로 Pro_Reg_Date 설정  => (작성일자 나타났다 사라지는 문제 발생하여 만들었음)
        product.setPro_Reg_Date(new Date());

        cartRepository.save(cart);
    }

    public List<Cart> cartList() {
        return cartRepository.findAll();
    }


    public Page<Cart> cartSearchList(String searchOption, String searchKeyword, Pageable pageable) {
        Page<Cart> list;

        // Pageable 객체를 적절하게 설정
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("Cart_Code").descending());

        if ("Cart_Code".equals(searchOption)) {
            list = cartRepository.findProductsByProCode(Integer.parseInt(searchKeyword), pageable);
        } else if ("Pro_Name".equals(searchOption)) {
            list = cartRepository.findProductsByProNameContaining(searchKeyword, pageable);
        } else if ("Cate_Name".equals(searchOption)) {
            // 수정: "Cate_Name" 검색을 위한 쿼리 추가
            list = cartRepository.findProductsByCateNameContaining(searchKeyword, pageable);
        } else {
            list = cartRepository.findAllOrderedByProCodeWithPagination(pageable);
        }
        return list;
    }

    public Optional<Cart> cartView(Integer Cart_Code) {
        Optional<Cart> optionalCart = cartRepository.findById(Cart_Code);
        return optionalCart;
    }

    public void cartDelete(Integer Cart_Code) {
        cartRepository.deleteById(Cart_Code);
    }

    public Page<Cart> productbuyListWithPagination(Pageable pageable) {
        return cartRepository.findAllOrderedByProCodeWithPagination(pageable);
    }


}
