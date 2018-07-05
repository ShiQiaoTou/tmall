package com.aopioc.tmall.coupon.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * author by JefferyChang on  2018/7/5
 */


@RestController
public class IndexController {


    @RequestMapping("/")
    public String index(){
        return "Hello-world";
    }


}
