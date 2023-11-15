package com.example.joinair.controller;

import com.example.joinair.dto.USERS;
import com.example.joinair.entity.Product;
import com.example.joinair.service.ProductBuyService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
    public String productbuyList(Model model,
                                 @PageableDefault(page = 0, size = 10, sort = "Pro_Code", direction = Sort.Direction.DESC) Pageable pageable,
                                 @RequestParam(name = "searchOption", required = false) String searchOption,
                                 @RequestParam(name = "searchKeyword", required = false) String searchKeyword) {
        Page<Product> list = productBuyService.productbuySearchList(searchOption, searchKeyword, pageable);

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        // Add search parameters to the model
        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("searchOption", searchOption);
        model.addAttribute("searchKeyword", searchKeyword); // Pass search keyword to the view

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

        return "productbuylist"; //페이징 및 검색기능 문제로 기존 productbuylist2였지만 productbuylist로 원복했습니다.
    }


    @GetMapping("/productbuy/view")
    public String productbuyView(Model model, Integer Pro_Code){
        model.addAttribute("Product",productBuyService.productbuyView(Pro_Code).orElse(null));

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

            regist(productTemp,file);
        }

        model.addAttribute("message", "상품 수정이 완료되었습니다.");
        model.addAttribute("searchUrl", "/productbuy/list");
        return  "message";
    }



}