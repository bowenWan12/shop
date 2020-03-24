package com.bww.shop.controller;

import com.bww.shop.domain.JsonData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/api/v1/order")
public class OrderController {

    @GetMapping("add")
    public JsonData saveOrder() {
        return JsonData.buildSuccess("success");
    }
}
