package com.example.joinair.controller;

import com.example.joinair.entity.Product;
import com.example.joinair.entity.Review;
import com.example.joinair.service.ProductBuyService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

import static com.example.joinair.service.ProductAdService.regist;


@Controller
public class productBuyController {
    @Autowired
    private ProductBuyService productBuyService;

    @GetMapping("/productbuy/regist")
    public String productbuyRegistForm(){return "productbuyregist";}

    @PostMapping("/productbuy/registpro")
    public String productbuyRegistPro(Product product, Model model, MultipartFile file)throws Exception{
        regist(product,file);

        model.addAttribute("message", "파일 등록이 완료되었습니다.");
        model.addAttribute("searchUrl","/productbuy/list");
        return "message";
    }

    @GetMapping("/productbuy/list")
    public String productbuyList(
            @RequestParam(name = "cateNo", required = false) Integer cateNo,
            Model model,
            @PageableDefault(page = 0, size = 10, sort = "Pro_Code", direction = Sort.Direction.DESC) Pageable pageable,
            @RequestParam(name = "searchOption", required = false) String searchOption,
            @RequestParam(name = "searchKeyword", required = false) String searchKeyword) {

        Page<Product> list;

        if (cateNo != null) {
            // 카테고리에 해당하는 상품 목록을 가져오는 서비스 메소드를 호출합니다.
            list = productBuyService.getProductListByCategory(cateNo, pageable);
        } else {
            // 카테고리가 선택되지 않은 경우, 전체 상품 목록을 가져오는 서비스 메소드를 호출합니다.
            list = productBuyService.productbuySearchList(searchOption, searchKeyword, pageable);
        }

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        // 모델에 상품 목록 및 페이징 정보를 추가합니다.
        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("searchOption", searchOption);
        model.addAttribute("searchKeyword", searchKeyword);

        return "productbuylist2";
    }



    @GetMapping("/productbuy/view")
    public String productbuyView(Model model, Integer Pro_Code){
        Optional<Product> product = productBuyService.productbuyView(Pro_Code);

        if (product.isPresent()) {
            // Reviews 정보를 가져와 Model에 추가
            List<Review> reviews = product.get().getReviews();
            model.addAttribute("Product", product.get());
            model.addAttribute("Reviews", reviews);
        }

        return "productbuyview2";
    }



    @GetMapping("/productbuy/delete")
    public String productbuyDelete(Integer Pro_Code){
        productBuyService.productbuyDelete(Pro_Code);

        return "redirect:/productbuy/list";
    }

    @GetMapping("/productbuy/modify/{Pro_No}")
    public String productbuyModify(@PathVariable("Pro_No") Integer Pro_Code, Model model){
        Product product = productBuyService.productbuyView(Pro_Code).orElse(null);

        if(product==null){
            return "redirect:/error";
        }
        model.addAttribute("product", product);
        return "productbuymodify";
    }

    @PostMapping("/productbuy/update/{Pro_Code}")
    public String productbuyUpdate(@PathVariable("Pro_Code")Integer Pro_Code, Product updateProduct, Model model, MultipartFile file) throws Exception{
        Product productTemp = productBuyService.productbuyView(Pro_Code).orElse(null);

        if(productTemp !=null){
            productTemp.setPro_Code(updateProduct.getPro_Code());
            productTemp.setCate_No(updateProduct.getCate_No());
            productTemp.setPro_Name(updateProduct.getPro_Name());
            productTemp.setPro_Price(updateProduct.getPro_Price());
            productTemp.setPro_Inventory(updateProduct.getPro_Inventory());
            productTemp.setPro_Weight(updateProduct.getPro_Weight());
            productTemp.setPro_Des(updateProduct.getPro_Des());
            regist(productTemp,file);
        }

        model.addAttribute("message", "상품 수정이 완료되었습니다.");
        model.addAttribute("searchUrl", "/productbuy/list");
        return  "message";
    }



}
