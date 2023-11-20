package com.example.joinair.controller;


import com.example.joinair.dto.api.*;
import com.example.joinair.service.ShippingInfoApiService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShippingInfoApiController {


    @Autowired
    private ShippingInfoApiService shippingInfoApiService;

    @PostMapping("/shipping/doc")
    public String getShippingInfoDoc(  ShippingDoc shippingDoc   ) {
        System.out.println( "getUserId => "  + shippingDoc.getUserId());
        System.out.println("getOrderId =>  "  + shippingDoc.getOrderId());
        // 올라와야할거 - >ShippingDoc
        ResShippingDocFormat resShippingDocFormat = new ResShippingDocFormat(true, 200, "SUCCESS", new ResShippingDoc(
                37.79788586946026,
                128.9086556407083,
                37.7962465,
                128.9115532
        ));
        Gson gson = new Gson();
        return gson.toJson(resShippingDocFormat);
    }





    @PostMapping("/shipping/cal/location")
    public String calDronPosition(  ReqDronLocation reqDronLocation   ) {
        ResDronLocation resDronLocation = shippingInfoApiService.calDronPosition( reqDronLocation);
        ResDronLocationFormat resShippingDocFormat = new ResDronLocationFormat(true, 200, "SUCCESS.",resDronLocation);
        Gson gson = new Gson();
        return gson.toJson(resShippingDocFormat);
    }


}
