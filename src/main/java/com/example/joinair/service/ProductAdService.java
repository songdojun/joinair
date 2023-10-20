package com.example.joinair.service;

import com.example.joinair.entity.Product;
import com.example.joinair.repository.ProductAdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductAdService {

    private static ProductAdRepository productAdRepository;

    @Autowired
    public ProductAdService(ProductAdRepository productAdRepository) {
        this.productAdRepository = productAdRepository;
    }

    public static void regist(Product product, MultipartFile file) throws Exception {
        String projectpath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

        UUID uuid = UUID.randomUUID();

        String fileName = uuid + "_" + file.getOriginalFilename();

        File saveFile = new File(projectpath, fileName);
        file.transferTo(saveFile);

        product.setPro_Filename(fileName);
        product.setPro_Filepath("/files/" + fileName);

        // 현재 날짜와 시간으로 Pro_Reg_Date 설정  => (작성일자 나타났다 사라지는 문제 발생하여 만들었음)
        product.setPro_Reg_Date(new Date());

        productAdRepository.save(product);
    }

    public List<Product> productadList() {
        return productAdRepository.findAll();
    }

    public Page<Product> productadSearchList(String searchKeyword, Pageable pageable) {
        return productAdRepository.findProductsByProNameContaining(searchKeyword, pageable);
    }







    public Optional<Product> productadView(Integer Pro_Code) {
        Optional<Product> optionalProduct = productAdRepository.findById(Pro_Code);
        return optionalProduct;
    }

    public void productadDelete(Integer Pro_Code) {
        productAdRepository.deleteById(Pro_Code);
    }

    public Page<Product> productadListWithPagination(Pageable pageable) {
        return productAdRepository.findAllOrderedByProCodeWithPagination(pageable);
    }
}
