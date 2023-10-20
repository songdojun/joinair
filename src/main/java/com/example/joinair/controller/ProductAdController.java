package com.example.joinair.controller;


import com.example.joinair.entity.Product;
import com.example.joinair.service.ProductAdService;
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

import static com.example.joinair.service.ProductAdService.regist;


@Controller
public class ProductAdController {

    @Autowired
    private ProductAdService productAdService;

    @GetMapping("/productad/regist")
    public String productadRegistForm(){return "productadregist";}

    @PostMapping("/productad/registpro")
    public String productadRegistPro(Product product, Model model, MultipartFile file)throws Exception{
        regist(product,file);

        model.addAttribute("message", "파일 등록이 완료되었습니다.");
        model.addAttribute("searchUrl","/productad/list");
        return "message";
    }

    @GetMapping("/productad/list")
    public String productadList(Model model,
                                @PageableDefault(page = 0, size = 10, sort = "Pro_Code", direction = Sort.Direction.DESC) Pageable pageable,
                                String searchKeyword){
        Page<Product> list =null;

        if(searchKeyword ==null){
            list = productAdService.productadListWithPagination(pageable);
        }else {
            list = productAdService.productadSearchList(searchKeyword, pageable);
        }

        int nowPage = list.getPageable().getPageNumber()+1;
        int startPage =Math.max(nowPage-4,1);
        int endPage = Math.min(nowPage+5, list.getTotalPages());

        model.addAttribute("list",list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage",endPage);

        return "productadlist";
    }

    @GetMapping("/productad/view")
    public String productadView(Model model, Integer Pro_Code){
        model.addAttribute("Product",productAdService.productadView(Pro_Code).orElse(null));
        return "productadview";
    }

    @GetMapping("/productad/delete")
    public String productadDelete(Integer Pro_Code){
        productAdService.productadDelete(Pro_Code);

        return "redirect:/productad/list";
    }

    @GetMapping("/productad/modify/{Pro_No}")
    public String productadModify(@PathVariable("Pro_No") Integer Pro_Code, Model model){
        Product product = productAdService.productadView(Pro_Code).orElse(null);

        if(product==null){
            return "redirect:/error";
        }
        model.addAttribute("product", product);
        return "productadmodify";
    }

    @PostMapping("/productad/update/{Pro_Code}")
    public String productadUpdate(@PathVariable("Pro_Code")Integer Pro_Code, Product updateProduct, Model model, MultipartFile file) throws Exception{
        Product productTemp = productAdService.productadView(Pro_Code).orElse(null);

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
        model.addAttribute("searchUrl", "/productad/list");
        return  "message";
    }


}